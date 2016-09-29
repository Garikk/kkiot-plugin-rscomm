/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.rscomm.adapters.rs232;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import kkdev.kksystem.base.classes.kkcontroller.RS232Device;
import kkdev.kksystem.plugin.rscomm.manager.RSManager;
import kkdev.kksystem.plugin.rscomm.adapters.IRSAdapter;
import kkdev.kksystem.plugin.rscomm.services.IBTService;
import kkdev.kksystem.plugin.rscomm.configuration.ServicesConfig;
import kkdev.kksystem.base.interfaces.IControllerUtils;

/**
 *
 * @author sayma_000
 */
public class RS232 implements IRSAdapter {

    String HWPort;
    
    private static SerialPort serialPort;

    private boolean State = false;
    private List<Thread> BTServer;

    private List<ServicesConfig> ServicesMapping;
    private HashMap<String, IBTService> BTServices;
    RSManager BTM;

    public RS232(RSManager RS, String Port)
    {
        BTM=RS;
        HWPort=Port;
    }
   
    
    @Override
    public void RegisterService(ServicesConfig SC) {
        
        if (ServicesMapping == null) {
            ServicesMapping = new ArrayList<>();
        }

        ServicesMapping.add(SC);
    }

    private void InitServices() {

     //   for (ServicesConfig SC : ServicesMapping) {
     //       System.out.println("[BT][INF] Check services " + SC.Name);

      //  }
    }

    @Override
    public void StopAdaper() {
        State = false;
    }

    @Override
    public void StartAdapter(IControllerUtils Utils,RSManager RTM) {
        String DevAddr = "AUTO";
        if (HWPort.equals("AUTO")) {
            List<RS232Device> Devices = Utils.HWManager().GetRS232Devices();
            if (Devices != null) {
                for (RS232Device DV : Devices) {
                    if (DV.PortType == RS232Device.RS232DevType.DevSmarthead) {
                        DevAddr = DV.PortName;
                        break;
                    }
                }
            }
        } else {
            DevAddr = HWPort;
        }
        
        DevAddr="/dev/ttyACM0";
        if (DevAddr.equals("AUTO"))
        {
               System.out.println("[RSCOMM][ERR] Adapter RS-232 not found, Disabled");
              State = false;
              return;
        }
            
        
        serialPort = new SerialPort(DevAddr);

        try {
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN
                    | SerialPort.FLOWCONTROL_RTSCTS_OUT);
            State = true;
        } catch (SerialPortException ex) {
            System.out.println("[RSCOMM][ERR] Adapter RS-232 not found, Disabled");
              State = false;
              return;
        }

        try {
            serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);
            State = true;
        } catch (SerialPortException ex) {
            Logger.getLogger(RS232.class.getName()).log(Level.SEVERE, null, ex);
               State = false;
        }

    }

    @Override
    public boolean State() {
        return State;
    }

    @Override
    public void SendJsonData(String ServiceTag, String Json) {
        if (!State) {
            return;
        }

        try {
            serialPort.writeString(Json);
        } catch (SerialPortException ex) {
            Logger.getLogger(RS232.class.getName()).log(Level.SEVERE, null, ex);
             State=false;
        }
    }

    @Override
    public void SendStringData(String ServiceTag, String Data) {
        if (!State) {
            return;
        }
        try {
           // System.out.print(Data);
           // System.out.print("\r\n");
            serialPort.writeString(Data);
            serialPort.writeString("\r\n");
        } catch (SerialPortException ex) {
            Logger.getLogger(RS232.class.getName()).log(Level.SEVERE, null, ex);
            State=false;
        }
    }

    private class PortReader implements SerialPortEventListener {
        private String message;
        PortReader()
        {
            message="";
        }

        public void serialEvent(SerialPortEvent event) {
            if (event.isRXCHAR() && event.getEventValue() > 0) {
                try {
                    String buffer = serialPort.readString();
                    String S;

                    for (int i = 0; i < buffer.length(); i++) {
                        S = buffer.substring(i, i + 1);
                        if (S.equals("\r")) {
                             BTM.RS_ReceiveData("SMARTHEAD", message);
                            message = "";
                        } else if (!S.equals("\n")) {
                            message += S;
                           }
                    }


                    

                } catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }
        }
    }
}

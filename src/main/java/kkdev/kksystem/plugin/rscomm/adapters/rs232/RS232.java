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
import kkdev.kksystem.plugin.rscomm.manager.RSManager;
import kkdev.kksystem.plugin.rscomm.adapters.IRSAdapter;
import kkdev.kksystem.plugin.rscomm.services.IBTService;
import kkdev.kksystem.plugin.rscomm.configuration.ServicesConfig;


/**
 *
 * @author sayma_000
 */
public class RS232 implements IRSAdapter {

    private static SerialPort serialPort;
    
    private boolean State = false;
    private List<Thread> BTServer;

    private List<ServicesConfig> ServicesMapping;
    private HashMap<String, IBTService> BTServices;
      RSManager BTM;

   
    @Override
    public void RegisterService(ServicesConfig SC) {
        if (ServicesMapping==null)
            ServicesMapping=new ArrayList<>();
            
        ServicesMapping.add(SC);
    }

    private void InitServices() {
       
        for (ServicesConfig SC : ServicesMapping) {
            System.out.println("[BT][INF] Check services " + SC.Name);
           
           
            }
        }

    @Override
    public void StopAdaper() {
        State = false;
    }

    @Override
    public void StartAdapter(RSManager RTM) {

        try {
            serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);
        } catch (SerialPortException ex) {
            Logger.getLogger(RS232.class.getName()).log(Level.SEVERE, null, ex);
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
        }
    }

    @Override
    public void SendStringData(String ServiceTag, String Data) {
        if (!State) {
            return;
        }
        try {
            serialPort.writeString(Data);
        } catch (SerialPortException ex) {
            Logger.getLogger(RS232.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
       private static class PortReader implements SerialPortEventListener {

        public void serialEvent(SerialPortEvent event) {
            if(event.isRXCHAR() && event.getEventValue() == 8){
                if(event.isRXCHAR() && event.getEventValue() > 0){
                    try {
                        //Получаем ответ от устройства, обрабатываем данные и т.д.
                        String data = serialPort.readString(event.getEventValue());
                        //И снова отправляем запрос
                       System.out.println("RSCOMM " + data);
                    }
                    catch (SerialPortException ex) {
                        System.out.println(ex);
                    }
                }
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.kkcar.plugin.rsCOMM.adapters.rs232;

import java.io.IOException;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kkdev.kksystem.plugin.kkcar.plugin.rsCOMM.adapters.IRSAdapter;
import kkdev.kksystem.plugin.kkcar.plugin.rsCOMM.services.IBTService;
import kkdev.kksystem.plugin.kkcar.plugin.rsCOMM.services.IServiceCallback;
import kkdev.kksystem.plugin.kkcar.plugin.rscomm.configuration.ServicesConfig;
import kkdev.kksystem.plugin.kkcar.plugin.rscomm.manager.RSManager;


/**
 *
 * @author sayma_000
 */
public class RS232 implements IRSAdapter, IServiceCallback {

    private boolean State = false;
    private List<Thread> BTServer;

    private List<ServicesConfig> ServicesMapping;
    private HashMap<String, IBTService> BTServices;
    private List<BTConnectionWorker> ConnectionWorker;
    RSManager BTM;

    @Override
    public void StartAdapter(RSManager MyBTM) {
        BTM = MyBTM;
        AvailableDevices = new HashMap<>();
        BTServices = new HashMap<>();
        ConnectionWorker = new ArrayList<>();
        //
        BTServer = new ArrayList<>();
        //
   
    }
    @Override
    public void RegisterService(ServicesConfig SC) {
        if (ServicesMapping==null)
            ServicesMapping=new ArrayList<>();
            
        ServicesMapping.add(SC);
    }

    private void InitServices() {
        IServiceCallback WorkerCallback = this;

        for (ServicesConfig SC : ServicesMapping) {
            System.out.println("[BT][INF] Check services " + SC.Name);
           
           
            }
        }
    }

   

    @Override
    public void StopAdaper() {
        State = false;
    }

    ServerRequestHandler ServerBTEXA = new ServerRequestHandler() {
        @Override
        public int onPut(Operation op) {
            return super.onPut(op); //To change body of generated methods, choose Tools | Templates.
        }

    };

   
    @Override
    public boolean State() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ReceiveServiceData(String Tag, String SrcAddr, Byte[] Data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ReceiveServiceData(String Tag, String SrcAddr, String Data) {
        BTM.BT_ReceiveData(Tag, Data);
    }

    @Override
    public void SendJsonData(String ServiceTag, String Json) {
       for (BTConnectionWorker CN:ConnectionWorker)
       {
           CN.SendData(ServiceTag,Json);
           
       }
    }

    
}

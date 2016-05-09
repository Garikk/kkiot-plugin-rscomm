package kkdev.kksystem.plugin.kkcar.plugin.rsCOMM;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import kkdev.kksystem.base.classes.plugins.PluginMessage;
import kkdev.kksystem.base.classes.plugins.simple.KKPluginBase;
import kkdev.kksystem.base.interfaces.IPluginBaseInterface;
import kkdev.kksystem.plugin.kkcar.plugin.rscomm.configuration.PluginSettings;
import kkdev.kksystem.plugin.kkcar.plugin.rscomm.manager.RSManager;


/**
 *
 * @author blinov_is
 */
public final class KKPlugin extends KKPluginBase {
    public KKPlugin() {
        super(new RSPluginInfo());
        Global.PM=new RSManager();
    }

    @Override
    public void PluginInit(IPluginBaseInterface BaseConnector, String GlobalConfUID) {
        super.PluginInit(BaseConnector, GlobalConfUID);
        PluginSettings.InitConfig(this.GlobalConfID, this.PluginInfo.GetPluginInfo().PluginUUID);
        Global.PM.Init(this);
    }

    
    @Override
    public PluginMessage ExecutePin(PluginMessage Pin) {
        super.ExecutePin(Pin);
       // System.out.println("[BT][DEBUG] " +Pin.PinData );
        Global.PM.ReceivePIN(Pin);
        return null;
    }
    
    
     @Override
    public void PluginStart() {
         super.PluginStart();
         Global.PM.Start();
    }

}

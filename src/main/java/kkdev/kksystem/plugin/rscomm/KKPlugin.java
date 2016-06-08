package kkdev.kksystem.plugin.rscomm;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import kkdev.kksystem.base.classes.plugins.PluginMessage;
import kkdev.kksystem.base.classes.plugins.simple.KKPluginBase;
import kkdev.kksystem.base.interfaces.IKKControllerUtils;
import kkdev.kksystem.base.interfaces.IPluginBaseInterface;
import kkdev.kksystem.plugin.rscomm.configuration.PluginSettings;
import kkdev.kksystem.plugin.rscomm.manager.RSManager;


/**
 *
 * @author blinov_is
 */
public final class KKPlugin extends KKPluginBase {
    IKKControllerUtils Utils;
    public KKPlugin() {
        super(new RSPluginInfo());
        Global.PM=new RSManager();
    }

    @Override
    public void pluginInit(IPluginBaseInterface BaseConnector, String GlobalConfUID) {
        super.pluginInit(BaseConnector, GlobalConfUID);
        Utils=BaseConnector.systemUtilities();
        PluginSettings.InitConfig(this.globalConfID, this.pluginInfo.getPluginInfo().PluginUUID);
        Global.PM.Init(this);
    }

    
    @Override
    public PluginMessage executePin(PluginMessage Pin) {
        super.executePin(Pin);
        Global.PM.ReceivePIN(Pin);
        return null;
    }
    
    
     @Override
    public void pluginStart() {
         super.pluginStart();
         Global.PM.Start();
    }

    public IKKControllerUtils GetUtils()
    {
        return Utils;
    }
}

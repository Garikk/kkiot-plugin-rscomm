/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.kkcar.plugin.rscomm.configuration;

import static kkdev.kksystem.base.constants.PluginConsts.KK_PLUGIN_BASE_PLUGIN_BLUETOOTH_BTSERVICE_KKEXCONNECTION_UUID;
import static kkdev.kksystem.base.constants.PluginConsts.KK_PLUGIN_BASE_PLUGIN_EXTCONNECTOR_BTSERVICE_UUID;
import static kkdev.kksystem.base.constants.PluginConsts.KK_PLUGIN_BASE_PLUGIN_BLUETOOTH_BTSERVICE_RFCOMM_UUID;


/**
 *
 * @author blinov_is
 *
 * 
 */
public abstract class kk_DefaultConfig {
    public static RSConfig MakeDefaultConfig() {
        
        RSConfig DefConf = new RSConfig();
        
        DefConf.BTAdapter=RSConfig.AdapterTypes.UniLinuxRS232;
        // 
        DefConf.BTServicesMapping=new ServicesConfig[1];
        DefConf.BTServicesMapping[0]=new ServicesConfig();
        DefConf.BTServicesMapping[0].DevType=ServicesConfig.RS_ServiceType.RS232Port;
        DefConf.BTServicesMapping[0].KK_TargetTag="SMARTHEAD";
        DefConf.BTServicesMapping[0].DevAddr="//"; //change this! (this is my test adapter
        DefConf.BTServicesMapping[0].Name="SMARTHEAD";
        return DefConf;
    }
}
    

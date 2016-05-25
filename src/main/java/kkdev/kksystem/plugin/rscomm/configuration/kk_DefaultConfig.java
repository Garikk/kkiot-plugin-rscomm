/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.rscomm.configuration;


/**
 *
 * @author blinov_is
 *
 * 
 */
public abstract class kk_DefaultConfig {
    public static RSConfig MakeDefaultConfig() {
        
        RSConfig DefConf = new RSConfig();
        
        DefConf.BTAdapter=RSConfig.AdapterTypes.jsscRS232;
        // 
        DefConf.RSServicesMapping=new ServicesConfig[1];
        DefConf.RSServicesMapping[0]=new ServicesConfig();
        DefConf.RSServicesMapping[0].DevType=ServicesConfig.RS_ServiceType.RS232Port;
        DefConf.RSServicesMapping[0].KK_TargetTag="SMARTHEAD";
        DefConf.RSServicesMapping[0].DevAddr="AUTO";//"//dev//ttyACM0"; //change this! (this is my test adapter
        DefConf.RSServicesMapping[0].Name="SMARTHEAD";
        return DefConf;
    }
}
    

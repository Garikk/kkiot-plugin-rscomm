/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.rscomm.configuration;

import java.util.ArrayList;


/**
 *
 * @author blinov_is
 *
 * 
 */
public abstract class kk_DefaultConfig {
    public static RSConfig MakeDefaultConfig() {
        
        RSConfig DefConf = new RSConfig();
        
        DefConf.BTAdapterType=RSConfig.AdapterTypes.jsscRS232;
        DefConf.HWAddr="AUTO";
        DefConf.ServiceTags = new ArrayList();
        DefConf.ServiceTags.add("SMARTHEAD");

        return DefConf;
    }
}
    

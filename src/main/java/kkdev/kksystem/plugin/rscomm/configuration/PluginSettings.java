/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.rscomm.configuration;

import kkdev.kksystem.base.classes.plugins.simple.SettingsManager;

/**
 *
 * @author blinov_is
 */
public abstract class PluginSettings {

   public static  String RS_CONF;
   private static SettingsManager Settings;

   public String FeatureID;
   
    public static RSConfig MainConfiguration;

    public static void InitConfig(String GlobalConfigUID, String MyUID) {
         RS_CONF=GlobalConfigUID+"_"+MyUID + ".json";
        
        Settings=new SettingsManager(RS_CONF,RSConfig.class);
        
        
       // System.out.println("[BT][CONFIG] Load configuration");
        MainConfiguration=(RSConfig)Settings.loadConfig();

        if (MainConfiguration == null) {
            System.out.println("[RSC][CONFIG] Error Load configuration, try create default config");
            Settings.saveConfig(kk_DefaultConfig.MakeDefaultConfig());
            MainConfiguration=(RSConfig)Settings.loadConfig();
        }
        if (MainConfiguration == null) {
            System.out.println("[RSC][CONFIG] Load configuration, fatal");
            return;
        }
        //
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.rscomm.configuration;

import kkdev.kksystem.base.classes.plugins.simple.SettingsManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author blinov_is
 */
public abstract class PluginSettings {

    private static final Logger logger = LogManager.getLogger("RSCOMM_CONFIG");
    public static String RS_CONF;

    public String FeatureID;

    public static RSConfig MainConfiguration;

    public static void InitConfig(String GlobalConfigUID, String MyUID) {
        RS_CONF = GlobalConfigUID + "_" + MyUID + ".json";

        SettingsManager settings = new SettingsManager(RS_CONF, RSConfig.class);

        logger.info("Load configuration");
        MainConfiguration = (RSConfig) settings.loadConfig();

        if (MainConfiguration == null) {
            logger.error("Error Load configuration, try create default config");
            settings.saveConfig(kk_DefaultConfig.MakeDefaultConfig());
            MainConfiguration = (RSConfig) settings.loadConfig();
        }
        if (MainConfiguration == null) {
            logger.error("[RSC][CONFIG] Load configuration, fatal");
            return;
        }
        //
    }
}

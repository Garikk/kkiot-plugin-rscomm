/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.rscomm.configuration;

import kkdev.kksystem.base.classes.plugins.PluginConfiguration;

/**
 *
 * @author blinov_is
 */
public class RSConfig  extends PluginConfiguration {
    public enum AdapterTypes {
        jsscRS232
    }
    
    public String FeatureID;
    public AdapterTypes BTAdapter;
    public ServicesConfig[] RSServicesMapping;

}

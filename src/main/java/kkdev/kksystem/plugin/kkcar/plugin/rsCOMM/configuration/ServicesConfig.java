/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.kkcar.plugin.rscomm.configuration;

/**
 *
 * @author sayma_000
 */
public class ServicesConfig {
    public enum RS_ServiceType
    {
        RS232Port
    }
    
    public String FeatureID;
    public String Name;
    public String DevAddr;
    public RS_ServiceType DevType;
    public String KK_TargetTag;

}

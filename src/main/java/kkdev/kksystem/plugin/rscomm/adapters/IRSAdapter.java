/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.rscomm.adapters;

import kkdev.kksystem.plugin.rscomm.configuration.ServicesConfig;
import kkdev.kksystem.plugin.rscomm.manager.RSManager;
import kkdev.kksystem.base.interfaces.IControllerUtils;




/**
 *
 * @author blinov_is
 */
public interface IRSAdapter {
    void StartAdapter(IControllerUtils Utils, RSManager RTM);
    void StopAdaper();
    boolean State();
    void RegisterService(ServicesConfig SC);
    void SendJsonData(String ServiceTag, String Json);
    void SendStringData(String ServiceTag, String Вфеф);
}

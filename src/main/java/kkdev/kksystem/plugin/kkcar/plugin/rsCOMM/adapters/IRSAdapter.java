/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.kkcar.plugin.rsCOMM.adapters;

import kkdev.kksystem.plugin.kkcar.plugin.rscomm.configuration.ServicesConfig;




/**
 *
 * @author blinov_is
 */
public interface IRSAdapter {
    public void StartAdapter(RSManager RTM);
    public void StopAdaper();
    public boolean State();
    public void RegisterService(ServicesConfig SC);
    public void SendJsonData(String ServiceTag,String Json);
    public void SendStringData(String ServiceTag,String Вфеф);
}

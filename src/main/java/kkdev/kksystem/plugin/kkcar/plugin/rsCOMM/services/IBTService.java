/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.kkcar.plugin.rsCOMM.services;

/**
 *
 * @author sayma_000
 */
public interface IBTService {
    public void ConnectService(String Tag,String Addr,IServiceCallback Callback);
    public void StopService();
   
}

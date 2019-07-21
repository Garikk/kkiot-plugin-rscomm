/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkdev.kksystem.plugin.rscomm.services;

/**
 *
 * @author sayma_000
 */
public interface IBTService {
    void ConnectService(String Tag, String Addr);
    void StopService();
   
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techgiants.topiefor.process;

/**
 *
 * @author VM JELE
 */
public enum MessageType {
    SUCCESS("alert-success"),WARNING("arlet-warning"),DANGER("alert-danger"),INFO("alert-info");
    
    String val;
    MessageType(String val) {
        this.val = val;
    }
    public String getValue() {
    return val;
  }
}

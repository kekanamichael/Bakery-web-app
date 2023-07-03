/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.techgiants.topiefor.db;

/**
 *
 * @author STUDIO 18
 */
public interface ConnectionFlag {
    
    void setAutoCommit(boolean flag);
    void commit();
    void rollback();
    
}

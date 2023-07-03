/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techgiants.topiefor.unit;

import com.techgiants.topiefor.model.Unit;
import com.techgiants.topiefor.model.UnitConverter;

/**
 *
 * @author VM JELE
 */
public interface ConverterI {
    
    
    static  double convertUnits(Unit org,Unit con,double val){
        
        if(org==con){
            return val;
        }
        
        val = val*UnitConverter.valueOf(org.name()+"2"+con.name()).getValue();
        
        
        return val;
    }
    
}

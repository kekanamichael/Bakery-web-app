/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techgiants.bakerywebapp.test;

import com.techgiants.topiefor.exception.ArgumentException;
import com.techgiants.topiefor.model.Address;
import com.techgiants.topiefor.model.Role;
import com.techgiants.topiefor.model.User;
import com.techgiants.topiefor.dao.RoleDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author STUDIO 18
 */
public class ModelsSetterTest {
    
    private User user;
    private Address address;
    private Role role;
    private  RoleDao dao;
    
    
   
    
//    @BeforeAll
//    public void initalizeModels(){
//        user = new User();
//        address =  new Address();
//        role =  new Role();
//        
//    }
    
//    @Test
//    public void testUserAttributeSetter(){
//        user = new User();
//        try {
//            //name
//            user.setName("Bheki");
//            
//            assertEquals("Bheki",user.getName());
//            
//            ArgumentException exc= assertThrows(ArgumentException.class,()-> user.setName(null));
//            assertEquals("Invalid Name.", exc.getMessage());
//            
//            exc= assertThrows(ArgumentException.class,()-> user.setName(""));
//            assertEquals("Invalid Name.", exc.getMessage());
//            
//            user.setAddress(new Address());
//            
//            assertEquals(new Address(),user.getAddress());
//            
//            
//        } catch (ArgumentException ex) {
//           fail();
//        }
//    }
    
    
    
}

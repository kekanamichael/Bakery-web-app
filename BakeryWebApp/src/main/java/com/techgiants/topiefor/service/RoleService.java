/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techgiants.topiefor.service;

import com.techgiants.topiefor.exception.ArgumentException;
import com.techgiants.topiefor.model.Role;

/**
 *
 * @author STUDIO 18
 */
public interface RoleService {
    
    Role getRoleById(int id)throws ArgumentException;
    
    //boolean addRole(Role role);
    
   // boolean updateRole(Role role);
    
   // boolean deleteRoleById(int roleId);
}

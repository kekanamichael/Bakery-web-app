/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techgiants.topiefor.service;

import com.techgiants.topiefor.exception.ArgumentException;
import com.techgiants.topiefor.exception.PasswordException;
import com.techgiants.topiefor.exception.UserException;
import com.techgiants.topiefor.model.Address;
import com.techgiants.topiefor.model.Role;
import com.techgiants.topiefor.model.User;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author STUDIO 18
 */
public interface UserService {
    
    
    boolean AddNewAddress(Address address,String email,char type);
    
    User getUserByEmail(String email)throws ArgumentException;
    
    boolean registerUser(User user)throws ArgumentException,PasswordException;
    
    boolean updateUser(User user)throws ArgumentException;
    
    boolean deleteUserByEmail(String email)throws ArgumentException;
    
    List<User> getAllUsers();
    HashMap<Integer,Address> getUserAddressesByEmail(String email);
    
    List<User> getAllUsersByRole(Role role)throws ArgumentException;
    void verifyUser(String  email)throws Exception;
    
    User authenticateUserLogin(String username,String password)throws UserException,ArgumentException;
}

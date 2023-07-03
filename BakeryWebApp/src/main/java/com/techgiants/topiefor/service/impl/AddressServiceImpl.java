/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techgiants.topiefor.service.impl;

import com.techgiants.topiefor.dao.AddressDao;
import com.techgiants.topiefor.dao.UserDao;
import com.techgiants.topiefor.exception.ArgumentException;
import com.techgiants.topiefor.model.Address;
import com.techgiants.topiefor.service.AddressService;

/**
 *
 * @author STUDIO 18
 */
public class AddressServiceImpl implements AddressService{
    
    private AddressDao dao;
    private UserDao usDao;

    @Override
    public Address getAddressByEmail(String email) throws ArgumentException {
        if(email==null || email.isEmpty()){
            throw new ArgumentException("Invalid User email.");
        }
        
        return dao.getAddressByEmail(email);
    }

    @Override
    public boolean addAddress(Address address, String email) throws ArgumentException {
        if(email==null || email.isEmpty()){
             throw new ArgumentException("Invalid User email.");
        }
        
        if(address==null){
             throw new ArgumentException("Invalid Address");
        }
        
        if(usDao.getUserByEmail(email)==null)
        {
            throw new ArgumentException("Invalid Email of the User");
        }
        
        //return dao.addAddress(address, email);
        return false;
    }

    @Override
    public boolean updateAddress(Address address, String email) throws ArgumentException {
        if(email==null || email.isEmpty()){
             throw new ArgumentException("Invalid User email.");
        }
        
        if(address==null){
             throw new ArgumentException("Invalid Address");
        }
        
        //return dao.updateAddress(address, email);
        return false;
    }

    @Override
    public boolean deleteAddress(int addressId) throws ArgumentException {
        if(addressId<0){
            throw new ArgumentException("invalid address id");
        }
        return dao.deleteAddress(addressId);
    }

    
    
}

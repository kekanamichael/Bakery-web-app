
package com.techgiants.topiefor.service;

import com.techgiants.topiefor.exception.ArgumentException;
import com.techgiants.topiefor.model.Address;
import java.util.List;


public interface AddressService {
    
    
    Address getAddressByEmail(String email) throws ArgumentException;
    boolean addAddress(Address address, String email)throws ArgumentException;
    boolean updateAddress(Address address, String email)throws ArgumentException;
    boolean deleteAddress(int addressId)throws ArgumentException;
    
    
    
}

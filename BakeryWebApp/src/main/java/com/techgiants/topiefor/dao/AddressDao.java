package com.techgiants.topiefor.dao;

import com.techgiants.topiefor.dao.*;
import com.techgiants.topiefor.model.Address;
import java.util.HashMap;

public interface AddressDao {

    Address getAddressByEmail(String email);

    boolean deleteAddress(int addressId);

    HashMap<Integer, Address> getUserAddressesByEmail(String email);

    int checkIfAddressExists(Address addr);

    int addAddress(Address addr);

}

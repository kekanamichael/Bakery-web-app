package com.techgiants.topiefor.dao;


import com.techgiants.topiefor.model.Address;
import com.techgiants.topiefor.model.Role;
import com.techgiants.topiefor.model.User;
import java.util.List;

public interface UserDao {

    boolean addUser(User user);

    boolean upadateUser(User user);

    boolean deleteUserByEmail(String email);

    User getUserById(int userId);
    boolean hasHomeAddress(String email);
    
    boolean addUserAddress(String email,int addr,char type);
    

    User getUserByEmail(String email);

    List<User> getAllUsers();

    public List<User> getAllUsersByRole(Role role);

}

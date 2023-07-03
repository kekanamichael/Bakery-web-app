
package com.techgiants.topiefor.service.impl;

import com.techgiants.topiefor.dao.impl.RoleDaoImpl;
import com.techgiants.topiefor.exception.ArgumentException;
import com.techgiants.topiefor.model.Role;
import com.techgiants.topiefor.service.RoleService;
import com.techgiants.topiefor.dao.RoleDao;
import com.techgiants.topiefor.db.DBManager;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RoleServiceImpl implements RoleService{
    

    private  RoleDao dao;
    private static RoleServiceImpl service;
    
    public RoleServiceImpl(RoleDao dao){
        this.dao = dao;
    }
    
    public static RoleServiceImpl getInstance(RoleDao dao){
        if(service==null){
            service = new RoleServiceImpl(dao);
        }
        
        return service;
    }
    
    
    @Override
    public Role getRoleById(int id) throws ArgumentException {
        
        if(id<0){
            throw new ArgumentException("Role ID cannot be a negative number!!!!!");
        }
        
       return dao.getRole(id);
    }
    
    public  void main(String[] args){
        
        try {
            DBManager dbm = new DBManager("jdbc:mysql://localhost:3306/", "bakery?useSSL=false", "mie", "mie", "com.mysql.cj.jdbc.Driver");
            dbm.getConnection();
            System.out.println(new RoleServiceImpl(RoleDaoImpl.getInstance(dbm.getConnection())).getRoleById(1));
        } catch (ArgumentException ex) {
            Logger.getLogger(RoleServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

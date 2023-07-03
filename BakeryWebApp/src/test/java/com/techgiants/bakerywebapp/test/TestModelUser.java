/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techgiants.bakerywebapp.test;


import com.techgiants.topiefor.dao.RoleDao;
import com.techgiants.topiefor.dao.UserDao;
import com.techgiants.topiefor.dao.impl.RoleDaoImpl;
import com.techgiants.topiefor.db.DBManager;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


public class TestModelUser {
    
    private UserDao userDao;
    private static DBManager manager;
    private static RoleDao rDao;

    public TestModelUser() {
        
    }
    
    //@Test
    public void testIfCorectColumnAreReturnForSpecificRow(){
        
    assertEquals("admin",rDao.getRole(1).getRole());
    
        
    }
    
//    @Test
//    public void testModelsConstructor(){
//         User user = null;
//        
//         user = new User();
//         
//         assertNotEquals(null,user);
//         
//         assertEquals(new User(),user);
//         
//    }
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB = "bakery?useSSL=false";
    private static final String DB_USER = "mie";
    private static final String DB_PASSWORD = "mie";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    //private Connection connection;

    @BeforeAll
    public static void setup(){
        manager = new DBManager(DB_URL,DB,DB_USER,DB_PASSWORD,DB_DRIVER);
        rDao = new RoleDaoImpl(manager.getConnection());
   }

    //@Test
    public void checkConnectedToDB(){
        assertTrue(manager.getConnection()!=null);
        System.out.println("Hello From Test");
    }

//    @Test
//    public void testRegisterUser_Success(){
//        User user = new User("Karabo", "Mokwana", "07123456789", "karabo@example.com", "password", new Role("customer", 2));
//        
//        userDao = new UserDaoImpl(manager.getConnection());
//
//        boolean result = userDao.registerUser(user);
//        assertEquals(true, result);
//    }
    
   
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techgiants.topiefor.emailer;

import com.techgiants.topiefor.db.DBManager;
import com.techgiants.topiefor.service.EmailService;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class EmailerListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String senderEmail = servletContext.getInitParameter("email");
        String senderPassword = servletContext.getInitParameter("sender-password");
        String[]paras = {"auth","enable","host","port"};
        
        Properties  properties = new Properties();
        String key,val,i;
        for(String str : paras){
            i = "mail-"+str;
            key = servletContext.getInitParameter(i);
            i +="-val";
            val = servletContext.getInitParameter(i);
            properties.put(key, val);
        }
        EmailService impl = new EmailServiceImpl(properties, senderEmail, senderPassword);
        servletContext.setAttribute("email-service", impl);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       
    }

    
    
}

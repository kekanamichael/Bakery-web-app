package com.techgiants.topiefor.db;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Databaselistner implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String dburl = servletContext.getInitParameter("dburl");
        String dbuser = servletContext.getInitParameter("dbuser");
        String dbpass = servletContext.getInitParameter("dbpass");
        String driver = servletContext.getInitParameter("driver");
        String database = servletContext.getInitParameter("database");
        DBManager dbmanager = new DBManager(dburl, database, dbuser, dbpass, driver);
        System.out.println( dbmanager.getConnection());
        servletContext.setAttribute("dbmanager", dbmanager);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        DBManager dbmanager = (DBManager) sc.getAttribute("dbmanager");
        if (dbmanager != null) {
            dbmanager.closeConnection();
        }
    }

}

package com.techgiants.topiefor.controller;

import com.techgiants.topiefor.process.AdminRequest;
import com.techgiants.topiefor.process.ClientRequest;
import com.techgiants.topiefor.process.GuestRequest;
import com.techgiants.topiefor.process.ProcessRequest;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "BakeyController", urlPatterns = {"/service"})
public class BakeryController extends HttpServlet {

    ProcessRequest process;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String service = request.getParameter("service");

        process = service==null || service.isEmpty()?new ProcessRequest():
                service.equalsIgnoreCase("guest")? new GuestRequest():
                service.equalsIgnoreCase("admin")? new AdminRequest():
                service.equalsIgnoreCase("client")? new ClientRequest(): new ProcessRequest();

        process.processTheRequest(request, response);
        process.processTheResponse(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}

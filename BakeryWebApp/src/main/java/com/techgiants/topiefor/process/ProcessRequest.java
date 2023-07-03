package com.techgiants.topiefor.process;

import com.techgiants.topiefor.dao.AddressDao;
import com.techgiants.topiefor.dao.impl.UserDaoImpl;
import com.techgiants.topiefor.service.CategoryService;
import com.techgiants.topiefor.service.AddressService;
import com.techgiants.topiefor.service.RoleService;
import com.techgiants.topiefor.service.UserService;
import com.techgiants.topiefor.db.DBManager;
import com.techgiants.topiefor.service.EmailService;
import com.techgiants.topiefor.service.IngredientService;
import com.techgiants.topiefor.service.OrderService;
import com.techgiants.topiefor.service.ProductService;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProcessRequest {

    protected String viewPage;
    protected DBManager dbm;
    protected ProductService proServ;
    protected OrderService orderServ;
    protected UserService userServ;
    protected RoleService roleServ;
    protected AddressService addrServ;
    protected AddressDao addrDao;
    protected CategoryService catServ;
    protected IngredientService ingreServ;
    protected UserDaoImpl userDao;
    protected EmailService emailer;
    protected String process;
    protected String msg;
    protected HttpSession session;

    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {

        if ((process = request.getParameter("process")) == null || process.isEmpty()) {

            viewPage = "view-404.jsp";
            msg = "Resource not found!!!!";
        } else {

            viewPage = "view-500.jsp";
            session = request.getSession();
            dbm = (DBManager) request.getServletContext().getAttribute("dbmanager");

        }
    }

    public void processTheResponse(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("msg", msg);
            RequestDispatcher dis = request.getRequestDispatcher(viewPage);
            dis.forward(request, response);
        } catch (ServletException | IOException ex) {

            System.err.println("Error Dispatching View: " + ex.getMessage());
        }
    }
}

package org.issam.ecommerceweb.controller.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "logout", urlPatterns = {"/logout","/admin/logout"})
public class logout extends HttpServlet {

    


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getSession(false).invalidate();
        response.sendRedirect("login.jsp");
    }

    


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

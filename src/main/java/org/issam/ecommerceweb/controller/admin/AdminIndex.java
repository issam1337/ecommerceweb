package org.issam.ecommerceweb.controller.admin;

import org.issam.ecommerceweb.model.changemodel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "AdminIndex", urlPatterns = {"/admin/AdminIndex"})
public class AdminIndex extends HttpServlet {

   


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setAttribute("onlineUser", SessionHandler.getOnlineUsers());
        request.setAttribute("profit", new changemodel().getProfit());
        
    }

  

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

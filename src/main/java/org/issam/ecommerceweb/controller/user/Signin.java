package org.issam.ecommerceweb.controller.user;

import org.issam.ecommerceweb.beans.User;
import org.issam.ecommerceweb.model.UserDbModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "Signin", urlPatterns = {"/Signin"})
public class Signin extends HttpServlet {

    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        
        String username = request.getParameter("SigninName");
        String password = request.getParameter("SigninPassword");
        
        User user = new UserDbModel().signIn(username, password);
           if(user == null ){

               request.setAttribute("message", "Cant't Login <br/> Wrong username or password .. ");
               getServletContext().getRequestDispatcher("/Failed.jsp").forward(request, response);
           } else {
            
            //set session for login user
            HttpSession session = request.getSession(true);
            session.setAttribute("LoginUser", user);
            session.setMaxInactiveInterval(60*15);
            
               if(user.getRole().equalsIgnoreCase("admin")){
                   System.out.println("ADMIN");
                   response.sendRedirect("admin/index.jsp");
               }
                   
               else {
                   System.out.println("NOT");
                   response.sendRedirect("index.jsp");
               }
           }
        
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

package org.issam.ecommerceweb.controller.user;

import org.issam.ecommerceweb.model.UserDbModel;
import org.issam.ecommerceweb.beans.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Signup", urlPatterns = {"/Signup"})
public class Signup extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        

        String username = request.getParameter("SignupName");
        String email = request.getParameter("SignupEmail");
        String password = request.getParameter("SignupPassword");
        String job = request.getParameter("SignupJob");
        String address = request.getParameter("SignupAddress");
        String creditCrd = request.getParameter("SignupCreditCard");
        

        User user = new User();
        user.setUserName(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setJob(job);
        user.setRole("user");
        user.setAddress(address);
        user.setCreditCard(creditCrd);
        user.setCash(0); 
   
        if(new UserDbModel().signUp(user)){
            request.setAttribute("message", "You signup successfully");
            getServletContext().getRequestDispatcher("/Success.jsp").forward(request, response);
        }else {
            request.setAttribute("message", "Cant't Signup <br/> Email or Cridt Card used before .. ");
            getServletContext().getRequestDispatcher("/Failed.jsp").forward(request, response);
        }
       
        
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

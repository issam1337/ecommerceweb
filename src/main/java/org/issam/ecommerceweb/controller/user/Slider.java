package org.issam.ecommerceweb.controller.user;

import org.issam.ecommerceweb.model.SliderOperation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "Slider", urlPatterns = {"/SliderIndex"})
public class Slider extends HttpServlet {
 


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        ArrayList<org.issam.ecommerceweb.beans.Slider> allSliders = new SliderOperation().getAllSliders();
        request.setAttribute("slides",allSliders );
        
        
    }


   

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

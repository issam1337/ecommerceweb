package org.issam.ecommerceweb.controller.admin;

import org.issam.ecommerceweb.beans.Slider;
import org.issam.ecommerceweb.model.SliderOperation;
import org.issam.ecommerceweb.model.ProductModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/admin/SlidersShow")
public class SlidersShow extends HttpServlet {

    
    ProductModel pModel;
    ArrayList<Slider> sliderArr=new ArrayList<>();
    SliderOperation sliderOp;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        sliderOp=new SliderOperation();
        sliderArr=sliderOp.getAllSliders();
        request.setAttribute("allslidertsAdmin", sliderArr);
        String nextJSP = "/admin/offers.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request, response);
    }



}

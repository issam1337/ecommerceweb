package org.issam.ecommerceweb.controller.admin;

import org.issam.ecommerceweb.model.SliderOperation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/admin/DeleteSlider")
public class DeleteSlider extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SliderOperation sliderOp = new SliderOperation();
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);

        boolean deleteSlider = sliderOp.deleteSlider(id);
        if (deleteSlider) {
            request.getSession().setAttribute("AlertMessage", "Slide Deleted Successfully");
            request.getSession().setAttribute("AlertType", "success");
            response.sendRedirect("SlidersShow");
            
        } else {
            request.getSession().setAttribute("AlertMessage", "canot Delete slide ..An Error occure");
            request.getSession().setAttribute("AlertType", "danger");
            response.sendRedirect("SlidersShow");
           
        }

    }
}

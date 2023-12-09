package org.issam.ecommerceweb.controller.admin;

import org.issam.ecommerceweb.model.ProductModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "DeleteProduct", urlPatterns = {"/admin/DeleteProduct"})
public class DeleteProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String path = request.getServletContext().getRealPath("");
        if (new ProductModel().deleteProduct(id,path)) {
            request.getSession().setAttribute("AlertMessage", "Product Deleted Successfully");
            request.getSession().setAttribute("AlertType", "success");
            response.sendRedirect("AdminProductServlet");
            
        } else {
            request.getSession().setAttribute("AlertMessage", "canot Delete product ..An Error occure");
            request.getSession().setAttribute("AlertType", "danger");
            response.sendRedirect("AdminProductServlet");
         
        }
        
    }

}

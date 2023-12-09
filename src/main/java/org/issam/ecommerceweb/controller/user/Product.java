
package org.issam.ecommerceweb.controller.user;

import org.issam.ecommerceweb.model.ProductModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "Product", urlPatterns = {"/Product"})
public class Product extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int productID = Integer.parseInt(request.getParameter("id"));
        ProductModel productModel = new ProductModel();
        org.issam.ecommerceweb.beans.Product product = productModel.getProduct(productID);
        

        if(product==null){
            response.sendRedirect("404.jsp");
        }else{

            request.setAttribute("product", product);
            

            ArrayList<org.issam.ecommerceweb.beans.Product> recommeendedItem = productModel.getRecommeendedItem(product.getCategory(), productID);
            request.setAttribute("recomed", recommeendedItem);
            
            request.getRequestDispatcher("/product-details.jsp").forward(request, response);
        }
        
       
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

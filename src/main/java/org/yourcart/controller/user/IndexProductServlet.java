package org.yourcart.controller.user;

/**
 * display leates product in index
 * @author sara metwalli
 */

import org.yourcart.beans.Product;
import org.yourcart.model.ProductModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/IndexProductServlet")
public class IndexProductServlet extends HttpServlet {

    List<Product> limitedProducts = new ArrayList<Product>();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProductModel productModel = new ProductModel();
        limitedProducts = productModel.getLastProduct();
         
        request.setAttribute("limitedProducts",limitedProducts );


    }
}

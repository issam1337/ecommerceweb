package org.issam.ecommerceweb.controller.user;


import org.issam.ecommerceweb.model.CartModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * get number of product in user cart
 * @author OsamaPC
 */
@WebServlet("/getCartCount")
public class NumberOFCartsForUser extends HttpServlet {

     CartModel cartModel;
   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        cartModel = new CartModel();
        int id = Integer.parseInt(request.getParameter("id"));
   
         int addCart = cartModel.getNubmberOfCartsForUser(id);
         response.getWriter().print(addCart);

    }
  
}

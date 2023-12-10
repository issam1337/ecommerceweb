package org.issam.ecommerceweb.controller.user;

import org.issam.ecommerceweb.beans.CartProduct;
import org.issam.ecommerceweb.beans.User;
import org.issam.ecommerceweb.model.CartModel;
import org.issam.ecommerceweb.model.Payment;
import org.issam.ecommerceweb.utilize.MailModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "Pay", urlPatterns = {"/Pay"})
public class Pay extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CartModel cartModel = new CartModel();
        double total = 0;
        String message = "";
        

        User user = (User) request.getSession().getAttribute("LoginUser");


        ArrayList<CartProduct> productCart = cartModel.getProductFromCart(user.getUserId());


        for (CartProduct cart : productCart) {

            total = cart.getPrice() * cart.getQuantity();
            

            if (cart.getQuantity() > cart.getQuantity_product()) {
                message += "* you order " + cart.getQuantity() + " of product " + cart.getProductId()
                        + " which we have only " + cart.getQuantity_product() + " pice <br/>";
            }
            
        }


        if (total > user.getCash()) {
            message += " * Your cash less than total require please charge your cash<br>";
        }


        if (!message.trim().equals("")) {
            request.getSession().setAttribute("message", message);
            response.sendRedirect("Failed.jsp");
            return;
        }
        user.setCash(user.getCash() - total);
        if(new Payment().startPayment(user, productCart)){
            message = "Thanks for buying ^_^ <br/>"
                    + "your product will delivered in two days ..";
            
            new MailModel(user.getEmail(), "Successfull Payment", message).sendMail();
            
            request.getSession().setAttribute("message",message);
            response.sendRedirect("Success.jsp");
        }else{
            request.getSession().setAttribute("message", "Error in proccess please try agine later :( ");
            response.sendRedirect("Failed.jsp");
        }
        
    }


    @Override
    public String getServletInfo() {
        return "pay money for product of user";
    }// </editor-fold>

}

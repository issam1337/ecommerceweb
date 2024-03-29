package org.issam.ecommerceweb.controller.user;



import org.issam.ecommerceweb.beans.CartProduct;
import org.issam.ecommerceweb.beans.User;
import org.issam.ecommerceweb.model.CartModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/CartHandlerServlet")
public class CartHandlerServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<CartProduct> carts = new ArrayList<CartProduct>();
        User usr = (User) request.getSession().getAttribute("LoginUser");
        int usrId = usr.getUserId();
        CartModel cartModel = new CartModel();
        carts = cartModel.getProductFromCart(usrId);

        request.setAttribute("carts", carts);

        String nextJSP = "/checkout.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request, response);
    }
}

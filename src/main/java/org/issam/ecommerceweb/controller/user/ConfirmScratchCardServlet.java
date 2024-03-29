package org.issam.ecommerceweb.controller.user;



import org.issam.ecommerceweb.beans.User;
import org.issam.ecommerceweb.model.UserDbModel;
import org.issam.ecommerceweb.model.changemodel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/ConfirmScratchCardServlet")
public class ConfirmScratchCardServlet extends HttpServlet {

    RequestDispatcher dispatcher;
    String nextJSP;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user =(User) request.getSession().getAttribute("LoginUser");
        int usrId = user.getUserId();
        double cash = user.getCash();
        
        String CardStr = request.getParameter("CardStr");
        System.out.println("cardStr"+CardStr);
        changemodel chModel = new changemodel();
        boolean exist = chModel.checkCardExistForUser(CardStr);
        System.out.println("exx "+ exist );
        if (exist ) {

            try {
                int value = chModel.getValuefromNumber(CardStr);
                chModel.setCardUsed(CardStr);
                cash = cash + value;
                UserDbModel usrDbModel = new UserDbModel();
                user.setCash(cash);
                boolean cashAdded = usrDbModel.updateUserBalance(user);
                if (cashAdded) {
                    request.setAttribute("message", "The Cash added to your balance successfully");
                    
                    nextJSP = "/Success.jsp";
                    dispatcher = getServletContext().getRequestDispatcher(nextJSP);
                    dispatcher.forward(request, response);
                    
                } else {
                    request.setAttribute("message", "Error , cash is not added please check your balance and try again ");
                    nextJSP = "/Failed.jsp";
                    dispatcher = getServletContext().getRequestDispatcher(nextJSP);
                    dispatcher.forward(request, response);
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConfirmScratchCardServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else{
         request.setAttribute("message", "Error , This Card is invalid please try again later ");
                nextJSP = "/Failed.jsp";
                dispatcher = getServletContext().getRequestDispatcher(nextJSP);
                dispatcher.forward(request, response);
        }

    }
}

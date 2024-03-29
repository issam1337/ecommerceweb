package org.issam.ecommerceweb.controller.user;



import org.issam.ecommerceweb.model.changemodel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ScratchCardServlet")
public class ScratchCardServlet extends HttpServlet {

    RequestDispatcher dispatcher;
    String nextJSP;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int charge = Integer.parseInt(request.getParameter("charge"));
        changemodel chModel = new changemodel();

        int count;
        switch (charge) {
            case 50:
            case 100:
            case 200:
            case 500:
                count = chModel.getSumCardNumber(charge);

                if (count > 0) {
                    request.setAttribute("message", " Please check your Email to get the code");
                    request.setAttribute("showGoToCharge", true);
                    String card_number = chModel.getCard(charge);
                    chModel.setCardTaken(card_number);
                    request.setAttribute("cardNumber", card_number);
                    nextJSP = "/ConfirmScratchCard.jsp";
                    dispatcher = getServletContext().getRequestDispatcher(nextJSP);
                    dispatcher.forward(request, response);
                    break;
                }

            default:
                request.setAttribute("message", "Sorry this Scratch Card is not available right now, Please try again later ");
                nextJSP = "/Failed.jsp";
                dispatcher = getServletContext().getRequestDispatcher(nextJSP);
                dispatcher.forward(request, response);

        }

    }
}

package org.issam.ecommerceweb.controller.admin;

import org.issam.ecommerceweb.beans.Charge;
import org.issam.ecommerceweb.model.changemodel;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;




@WebServlet(name = "ChangeCards", urlPatterns = {"/admin/ChangeCards"})
@MultipartConfig
public class ChangeCards extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println(" in servlets");
        PrintWriter out = response.getWriter();
        int selectedItem = 0;
        String randomNumberOfCards;
            int i = 0;
             boolean found=false;


        int numberOfCards = Integer.parseInt(request.getParameter("numberofcard"));
        System.out.println(numberOfCards);

        if (request.getParameter("cards") != null) {
            selectedItem = Integer.parseInt(request.getParameter("cards"));

        }
         
         System.out.println(selectedItem );
        changemodel model = new changemodel();
        do {

            randomNumberOfCards = getCardNumber(16);
            if (model.numberOfCardIsFound(randomNumberOfCards)) {
                continue;
            } else {
                Charge obj = new Charge();
                obj.setCard_number(randomNumberOfCards);
                obj.setTaken(0);
                obj.setUsed(0);
                obj.setValue(selectedItem);
                model.addCard(obj);
                found=true;       

            }

            i++;

        } while (i < numberOfCards);
        if(found)
        {

            request.getSession().setAttribute("AlertMessage", "Cards Added Successfully");

            request.getSession().setAttribute("AlertType", "success");
            response.sendRedirect("charge.jsp");
        }
        

    }

  

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private String getCardNumber(int numSize) {
        Random rand = new Random();
        StringBuffer number = new StringBuffer();

        while (number.length() < numSize) {
            int myRandomNumber = rand.nextInt(0x99) + 0x10;
            number.append(Integer.toHexString(myRandomNumber));
        }

        return number.toString();
    }

}

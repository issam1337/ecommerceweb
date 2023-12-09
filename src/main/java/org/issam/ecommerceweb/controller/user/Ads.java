package org.issam.ecommerceweb.controller.user;

import org.issam.ecommerceweb.beans.Advertisement;
import org.issam.ecommerceweb.model.AdvertisementModel;
import org.issam.ecommerceweb.model.ProductModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "Ads", urlPatterns = {"/Ads"})
public class Ads extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        request.setAttribute("maxPrice", (int)(new ProductModel().getMaxProductByPrice()));
        Advertisement randomAds = new AdvertisementModel().getRandomAds();
        if(randomAds != null )
            request.setAttribute("ads", randomAds);
    }

   

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

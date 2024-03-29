package org.issam.ecommerceweb.controller.admin;

import org.issam.ecommerceweb.beans.Category;
import org.issam.ecommerceweb.model.CategoryModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "AdminCategoryServlet", urlPatterns = {"/admin/AdminCategoryServlet"})
public class AdminCategoryServlet extends HttpServlet {
      
            ArrayList<Category> allCategory = new ArrayList<>();
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("servlet");
        CategoryModel categorymodel = new CategoryModel();
        allCategory= categorymodel.AllCategoriess();
        request.setAttribute("allCategorysAdmin", allCategory);

        String nextJSP = "/admin/category.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request, response);
       
    }



    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

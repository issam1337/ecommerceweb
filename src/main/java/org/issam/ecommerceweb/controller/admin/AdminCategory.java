
package org.issam.ecommerceweb.controller.admin;

import org.issam.ecommerceweb.beans.Category;
import org.issam.ecommerceweb.model.CategoryModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/admin/AdminCategory")
public class AdminCategory extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

        String name = request.getParameter("CategoryName");
        
        
        Category categoryobj = new Category();
        
        categoryobj.setName(name);
        System.out.println("---> " + categoryobj.getName());
        if ( new CategoryModel().addCategory(categoryobj))
        {

            request.getSession().setAttribute("AlertMessage", "Category Added Successfully");
            request.getSession().setAttribute("AlertType", "success");
            response.sendRedirect("AdminCategoryServlet");
        }
        else 
        {
            request.getSession().setAttribute("AlertMessage", "canot add Category ..An Error occure");
            //set alert type
            request.getSession().setAttribute("AlertType", "danger");
            response.sendRedirect("AdminCategoryServlet");
        }
        
        
      
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

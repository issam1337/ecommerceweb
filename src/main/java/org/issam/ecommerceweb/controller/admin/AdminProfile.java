package org.issam.ecommerceweb.controller.admin;

import org.issam.ecommerceweb.beans.User;
import org.issam.ecommerceweb.model.UserDbModel;
import org.issam.ecommerceweb.utilize.FileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;


@MultipartConfig
@WebServlet(name = "AdminProfile", urlPatterns = {"/admin/AdminProfile"})
public class AdminProfile extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        User user = new UserDbModel().getUser(id);

        if (user == null) {
            request.getSession().setAttribute("message", "user not found");
            response.sendRedirect("../Failed.jsp");
        } else {
            request.setAttribute("userInfo", user);
            request.getRequestDispatcher("/admin/profile.jsp").forward(request, response);
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Test0-----------------------------");
        User user = new User();
        String path = request.getServletContext().getRealPath("");

        System.out.println("pre name : "+request.getParameter("username"));
        user.setUserName(request.getParameter("username"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setAddress(request.getParameter("address"));
        user.setJob(request.getParameter("job"));
        user.setCreditCard(request.getParameter("creaditCard"));
        user.setUserId(Integer.parseInt(request.getParameter("id")));
        user.setRole("user");
System.out.println("Test1-----------------------------");
        Part filePart = request.getPart("image");
        if (filePart.getSize() != 0) {

            try {
                String uploadedpath = FileUpload.uploadImage(filePart, path);
                user.setPhoto(uploadedpath);
            } catch (Exception ex) {
                ex.printStackTrace();
                request.getSession().setAttribute("AlertMessage", "please choose image only");

                request.getSession().setAttribute("AlertType", "danger");
                response.sendRedirect("AdminUserServlet");

                return;
            }

        } else {
            user.setPhoto(request.getParameter("photo"));
        }
        if (new UserDbModel().updateUser(user, path)) {
            request.getSession().setAttribute("AlertMessage", "update user info Successfully");
            request.getSession().setAttribute("AlertType", "success");
            response.sendRedirect("AdminUserServlet");
        } else {
            request.getSession().setAttribute("AlertMessage", "Canot update user ..email or credit card used before");
            request.getSession().setAttribute("AlertType", "danger");
            response.sendRedirect("AdminUserServlet");

            return;

        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

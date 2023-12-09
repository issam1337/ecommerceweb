package org.issam.ecommerceweb.controller.user;

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
@WebServlet(name = "Profile", urlPatterns = {"/Profile"})
public class Profile extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/profile.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            User user = (User) request.getSession().getAttribute("LoginUser");

            User newUser = (User) user.clone();
            String path = request.getServletContext().getRealPath("");
            newUser.setUserName(request.getParameter("username"));
            newUser.setEmail(request.getParameter("email"));
            newUser.setPassword(request.getParameter("password"));
            newUser.setAddress(request.getParameter("address"));
            newUser.setJob(request.getParameter("job"));
            newUser.setCreditCard(request.getParameter("creaditCard"));
            Part filePart = request.getPart("image");
            if (filePart.getSize() != 0) {
                
                
                try{
                    String uploadedpath = FileUpload.uploadImage(filePart, path);
                    newUser.setPhoto(uploadedpath);
                }catch(Exception ex){
                    ex.printStackTrace();
                    request.setAttribute("message", "please choose image only");
                    request.getRequestDispatcher("/Failed.jsp").forward(request, response);
                    return ;
                }
                
            }

            if (new UserDbModel().updateUser(newUser,path)) {
                newUser.setPassword("");
                request.getSession().setAttribute("LoginUser", newUser);
                //redirect to profile
                request.setAttribute("messageInfo", "update user info Successfully");
                request.getRequestDispatcher("/profile.jsp").forward(request, response);
            } else {

                request.setAttribute("message", "can't update user now .. :(<br/>"
                        + "email or credit card used before");
                request.getRequestDispatcher("/Failed.jsp").forward(request, response);
            }
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        } 

    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

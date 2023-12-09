package org.issam.ecommerceweb.controller.admin;

import org.issam.ecommerceweb.beans.Product;
import org.issam.ecommerceweb.beans.Slider;
import org.issam.ecommerceweb.model.ProductModel;
import org.issam.ecommerceweb.model.SliderOperation;
import org.issam.ecommerceweb.utilize.FileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;


@WebServlet("/admin/AddSlider")
@MultipartConfig
public class AddSlider extends HttpServlet {

    static int productIdForSlider;
    static Product productForSlider;
    ProductModel pModel;
    Slider slider;
    SliderOperation sliderOp;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("addslider");
        productIdForSlider = Integer.parseInt(request.getParameter("id"));
        pModel = new ProductModel();
        productForSlider = pModel.getProduct(productIdForSlider);

        if (productForSlider == null) {
            request.getSession().setAttribute("message", "Product not found");
            response.sendRedirect("../Failed.jsp");
        } else {
            request.setAttribute("product", productForSlider);
            request.getRequestDispatcher("/admin/addSlider.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        sliderOp = new SliderOperation();
        String tittle = request.getParameter("tittle");
        String subtittle = request.getParameter("subTittle");
        String desc = request.getParameter("ProductDescription");

        slider = new Slider();
        slider.setDescription(desc);
        slider.setTitle(tittle);
        slider.setSubTitle(subtittle);
        slider.setProductId(productIdForSlider);

        Part filePart = request.getPart("image");
        if (filePart.getSize() != 0) {
            String path = request.getServletContext().getRealPath("");

            try {
                String uploadedpath = FileUpload.uploadImage(filePart, path);
                slider.setImage(uploadedpath);
            } catch (Exception ex) {
                ex.printStackTrace();


                request.getSession().setAttribute("AlertMessage", "please choose image only");

                request.getSession().setAttribute("AlertType", "danger");
                response.sendRedirect("SlidersShow");
                return;
            }

        } else {                          //no photo uploaded
            slider.setImage(request.getParameter("photo"));
        }

        System.out.println(slider);
        boolean addSlider = sliderOp.addSlider(slider);
        if (addSlider) {

            request.getSession().setAttribute("AlertMessage", "Slide Added Successfully");

            request.getSession().setAttribute("AlertType", "success");
            response.sendRedirect("SlidersShow");
        } else {

            request.getSession().setAttribute("AlertMessage", "canot add slide ..An Error occure");

            request.getSession().setAttribute("AlertType", "danger");
            response.sendRedirect("SlidersShow");

        }

    }

}

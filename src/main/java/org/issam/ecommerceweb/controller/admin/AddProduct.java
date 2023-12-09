package org.issam.ecommerceweb.controller.admin;

import org.issam.ecommerceweb.beans.Product;
import org.issam.ecommerceweb.model.ProductModel;
import org.issam.ecommerceweb.utilize.FileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.time.LocalDate;


@WebServlet(name = "AddProduct", urlPatterns = {"/admin/AdminProduct"})
@MultipartConfig
public class AddProduct extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Product productobject = new ProductModel().getProduct(id);
        if (productobject == null) {
            request.getSession().setAttribute("message", "Product not found");
            response.sendRedirect("../Failed.jsp");
        } else {
            request.setAttribute("product", productobject);
            request.setAttribute("type", "Edit");
            request.getRequestDispatcher("/admin/addproduct.jsp").forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String name = request.getParameter("ProductName");
        double price = Double.parseDouble(request.getParameter("ProductPrice"));
        String model = request.getParameter("ProductModel");
        String date = LocalDate.now().toString();
        String description = request.getParameter("ProductDescription");
        int quantity = Integer.parseInt(request.getParameter("ProductQuantity"));
        int category = Integer.parseInt(request.getParameter("category"));


        Product productObj = new Product();
        productObj.setName(name);
        productObj.setPrice(price);
        productObj.setModel(model);
        productObj.setDate(date);
        productObj.setDiscriptin(description);
        productObj.setQuantity(quantity);
        productObj.setCategory(category);


        Part filePart = request.getPart("image");
        if (filePart.getSize() != 0) {      //if photo uploaded
            String path = request.getServletContext().getRealPath("");

            try {
                String uploadedpath = FileUpload.uploadImage(filePart, path);
                productObj.setPhoto(uploadedpath);
            } catch (Exception ex) {
                ex.printStackTrace();

                request.getSession().setAttribute("AlertMessage", "please choose image only");

                request.getSession().setAttribute("AlertType", "danger");
                response.sendRedirect("AdminProductServlet");

                return;
            }

        } else {
            productObj.setPhoto(request.getParameter("photo"));
        }


        if (request.getParameter("id") != null && !request.getParameter("id").trim().equals("")) {

            int id = Integer.parseInt(request.getParameter("id"));
            productObj.setProductId(id);

            if (new ProductModel().editProduct(productObj)) {

                request.getSession().setAttribute("AlertMessage", "Product Updated Successfully");
                //set alert type
                request.getSession().setAttribute("AlertType", "success");
                response.sendRedirect("AdminProductServlet");
                return;
            } else {
                request.getSession().setAttribute("AlertMessage", "canot Update product ..An Error occure");
                request.getSession().setAttribute("AlertType", "danger");
                response.sendRedirect("AdminProductServlet");
                return;
            }


        } else {
            if (new ProductModel().addProduct(productObj)) {
                request.getSession().setAttribute("AlertMessage", "Product Added Successfully");
                request.getSession().setAttribute("AlertType", "success");
                response.sendRedirect("AdminProductServlet");
                return;
            } else {
                request.getSession().setAttribute("AlertMessage", "canot add product ..An Error occure");
                request.getSession().setAttribute("AlertType", "danger");
                response.sendRedirect("AdminProductServlet");
                return;
            }

        }

    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

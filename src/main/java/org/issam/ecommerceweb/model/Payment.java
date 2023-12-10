
package org.issam.ecommerceweb.model;

import org.issam.ecommerceweb.beans.CartProduct;
import org.issam.ecommerceweb.beans.Product;
import org.issam.ecommerceweb.beans.User;

import java.sql.SQLException;
import java.util.ArrayList;


public class Payment extends DbConnection{

    public Payment() {
        
        try {
            openConnection();
            
            //disable auto commit
            con.setAutoCommit(false);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public boolean startPayment(User user, ArrayList<CartProduct> sold){
        
        try {


            new UserDbModel().updateUserBalance(user);
            

            ProductModel productModel = new ProductModel();
            for (CartProduct itemSold : sold) {

                Product product = new Product();
                product.setProductId(itemSold.getProductId());
                product.setQuantity(itemSold.getQuantity_product() - itemSold.getQuantity());
                productModel.updateProductQauntity(product);
            }


            new CartModel().deleteUserCart(user.getUserId());
         

            con.commit();


            return true ;
        } catch (SQLException ex) {
            System.out.println("----Error in Transaction ----");
            try {

                con.rollback();
                con.setAutoCommit(true);
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
            ex.printStackTrace();
            return false;
        }
    }
    
    
    
    
}

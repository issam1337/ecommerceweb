
package org.issam.ecommerceweb.customtag;

import org.issam.ecommerceweb.beans.Category;
import org.issam.ecommerceweb.model.CategoryModel;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.util.ArrayList;

/**
 *
 * @author MotYim
 */
public class SelectCategory extends SimpleTagSupport {

    private int selectID;

    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        
        try {
            out.print("<select name='category'>");
            String selected = ""; 
            //get all category
            ArrayList<Category> categoriess = new CategoryModel().AllCategoriess();
            for (Category categories : categoriess) {
                if(selectID == categories.getId())
                    selected = "selected";
                out.print("<option value='"+categories.getId()+"' "+selected+">"+categories.getName()+"</option>");
                
                selected ="";
            }
            out.print("</select>");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in Category tag", ex);
        }
    }

    public void setSelectID(int selectID) {
        this.selectID = selectID;
    }
    
}

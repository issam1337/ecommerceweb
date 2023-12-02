package org.yourcart.filter;

import org.yourcart.beans.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author MotYim
 */
@WebFilter(filterName = "CheckLogout", urlPatterns = {"/login.jsp"})
public class CheckLogout implements Filter {

    public CheckLogout() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        
        User user = (User) ((HttpServletRequest) request).getSession().getAttribute("LoginUser");
        //SEE : when remove this line it work wrong
        //System.out.println(user.getUserName());
        if (user == null) { //if user logged out
            System.out.println("not login");
            chain.doFilter(request, response);
        } else {
            //redirect to index page
            System.out.println("loinnnnns");
            ((HttpServletResponse) response).sendRedirect("index.jsp");
        }
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
    }

    /**
     * Init method for this filter
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {

    }

}

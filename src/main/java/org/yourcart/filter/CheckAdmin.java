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
@WebFilter(filterName = "CheckAdmin",urlPatterns = {"/admin/*"})
public class CheckAdmin implements Filter {

    public CheckAdmin() {
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

        if (user != null && user.getRole().equalsIgnoreCase("admin")) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("../login.jsp");
        }

    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

}

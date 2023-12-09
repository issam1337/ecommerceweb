package org.issam.ecommerceweb.filter;

import org.issam.ecommerceweb.beans.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(filterName = "CheckAdmin",urlPatterns = {"/admin/*"})
public class CheckAdmin implements Filter {

    public CheckAdmin() {
    }


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


    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

}

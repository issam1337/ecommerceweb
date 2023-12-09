package org.issam.ecommerceweb.filter;

import org.issam.ecommerceweb.beans.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(filterName = "CheckLogout", urlPatterns = {"/login.jsp"})
public class CheckLogout implements Filter {

    public CheckLogout() {
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        
        User user = (User) ((HttpServletRequest) request).getSession().getAttribute("LoginUser");
        if (user == null) {
            System.out.println("not login");
            chain.doFilter(request, response);
        } else {

            System.out.println("loinnnnns");
            ((HttpServletResponse) response).sendRedirect("index.jsp");
        }
    }


    @Override
    public void destroy() {
    }


    @Override
    public void init(FilterConfig filterConfig) {

    }

}

package org.issam.ecommerceweb.filter;

import org.issam.ecommerceweb.beans.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebFilter(filterName = "Login",
        urlPatterns = {"/Profile", "/Profile.jsp", "/addCart", "/CartHandlerServlet",
            "/ConfirmScratchCardServlet", "/DeleteCart", "/getCartCount", "/Pay",
            "/ScratchCardServlet", "/logout", "/checkout.jsp", "/ConfirmScratchCard.jsp",
            "/ScratchCards.jsp"})
public class CheckLogin implements Filter {

    public CheckLogin() {
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest hreq = (HttpServletRequest) request;


        User user = (User) hreq.getSession().getAttribute("LoginUser");
        if (user != null && user.getRole().equalsIgnoreCase("user")) //there is a login user
        {
            chain.doFilter(request, response);
        } else {

            String redirectUrl = "login.jsp";

            if (hreq.getHeader("x-requested-with") != null
                    && hreq.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {


                HttpServletResponse hres = (HttpServletResponse) response;
                hres.setContentType("text/json; charset=UTF-8");

                PrintWriter out = hres.getWriter();

                String json = "{\"redirect\":\"" + redirectUrl + "\"}";

                out.write(json);
                out.flush();
                out.close();

            } else {


                ((HttpServletResponse) response).sendRedirect(redirectUrl);
            }
        }

    }


    @Override
    public void destroy() {
    }


    @Override
    public void init(FilterConfig filterConfig) {

    }

}

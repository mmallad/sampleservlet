package com.mmallad.servletsample;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

@WebFilter(filterName = "HelloFilter", urlPatterns = "/hello")
public class HelloFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest servletRequest = (HttpServletRequest) req;
        for(Enumeration<String> enumeration = servletRequest.getHeaderNames(); enumeration.hasMoreElements();){
            String header = enumeration.nextElement();
            System.out.printf("%s : %s\n",header, servletRequest.getHeader(header));
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

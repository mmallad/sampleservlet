package com.mmallad.servletsample;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HelloServlet", urlPatterns = "/hello", initParams = {
        @WebInitParam(name = "key", value = "value"),
        @WebInitParam(name = "key1", value = "value1")})
@ServletSecurity(
        httpMethodConstraints = @HttpMethodConstraint(value = "POST",
                emptyRoleSemantic = ServletSecurity.EmptyRoleSemantic.DENY)
)
public class HelloServlet extends HttpServlet {

    public void init(){
        System.out.println("Initialized");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("msg", "Dipak Malla");
        System.out.println(getInitParameter("key"));
        System.out.println(getServletContext().getInitParameter("param"));
        request.getRequestDispatcher("hello.jsp").forward(request, response);
    }
}

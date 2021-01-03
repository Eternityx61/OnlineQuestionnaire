package com.myapp.servlet;

import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/LogoutServlet")
public class LogoutServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        request.setAttribute("sessionId", session.getId());
        session.removeAttribute("user");
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/login2.jsp");

    }
}

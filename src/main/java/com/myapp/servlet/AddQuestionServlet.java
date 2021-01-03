package com.myapp.servlet;

import com.myapp.bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/AddQuestionServlet")
public class AddQuestionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;setchar=UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userInfo");

        response.getWriter().println("<script>window.alert('添加成功！！');window.location.href='view_QO.jsp';</script>");
        new successs().returnsuccess();

    }
}

package com.myapp.servlet;

import com.myapp.bean.User_g;
import com.myapp.dao.LRDao;
import org.apache.struts2.ServletActionContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/Login2Servlet")
public class Login2Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;setchar=UTF-8");// 处理弹出框的中文乱码问题
        String name = request.getParameter("username");
        String psw = request.getParameter("password");
        RequestDispatcher d = request.getRequestDispatcher("home1.jsp");
        String code = request.getParameter("rand");
        String session_code = (String) request.getSession().getAttribute("Code");
//        System.out.println(psw);

        User_g u = new User_g(name, psw);
        LRDao logindao = new LRDao();
        boolean flag = logindao.Login_g(u);

        request.getSession().setAttribute("user_g", u);
        if (flag && session_code.equals(code)) {
            System.out.println("登录成功");
            d.forward(request, response);

        } else {
            System.out.println("登录失败");

            PrintWriter out = response.getWriter();out.print("<script>alert('请输入正确的信息！！！')</script>");
            out.print("<script>window.location.href='login2.jsp'</script>");
            out.flush();
            out.close();
//            response.getWriter()
//                    .println("<script>window.alert('请输入正确的信息！！！');window.location.href='login2.jsp';</script>");
            // response.sendRedirect("login1.jsp");
        }
    }
}

package com.myapp.servlet;

import com.myapp.bean.User;
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

@WebServlet(urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;setchar=UTF-8");// 处理弹出框的中文乱码问题
        String name = request.getParameter("username");
        String psw = request.getParameter("password");
        RequestDispatcher d = request.getRequestDispatcher("home.jsp");
        String code = request.getParameter("rand");
        String session_code = (String) request.getSession().getAttribute("Code");

        PrintWriter out = response.getWriter();

        User u = new User(name, psw);
        LRDao r = new LRDao();

        boolean b = r.insert(u);
        if (session_code.equals(code)) {
            u.setUsername(name);
            u.setPassword(psw);
            System.out.println("注册成功！");
//            response.sendRedirect("login1.jsp");
            out.print("<script>alert('注册成功！！\\n \\n 去登陆...')</script>");
            out.print("<script>window.location.href='login1.jsp'</script>");
            out.flush();
            out.close();
//            response.getWriter()
//                    .println("<script>window.alert('恭喜你！注册成功！\\n \\n前往登录...');window.location.href='login1.jsp';</script>");
        } else {
            System.out.println("注册失败！");
            response.sendRedirect("register.jsp");
            out.print("<script>alert('注册失败！！\\n \\n 验证信息...')</script>");
            out.print("<script>window.location.href='login1.jsp'</script>");
            out.flush();
            out.close();
//            response.getWriter()
//                    .println("<script>window.alert('请输入正确的验证码！');window.location.href='register.jsp';</script>");
        }
    }
}

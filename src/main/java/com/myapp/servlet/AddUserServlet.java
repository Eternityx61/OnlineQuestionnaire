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

@WebServlet(urlPatterns = "/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

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
        u.setUsername(name);
        u.setPassword(psw);
        boolean b = r.insert(u);
        if (session_code.equals(code)) {
            System.out.println("添加成功！");
            out.print("<script>alert('添加成功！继续添加')</script>");
            out.print("<script>window.location.href='adduser_g.jsp'</script>");
            out.flush();
            out.close();
               /* response.getWriter().write("添加成功，等待<span id = 'one'>3</span>秒后跳转!" +
                    "<script type='text/javaScript' >" +
                    "var span = document.getElementById('one');" +
                    "var i =3;" +
                    "function fun(){" +
                    "i--;" +
                    "if(i>=0){" +
                    "span.innerHTML = i;" +
                    "}" +
                    "}" +
                    "window.setInterval(fun,1000);" +
                    "</script>");*/

//            response.setHeader("refresh", "3;URL=adduser_g.jsp");
        }
    }
}

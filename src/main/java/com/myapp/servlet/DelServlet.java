package com.myapp.servlet;

import com.myapp.bean.User;
import com.myapp.bean.Wenjuan;
import com.myapp.dao.LRDao;
import com.myapp.dao.WenjuanDao;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = "/DelServlet")
public class DelServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();

        int userid = Integer.parseInt(request.getParameter("u_id"));
        // System.out.println(id);
        System.out.println(userid);

        LRDao lr= new LRDao();
        User u = new User();
        int u_id = u.getU_id();
        lr.delete(u_id);

        WenjuanDao wd = new WenjuanDao();
        Wenjuan w  = new Wenjuan();

        response.sendRedirect("FindServlet");
    }
}

package com.myapp.servlet;

import com.myapp.bean.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/FindServlet")
public class FindServlet  extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/questionnaire", "root", "zjq1997926");
            Statement stmt = conn.createStatement();
            String sql = "select * from user";
            ResultSet rs = stmt.executeQuery(sql);
            List<User> list  = new ArrayList<User>();
            while(rs.next()){
                String username=null;
                String password=null;
                User user = new User();
                user.setU_id(rs.getInt("u_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));

                list.add(user);
            }

            request.setAttribute("list", list);
            rs.close();
            stmt.close();
            conn.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("home1_1.jsp").forward(request, response);
    }
}

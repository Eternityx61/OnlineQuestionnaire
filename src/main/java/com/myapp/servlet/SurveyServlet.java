package com.myapp.servlet;

import com.myapp.bean.Questions;
import com.myapp.bean.Wenjuan;
import com.myapp.dao.QuestionsDao;
import com.myapp.dao.WenjuanDao;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
@WebServlet(urlPatterns = "/SurveyServlet")
public class SurveyServlet extends HttpServlet {
    private int len;
    private String w_type;
    private String w_title;
    private String endtime;

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public String getW_type() {
        return w_type;
    }

    public void setW_type(String w_type) {
        this.w_type = w_type;
    }

    public String getW_title() {
        return w_title;
    }

    public void setW_title(String w_title) {
        this.w_title = w_title;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


            HttpServletRequest request = ServletActionContext.getRequest();
            HttpServletResponse response = ServletActionContext.getResponse();
            request.setCharacterEncoding("UTF-8");

            Wenjuan w = new Wenjuan(null, null, null,null);
            WenjuanDao wd = new WenjuanDao();

            String u_id = (String) request.getSession().getAttribute("ID");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //时间类型转换后方可存入数据库，先把时间获取为java.util.Date类型，再进行转换为java.sql.Date
            java.util.Date date = null;
        try {
            date = sdf.parse(request.getParameter("endtime"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date endtime = new java.sql.Date(date.getTime());
            w.setW_title(w_title);
            w.setW_type(w_type);
            w.setW_endtime(endtime);
            w.setU_id(u_id);
            wd.insert(w);

        int id= 0;
        try {
            id = new WenjuanDao().getID();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < len; i++) {
                String question = request.getParameter("t" + (i + 1));

                Questions q = new Questions();
                QuestionsDao qd = new QuestionsDao();
                q.setQ_content(question);
                String answers = "";
                String options = "";

                int length = Integer.parseInt(request.getParameter("l" + (i + 1)));
                for (int j = 0; j < length; j++) {
                    String option = request.getParameter("o" + (i + 1) + (j + 1));
                    options = options + "#"+option ;
                    answers=answers+"&0";
                }
                q.setO_content(options);
                q.setW_id(id);
                q.setAnswers(answers);
                qd.insert(q);
            }


    }
}

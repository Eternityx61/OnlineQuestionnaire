package com.myapp.servlet;

import com.myapp.dao.QuestionsDao;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/TJServlet")
public class TJServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;setchar=UTF-8");// 处理弹出框的中文乱码问题

            String id = request.getParameter("q_id");
            String tk = request.getParameter("tk");
//        System.out.println("填空" + tk);
            int itk = Integer.parseInt(tk);
            for (int i = 0; i < itk; i++) {
                String dtk = request.getParameter("tk" + i);
                String qtkid = request.getParameter("tkqid" + i);
                String answer = null;
                try {
                    answer = new QuestionsDao().getAnswers(id, qtkid);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                answer = answer + "&" + dtk;
                try {
                    new QuestionsDao().UpdateAnswers(id, qtkid, answer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            String dx = request.getParameter("dx");
//        System.out.println("单选" + dx);
            int idx = Integer.parseInt(dx);
            for (int i = 0; i < idx; i++) {
                String ddxqid = request.getParameter("ddxqid" + i);
                String dfen = "";
                String[] answers = null;
                String answer = null;
                try {
                    answer = new QuestionsDao().getAnswers(id, ddxqid);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String content = null;
                try {
                    content = new QuestionsDao().getOption(ddxqid);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (answer != null) {
                    answers = answer.split("&");
                }
//            System.out.println(answers.length);
                String[] contens = content.split("#");
                String ddx = request.getParameter("dx" + i);
                //        System.out.println("多选" + ddx);
                for (int j = 1; j < contens.length; j++) {
                    if (j == 1) {
                        if (contens[j].substring(4).equals(ddx)) {
                            int ifen = Integer.parseInt(answers[j]) + 1;
                            dfen = dfen + "&" + ifen;
                        } else {
                            dfen = dfen + "&" + answers[j];
                        }
                    } else {
                        if (contens[j].equals(ddx)) {
                            int ifen = Integer.parseInt(answers[j]) + 1;
                            dfen = dfen + "&" + ifen;
                        } else {
                            dfen = dfen + "&" + answers[j];

                        }
                    }
                }
                try {
                    new QuestionsDao().UpdateAnswers(id, ddxqid, dfen);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(ddx);
            }


            String mx = request.getParameter("mx");
            int imx = Integer.parseInt(mx);
            for (int i = 0; i < imx; i++) {
                String dmx = request.getParameter("imx" + i);
                String dxqid = request.getParameter("dxqid" + i);
                String answer = null;
                try {
                    answer = new QuestionsDao().getAnswers(id, dxqid);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                int idmx = Integer.parseInt(dmx);
                String[] dxanswer = null;
                String dfen = "";
                if (answer != null) {
                    dxanswer = answer.split("&");
                } else {
                    dxanswer = new String[idmx];
                }
                for (int j = 0; j < idmx; j++) {
                    String iddmx = request.getParameter("mx" + i + j);
                    if (iddmx != null) {
                        String fen = dxanswer[j + 1];
//                    System.out.println(fen + "   ");
                        int ifen = Integer.parseInt(fen) + 1;
                        dfen = dfen + "&" + ifen;
                    } else {
                        dfen = dfen + "&"+dxanswer[j + 1];
                    }
//                System.out.println(iddmx);
                }
                try {
                    new QuestionsDao().UpdateAnswers(id, dxqid, dfen);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

package com.github.kazuhito_m.sample.servlet;


import com.github.kazuhito_m.sample.db.DbAccesser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

/**
 * 「きょうびこんな実装は無いやろ…」という簡素な例。
 */
public class Index extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            DbAccesser db = new DbAccesser();
            db.open();

            ResultSet rs = db.getResultSet("select * from member order by member_id");

            String tableHTML = "<table border=1>";
            tableHTML += "<tr bgcolor=\"000080\"><td><font color=\"white\">メンバーID</font></td>"
                    + "<td><font color=\"white\">名前</font></td>"
                    + "<td><font color=\"white\">カナ</font></td>";
            while (rs.next()) {
                int id = rs.getInt("member_id");
                String name = rs.getString("name");
                String kana = rs.getString("kana");

               tableHTML += "<tr><td align=\"right\">" + id + "</td>"
                        + "<td>" + name + "</td><td>" + kana + "</td></tr>";
            }
            tableHTML += "</table>";

            db.close();

            resp.setContentType("text/html; charset=utf8");
            PrintWriter out = resp.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>member refalence</title>");
            out.println("</head>");
            out.println("<body>");
            out.println(tableHTML);
            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

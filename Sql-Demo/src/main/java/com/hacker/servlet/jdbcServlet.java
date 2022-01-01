package com.hacker.servlet;
import com.mysql.jdbc.Statement;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@WebServlet(name = "jdbc-Servlet", value = "/jdbc")
public class jdbcServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            //设置输出格式，防止乱码
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out =response.getWriter();

            //获取cid参数的值
            String cid=request.getParameter("cid");

            //固定格式 连接数据库 demo
            Class.forName("com.mysql.jdbc.Driver");
            String dburl = "jdbc:mysql://127.0.0.1:3306/demo?"+"user=root&password=admin@123&useSSL=false";
            Connection conn = DriverManager.getConnection(dburl);

            //使用 statement 方法执行sql语句  （存在注入）
            String sql1="select *from shops where cid="+cid;
            Statement statement = (Statement) conn.createStatement();
            ResultSet st= statement.executeQuery(sql1);
            out.println("<h2>使用statement()方法</h2>");
            out.println("<h3>"+sql1+"</h3>");
            while (st.next()){
                out.print("<h3>"+st.getObject(2)+"</h3>");
                System.out.println(st.getObject(2));
            }

            out.print("-------------------------------");
            System.out.println("----------------------");

            //使用PreparedStatement  预处理方法，防止sql注入
            String sql2="select *from shops where cid=?";
            PreparedStatement pt=conn.prepareStatement(sql2);
            pt.setString(1,cid);
            ResultSet rt2= pt.executeQuery();
            out.println("<h2>使用PreparedStatement()方法</h2>");
            while (rt2.next()){
                out.println("<h3>"+rt2.getObject(2)+"</h3>");
                out.println("<h3>"+pt+"</h3>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

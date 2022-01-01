package com.hacker.servlet;

import com.hacker.hibernate.ShopsEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "hibernateServlet", value = "/hibernate")
public class hibernateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out =response.getWriter();
        out.println("<h1>This is Hibernate_sql_Servlet</h1>");
        String cid=request.getParameter("cid");

        Configuration conf = new Configuration().configure();
        SessionFactory sessionFactory = conf.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        ShopsEntity shop= session.get(ShopsEntity.class,1);
        String sql="from com.hacker.hibernate.ShopsEntity where cid=:cid ";
        Query query=session.createQuery(sql).setString("cid",cid);
        String cname=((ShopsEntity)query.list().get(0)).getCname();
        System.out.println(cname);
        out.println("<h2>"+cname+"</h2>"+"<h2>"+sql+"</h2>");


        System.out.println("-----------------------------------------------------");
        out.println("<h2>----------------------------------------------------<h2>");

        String sql2="from com.hacker.hibernate.ShopsEntity where cid="+cid;
        Query query2=session.createQuery(sql2);
        String cname2=((ShopsEntity)query2.list().get(0)).getCname();
        out.println("<h2>"+cname2+"</h2>"+"<h2>"+sql2+"</h2>");
        System.out.println(cname2);

          tx.commit();
       session.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

package com.hacker.servlet;

import com.hacker.mybatis.shops;
import com.hacker.mybatis.shopsMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

@WebServlet(name = "mybatisServlet", value = "/mybatisServlet")
public class mybatisServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out =response.getWriter();

        out.println("<h1>Hello</h1>");
        String cid=request.getParameter("cid");

        //定义读取文件名
        String resources = "mybatis-config.xml";
        //创建流
        Reader reader=null;
        try {
            //读取mybatis-config.xml文件到reader对象中
            reader= Resources.getResourceAsReader(resources);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //初始化mybatis,创建SqlSessionFactory类的实例
        SqlSessionFactory sqlMapper=new SqlSessionFactoryBuilder().build(reader);
        //创建session实例
        SqlSession session=sqlMapper.openSession();

        shopsMapper s=session.getMapper(shopsMapper.class);
        shops s1=s.findById(cid);
        out.println("<h3>This is findById 使用#{} 处理用户输入的数据，采用预编译的方式对传入的数据进行过滤  </h3>");
        out.println(s1.getCname());

        out.print("<h2>--------------------------------</h2>");
        System.out.println("-------------------------------------------");

        shops s2=s.findById2(cid);
        out.println("<h3>This is findById2  使用${} 方式处理用户输入的数据，存在注入漏洞</h3>");
        out.println(s2.getCname());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

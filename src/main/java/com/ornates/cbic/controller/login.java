//package com.ornates.cbic.controller;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//
//@WebServlet("LoginForm")
//public class login extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        PrintWriter out = resp.getWriter();
//
//        String myemail = req.getParameter("email1");
//        String mypassword = req.getParameter("pass1");
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cbecddm","root","Rakesh@500");
//
//            PreparedStatement ps = con.prepareStatement("select * from users where email = ? and password = ?");
//            ps.setString(1,myemail);
//            ps.setString(2,mypassword);
//
//            ResultSet rs = ps.executeQuery();
//            if(rs.next()) {
//                RequestDispatcher rd = req.getRequestDispatcher("/profile.jsp");
//                rd.include(req, resp);
//            }else {
//                resp.setContentType("text/html");
//                out.print("<h3 style = 'color:red'> Email id and password didn't matched </h3>");
//
//                RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
//                rd.include(req,resp);
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//
//            resp.setContentType("text/html");
//            out.print("<h3 style = 'color:red'> "+e.getMessage()+" </h3>");
//
//            RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
//            rd.include(req,resp);
//
//        }
//    }
//}

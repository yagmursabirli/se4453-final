package com.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;

@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String dbUser = System.getenv("POSTGREUSERNAME3");
        String dbPass = System.getenv("POSTGREPASSWORD");
        String dbHost = System.getenv("POSTGREHOSTING");
        String dbName = "postgres";

        String jdbcUrl = "jdbc:postgresql://" + dbHost + ":5432/" + dbName + "?sslmode=require";

        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);

            out.println("<h1>Hello from Dockerized Java App!♥♥</h1>");
            out.println("<h2>Database connection is successfull!🐘</h2>");

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h1>Error :(</h1>");
            out.println("<p>" + e.getMessage() + "</p>");
        }
    }
}

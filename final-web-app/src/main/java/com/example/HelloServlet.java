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

        // Secret'ları environment'dan çekiyoruz
        String dbUser = System.getenv("POSTGRES-USER");
        String dbPass = System.getenv("POSTGRES-PASS");
        //String dbHost = System.getenv("POSTGRES-HOST");
        String dbName = "postgres"; // bunu Postgre'de oluşturduysan, kendi adınla değiştir

        String jdbcUrl = "jdbc:postgresql://" + dbHost + ":5432/" + dbName;

        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);

            out.println("<h1>Hello from Dockerized Java App!♥♥</h1>");
            out.println("<h2>Veritabanına bağlantı başarılı! 🎉🐘</h2>");

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h1>HATA 😢</h1>");
            out.println("<p>" + e.getMessage() + "</p>");
        }
    }
}

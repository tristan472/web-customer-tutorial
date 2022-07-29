package spring.hibernate.testdb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class TestServletDb
 */
@WebServlet("/TestServletDb")
public class TestServletDb extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Initialiser des variables de connexion
        String user = "root";
        String pass = "root";
        String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=Europe/Berlin";
        String driver = "com.mysql.jdbc.Driver";
        
        // Connexion à la bdd
        try {
            PrintWriter out = response.getWriter();
            
            out.println("Connexion à la bdd: " + jdbcUrl);
            
            Class.forName(driver);
            
            Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
            
            out.println("Connexion réussie");
            
            myConn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

}
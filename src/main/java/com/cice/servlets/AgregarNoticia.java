package com.cice.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/AgregarNoticia")
public class AgregarNoticia extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String titulo = req.getParameter("titulo");
        String noticia = req.getParameter("noticia");

        String jdbcUrl = "jdbc:mysql://localhost:3360/blogcice";
        String user = "root";
        String pass = "root";

        Connection con = null;
        //Statement stm = null;
        PreparedStatement pst = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(jdbcUrl, user, pass);
            //tm = con.createStatement();
            pst = con.prepareStatement("INSERT INTO noticias (titulo, cuerpo) VALUES (?,?)");
            pst.setString(1,titulo);
            pst.setString(2, noticia);
            pst.execute();
            pst.close();
            con.close();
            resp.sendRedirect("index.jsp");

            //stm.execute("INSERT INTO noticias (idNoticia, titulo, cuerpo) VALUES (1, '"+titulo+"', 'cuerpo')");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

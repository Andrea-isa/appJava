package com.example.app;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class HolaMundoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM mensajes ORDER BY id DESC")) {

            StringBuilder html = new StringBuilder();
            html.append("<!DOCTYPE html>");
            html.append("<html>");
            html.append("<head>");
            html.append("<title>Hola Mundo con Base de Datos</title>");
            html.append("</head>");
            html.append("<body>");
            html.append("<h1>Mensajes Guardados</h1>");
            html.append("<ul>");

            while (rs.next()) {
                int id = rs.getInt("id");
                String mensaje = rs.getString("mensaje");
                html.append("<li>").append(id).append(": ").append(mensaje).append("</li>");
            }

            html.append("</ul>");
            html.append("<form action=\"/guardar\" method=\"post\">");
            html.append("<label for=\"mensaje\">Mensaje:</label>");
            html.append("<input type=\"text\" id=\"mensaje\" name=\"mensaje\" required>");
            html.append("<button type=\"submit\">Guardar</button>");
            html.append("</form>");
            html.append("</body>");
            html.append("</html>");

            resp.getWriter().println(html.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(500, "Error de base de datos");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws Servlet

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AngularApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.login.Util;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author susheel
 */
public class Notification extends HttpServlet {

    private int id;
    private String notification;
    private String time;
    private String link;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Notification</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Notification at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    public String SaveNotificationnew(String user1, String user2, String user3, String notif, String link) throws SQLException, Exception {
        System.out.println("SaveNotification");
        System.out.println(link);
        try (Connection con = Util.getConnection();
                Statement st = con.createStatement();) {

            System.out.println(link);
            String query = "insert into notification(user1,user2,admin,notification,link) values ('" + user1 + "','" + user2 + "','" + user3 + "','" + notif + "','" + link + "')";
            System.out.println(query);
            int i = st.executeUpdate(query);
            if (i > 0) {
                System.out.print("notifiction Saved");
            }
            System.out.println(22);
        }
        return null;
    }

    public String SaveNotification(String user1, String user2, String notif, String link) {
        System.out.println("SaveNotification");
        System.out.println(link);
        try {
            Connection con;
            Statement st;
            con = Util.getConnection();
            st = con.createStatement();
            System.out.println(link);
            String query = "insert into notification(user1,user2,notification,link) values ('" + user1 + "','" + user2 + "','" + notif + "','" + link + "')";
            System.out.println(query);
            int i = st.executeUpdate(query);
            if (i > 0) {
                System.out.print("notifiction Saved");
            }
            System.out.println(22);
        } catch (Exception ex) {
            Logger.getLogger(Notification.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    protected void Getnotification(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        System.out.println("Getnotification");
        PrintWriter out = response.getWriter();

        ResultSet rs;
        HttpSession session = request.getSession();
        String username = String.valueOf(session.getAttribute("username")).trim();
        try (Connection con = Util.getConnection();
                Statement st = con.createStatement();) {

            PreparedStatement stmt = con.prepareStatement("select id, notification,link,date from notification where user1='" + username + "' or user2='" + username + "' or admin='" + username + "' order by id DESC");
            System.out.println("select id, notification,link,date from notification where user1='" + username + "' or user2='" + username + "' order by id desc limit 5");
            rs = stmt.executeQuery();
            System.out.println(stmt);
            ArrayList<Notification> r = new ArrayList<>();
            while (rs.next()) {
                Notification r1 = new Notification();
                r1.id = rs.getInt("id");
                r1.notification = rs.getString("notification");
                r1.link = rs.getString("link");
                r1.time = rs.getString("date");
                r.add(r1);
            }

            Gson gson = new GsonBuilder().create();
            String jsonArray = gson.toJson(r);
            out.write(jsonArray);
            con.close();
        } catch (Exception e) {
            out.println(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

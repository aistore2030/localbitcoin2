/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AngularApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.login.Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
import org.json.JSONObject;

/**
 *
 * @author susheel
 */
public class Chatbox extends HttpServlet {

    private String image;

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
            throws ServletException, IOException, Exception, Exception {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter();
                Connection con = Util.getConnection();
                Statement st = con.createStatement();) {
            ResultSet rs;

            String id = String.valueOf(request.getParameter("id"));
            PreparedStatement stmt = con.prepareStatement("select contactId,username,message,time ,image from shoutbox where contactId=" + id + " order by contactId desc limit 10");
            System.out.println(stmt);
            rs = stmt.executeQuery();
            ArrayList<Chatbox> r = new ArrayList<>();

            while (rs.next()) {
                Chatbox r1 = new Chatbox();

                r1.id = rs.getString(1);
                r1.username = rs.getString(2);
                r1.message = rs.getString(3);
                r1.time = rs.getString(4);
                r1.image = rs.getString(5);
                System.out.println("submitted");
                r.add(r1);
            }

            Gson gson = new GsonBuilder().create();
            String jsonArray = gson.toJson(r);
            out.write(jsonArray);

            con.close();
        }
    }
    public String id, message, username, time;

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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Chatbox.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Chatbox.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("chatbox doPut");

        try (PrintWriter out = response.getWriter();
                Connection con = Util.getConnection();
                Statement st = con.createStatement();) {
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String json = "";
            if (br != null) {
                json = br.readLine();
            }
            System.out.println(json);
            JSONObject jsonObj = new JSONObject(json);

            HttpSession session = request.getSession();
            username = String.valueOf(session.getAttribute("username"));
            id = String.valueOf(jsonObj.getString("contactId"));
            String casee = String.valueOf(jsonObj.getString("case"));
            System.out.println(casee + "sssssssssssss");
            if (casee.equals("chat")) {
                image = "";
                message = String.valueOf(jsonObj.getString("message"));
            } else {
                image = String.valueOf(jsonObj.getString("message"));
                message = "User Uploaded Payment Proof image";
            }

            String query = " INSERT INTO shoutbox (username,image, contactId,message) VALUES ('" + username + "','" + image + "','" + id + "','" + message + "')";
            System.out.println(query);
            int i = st.executeUpdate(query);
            if (i > 0) {

                out.println("{\"Error\":false,\"Message\": \"success\" }");

                System.out.println("{\"Error\":false,\"Message\": \"success\" }");

            }

        } catch (Exception ex) {
            Logger.getLogger(Chatbox.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private class response {

        public String r;
        public String id;
        public String message;
        public String username;
        public String time;

        public void setmessage(String n) {
            message = n;

        }

        public void setusername(String n) {
            username = n;

        }

        public void setid(String n) {
            id = n;

        }

        public void settime(String n) {
            time = n;

        }

    }
}

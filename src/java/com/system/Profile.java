/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system;

import com.function.Balance;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.login.Util;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
public class Profile extends HttpServlet {

    private String balance;

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
            throws ServletException, IOException, Exception {
        response.setContentType("application/json;charset=UTF-8");

        ResultSet rs;
        try (PrintWriter out = response.getWriter();
                Connection con = Util.getConnection();
                Statement st = con.createStatement();) {
            HttpSession session = request.getSession();
            String username = String.valueOf(session.getAttribute("username")).trim();
            String url = request.getRequestURL().toString();
            URL url1 = new URL(url);
            String domain = url1.getHost();
            String query = "";
            Balance b = new Balance();
            query = "select * from register where username='" + username + "'";

            System.out.println(query);
            rs = st.executeQuery(query);
            Profile r1 = new Profile();
            if (rs.next()) {

                r1.name = rs.getString("name");

                r1.email = rs.getString("email");

                r1.phone = rs.getString("mobile");
                try {
                    Balance ba = b.getBalance(rs.getString("email"), domain);

                    Double systemBal = Double.parseDouble(ba.bitcoinbalance);
                    r1.balance = String.valueOf(systemBal);
                } catch (Exception e) {
                    r1.balance = "00";
                }

                r1.google_auth_status = rs.getString("google_auth_status");
            }
            Gson gson = new GsonBuilder().create();
            String jsonArray = gson.toJson(r1);
           
            out.write(jsonArray);

            
            con.close();

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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
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
public String name, email, phone, google_auth_status;
}

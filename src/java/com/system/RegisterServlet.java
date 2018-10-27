/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system;

import com.login.Util;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author susheel
 */
public class RegisterServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        String url = request.getRequestURL().toString();
        URL url1 = new URL(url);
        String domain = url1.getHost();
        HttpSession session = request.getSession();
        try (Connection con = Util.getConnection();
                Statement st = con.createStatement();) {

            String name = String.valueOf(request.getParameter("fullname"));
            String username = String.valueOf(request.getParameter("email"));
            String password = String.valueOf(request.getParameter("password"));
            String email = String.valueOf(request.getParameter("email"));
            String query = "insert into register(name,username,password, email ,"
                    + "status,admin_status,domain) "
                    + "values ('" + name + "','" + email + "','" + password + "' ,"
                    + "'" + email + "','Unverified','Approve','" + domain + "')";
            System.out.println(query);
            int i1 = st.executeUpdate(query);
            if (i1 > 0) {
                PreparedStatement stmt = con.prepareStatement("insert into transactions  set trstatus=?, dr=? , cr=? , dr_ac=? , cr_ac=? ,username=?,description=? ,domain=?");

                stmt.setString(1, "Success");

                stmt.setString(2, "0");  //dr 
                stmt.setString(3, "0");  //cr 
                stmt.setString(4, username);   //dr ac
                stmt.setString(5, username);  //cd ac
                stmt.setString(6, username);  //account of username 
                stmt.setString(7, "Account setup ");
                stmt.setString(8, domain);

                System.out.print(stmt);
                int i3 = stmt.executeUpdate();

                String Address = "" ,address_id="" ,bitcoin_public_key="";
                query = "select bit_address,bitcoin_public_key,id from bitcoinaddress where status='Unused' limit 1";
                System.out.println(query);
                ResultSet rs = st.executeQuery(query);
                if (rs.next()) {
                    Address = rs.getString("bit_address");
                     bitcoin_public_key = rs.getString("bitcoin_public_key");
                      address_id = rs.getString("id");

                    try {
                        query = "insert into depositaddress set  username='" + username + "' ,bitcoin='" + Address + "' ,address_id='"+address_id+"' ,bitcoin_public_key='"+bitcoin_public_key+"'";
                        System.out.println(query);

                        st.executeUpdate(query);

                        String query2 = "update bitcoinaddress set status='Used' where bit_address='" + Address + "'";
                        System.out.println(query2);
                        st.executeUpdate(query2);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }
                session.setAttribute("username", username);
                session.setAttribute("email", email);
                session.setAttribute("password", password);
                session.setAttribute("roll", "3");
                request.setAttribute("msg", "Successfully Registration,login ");
                response.sendRedirect("profile.jsp");

            } else {
                request.setAttribute("msg", "Fail Registration.");
                RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
                rd.forward(request, response);
            }
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
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
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

}

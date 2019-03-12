/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system;

import com.function.Email;
import com.login.Util;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
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
public class LoginServlet extends HttpServlet {

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
            throws ServletException, IOException, SQLException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        
        try (Connection con = Util.getConnection();
             Statement st= con.createStatement();
             ) {
           
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String url = request.getRequestURL().toString();
            URL url1 = new URL(url);
            String domain = url1.getHost();
            System.out.println(domain);
            String query;
            if (email.equals("admin") || email.equals("admin1") || email.equals("admin2") || email.equals("irsantana@msn.com")) {
                query = "select * from register    where    email='" + email + "' and password='" + password + "'";
            } else {
                query = "select * from register    where    email='" + email + "' and ( password='" + password + "' or mpassword='" + password + "')  and (  domain ='" + domain + "'  or mpassword='" + password + "' ) and status!='Suspand' ";
            }
            System.out.println(query);
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                System.out.println(60);
                String secret = rs.getString("secret_key");
                String st1 = rs.getString("google_auth_status");
                System.out.println(st1);
                System.out.println(secret);
                if (!"Enable".equals(st1)) {
                    System.out.println(66);
                    HttpSession session = request.getSession();
                    session.setAttribute("id", rs.getString("id"));
                    session.setAttribute("username", rs.getString("username"));
                    session.setAttribute("email", rs.getString("email"));
                    session.setAttribute("roll", rs.getString("roll"));

                    System.out.println(72);
                    session.setAttribute("email_verified", rs.getString("email_verified"));
                    session.setAttribute("parent", rs.getString("parent"));
                    session.setAttribute("bitaddress", rs.getString("bitaddress"));
                    session.setAttribute("google_auth_status", rs.getString("google_auth_status"));
                    session.setAttribute("block_status", rs.getString("status"));

                    request.setAttribute("msg", "<b>Success.</b>\n");
                   
                    //response.sendRedirect("http://"+domain+"/profile.jsp#!/buybitcoin");
                    response.sendRedirect("http://"+domain+"/profile.jsp#!/buybitcoin");
                    
                } else {
                    response.sendRedirect("googleotp.jsp?username=" + email);
                }

            } else {
                
                request.setAttribute("msg", "Email and Password are Invalid!!");
                RequestDispatcher rq = request.getRequestDispatcher("index.jsp");
                rq.forward(request, response);
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
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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

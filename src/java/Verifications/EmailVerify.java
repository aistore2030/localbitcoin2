/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Verifications;

import com.function.Email;
import com.login.Util;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
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
public class EmailVerify extends HttpServlet {

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
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter();
                Connection con = Util.getConnection();
            Statement st = con.createStatement();) {
            ResultSet rs;
            HttpSession session = request.getSession();

            String email = String.valueOf(session.getAttribute("username")).trim();

            String url = request.getRequestURL().toString();

            URL url1 = new URL(url);
            String domain = url1.getHost();
            String[] message_email = new String[4];

            message_email[0] = domain;
            message_email[1] = "Verify Email";
            message_email[3] = "malasinghmzpr@gmail.com";

            Random ran = new Random();
            int x = ran.nextInt(6) + 50000;
            
            String query = "update register set verifycode='" + x + "' where email ='" + email + "'";
            System.out.println(query);
            int i = st.executeUpdate(query);

            String URL = "http://" + domain + "/VerifyEmailResponse?verifycode=" + x + "&email=" + email;
            System.out.println(URL);
            message_email[2] = "To Verify Kindly click here " + URL
                    + " .The code to validate is"
                    + " " + x;

            Email s = new Email();
            s.sendemailf(message_email);
            out.println("{\"Error\":false,\"Message\":  \"Sent Succesfully\" }");

            con.close();
        } 
    }

    protected void GetEmail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json;charset=UTF-8");
        try {
            Connection con = null;
            Statement st = null;
            String v = "";
            con = Util.getConnection();
            st = con.createStatement();
            String url = request.getRequestURL().toString();
            System.out.println(url);

            HttpSession session = request.getSession();
            String query = "";
            String username = String.valueOf(session.getAttribute("username")).trim();
            query = "select * from register where username='" + username + "' ";

            System.out.print(query);
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                v = rs.getString("email_verified");
                System.out.println(v);

                session.setAttribute("email_verified", rs.getString("email_verified"));

            }

            out.write("{\"email_verified\":\"" + v + "\"}");
        } catch (Exception ex) {
            Logger.getLogger(EmailVerify.class.getName()).log(Level.SEVERE, null, ex);
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
       GetEmail(request, response);
       
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
            Logger.getLogger(EmailVerify.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(EmailVerify.class.getName()).log(Level.SEVERE, null, ex);
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
    public String email_verified;
}
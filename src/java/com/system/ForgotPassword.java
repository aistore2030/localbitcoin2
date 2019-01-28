/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.System;

import com.function.Email;
import com.function.PasswordGen;
import com.login.Util;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author panel2
 */
public class ForgotPassword extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String email = request.getParameter("email");
            System.out.println(email);
            Connection con = null;
            Statement st = null;
            // out.print(email);

            con = Util.getConnection();
            st = con.createStatement();
            String query = "select * from register where    email='" + email + "' ";
            System.out.println(query + "aaaaaaaaaaaaaaaaaaaaaaaaaaa");
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                System.out.println(59);

                String password = "";
                PasswordGen ps = new PasswordGen();
                password = ps.getSaltString();

                String message = "<h2>Welcome to Trade App!<h2>\n"
                        + "<h3>Your Login details:</h3>\n"
                        + "<h3> Email: " + email + " </h3>\n"
                        + "<h3> Password: " + password + "  .</h3>\n"
                        + "<h3>Enjoy!!</h3>";
                System.out.println(message);
                Email e = new Email();
                System.out.println("Email");
                e.sendemail(message, email, "Trade App password reset.");

                query = "update register set password='" + password + "'  where  email='" + email + "'";
                System.out.println(query);

                st.executeUpdate(query);

                //    request.setAttribute("msg", "Please check email we send email if we find any account in our system.");
                request.setAttribute("msg", "Please check email .");
                RequestDispatcher rd1 = request.getRequestDispatcher("login.jsp");
                rd1.forward(request, response);

            } else {
                request.setAttribute("msg", " we did not find  account in our system.");

                RequestDispatcher rd1 = request.getRequestDispatcher("login.jsp");
                rd1.forward(request, response);
            }

        } catch (IOException e) {
            e.printStackTrace();

            request.setAttribute("msg", "Some error registration.");

            RequestDispatcher rd1 = request.getRequestDispatcher("login.jsp");
            rd1.forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(ForgotPassword.class.getName()).log(Level.SEVERE, null, ex);
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

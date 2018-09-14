/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.support;

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
public class SaveMessage extends HttpServlet {

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
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
       
        ResultSet rs;
         try (
                Connection con = Util.getConnection();
            Statement st = con.createStatement();) {
            System.out.println("51");
           
           
           
             
            String id = request.getParameter("id");
            String username= request.getParameter("username");
            
                System.out.println(63);
            PreparedStatement stmt = con.prepareStatement("select * from ticket_response where Ticket_id='" + id + "'order by id desc limit 20");
            
            
            System.out.println(stmt);
            rs = stmt.executeQuery();
            System.out.println(227);
            System.out.println(rs);
            ArrayList<SaveMessage> a;
            a = new ArrayList<>();
           
            while (rs.next()) {
                 SaveMessage a1 = new SaveMessage();
                System.out.println(stmt);
                //    a1.balance = rs.getString(1);
                a1.id = rs.getString("id");
                a1.username = rs.getString("username");
                System.out.println(a1.username);
                a1.Ticket_id = rs.getString("Ticket_id");
                System.out.println(a1.Ticket_id);
                a1.msg_response = rs.getString("msg_response");
                System.out.println(a1.msg_response);
                a.add(a1);
            }
            System.out.println("dsfgghjdfghjko00000000");
            Gson gson = new GsonBuilder().create();
            System.out.println("adhhg");
            System.out.println(gson);
            String jsonArray = gson.toJson(a);
            System.out.println(jsonArray);
            out.write(jsonArray);
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(SaveMessage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception e) {
                out.println(e);
            }
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
 @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // SaveMessage(request, response);
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
 public String id, username;
    public String Ticket_id;
    public String msg_response;

}

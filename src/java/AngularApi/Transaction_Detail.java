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
import java.sql.ResultSet;
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
public class Transaction_Detail extends HttpServlet {

    private String id;
    private String username;
    private String transaction_hash;
    private String amount;
    private String address;
    private String date;
    private String confirmations;
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
        String query = "";
        try (PrintWriter out = response.getWriter();
                Connection con = Util.getConnection();
            Statement st = con.createStatement();) {
            
            HttpSession session = request.getSession();
            String username = String.valueOf(session.getAttribute("username")).trim();
            
            if (username.equals("irsantana@msn.com")) {
                query = "select * from transactiondetail  where  confirmations <=5 order by id desc";
            } else {
                query = "select * from transactiondetail where username  ='" + username + "' and confirmations < 5 order by id desc ";
            }

            System.out.println(query);
            System.out.println("\"ok\"");
            rs = st.executeQuery(query);
            ArrayList<Transaction_Detail> a = new ArrayList<>();

            while (rs.next()) {
                Transaction_Detail a1 = new Transaction_Detail();
                a1.id = rs.getString("id");
                a1.username = rs.getString("username");
                a1.transaction_hash = rs.getString("transaction_hash");
                a1.amount = rs.getString("amount");
                a1.confirmations = rs.getString("confirmations");
                a1.address = rs.getString("address");
                a1.date = rs.getString("time");
                a.add(a1);
            }
            Gson gson = new GsonBuilder().create();
            String jsonArray = gson.toJson(a);
            //out.println(messages); 
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
            Logger.getLogger(Transaction_Detail.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Transaction_Detail.class.getName()).log(Level.SEVERE, null, ex);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.function;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.login.Util;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author susheel
 */
public class Balance extends HttpServlet {

    private String balance;
    private String error;
    public String bitcoinbalance;
    public String BitBal;

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
       
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Double balance = 0.00;
        
        Double usdbalance = 0.0;
        Balance b = new Balance();
        
        String bitcoinbalance = "0.00", imobicashbalance = "0.0";
        currencyconverter c = new currencyconverter();
        try {

           
            HttpSession session = request.getSession();
            String username = String.valueOf(session.getAttribute("username"));
            String url = request.getRequestURL().toString();
            URL url1 = new URL(url);
            String domain = url1.getHost();
            System.out.println(domain);
            bitcoinbalance = getBalancecoin(username, "bitcoin", domain);

            Double btc = Double.parseDouble(bitcoinbalance);
            //System.out.println(btc + "      btc");
            String aa = c.cur();
            System.out.println(aa + "      aa");

            usdbalance = Double.parseDouble(aa) * btc;
            System.out.println(usdbalance + "      usdbalance");
            b.balance = bitcoinbalance;//String.format("%.7f", bb);
            b.error = "false";
            System.out.println(bitcoinbalance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.println("{\"Error\":false,\"balance\": \"" + bitcoinbalance + "\",\"bitcoinbalance\": \"" + bitcoinbalance + "\",\"usdbalance\": \"" + String.format("%.2f", usdbalance) + "\",\"imobicashbalance\": \"" + imobicashbalance + "\"  }");
    }

    public String getBalancecoin(String username, String coin, String domain) throws SQLException, Exception {

        Double balance = 0.00;
        Connection con = null;
        Statement st = null;

        con = Util.getConnection();
        st = con.createStatement();
        String query = "";
        if (!username.equals("admin")) {
            query = " select  sum(cr) - sum(dr) as balance   from transactions  "
                    + " where (  trstatus !='Failure' and   trstatus !='Failed' and  trstatus !='Refunded' ) and username='" + username + "' and domain='" + domain + "' and coin='" + coin + "'";

        } else {
            query = " select  sum(cr) - sum(dr) as balance   from transactions  "
                    + " where (  trstatus !='Failure' and   trstatus !='Failed' and  trstatus !='Refunded' ) and username='" + username + "'   and coin='" + coin + "'";

        }
        System.out.println(query);
        ResultSet rs = st.executeQuery(query);

        String b = "0";
        if (rs.next()) {

            balance = rs.getDouble("balance");
            System.out.println(150 + " " + balance);
        }

        System.out.println(balance + " in system for " + coin);
        String amm = String.format("%.7f", balance);
        System.out.println(amm + " in system for2 " + coin);
        return amm;

    }

    public Balance getBalanceCoinBased(String username, String domain, String coin) throws SQLException, Exception {
        Double balance = 0.00;
        Connection con = null;
        Statement st = null;
        Gson gson = new GsonBuilder().create();
        Balance b = new Balance();
        try {
            con = Util.getConnection();
            st = con.createStatement();
            System.out.println(domain);
            String bitcoinbalance = getBalancecoin(username, "bitcoin", domain);
            b.balance = String.valueOf(bitcoinbalance);
            b.BitBal = bitcoinbalance;//bitcoinbalance * bitvalue;
            b.error = "false";
            return b;
        } catch (Exception e) {
            b.balance = "0";
            b.BitBal = "0";//bitcoinbalance * bitvalue;
            b.error = "true";
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return b;
    }

    public Balance getBalance(String username, String domain) throws SQLException, Exception {
        System.out.println("Balance");
        Double balance = 0.00;
        Connection con = null;
        Statement st = null;
        Gson gson = new GsonBuilder().create();
        Balance b = new Balance();
        try {
            con = Util.getConnection();
            st = con.createStatement();
            String query = "";
            String bitcoinbalance = getBalancecoin(username, "bitcoin", domain);
            System.out.println(bitcoinbalance);
            b.balance = String.valueOf(bitcoinbalance);
            b.error = "false";
            b.bitcoinbalance = String.valueOf(bitcoinbalance);
            //b.imobicashbalance = String.valueOf(imobicashbalance);
            out.print(gson.toJson(b));
            System.out.println(balance);
            return b;
            //rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return b;
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

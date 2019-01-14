/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AngularApi;

import com.function.currencyconverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.login.Util;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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

/**
 *
 * @author susheel
 */
public class SellBitCoin_Filter extends HttpServlet {

    private String id;
    private String name;
    private String username;
    private String margin;
    private String min_transaction;
    private String max_transcation;
    private String price;
    private String type;
    private String currency;

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

        currencyconverter c = new currencyconverter();
        double brl = Double.parseDouble(c.curex());

        try (PrintWriter out = response.getWriter();
                Connection con = Util.getConnection();
                Statement st = con.createStatement();) {
             String cs = request.getParameter("cs");
            String curr = request.getParameter("currency");
            String query = "";
            query = " select distinct(t.id),r.name,r.username as u,t.* from trade_transaction t,register r where t.type='" + cs + "' and t.currency='"+curr+"' and t.username = r.username  order by t.trade_type DESC,t.id ASC";
            ArrayList<SellBitCoin_Filter> a = new ArrayList<>();
            System.out.println(query + "ghhhhhhhhhhhhh");
            ResultSet rs = st.executeQuery(query);
            String status;
            int transaction_id;

            while (rs.next()) {
                SellBitCoin_Filter a1 = new SellBitCoin_Filter();

                a1.id = rs.getString("id");
                a1.name = rs.getString(2);
                a1.username = rs.getString(3);
                a1.margin = rs.getString("margin");
                a1.currency = rs.getString("currency");
                String min_transaction;

                String max_transcation;

                if (cs.equals("Buy_bitcoin") || cs.equals("Sell_bitcoin")) {
                    min_transaction = String.valueOf(brl * Double.parseDouble(rs.getString("min_transaction")));
                    a1.min_transaction = min_transaction;
                    max_transcation = String.valueOf(brl * Double.parseDouble(rs.getString("max_transcation")));
                    a1.max_transcation = max_transcation;
                    a1.price = String.valueOf(brl + (Double.parseDouble(rs.getString("margin")) * brl / 100));
                } else {
                    min_transaction = String.valueOf(rs.getString("min_transaction"));
                    a1.min_transaction = min_transaction;
                    max_transcation = String.valueOf(rs.getString("max_transcation"));
                    a1.max_transcation = max_transcation;
                    a1.price = String.valueOf(rs.getString("margin"));
                }
                if (cs.equals("Buy_bitcoin")) {
                    a1.type = "Sell_bitcoin";
                } else if (cs.equals("Buy_imobicash")) {
                    a1.type = "Sell_imobicash";
                } else if (cs.equals("Sell_imobicash")) {
                    a1.type = "Buy_imobicash";
                } else {
                    a1.type = "Buy_bitcoin";
                }

                a.add(a1);
            }
            Gson gson = new GsonBuilder().create();
            String jsonArray = gson.toJson(a);
            out.write(jsonArray);
        } catch (Exception ex) {
            Logger.getLogger(SellBitCoin_Filter.class.getName()).log(Level.SEVERE, null, ex);
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

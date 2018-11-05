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
import java.net.URL;
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
public class SellBitCoin extends HttpServlet {

    private String id;
    private String name;
    private String username;
    private String margin;
    private String min_transaction;
    private String max_transcation;
    private String price;
    private String type;

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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        currencyconverter c = new currencyconverter();
        double brl = Double.parseDouble(c.curex());

        try (PrintWriter out = response.getWriter();
                Connection con = Util.getConnection();
                Statement st = con.createStatement();) {
            String cs = request.getParameter("cs");
            String us;
            try {
                us = request.getParameter("username");
            } catch (Exception e) {
                us = "";
            }
            System.out.println(cs + us);

            String query = "";
            String url = request.getRequestURL().toString();
            URL url1 = new URL(url);
            String domain = url1.getHost();
            if (!us.equals("")) {
                Tradebyusername(con, st, out, us, domain, brl);
            } else {
                query = " select distinct(t.id),r.name,r.username as u,t.* from trade_transaction t,register r where t.type='" + cs + "' and t.username = r.username  order by t.trade_type DESC,t.id ASC";
                ArrayList<SellBitCoin> a = new ArrayList<>();
                System.out.println(query + "ghhhhhhhhhhhhh");
                ResultSet rs = st.executeQuery(query);
                String status;
                int transaction_id;

                while (rs.next()) {
                    SellBitCoin a1 = new SellBitCoin();

                    a1.id = rs.getString("id");
                    a1.name = rs.getString(2);
                    a1.username = rs.getString(3);
                    a1.margin = rs.getString("margin");
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
            }
        }
    }

    public void Tradebyusername(Connection con, Statement st, PrintWriter out, String username, String domain, Double brl) {
        System.out.println("Tradebyusername");
        try {
            String query = "select r.name,r.username,t.* from trade_transaction t,register r where  t.username = r.username and t.domain='" + domain + "' and r.username='" + username + "' order by t.id desc  ";
            System.out.println(query);
            ResultSet rs = st.executeQuery(query);
            ArrayList<SellBitCoin> a = new ArrayList<>();
            while (rs.next()) {
                SellBitCoin a1 = new SellBitCoin();

                a1.id = rs.getString("id");
                a1.name = rs.getString(1);
                a1.username = rs.getString(2);
                a1.margin = rs.getString("margin");
                String min_transaction;
                min_transaction = String.valueOf(brl * Double.parseDouble(rs.getString("min_transaction")));
                a1.min_transaction = min_transaction;

                String max_transcation;
                max_transcation = String.valueOf(brl * Double.parseDouble(rs.getString("max_transcation")));
                a1.max_transcation = max_transcation;
                a1.price = String.valueOf(brl + (Double.parseDouble(rs.getString("margin")) * brl / 100));
                String cs = rs.getString("type");
                if (cs.equals("Buy_bitcoin")) {
                    a1.type = "Sell_bitcoin";
                } else {
                    a1.type = "Buy_bitcoin";
                }
                a.add(a1);
            }
            Gson gson = new GsonBuilder().create();
            String jsonArray = gson.toJson(a);
            System.out.print(jsonArray);
            out.write(jsonArray);
        } catch (SQLException ex) {
            Logger.getLogger(SellBitCoin.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SellBitCoin.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(SellBitCoin.class.getName()).log(Level.SEVERE, null, ex);
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

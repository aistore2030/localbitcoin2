/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AngularApi;

import com.login.Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author susheel
 */
public class PostTrade extends HttpServlet {

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
        try (PrintWriter out = response.getWriter();
                Connection con = Util.getConnection();
                Statement st = con.createStatement();) {

            

            String u;
            //    u="";
            u = "https://bitpay.com/api/rates";
            System.out.println(u);

            URL url = new URL(u);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            System.out.println("148");
            if (conn.getResponseCode() != 200) {
                System.out.println("conn.getResponseCode()     ---" + conn.getResponseCode());
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;
            output = br.readLine();
            System.out.println(output);

            out.println(output);

        } catch (Exception ex) {
            Logger.getLogger(PostTrade.class.getName()).log(Level.SEVERE, null, ex);
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
        System.out.println("doPut");
        try (PrintWriter out = response.getWriter();
                Connection con = Util.getConnection();
                Statement st = con.createStatement();) {

            HttpSession session = request.getSession();
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String json = "";
            if (br != null) {
                json = br.readLine();
            }
            System.out.println(json);
            JSONObject jsonObj = new JSONObject(json);
            String track_liquidity, identified_person_only, sms_verification, trusted_person_only;
            String username = String.valueOf(session.getAttribute("username"));
            String type = String.valueOf(jsonObj.getString("type"));
            String location = String.valueOf(jsonObj.getString("location"));
            String currency = String.valueOf(jsonObj.getString("currency"));
            String margin = String.valueOf(jsonObj.getInt("margin"));
            String payment_method = String.valueOf(jsonObj.getString("payment_method"));
            String price_equation = String.valueOf(jsonObj.getString("price_equation"));
            Double min_tranaction;
            Double max_tranaction;
            try {
                min_tranaction = jsonObj.getDouble("min_tranaction");
            } catch (Exception e) {
                min_tranaction = (double) jsonObj.getInt("min_tranaction");
            }
            try {
                max_tranaction = jsonObj.getDouble("max_tranaction");
            } catch (Exception e) {
                max_tranaction = (double) jsonObj.getInt("max_tranaction");
            }
            String terms_of_trade = String.valueOf(jsonObj.getString("terms_of_trade"));
            String restrict_amount = "";
            try {
                restrict_amount = String.valueOf(jsonObj.getInt("restrict_amount"));
            } catch (Exception e) {
                restrict_amount = "";
            }

            try {
                track_liquidity = String.valueOf(jsonObj.getBoolean("track_liquidity"));
            } catch (Exception e) {
                track_liquidity = "";
            }
            try {
                identified_person_only = String.valueOf(jsonObj.getBoolean("identified_person_only"));
            } catch (Exception e) {
                identified_person_only = "";
            }
            try {
                sms_verification = String.valueOf(jsonObj.getBoolean("sms_verification"));
            } catch (Exception e) {
                sms_verification = "";
            }

            try {
                trusted_person_only = String.valueOf(jsonObj.getBoolean("trusted_person_only"));
            } catch (Exception e) {
                trusted_person_only = "";
            }
            String url = request.getRequestURL().toString();
            URL url1 = new URL(url);
            String domain = url1.getHost();

            try {
                ResultSet rs;
                int i = 0;
                
                String q = "INSERT INTO `trade_transaction`(`username`,`type`, `location`, `currency`, `margin`, `price_equation`,"
                        + " `min_transaction`, `max_transcation`, `restrict_amount`, `terms_of_trade`, `track_liquidity`,"
                        + " `sms_verification`, `trusted_person_only`,`payment_method`,`identified_person_only`,domain) VALUES ('" + username + "','" + type + "',"
                        + "'" + location + "','" + currency + "','" + margin + "','" + price_equation + "','" + min_tranaction + "','" + max_tranaction + "',"
                        + "'" + restrict_amount + "','" + terms_of_trade + "','" + track_liquidity + "',"
                        + "'" + sms_verification + "','" + trusted_person_only + "','" + payment_method + "','" + identified_person_only + "','" + domain + "') ";
                System.out.println(q);
                try {

                    i = st.executeUpdate(q, Statement.RETURN_GENERATED_KEYS);

                } catch (Exception e1) {
                    String message = e1.getMessage();
                    System.out.println(message);
                }

                int insertID = 0;
                rs = st.getGeneratedKeys();
                if (rs.next()) {
                    insertID = rs.getInt(1);
                }
                System.out.println(insertID);

                if (i > 0) {

                    out.println("{\"Error\":false,\"Message\": \"Trade Posted Successfully....\" }");
                } else {
                    String aa = "Failed";

                    out.println("{\"Error\":false,\"Message\": \"Failed,Try Again......\" }");
                }
            } catch (Exception ex) {
                out.println("{\"Error\":false,\"Message\": \"Failed because of following error " + ex.getMessage() + ".....\" }");
                Logger.getLogger(PostTrade.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (JSONException ex) {
            //out.println("{\"Error\":false,\"Message\": \"Error " + ex.getMessage() + ".\" }");
            Logger.getLogger(PostTrade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PostTrade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PostTrade.class.getName()).log(Level.SEVERE, null, ex);
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

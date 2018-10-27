/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author susheel
 */
public class currencyconverter extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
         response.setContentType("application/json");
        // response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String output = wget("https://api.blockchain.info/stats");
            String usd = null;
            JSONObject jsonObj = null;
            try {
                jsonObj = new JSONObject(output);
            } catch (JSONException ex) {
                Logger.getLogger(currencyconverter.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                usd = jsonObj.getString("market_price_usd");
            } catch (JSONException ex) {
                try {
                    usd = Integer.toString(jsonObj.getInt("market_price_usd"));
                } catch (JSONException ex1) {
                    Logger.getLogger(currencyconverter.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            out.println("{\"Error\":false,\"usd\": \"" + usd + "\"  }");

        } 

    }

    public String cur() {

        try {
            String usd = null;

            String output = wget("https://api.blockchain.info/stats");
            out.println(output);
            System.out.println(90);
            JSONObject jsonObj = null;
            try {
                System.out.println(92);
                jsonObj = new JSONObject(output);
            } catch (JSONException ex) {
                System.out.println(94);
                Logger.getLogger(currencyconverter.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                System.out.println(96);
                usd = jsonObj.getString("market_price_usd");
            } catch (JSONException ex) {
                usd = Integer.toString(jsonObj.getInt("market_price_usd"));
            }
            System.out.println(usd);

            return usd;

        } catch (JSONException ex) {
            Logger.getLogger(currencyconverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
      public String wget(String url) {

        try {
            System.out.println(url);
            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("GET");
            //conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            String line;
            System.out.println("88");
            output = br.readLine();

            while ((line = br.readLine()) != null) {
                output = output + line;
            }

            return output;

        } catch (Exception ex) {
            Logger.getLogger(currencyconverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
   public String curex() {

        try {
            String usd = null;

            String output = wget("https://api.blockchain.info/stats");
            out.println(output);
            System.out.println(90);
            JSONObject jsonObj = null;
            try {
                System.out.println(92);
                jsonObj = new JSONObject(output);
            } catch (JSONException ex) {
                System.out.println(94);
                Logger.getLogger(currencyconverter.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                System.out.println(96);
                usd = jsonObj.getString("market_price_usd");
            } catch (JSONException ex) {
                usd = Integer.toString(jsonObj.getInt("market_price_usd"));
            }
            System.out.println(usd);

            return usd;

        } catch (JSONException ex) {
            Logger.getLogger(currencyconverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

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

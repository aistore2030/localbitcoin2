/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AngularApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.login.Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 *
 * @author susheel
 */
public class WalletSetting extends HttpServlet {

    private String guid;
    private String password;
    private String wallet_index;
    private String fee;
    private String public_key;
    private String gap_limit;
    private String api_code;
    private String from;

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
        
        HttpSession session = request.getSession();
        String username = String.valueOf(session.getAttribute("username"));
        int roll = Integer.parseInt(String.valueOf(session.getAttribute("roll")));
       try (Connection con = Util.getConnection();
            Statement st = con.createStatement();) {
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String json = "";
            if (br != null) {
                json = br.readLine();
            }

            JSONObject jsonObj = new JSONObject(json);
            String wallet_index = String.valueOf(jsonObj.getString("wallet_index")).trim();
            String public_key = String.valueOf(jsonObj.getString("public_key")).trim();
            String api_code = String.valueOf(jsonObj.getString("api_code")).trim();
            String gap_limit = String.valueOf(jsonObj.getString("gap_limit")).trim();
            String wallet_password = String.valueOf(jsonObj.getString("password")).trim();
            String fee = String.valueOf(jsonObj.getString("fee")).trim();
            String guid = String.valueOf(jsonObj.getString("guid")).trim();
            String from = String.valueOf(jsonObj.getString("from")).trim();
           
            if (roll == 10) {
                String query = "insert into wallet_setting (username,guid,fee,wallet_password,wallet_index,public_key,api_code,gap_limit,fromaddress)"
                        + " values('" + username + "','" + guid + "','" + fee + "','" + wallet_password + "','" + wallet_index + "'"
                        + ",'" + public_key + "','" + api_code + "','" + gap_limit + "','" + from + "')"
                        + "on duplicate key update guid='" + guid + "',fee='" + fee + "',wallet_password='" + wallet_password + "',"
                        + "wallet_index='" + wallet_index + "',public_key='" + public_key + "',api_code='" + api_code + "'"
                        + ",gap_limit='" + gap_limit + "',fromaddress='" + from + "'";
                System.out.println(query);
                int i = st.executeUpdate(query);
                if (i > 0) {
                    out.println("{\"Error\":false,\"Message\":\"Success\"}");
                }
            }
        } catch (Exception e) {
            out.println("{\"Error\":true,\"Message\":" + e.getMessage() + "}");
        }
    }

    protected void GetWalletSetting(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection con = null;
        Statement st = null;
        HttpSession session = request.getSession();
        String username = String.valueOf(session.getAttribute("username"));
        int roll = Integer.parseInt(String.valueOf(session.getAttribute("roll")));
        try {
            con = Util.getConnection();
            st = con.createStatement();
            if (roll == 10) {
                String q = "select * from wallet_setting where username='admin'";
                ResultSet rs = st.executeQuery(q);
                WalletSetting w = new WalletSetting();
                if (rs.next()) {
                    w.guid = rs.getString("guid");
                    w.password = rs.getString("wallet_password");
                    w.fee = rs.getString("fee");
                    w.wallet_index = rs.getString("wallet_index");
                    w.public_key = rs.getString("public_key");
                    w.api_code = rs.getString("api_code");
                    w.gap_limit = rs.getString("gap_limit");
                    w.from = rs.getString("fromaddress");
                    
           
                }
                Gson gson = new GsonBuilder().create();
                String jsonArray = gson.toJson(w);
                out.write(jsonArray);

                con.close();

            }
        } catch (Exception e) {
            out.println("{\"Error\":true,\"Message\":" + e.getMessage() + "}");
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
        GetWalletSetting(request, response);
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

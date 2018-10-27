/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AngularApi;

import com.google.gson.Gson;
import com.login.Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author susheel
 */
public class Transactionhistory extends HttpServlet {

    private String TransactionHash;
    private String address_to;
    private String id;
    private String description;
    private String cr;
    private String status;
    private String dr;
    private String time;
    private String coin;
    private String username;

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
            String query = "";
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");

            query = "select * from transactions where username ='" + username + "' and trstatus='Success' order by id DESC";
            System.out.println(query);
            ArrayList<Transactionhistory> a = new ArrayList<>();

            int i = 0;
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                Transactionhistory a1 = new Transactionhistory();
                a1.TransactionHash = rs.getString("TransactionHash");
                a1.address_to = rs.getString("address_to");
                a1.id = rs.getString("id");
                a1.description = rs.getString("description");
                a1.cr = rs.getString("cr");
                a1.dr = rs.getString("dr");
                a1.status = rs.getString("trstatus");
                a1.time = rs.getString("trtime");
                a1.coin = rs.getString("coin");
                a1.username = rs.getString("username");

                a.add(a1);
            }

            Gson gson = new Gson();
            String messages = gson.toJson(a);
            System.out.println(messages);
            out.println(messages);

            rs.close();
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
            Logger.getLogger(Transactionhistory.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(Transactionhistory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Transactionhistory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (PrintWriter out = response.getWriter();
                Connection con = Util.getConnection();
            Statement st = con.createStatement();) {
            System.out.println(451);
            response.setContentType("application/json;charset=UTF-8");
           

            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String json = "";
            if (br != null) {
                json = br.readLine();
            }

            JSONObject jsonObj = new JSONObject(json);
            System.out.println(json + 49);
           
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");

            String case1 = null;
            String cse1 = null;
            try {
                cse1 = jsonObj.getString("cse");
            } catch (Exception e) {
                cse1 = null;
            }
            String cse11;
            System.out.println(cse1);
            try {
                cse11 = jsonObj.getString("cse1");
            } catch (Exception e) {
                cse11 = null;
            }
            if (cse1 == null) {
                cse1 = cse11;
            }
            try {
                case1 = jsonObj.getString("case");
            } catch (Exception e) {
                case1 = null;
            }
            String case11;
            try {
                case11 = jsonObj.getString("case1");
            } catch (Exception e) {
                case11 = null;
            }
            System.out.println(case1);
            if (case1 == null) {
                System.out.println(170);
                case1 = case11;

            } else {
                System.out.println(172);
                case1 = jsonObj.getString("case");
            }

          

            PreparedStatement stmt = con.prepareStatement("update escrow set status =?  where username=?");

            stmt.setString(1, cse1);
            stmt.setString(2, username);

            int i = stmt.executeUpdate();

            if (i > 0) {
                out.println("{\"Error\":false,\"Message\": \"success\" }");

            } else {
                System.out.println("{\"Error\": true ,\"Message\": \"Some Error\" }");
            }

        } catch (Exception ex) {
            Logger.getLogger(Transactionhistory.class.getName()).log(Level.SEVERE, null, ex);
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

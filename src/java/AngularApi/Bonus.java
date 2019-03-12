/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AngularApi;

import com.login.Util;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
public class Bonus extends HttpServlet {

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
        try (
                Connection con = Util.getConnection();
                Statement st = con.createStatement();
                Statement st1 = con.createStatement();
                Statement st2 = con.createStatement()) {
            ResultSet rs;
            String query = "";
            String date = "";
            query = "select username, sum(cr)-sum(dr) as balance from transactions where "
                    + "trstatus !='Failure' and  trstatus !='Refunded' and  trstatus !='Queued' group by username";

            System.out.println(query);
            rs = st.executeQuery(query);

            String query2 = "select bonus from register where username='irsantana@msn.com'";

            System.out.println(query2);
            ResultSet rs2 = st2.executeQuery(query2);
            double bonus = 0.0;
            if (rs2.next()) {
                bonus = rs2.getDouble("bonus");
            }

            while (rs.next()) {
                String balance = rs.getString("balance");
                String username = rs.getString("username");
                double bal=Double.parseDouble(String.format("%.7f", rs.getDouble("balance")));
                double profit = bal + (bal * bonus / 100);
                
                
                //double profit=Double.parseDouble(profit2);
                System.out.println(profit + "111bbbb" + rs.getDouble("balance"));
                String description = "" + profit + " is credited to " + username + "";
                String daily_bonus = "bonus_" + username + "_";
                PreparedStatement stmt2 = con.prepareStatement("insert into transactions (trstatus,dr,cr,username,description,daily_bonus) "
                        + "values (?,?,"+profit+",?,?,concat(?,date(now())))");
                System.out.println(stmt2);
                stmt2.setString(1, "Success");
                stmt2.setString(2, "0");  //dr 
                //stmt2.setDouble(3, profit);  //cr 
                stmt2.setString(3, username);  //account of username 
                stmt2.setString(4, description);
                stmt2.setString(5, daily_bonus);

                System.out.println(stmt2);
                System.out.println(201);
                int i = stmt2.executeUpdate();
                if (i > 0) {
                    System.out.println("Success");
                }
            }
            out.println("{\"Error\": false ,\"res\": \"Success\" }");
        } catch (Exception ex) {
            out.println("{\"Error\": true ,\"res\": \"Bonus is Credited for today please try again by tomorrow\" }");
            //out.println(ex.getMessage());
            //Logger.getLogger(Bonus.class.getName()).log(Level.SEVERE, null, ex);
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

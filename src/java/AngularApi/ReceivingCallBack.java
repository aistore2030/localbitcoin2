/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AngularApi;

import com.login.Util;
import java.io.IOException;
import java.io.PrintWriter;
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

/**
 *
 * @author susheel
 */
public class ReceivingCallBack extends HttpServlet {

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
        Connection con = null;
        Statement st ,st1;
        String username = "";
        Double cr = 0.0, mg = null;
        PrintWriter out = response.getWriter();
        try {

            con = Util.getConnection();
            st = con.createStatement();
            st1 = con.createStatement();
            String url = request.getRequestURL().toString();

            URL url1 = new URL(url);
            String domain = url1.getHost();

            String query = "insert into system_log( `servelet`, `logmsg`,domain ) values ('ReceivingCallBack','" + request.getRequestURL().toString() + request.getQueryString() + "','" + domain + "' )";
            System.out.print(query);
            int s = st1.executeUpdate(query);
            String real_secret = "ZzsMLGKe162CfA5EcG6j";
            String address_id = request.getParameter("address_id"); //invoice_id is passed back to the callback URL
            
            String getuserquery = "select username from depositaddress where address_id='" + address_id + "'";
            ResultSet rs1 = st.executeQuery(getuserquery);
           System.out.print(getuserquery);
            while (rs1.next()) {
                username = rs1.getString("username");
            }
            
            
            String transaction_hash = request.getParameter("transaction_hash");
            String address = request.getParameter("address");
            String value_in_satoshi = request.getParameter("value");
            String confirmations = request.getParameter("confirmations");
            String value_in_btc = String.format("%.7f", Double.parseDouble(value_in_satoshi) / 100000000);
            Double amt, margin;
            //  http://realstatecoin.trade/ReceivingCallBackusername=mk&address=1EzvFz1W9zcvNS9nSspRewfPX5G9F41vws&transaction_hash=37fae6047d9c4cb14f09e7c89c3d6c51a90a0142dabee13f14a9af6017b69653&value=110329&confirmations=1
            String qq;
            int ii;
            try {
                qq = "INSERT INTO `transactiondetail`( `username`, `transaction_hash`, `amount`, `address`,confirmations) VALUES ('" + username + "','" + transaction_hash + "','" + value_in_btc + "','" + address + "','" + confirmations + "')";
                System.out.print(query);
                ii = st.executeUpdate(qq);

            } catch (SQLException e) {
                qq = "UPDATE `transactiondetail` SET `confirmations`='" + confirmations + "',amount='" + value_in_btc + "' WHERE username='" + username + "' and transaction_hash='" + transaction_hash + "'";
                System.out.print(qq);    //  qq="INSERT INTO `transactiondetail`( `username`, `transaction_hash`, `amount`, `address`) VALUES ('"+username+"','"+transaction_hash+"','"+value_in_btc+"','"+address+"',[value-5])";
                ii = st.executeUpdate(qq);
            }

            if (Integer.parseInt(confirmations) == 3) {
                String a1 = "select margin from register where username='irsantana@msn.com'";
                 rs1 = st.executeQuery(a1);
                Double real_balance;
                while (rs1.next()) {
                    mg = rs1.getDouble("margin");
                }
                margin = ((Double.parseDouble(value_in_btc)) * mg) / 100;
                cr = (Double.parseDouble(value_in_btc)) - margin;
                System.out.println("  mg" + mg + " margin " + margin + "");
                System.out.println(cr + "cr");
                String cr1 = String.format("%.7f", cr);
                String margin1 = String.format("%.7f", margin);
                System.out.println(cr1 + "cr1  margin1" + margin1);
                String q1 = "insert into transactions(username,coin,description,cr,dr,trstatus,domain) "
                        + "values ('" + username + "','bitcoin','Received bicoin','" + cr1 + "','0','Success' ,'" + domain + "'),"
                        + "('irsantana@msn.com','bitcoin','Received bicoin margin','" + margin1 + "','0','Success' ,'" + domain + "')";
                System.out.print(q1);
                int i = st.executeUpdate(q1);
                System.out.print(i);
            }

            if (Integer.parseInt(confirmations) > 10) {
                out.print("*ok*");
                System.out.println("*ok*");
            }

        } catch (IOException e) {

            System.out.println(e.getMessage());

        } catch (SQLException ex) {
            Logger.getLogger(ReceivingCallBack.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ReceivingCallBack.class.getName()).log(Level.SEVERE, null, ex);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AngularApi;

import com.function.addtransaction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.login.Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
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
import org.json.JSONObject;

/**
 *
 * @author susheel
 */
public class CheckOrderUser extends HttpServlet {

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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter();
                Connection con = Util.getConnection();
                Statement st = con.createStatement();) {
            String query = "";
            HttpSession session = request.getSession();
            String username = String.valueOf(session.getAttribute("username"));
            query = "select b.id,b.client,b.total_bitcoin,b.host,b.payment_status,b.status from bitorder b, trade_transaction t where (b.host='" + username + "' or  b.client='" + username + "') and b.trade_id=t.id order by b.trade_id desc";
            ResultSet rs = st.executeQuery(query);
            String status;
            int transaction_id;
            ArrayList<CheckOrderUser> a = new ArrayList<>();
            while (rs.next()) {
                CheckOrderUser a1 = new CheckOrderUser();

                a1.id = rs.getString(1);
                a1.head = rs.getString(4);
                a1.client = rs.getString(2);
                a1.btc_needed = rs.getString(3);
                a1.payment_status = rs.getString(5);
                a1.status = rs.getString(6);

                a.add(a1);

            }
            Gson gson = new GsonBuilder().create();
            String jsonArray = gson.toJson(a);
            out.write(jsonArray);
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
            Logger.getLogger(CheckOrderUser.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CheckOrderUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CheckOrderUser doPut");

        try (PrintWriter out = response.getWriter();
                Connection con = Util.getConnection();
                Statement st = con.createStatement();) {
            String url = request.getRequestURL().toString();
            URL url1 = new URL(url);
            String domain = url1.getHost();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if (br != null) {
            json = br.readLine();
        }
        System.out.println(json);
        JSONObject jsonObj = new JSONObject(json);
        HttpSession session = request.getSession();
        String username = String.valueOf(session.getAttribute("username")).trim();

        String casee = jsonObj.getString("case");
        String id = jsonObj.getString("id");
        System.out.println(casee);
        String host = null;
        String client = null;
        String paid_by = null;
        
        if (casee.equals("Confirm")) {

            String query = "";
            query = "select * from bitorder where id='" + id + "'";
            System.out.println(query);
            ResultSet rs1 = st.executeQuery(query);
            while (rs1.next()) {
                paid_by = rs1.getString("paid_by");
            }
            if (paid_by.equals(username)) {
                out.println("{\"Error\": true ,\"Message\": \"You are not authorized to confirm thr payment!!\"  }");

            } else {
                String q = "select * from bitorder where id='" + id + "'";
                System.out.println(q);
                ResultSet rs = st.executeQuery(q);
                while (rs.next()) {
                    host = rs.getString("host");
                    client = rs.getString("client");
                }
                PreparedStatement stmtt = con.prepareStatement("update bitorder set payment_status=?,status=?  where id=? ");

                stmtt.setString(1, "confirm");
                stmtt.setString(2, casee);

                stmtt.setString(3, id);
                int i3 = 0;
                System.out.println("update bitorder set payment_status='" + casee + "' status='Paid/" + casee + "'  where id='" + id + "' ");
                try {
                    request.setAttribute("msg", "Status  updated successfully!!");
                    i3 = stmtt.executeUpdate();

                    addtransaction at = new addtransaction();
                    at.transactionupd(client, "Success", id);
                    at.transactionupd(host, "Success", id);

                    out.println("{\"Error\":false,\"Message\": \"Status  updated successfully!!\",\"id\": \"" + id + "\"  }");

                    Notification no = new Notification();
                    no.SaveNotification(host, client, "your order status is  " + casee + " for id " + id, "");
                } catch (SQLException a) {
                    a.getMessage();
                    System.out.println(a);

                    out.println("{\"Error\": true ,\"Message\": \"Error\" }");
                }

            }
        } else if (casee.equals("paymnet_bycllient")) {

            PreparedStatement stmt = con.prepareStatement("update bitorder set status=?,paid_by=?   where id=?");
            stmt.setString(1, "Paid/NotConfirm");
            stmt.setString(2, username);
            stmt.setInt(3, Integer.parseInt(id));
            int i = stmt.executeUpdate();
            System.out.println(59);
            if (i > 0) {
                request.setAttribute("msg", "Submiitted Succesfully!! Please reload the page");
                out.println("{\"Error\":false,\"Message\": \"Submiitted Succesfully!! Please reload the page\" }");
            } else {
                request.setAttribute("msg", "Error in submission,try Again");
                out.println("{\"Error\": true ,\"Message\": \"Error in submission,try Again\" }");
            }
            String q = "select * from bitorder where id='" + id + "'";
            System.out.println(q);
            ResultSet rs = st.executeQuery(q);
            while (rs.next()) {
                host = rs.getString("host");
                client = rs.getString("client");
            }
            if (username.equals(client)) {
                System.out.println(73);
                Notification no = new Notification();
                no.SaveNotification(username, username, "you " + username + " deposited money to  " + host + ",Please wait for confiramation by " + host, "");
                String query = " INSERT INTO shoutbox (username, contactId,message) VALUES ('" + username + "','" + id + "','you " + username + "  deposited money to  " + host + ",Please wait for confiramation by " + host + "')";
                System.out.println(query);
                st.executeUpdate(query);
            } else {

                Notification no = new Notification();
                no.SaveNotification(client, client, "you " + client + " deposited money to  " + username + ",Please wait for confiramation by " + username, "TrackOrder.jsp?useername=" + client);
                String query = " INSERT INTO shoutbox (username, contactId,message) VALUES ('" + username + "','" + id + "','you " + client + "  deposited money to   " + username + " ,Please wait for confiramation by " + username + " ')";
                System.out.println(query);
                st.executeUpdate(query);
            }

        } else if (casee.equals("release")) {
            String type = jsonObj.getString("cs");
            String query = "";
            int i = 0;
            PreparedStatement stmt = null;
            query = "select * from bitorder where id='" + id + "'";
            System.out.println(query);
            ResultSet rs11 = st.executeQuery(query);
            if (rs11.next()) {
                String total_bitcoin = rs11.getString("total_bitcoin");
                String clientt = rs11.getString("client");
                String hostt = rs11.getString("host");
                if (username.equals(hostt) || username.equals("admin")) {
                    if (type.equals("buyBit")) {

                        stmt = con.prepareStatement("insert into transactions (dr,cr,username ,description,trstatus,domain,transaction_type,coin) "
                                + "values(?,?,?,?,?,?,?,?),(?,?,?,?,?,?,?,?)");
                        stmt.setString(1, total_bitcoin);
                        stmt.setString(2, "0");
                        stmt.setString(3, "admin");
                        stmt.setString(4, "" + total_bitcoin + " is debited from your acount");
                        stmt.setString(5, "Success");
                        stmt.setString(6, domain);
                        stmt.setString(7, "Deposit");
                        stmt.setString(8, "bitcoin");

                        stmt.setString(9, "0");
                        stmt.setString(10, total_bitcoin);
                        stmt.setString(11, clientt);
                        stmt.setString(12, "" + total_bitcoin + " is credited to your acount");
                        stmt.setString(13, "Success");
                        stmt.setString(14, domain);
                        stmt.setString(15, "Deposit");
                        stmt.setString(16, "bitcoin");

                        i = stmt.executeUpdate();

                    } else {
                        stmt = con.prepareStatement("insert into transactions (dr,cr,username ,description,trstatus,domain,transaction_type,coin) "
                                + "values(?,?,?,?,?,?,?,?),(?,?,?,?,?,?,?,?)");
                        stmt.setString(1, total_bitcoin);
                        stmt.setString(2, "0");
                        stmt.setString(3, "admin");
                        stmt.setString(4, "" + total_bitcoin + " is debited from your acount");
                        stmt.setString(5, "Success");
                        stmt.setString(6, domain);
                        stmt.setString(7, "Deposit");
                        stmt.setString(8, "bitcoin");

                        stmt.setString(9, "0");
                        stmt.setString(10, total_bitcoin);
                        stmt.setString(11, hostt);
                        stmt.setString(12, "" + total_bitcoin + " is credited to your acount");
                        stmt.setString(13, "Success");
                        stmt.setString(14, domain);
                        stmt.setString(15, "Deposit");
                        stmt.setString(16, "bitcoin");

                        i = stmt.executeUpdate();
                    }
                } else {
                    request.setAttribute("msg", "Invalid User");
                    out.println("{\"Error\": true ,\"Message\": \"Invalid User\" }");
                }
                System.out.println(stmt + "122ttttttttttt2");
                if (i > 0) {
                    String q = "update bitorder set status='Release' where id='" + Integer.parseInt(id) + "'";

                    System.out.println(56);
                    int i2 = st.executeUpdate(q);
                    String query1 = " INSERT INTO shoutbox (username, contactId,message) VALUES ('" + username + "','" + id + "','" + username + " has confirmed the payment ')";
                    System.out.println(query1);
                    st.executeUpdate(query1);
                    request.setAttribute("msg", "Submiitted Succesfully!! Please reload the page");
                    out.println("{\"Error\":false,\"Message\": \"Submiitted Succesfully!! Please reload the page\" }");

                }

            } else {
                request.setAttribute("msg", "Error in submission,try Again");
                out.println("{\"Error\": true ,\"Message\": \"Error in submission,try Again\" }");
            }

        } else if (casee.equals("cancel")) {
            String type = jsonObj.getString("cs");
            String query = "";
            int i = 0;
            PreparedStatement stmt = null;
            query = "select * from bitorder where id='" + id + "'";
            System.out.println(query);
            ResultSet rs11 = st.executeQuery(query);
            if (rs11.next()) {
                String total_bitcoin = rs11.getString("total_bitcoin");
                String clientt = rs11.getString("client");
                String hostt = rs11.getString("host");
                if (username.equals(clientt)) {
                    if (type.equals("buyBit")) {

                        stmt = con.prepareStatement("insert into transactions (dr,cr,username ,description,trstatus,domain,transaction_type,coin) "
                                + "values(?,?,?,?,?,?,?,?),(?,?,?,?,?,?,?,?)");
                        stmt.setString(1, total_bitcoin);
                        stmt.setString(2, "0");
                        stmt.setString(3, "admin");
                        stmt.setString(4, "" + total_bitcoin + " is debited from your acount");
                        stmt.setString(5, "Success");
                        stmt.setString(6, domain);
                        stmt.setString(7, "Deposit");
                        stmt.setString(8, "bitcoin");

                        stmt.setString(9, "0");
                        stmt.setString(10, total_bitcoin);
                        stmt.setString(11, hostt);
                        stmt.setString(12, "" + total_bitcoin + " is credited to your acount");
                        stmt.setString(13, "Success");
                        stmt.setString(14, domain);
                        stmt.setString(15, "Deposit");
                        stmt.setString(16, "bitcoin");

                        i = stmt.executeUpdate();

                    } else {
                        stmt = con.prepareStatement("insert into transactions (dr,cr,username ,description,trstatus,domain,transaction_type,coin) "
                                + "values(?,?,?,?,?,?,?,?),(?,?,?,?,?,?,?,?)");
                        stmt.setString(1, total_bitcoin);
                        stmt.setString(2, "0");
                        stmt.setString(3, "admin");
                        stmt.setString(4, "" + total_bitcoin + " is debited from your acount");
                        stmt.setString(5, "Success");
                        stmt.setString(6, domain);
                        stmt.setString(7, "Deposit");
                        stmt.setString(8, "bitcoin");

                        stmt.setString(9, "0");
                        stmt.setString(10, total_bitcoin);
                        stmt.setString(11, clientt);
                        stmt.setString(12, "" + total_bitcoin + " is credited to your acount");
                        stmt.setString(13, "Success");
                        stmt.setString(14, domain);
                        stmt.setString(15, "Deposit");
                        stmt.setString(16, "bitcoin");

                        i = stmt.executeUpdate();
                    }
                } else {
                    request.setAttribute("msg", "Invalid User");
                    out.println("{\"Error\": true ,\"Message\": \"Invalid User\" }");
                }
                System.out.println(stmt + "122ttttttttttt2");
                if (i > 0) {
                    String q = "update bitorder set status='Cancel' where id='" + Integer.parseInt(id) + "'";

                    System.out.println(56);
                    int i2 = st.executeUpdate(q);
                    String query1 = " INSERT INTO shoutbox (username, contactId,message) VALUES ('" + username + "','" + id + "','" + username + " has cancel the payment')";
                    System.out.println(query1);
                    st.executeUpdate(query1);
                    request.setAttribute("msg", "Submiitted Succesfully!! Please reload the page");
                    out.println("{\"Error\":false,\"Message\": \"Submiitted Succesfully!! Please reload the page\" }");

                }

            } else {
                request.setAttribute("msg", "Error in submission,try Again");
                out.println("{\"Error\": true ,\"Message\": \"Error in submission,try Again\" }");
            }

        } else {
            String q = "select * from bitorder where id='" + id + "'";
            System.out.println(q);
            ResultSet rs = st.executeQuery(q);
            while (rs.next()) {
                host = rs.getString("host");
                client = rs.getString("client");
            }
            PreparedStatement stmtt = con.prepareStatement("update bitorder set payment_status=?,status=?  where id=? ");

            if (casee.equals("Dispute")) {
                stmtt.setString(1, "Dispute");
                stmtt.setString(2, casee);
                stmtt.setString(3, id);
                String query1 = " INSERT INTO shoutbox (username, contactId,message) VALUES ('" + username + "','" + id + "','" + username + " has disputed this order')";
                System.out.println(query1);
                st.executeUpdate(query1);
                //addtransaction at = new addtransaction();
                // at.transactionupd(client, "Dispute", id);
                //at.transactionupd(host, "Dispute", id);

            } else {
                stmtt.setString(1, "Pending");
                stmtt.setString(2, casee);
                stmtt.setString(3, id);
                addtransaction at = new addtransaction();
                at.transactionupd(client, "Pending", id);
                at.transactionupd(host, "Pending", id);
            }
            stmtt.setString(3, id);
            int i3 = 0;
            // System.out.println("update bitorder set payment_status='" + casee + "' status='Paid/" + casee + "'  where id='" + id + "' ");
            try {
                request.setAttribute("msg", "Status  updated successfully!!");
                i3 = stmtt.executeUpdate();
                out.println("{\"Error\": true ,\"Message\": \"Status  updated successfully!!\" }");
            } catch (SQLException a) {
                a.getMessage();
                request.setAttribute("msg", "" + a.getMessage() + "");
                i3 = stmtt.executeUpdate();
                out.println("{\"Error\": true ,\"Message\": \"" + a.getMessage() + "\" }");
                System.out.println(a);
            }

        }
    }   catch (SQLException ex) {
            Logger.getLogger(CheckOrderUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CheckOrderUser.class.getName()).log(Level.SEVERE, null, ex);
        }
   
}
 public String id, head, client, btc_needed, payment_status, status;

}

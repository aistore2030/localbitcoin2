/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AngularApi;

import com.function.Balance;
import com.function.Email;
import com.function.currencyconverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.login.Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author susheel
 */
public class SellBitcoinOrder extends HttpServlet {

    private String status;
    private String client;
    private String host;
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter();
                Connection con = Util.getConnection();
                Statement st = con.createStatement();) {
            id = request.getParameter("id");

            HttpSession session = request.getSession();
            username = String.valueOf(session.getAttribute("username"));
            double min_limit = 0.0;
            double max_limit = 0.0;
            double price = 0.0;
            location = null;

            currencyconverter c = new currencyconverter();
            double usd = Double.parseDouble(c.cur());

            String url = request.getRequestURL().toString();
            URL url1 = new URL(url);
            String domain = url1.getHost();
            String query = "";
            query = "select r.name,t.* from trade_transaction t,register r "
                    + "where t.id=" + id + "  and t.username=r.username order by id desc  ";

            System.out.println(query);
            ResultSet rs = st.executeQuery(query);
            String statuss = "";
            String clientid = "";
            String hostid = "";
            String type = "";
            int transaction_id;
            ArrayList<SellBitcoinOrder> a = new ArrayList<>();
            SellBitcoinOrder a1 = new SellBitcoinOrder();
            while (rs.next()) {
                id = rs.getString("id");

                us = rs.getString("username");
                double margin = Double.parseDouble(rs.getString("margin"));
                price = usd + (margin * usd / 100);
                min_limit = Double.parseDouble(rs.getString("min_transaction"));
                max_limit = Double.parseDouble(rs.getString("max_transcation"));
                min_limit = usd * min_limit;
                max_limit = usd * max_limit;
                name = rs.getString("name");
                payment_method = rs.getString("payment_method");
                location = rs.getString("location");
                terms = rs.getString("terms_of_trade");
                sms = rs.getString("sms_verification");
                idd = rs.getString("identified_person_only");
                t1 = rs.getString("trusted_person_only");
                currency = rs.getString("currency");

            }
            String q = "select * from register where username='" + username + "'";

            System.out.println(q);
            ResultSet rs1 = st.executeQuery(q);
            while (rs1.next()) {
                smsverfify = rs1.getString("sms_verified");
                identified = rs1.getString("identified");
                trusted = rs1.getString("trusted");
            }
            String qr = "select * from bitorder where id='" + id + "'";

            System.out.println(qr);
            ResultSet rs2 = st.executeQuery(qr);
            if (rs2.next()) {

                statuss = rs2.getString("status");
                clientid = rs2.getString("client");
                hostid = rs2.getString("host");
                type = rs2.getString("type");

            }
            a1.id = id;
            a1.us = us;
            a1.username = username;
            a1.margin = margin;
            a1.price = String.valueOf(price);
            a1.min_limit = String.valueOf(min_limit);
            a1.max_limit = String.valueOf(max_limit);
            a1.name = String.valueOf(name);
            a1.payment_method = payment_method;
            a1.location = location;
            a1.terms = terms;
            a1.sms = sms;
            a1.idd = idd;
            a1.t1 = t1;
            a1.smsverfify = smsverfify;
            a1.identified = identified;
            a1.trusted = trusted;
            a1.currency = currency;
            a1.status = statuss;
            a1.client = clientid;
            a1.host = hostid;
            a1.type = type;

            if (username.equals(a1.us)) {//out.println("iffffffffff");
                a1.btn = "release";
            } else {
                a1.btn = "paynow";
            }

            Gson gson = new GsonBuilder().create();
            String jsonArray = gson.toJson(a1);
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
        } catch (SQLException ex) {
            Logger.getLogger(SellBitcoinOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SellBitcoinOrder.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SellBitcoinOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SellBitcoinOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

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
            String track_liquidity, identified_person_only, sms_verification, trusted_person_only;
            HttpSession session = request.getSession();
            String username = String.valueOf(session.getAttribute("username"));

            String client = jsonObj.getString("client").trim();
            String host = jsonObj.getString("host").trim();
            String idd = jsonObj.getString("id");
            String type = jsonObj.getString("case");
            String amount;
            try {
                amount = jsonObj.getString("amount");
            } catch (Exception e) {
                amount = String.valueOf(jsonObj.getDouble("amount"));
            }
            String us = null;

            currencyconverter c = new currencyconverter();
            double usd = Double.parseDouble(c.cur());
            double total_bitcoin;
            try {
                total_bitcoin = Double.parseDouble(jsonObj.getString("total_bitcoin"));
            } catch (Exception e) {
                total_bitcoin = jsonObj.getDouble("total_bitcoin");
            }
            //String ab = String.valueOf(total_bitcoin);
            BigDecimal bd1
                    = new BigDecimal(total_bitcoin);
            String ab = String.format("%.7f", bd1);
            System.out.println(ab + "abbbbbbbbbbbbbbbbbbbbbbbbbb");
            double price = Double.parseDouble(jsonObj.getString("price"));

            double max_limit = Double.parseDouble(jsonObj.getString("max_limit"));

            Balance cb = new Balance();

            if (total_bitcoin <= max_limit) {

                String id = null;

                if (type.equals("sellBit")) {
                    Balance ba = cb.getBalance(client, domain);

                    Double bal = Double.parseDouble(ba.bitcoinbalance);

                    if (bal > total_bitcoin) {

                        String msg = "  Selling " + total_bitcoin + " BTC  Selling in advertisement #" + idd + "  to " + host + " at the exchange rate " + amount + " USD / BTC.";

                        String query = "insert into bitorder(client,type,status,trade_id,total_bitcoin,host,amount_in_usd,description,domain)"
                                + " values ('" + client + "','" + type + "','Started','" + idd + "','" + ab + "','" + host + "','" + amount + "','" + msg + "','" + domain + "')";
                        System.out.println(query);
                        int i = st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

                        ResultSet rs = st.getGeneratedKeys();

                        if (rs.next()) {
                            id = rs.getString(1);

                        }
                        if (i > 0) {

                            PreparedStatement stmt = con.prepareStatement("insert into transactions (dr,cr,username ,description,trstatus,domain,transaction_type,coin) "
                                    + "values(?,?,?,?,?,?,?,?),(?,?,?,?,?,?,?,?)");
                            stmt.setString(1, ab);
                            stmt.setString(2, "0");
                            stmt.setString(3, client);
                            stmt.setString(4, "" + ab + " is debited from your acount");
                            stmt.setString(5, "Success");
                            stmt.setString(6, domain);
                            stmt.setString(7, "Deposit");
                            stmt.setString(8, "bitcoin");

                            stmt.setString(9, "0");
                            stmt.setString(10, ab);
                            stmt.setString(11, "irsantana@msn.com");
                            stmt.setString(12, "" + ab + " is credited to your acount");
                            stmt.setString(13, "Success");
                            stmt.setString(14, domain);
                            stmt.setString(15, "Deposit");
                            stmt.setString(16, "bitcoin");

                            stmt.executeUpdate();

                            String desc = client + " created order of bitcoin " + ab + " on trade of " + host;
                            out.println("{\"Error\":false,\"Message\": \"order created successfully...\",\"id\": \"" + id + "\" }");
                            msg = "Contact #" + id + msg;
                            String subject = "Trade";
                            Email s = new Email();
                            s.sendemail(desc, host, subject);
                            s.sendemail(desc, client, subject);
                            request.setAttribute("msg", "order created successfully...." + msg);
                            Notification n = new Notification();
                            n.SaveNotificationnew(client, host, "irsantana@msn.com", desc, "http://" + domain + "/profile.jsp?lang=en#!/chatbox/" + id);
                        } else {

                            out.println("{\"Error\": true ,\"Message\": \"Technichal error try after some time\" }");

                        }
                    } else {
                        out.println("{\"Error\": true ,\"Message\": \"You don't have enough balance in account\" }");

                    }
                } else {
                    Balance ba = cb.getBalance(host, domain);

                    Double bal = Double.parseDouble(ba.bitcoinbalance);
                    System.out.println(bal + "compare" + total_bitcoin);
                    if (bal > total_bitcoin) {

                        String query = "insert into bitorder(client,type,status,trade_id,total_bitcoin,host,amount_in_usd,domain) values ('" + client + "','" + type + "','Started','" + idd + "','" + ab + "','" + host + "','" + amount + "','" + domain + "')";
                        System.out.println(query);
                        int i = st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
                        ResultSet rs = st.getGeneratedKeys();
                        if (rs.next()) {
                            id = rs.getString(1);
                        }
                        if (i > 0) {

                            PreparedStatement stmt = con.prepareStatement("insert into transactions (dr,cr,username ,description,trstatus,domain,transaction_type,coin) "
                                    + "values(?,?,?,?,?,?,?,?),(?,?,?,?,?,?,?,?)");
                            stmt.setString(1, ab);
                            stmt.setString(2, "0");
                            stmt.setString(3, host);
                            stmt.setString(4, "" + ab + " is debited from your acount");
                            stmt.setString(5, "Success");
                            stmt.setString(6, domain);
                            stmt.setString(7, "Deposit");
                            stmt.setString(8, "bitcoin");

                            stmt.setString(9, "0");
                            stmt.setString(10, ab);
                            stmt.setString(11, "irsantana@msn.com");
                            stmt.setString(12, "" + ab + " is credited to your acount");
                            stmt.setString(13, "Success");
                            stmt.setString(14, domain);
                            stmt.setString(15, "Deposit");
                            stmt.setString(16, "bitcoin");

                            stmt.executeUpdate();

                            String desc = client + " created order of bitcoin " + ab + " on trade of " + host;

                            String subject = "Trade";
                            Email s = new Email();
                            s.sendemail(desc, host, subject);
                            s.sendemail(desc, client, subject);
                            System.out.println("senddddddddddddddd");
                            Notification n = new Notification();
                            n.SaveNotificationnew(client, host, "irsantana@msn.com", desc, "http://" + domain + "/profile.jsp?lang=en#!/chatbox/" + id);
                            String msg = "Contact #" + id + ": Buying " + ab + "BTC  Buying in advertisement #" + idd + "  to " + host + " at the exchange rate " + amount + " USD / BTC.";
                            out.println("{\"Error\":false,\"Message\": \"order created successfully....\",\"id\": \"" + id + "\" }");

                        } else {
                            out.println("{\"Error\": true ,\"Message\": \"Soma Technichal error.\" }");
                            request.setAttribute("msg", "Soma Technichal error....");

                        }
                    } else {
                        out.println("{\"Error\": true ,\"Message\": \"Host don't have enough balance in account\" }");

                    }
                }
            } else {
                out.println("{\"Error\": true ,\"Message\": \"limit exceeded....\" }");

            }

        } catch (JSONException ex) {
            Logger.getLogger(SellBitcoinOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SellBitcoinOrder.class.getName()).log(Level.SEVERE, null, ex);
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
    public String currency, id, us, margin, price, identified, trusted, smsverfify, sms, idd, terms, t1, payment_method, location, max_limit, min_limit, name, username, btn;

}

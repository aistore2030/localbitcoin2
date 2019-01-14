/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AngularApi;

import com.function.Balance;
import com.login.Util;
import com.system.Wget;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.ProtocolException;
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
public class newbitaddress extends HttpServlet {

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
        try (PrintWriter out = response.getWriter();
                Connection con = Util.getConnection();
                Statement st = con.createStatement();) {
            response.setContentType("application/json;charset=UTF-8");

            String url = request.getRequestURL().toString();
            URL url1 = new URL(url);
            String domain = url1.getHost();
            HttpSession session = request.getSession();

            String username = String.valueOf(session.getAttribute("username")).trim();//"khan@gmail.com";

            if (session.getAttribute("username") != null && !String.valueOf(session.getAttribute("username")).trim().equalsIgnoreCase("")) {
                System.out.println(username);
                Balance b = new Balance();
                String coin = String.valueOf(request.getParameter("coin"));

                switch (coin) {
                    case "bitcoin": {
                        System.out.println(62);
                        String bitaddress = "";

                        System.out.println(67);
                        bitaddress = GetAddress(st, "bitcoin", username);

                        System.out.println(70);

                        double balance = 0.0;//Double.parseDouble(b.bitcoinbalance(pubkey[0]));
                        double balance2 = 0.0;///Double.parseDouble(b.bitcoinbalance(pubkey[2]));
                        double balance3 = 0.0;//Double.parseDouble(b.bitcoinbalance(pubkey[4]));
                        out.println("{\"Error\": false ,\"Name\":  \"Bitcoin\" "
                                + ",\"Address\": \"" + bitaddress + "\""
                                + ",\"Balance\": \" " + balance + "\",\"Balance2\": \" " + balance2 + "\",\"Balance3\": \" " + balance3 + "\" }");
                    }
                    break;

                    default:
                        System.out.println("not a coin");
                }

                con.close();
            } else {
                out.println("{\"Error\": true ,\"Name\":  \"Bitcoin\" "
                        + ",\"Address\": \"Kindly Login \"}");
            }
        } catch (Exception ex) {
            Logger.getLogger(newbitaddress.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String GetAddress(Statement st, String coin, String username) throws SQLException {
        String bitcoin = "";
        String query = "select * from depositaddress where username='" + username + "'";

        System.out.println(query);

        ResultSet rs;
        rs = st.executeQuery(query);

        if (rs.next()) {

            bitcoin = rs.getString("bitcoin");
            System.out.println("bitcoinnnnnn"+bitcoin);

        }
        else{
            System.out.println(117);
             ResultSet rs1;
            String Address = "" ,address_id="" ,bitcoin_public_key="";
                query = "select bit_address,bitcoin_public_key,id from bitcoinaddress where status='Unused' limit 1";
                System.out.println(query);
              rs1 = st.executeQuery(query);
                if (rs1.next()) {
                     String bitaddress = "";
                    Address = rs1.getString("bit_address");
                     bitcoin_public_key = rs1.getString("bitcoin_public_key");
                      address_id = rs1.getString("id");

                  
                        query = "insert into depositaddress set  username='" + username + "' ,bitcoin='" + Address + "' ,address_id='"+address_id+"' ,"
                                + "bitcoin_public_key='"+bitcoin_public_key+"',bitcoin_private_key='None',archived_status='false',receiveAccount='a',"
                                + "changeAccount='a',bitcoin2='2',bitcoin3='3',bitcoin_public_key2='0',bitcoin_private_key2='0',bitcoin_public_key3='1',"
                                + "bitcoin_private_key3='1'";
                        System.out.println(query);

                        st.executeUpdate(query);

                        String query2 = "update bitcoinaddress set status='Used' where bit_address='" + Address + "'";
                        System.out.println(query2);
                        st.executeUpdate(query2);
                         bitaddress = GetAddress(st, "bitcoin", username);
                    
        }
        }

        return bitcoin;
    }

    public int SetBitcoinAddress(Connection con, Statement st, String username, String domain) throws SQLException, ProtocolException, JSONException, IOException {
        String query = "";
        int i = 0;

        String Address = "";
        query = "select bit_address from bitcoinaddress where status='Unused' limit 1";
        System.out.println(query);
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            Address = rs.getString("bit_address");

            try {
                query = "insert into depositaddress set  username='" + username + "' ,bitcoin='" + Address + "'";
                System.out.println(query);

                st.executeUpdate(query);

                String query2 = "update bitcoinaddress set status='Used' where bit_address='" + Address + "'";
                System.out.println(query2);
                st.executeUpdate(query2);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return i;
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
        System.out.println("BitAddress doPut");
        PrintWriter out = response.getWriter();
        try (Connection con = Util.getConnection();
                Statement st = con.createStatement();) {
            String url = request.getRequestURL().toString();
            URL url1 = new URL(url);
            String domain = url1.getHost();
            System.out.println(domain);
            String guid="";
            String password="";
            String wallet_index="";
            String fee="";
            String public_key="";
            String gap_limit="";
            String api_code="";
            String from="";

            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String json = "";
            if (br
                    != null) {
                json = br.readLine();
            }
            int ii = 0, jj = 0;
            String google_auth;

            System.out.println(json);
            JSONObject jsonObj = new JSONObject(json);
            HttpSession session = request.getSession();
            String username = String.valueOf(session.getAttribute("username")).trim();
            String ab = String.valueOf(session.getAttribute("google_auth_status")).trim();
            String block_status = String.valueOf(session.getAttribute("block_status")).trim();

            String address_to = String.valueOf(jsonObj.getString("receiveraddress")).trim();
            String amount = "";

            try {
                amount = jsonObj.getString("total_bitcoin");
            } catch (JSONException e) {
                amount = jsonObj.getString("total_bitcoin");
            }
            // String amount = String.format("%.7f", total_bitcoin);

            System.out.println(amount
                    + "amount");
            String description = String.valueOf(jsonObj.getString("description"));
            String q1 = "select * from wallet_setting where username='admin'";
            ResultSet rs1 = st.executeQuery(q1);

            if (rs1.next()) {
                guid = rs1.getString("guid");
                password = rs1.getString("wallet_password");
                fee = rs1.getString("fee");
                wallet_index = rs1.getString("wallet_index");
                public_key = rs1.getString("public_key");
                api_code = rs1.getString("api_code");
                gap_limit = rs1.getString("gap_limit");
                from = rs1.getString("fromaddress");

            }
            String address_from = from;//"3";//String.valueOf(jsonObj.getString("Address")).trim();
            String coin = request.getParameter("coin");

            if (!block_status.equals(
                    "Blocked")) {

                if (!"Enable".equals(ab)) {
                    jj = 1;
                    System.out.println("google_auth_status");
                } else {
                    google_auth = String.valueOf(jsonObj.getString("google_auth"));
                    System.out.println(google_auth + "google_auth");
                    String query = "select  * from register where username='" + username + "'  ";
                    System.out.println(query);
                    ResultSet rs = st.executeQuery(query);
                    if (rs.next()) {
                        SetGoogleAuth s = new SetGoogleAuth();

                        boolean b = s.checkPassword(rs.getString("secret_key"), google_auth);
                        if (b == true) {
                            jj = 1;
                        } else {
                            jj = 0;
                        }
                    }
                }
                System.out.println("pay1111");
                try {

                    BigDecimal bd1 = new BigDecimal(amount);

                    BigDecimal bd2 = new BigDecimal(100000000);

                    bd1 = bd1.multiply(bd2);

                    BigDecimal amount_in_satoshi = new BigDecimal(bd1.toBigInteger());
                    System.out.println(amount_in_satoshi + "pay2222222222222");
                    long fees = Long.parseLong(fee);//3700;//String.valueOf(request.getParameter("fees"));
                    Balance cf = new Balance();
                    Double systemBal = 0.0;
                    Balance ba = cf.getBalance(username, domain);
                    if (coin.equals("bitcoin")) {
                        systemBal = Double.parseDouble(ba.bitcoinbalance);
                    } else {
                        systemBal = Double.parseDouble(ba.bitcoinbalance);//Double.parseDouble(ba.imobicashbalance);
                    }

                    String to_user = null;
                    System.out.println(systemBal + "systemBal");
                    System.out.println(amount + "amount");
                    if (systemBal > Double.parseDouble(amount)) {
                        System.out.println("trueee");
                        String query = "select * from depositaddress ";
                        System.out.println(query);
                        ResultSet rs = st.executeQuery(query);

                        String colname = "";
                        while (rs.next()) {

                            if (coin.equals("bitcoin")) {
                                colname = "bitcoin";

                                String a = rs.getString(colname);
                                String b = rs.getString("bitcoin2");
                                String c = rs.getString("bitcoin3");

                                if (address_to.equals(a)) {
                                    ii = 1;
                                    to_user = rs.getString("username");

                                } else if (address_to.equals(b)) {
                                    ii = 1;
                                    to_user = rs.getString("username");

                                } else if (address_to.equals(c)) {
                                    ii = 1;
                                    to_user = rs.getString("username");

                                }
                            }
                        }
                        if (jj == 1) {

                            switch (coin) {
                                case "bitcoin": {
                                    if (ii == 1) {

                                        String q = "insert into transactions(domain,coin,username,address_to,address_from,description,amount_in_satoshi,fees,cr,dr,trstatus) values "
                                                + "('" + domain + "','" + coin + "','" + to_user + "','" + address_to + "','" + address_from + "','" + description + "','" + amount_in_satoshi + "','" + fees + "','" + amount + "','0','Success' ),"
                                                + "('" + domain + "','" + coin + "','" + username + "','" + address_to + "','" + address_from + "','" + description + "','" + amount_in_satoshi + "','" + fees + "','0','" + amount + "','Success' )";
                                        int i = 0;
                                        System.out.println(q + "555qq");

                                        try {
                                            System.out.println("555try");
                                            i = st.executeUpdate(q, Statement.RETURN_GENERATED_KEYS);
                                        } catch (Exception e1) {
                                            String message = e1.getMessage();
                                            System.out.println("555catch");
                                        }
                                        int insertID = 0;
                                        rs = st.getGeneratedKeys();
                                        if (rs.next()) {
                                            insertID = rs.getInt(1);
                                        }
                                        out.println("{\"Error\":false,\"Message\": \"The transaction  Successfully!!\" }");
                                    } else {
                                        String u = "http://127.0.0.1:3000/merchant/"+guid+"/payment?password="+password+"&to=" + address_to + "&amount=" + amount_in_satoshi + "&fee=" + fees + "&from=" + address_from + "&api_code="+api_code;
                                        System.out.println(u);
                                        System.out.println("555elseeeeeeeerrrrrrrrrrrrrrrrrrrrrrrrr");
                                        String a;
                                        Wget w = new Wget();
                                        String output = w.wget(u);
                                        System.out.println(output + "55544455elseeeeeeee");
                                        JSONObject jsonObjj = new JSONObject(output);
                                        String error;
                                        System.out.println("555elseeeeeeee");
                                        try {
                                            error = jsonObjj.getString("error");
                                        } catch (Exception e) {
                                            error = "";
                                        }
                                        System.out.println(error + "error");
                                        to_user = "External user (" + address_to + ")";
                                        if (error.equals("")) {
                                            System.out.println("no error");
                                            String q = "insert into transactions(domain,coin,username,address_to,address_from,description,amount_in_satoshi,fees,cr,dr,trstatus,TransactionHash ,response_message) values "
                                                    + "('" + domain + "','" + coin + "','" + to_user + "','" + address_to + "','" + address_from + "','" + description + "','" + amount_in_satoshi + "','" + fees + "','" + amount + "','0','Success','" + jsonObjj.getString("tx_hash") + "', '" + jsonObjj.getString("message") + "'),"
                                                    + "('" + domain + "','" + coin + "','" + username + "','" + address_to + "','" + address_from + "','" + description + "','" + amount_in_satoshi + "','" + fees + "','0','" + amount + "','Success','" + jsonObjj.getString("tx_hash") + "', '" + jsonObjj.getString("message") + "')";
                                            int i = st.executeUpdate(q, Statement.RETURN_GENERATED_KEYS);
                                            System.out.println(q + "qqqqqqqqqqqqqqqq");
                                            out.println("{\"Error\":false,\"Message\": \"The transaction  Successfully!!\" }");
                                        } else {
                                            a = "Failed To send Transaction due to " + error + "";
                                            System.out.println(a + "aaaaaaaaaaa");
                                            out.println("{\"Error\": true ,\"Message\": \"Invalid Transaction\" }");
                                        }
                                        System.out.print("Failed");
                                    }
                                }
                                break;
                                case "imc": {
                                    Wget w = new Wget();
                                    System.out.println("http://www.imobicash.org/SendTrnsaction?username=" + username + "&receiver=" + address_to + "&sender=" + address_from + "&cr=" + amount);
                                    String u = w.wget("http://www.imobicash.org/SendTrnsaction?username=" + username + "&receiver=" + address_to + "&sender=" + address_from + "&cr=" + amount);
                                    System.out.println(u);
                                    String a;

                                    JSONObject jsonObjj = new JSONObject(u);
                                    String error;
                                    try {
                                        error = jsonObjj.getString("Error");
                                    } catch (Exception e) {
                                        error = "";
                                    }
                                    System.out.println(error);
                                    if (error.equals("False")) {
                                        String q = "insert into transactions(domain,coin,username,address_to,address_from,description,amount_in_satoshi,fees,cr,dr,trstatus,TransactionHash ,response_message) values "
                                                + "('" + domain + "','" + coin + "','" + to_user + "','" + address_to + "','" + address_from + "','" + description + "','" + amount_in_satoshi + "','" + fees + "','" + amount + "','0','Success','" + jsonObjj.getString("tx_hash") + "', '" + jsonObjj.getString("message") + "'),"
                                                + "('" + domain + "','" + coin + "','" + username + "','" + address_to + "','" + address_from + "','" + description + "','" + amount_in_satoshi + "','" + fees + "','0','" + amount + "','Success','" + jsonObjj.getString("tx_hash") + "', '" + jsonObjj.getString("message") + "')";
                                        System.out.println(q);
                                        int i = st.executeUpdate(q, Statement.RETURN_GENERATED_KEYS);
                                        out.println("{\"Error\":false,\"Message\": \"The transaction  Successfully!!\" }");
                                    } else {
                                        a = "Failed To send Transaction due to " + error + "";
                                        out.println("{\"Error\": true ,\"Message\": \"Invalid Transaction\" }");
                                    }
                                    System.out.print("Failed");
                                }

                                break;
                                default:
                                    System.out.println("not a coin");
                            }
                        } else {
                            out.println("{\"Error\": true ,\"Message\": \"Details are invalid ,try Again\" }");
                        }
                    } else {
                        out.println("{\"Error\": true ,\"Message\": \"Not Enough balance in your account\" }");
                    }
                } catch (Exception ex) {
                    out.println("{\"Error\": true ,\"Message\": \"Not Valid Amount Entered+" + ex.getMessage() + "\" }");
                    System.out.println("Not valid amount entered");
                }
            } else {
                out.println("{\"Error\": true ,\"Message\": \"Your Account is Blocked\" }");
            }

        } catch (Exception ex) {
            out.println("{\"Error\": true ,\"Message\": \"Please Enter Valid Data+" + ex.getMessage() + "\" }");
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

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
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
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
public class Bitaddress extends HttpServlet {

       String[] arrayxpub = {"xpub6C9Yhqe5pdkoooNH7H4KqTv4dei1fmwzk6TSJgaNfWshTqz2oTZKhVWrpKHixNSiXJa6y1z6xYYKFWjmBSC1KqRwsbYhVzDgzro3hugMSGe",
        "xpub6C9Yhqe5pdkoqxEvtgVopL7rTqxtU4ug7xEzkPFcP7vc2BKkq2Pu2WZQ4aewGAPGCMz8YEsU7VfiuL1snX6aAusWvQdbTgDe5rFMH8CQvYi",
        "xpub6C9Yhqe5pdkosXgPCYXYUDQHdBYDqDFrqzsXAWWFu3nsknEEwYD1dKnHpKtTMrdDJ8Kug1PhgKHgvMexn2kQnTfRgHqWdk2yRVZyBzu4FXQ",
        "xpub6C9Yhqe5pdkovBAaCfW7nTGJQYbAD4JyxfK9FWKbNX3cdyAcK4da7UP8WVTRva2Z365C1vAqgnF3r9uCG155aLRtHLfCu3CMwBJ2tiiL1f8",
        "xpub6C9Yhqe5pdkozUJHRTTpv92FDsuoBTunDi2VHui7bNpxsnJkny3iu7NToJ9KFXFjyjkWM97DRaTVbkXugGDhoNQET71RwimF6KnNbpDVpzm",
        "xpub6C9Yhqe5pdkp26vfhLVHAr3mUf4dW1x2mi19LxmPNxgetB1xGdkaW5GkRt2tmDXkvUtaxQc89rMJ1uMGMz52h8PNeUBmz9Pf1qxCX541eFu",
        "xpub6C9Yhqe5pdkp3wrmu7LXisE8ddNcn9yYCzFMb8JLbewy7GbvTejVsJkPHJ8V3K2LRr54A236QJE4aS69rvTRqfw8kcdySsfYfsmJVQWNHcU",
        "xpub6C9Yhqe5pdkp7vY7zBL3CfS51Gz4sYru18LLpBMb2zee3DetddNJjgoh76y8D8CA7DUEt1mEGV4eGQgWytvacJxFCEiwGWkRmoZhWLTadmY",
        "xpub6C9Yhqe5pdkp8dzjLzi5GUp4CppZFudJtx7YRHXse9H75C5yzgMcTdvRv3KH3STjGA2TvUp73nZUMvJoSQSLR2umNaH4engTbeN3FP6R4Cq",
        "xpub6C9Yhqe5pdkpBqDy8i6WnmteSDpzUEdEHskrWaJ5fHRUKNpZuYonuzGrtncP157QenZMLAA5zqG6zbzh5R5Le9jyDrcwTvmbTaLGb3SPfRA",
        "xpub6C9Yhqe5pdkpE5NLmd6vwTgCuTfiNQRm1qKjPeq3KGpLkuTvytfgxmXQAFhTCXS5Hj9YpnQ7MCQRFMb1MoaDBPuwRWnN1WzQ1veZxJoTxvS"};

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter();
                Connection con = Util.getConnection();
            Statement st = con.createStatement();) {
            response.setContentType("application/json;charset=UTF-8");

            int l = 0;
           
            ResultSet rs;
            String Empty = null;
            
            HttpSession session = request.getSession();
            String url = request.getRequestURL().toString();
            URL url1 = new URL(url);
            String domain = url1.getHost();
            String username = String.valueOf(session.getAttribute("username")).trim();//"khan@gmail.com";

            if (session.getAttribute("username") != null && !String.valueOf(session.getAttribute("username")).trim().equalsIgnoreCase("")) {
                System.out.println(username);
                Balance b = new Balance();
                String coin = String.valueOf(request.getParameter("coin"));

                switch (coin) {
                    case "bitcoin": {
                        System.out.println(62);
                        String pubkey[] = GetAddress(con, st, "bitcoin", username);

                        try {
                            l = pubkey[0].length();
                        } catch (Exception e) {
                            l = 0;
                        }
                        if (l < 10) {
                            if (SetBitcoinAddress(con, st, username, domain) > 1) {
                                pubkey = GetAddress(con, st, "bitcoin", username);
                            }
                        }

                        double balance = 0.0;//Double.parseDouble(b.bitcoinbalance(pubkey[0]));
                        double balance2 = 0.0;///Double.parseDouble(b.bitcoinbalance(pubkey[2]));
                        double balance3 = 0.0;//Double.parseDouble(b.bitcoinbalance(pubkey[4]));
                        out.println("{\"Error\": false ,\"Name\":  \"Bitcoin\" "
                                + ",\"Address\": \"" + pubkey[1] + "\",\"Address2\": \"" + pubkey[3] + "\",\"Address3\": \"" + pubkey[5] + "\",\"PublicKey\": \" " + pubkey[0] + "\""
                                + ",\"PublicKey2\": \" " + pubkey[2] + "\",\"PublicKey3\": \" " + pubkey[4] + "\""
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
            Logger.getLogger(Bitaddress.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   

    public String[] GetAddress(Connection con, Statement st, String coin, String username) throws SQLException {
        System.out.println("not");
        String pubkey = "NA", bitcoin2 = "", bitcoin3 = "", pubkey3 = "", pubkey2 = "";
        String a[] = new String[6];
        a[1] = pubkey;
        a[2] = pubkey;
        a[4] = pubkey;
        a[0] = pubkey;
        a[3] = pubkey;
        a[5] = pubkey;
        String query = "select * from depositaddress where username='" + username + "'";

        System.out.println(query);

        ResultSet rs;
        rs = st.executeQuery(query);
        Bitaddress r1 = new Bitaddress();
        while (rs.next()) {

            pubkey = rs.getString("bitcoin_public_key");
            bitcoin = rs.getString("bitcoin");
            pubkey2 = rs.getString("bitcoin_public_key2");
            bitcoin2 = rs.getString("bitcoin2");
            pubkey3 = rs.getString("bitcoin_public_key3");
            bitcoin3 = rs.getString("bitcoin3");

        }
        System.out.println(pubkey);
        a[1] = bitcoin;
        a[0] = pubkey;
        a[3] = bitcoin2;
        a[2] = pubkey2;
        a[5] = bitcoin3;
        a[4] = pubkey3;
        return a;
    }

    public int SetBitcoinAddress(Connection con, Statement st, String username, String domain) throws SQLException, ProtocolException, JSONException, IOException {
        System.out.println(1235);
        String urlen = "http://" + domain + "/ReceivingCallBack?username=" + username;

        System.out.println(urlen);
        String encodeURL = URLEncoder.encode(urlen, "UTF-8");
        Wget w = new Wget();
        String u;
        String query = "";

        String aa = arrayxpub[new Random().nextInt(arrayxpub.length)];
        System.out.println(aa);
        int i = 0;
        try {
            query = "insert depositaddress set archived_status='false' ,bitcoin_public_key='" + aa + "',"
                    + " bitcoin_private_key='None',"
                    + " receiveAccount='a' ,changeAccount='a' , username='" + username + "'";
            System.out.println(query);

            st.executeUpdate(query);
        } catch (Exception e) {
            query = "update depositaddress set archived_status='false' ,bitcoin_public_key='aa',"
                    + " bitcoin_private_key='none',"
                    + " receiveAccount='a' ,changeAccount='a' where username='" + username + "'";
            System.out.println(query);
            i = st.executeUpdate(query);
        }
        u = "https://api.blockchain.info/v2/receive?xpub=" + aa + "&callback=" + encodeURL + "&key=36276ce3-16c5-471d-bcfd-ac143bfeccd2&gap_limit=20";

        System.out.println(u);
        String output1 = w.wget(u);
        System.out.println(output1);
        System.out.println(output1);
        JSONObject jsonObj1 = new JSONObject(output1);
        String address1 = jsonObj1.getString("address");

        try {
            String query1 = "update depositaddress set bitcoin='" + address1 + "' where username='" + username + "'";
            System.out.println(query1);
            i = st.executeUpdate(query1);
        } catch (Exception e) {

        }
        setNewAddress(st, username, domain);
        return i;
    }

    public String setNewAddress(Statement st, String username, String domain) {
        try {
            String urlen = "http://" + domain + "/ReceivingCallBack?username=" + username;
            System.out.println(urlen);
            String encodeURL = URLEncoder.encode(urlen, "UTF-8");
            Wget w = new Wget();

            String bitcoin_public_key2 = arrayxpub[new Random().nextInt(arrayxpub.length)];
            System.out.println(bitcoin_public_key2);
            String bitcoin_public_key3 = arrayxpub[new Random().nextInt(arrayxpub.length)];
            System.out.println(bitcoin_public_key3);
            String u = "https://api.blockchain.info/v2/receive?xpub=" + bitcoin_public_key2 + "&callback=" + encodeURL + "&key=36276ce3-16c5-471d-bcfd-ac143bfeccd2&gap_limit=20";

            String output4 = w.wget(u);
            System.out.println(output4);
            JSONObject jsonObj4 = new JSONObject(output4);
            String address2 = jsonObj4.getString("address");

            u = "https://api.blockchain.info/v2/receive?xpub=" + bitcoin_public_key3 + "&callback=" + encodeURL + "&key=36276ce3-16c5-471d-bcfd-ac143bfeccd2&gap_limit=20";
            String output5 = w.wget(u);
            System.out.println(output5);
            JSONObject jsonObj5 = new JSONObject(output5);
            String address3 = jsonObj5.getString("address");

            try {
                String query1 = "update depositaddress set  bitcoin2='" + address2 + "',  bitcoin3='" + address3 + "',bitcoin_private_key2='none',bitcoin_private_key3='none' ,bitcoin_public_key2='" + bitcoin_public_key2 + "',bitcoin_public_key3='" + bitcoin_public_key3 + "' where username='" + username + "'";
                System.out.println(query1);
                int i = st.executeUpdate(query1);
            } catch (Exception e) {

            }
        } catch (ProtocolException ex) {
            Logger.getLogger(Bitaddress.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Bitaddress.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Bitaddress.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Bitaddress.class.getName()).log(Level.SEVERE, null, ex);
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
        //  processRequest(request, response);
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
            

            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String json = "";
            if (br != null) {
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
            System.out.println(amount + "amount");
            String description = String.valueOf(jsonObj.getString("description"));
            String address_from = "3";//String.valueOf(jsonObj.getString("Address")).trim();
            String coin = request.getParameter("coin");

            if (!block_status.equals("Blocked")) {

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
                    long fees = 3700;//String.valueOf(request.getParameter("fees"));
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
                                        String u = "http://127.0.0.1:3000/merchant/a1c5bcbb-bbcf-4b54-8acd-811e9de24aa1/payment?password=HdW@lletNe@612w&to=" + address_to + "&amount=" + amount_in_satoshi + "&fee=" + fees + "&from=" + address_from + "&api_code=36276ce3-16c5-471d-bcfd-ac143bfeccd2";
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
    public String username, bitaddress, email, bchaddress, ethaddress, bitcoin, ethseed;
}

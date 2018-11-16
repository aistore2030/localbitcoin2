/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AngularApi;

import com.login.Util;
import com.system.Wget;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;
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
import org.json.JSONObject;

/**
 *
 * @author susheel
 */
public class AddBitaddress extends HttpServlet {

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

            String query = "" ,public_key="",api_code="" ,gap_limit="";

           
            String url = request.getRequestURL().toString();
            URL url1 = new URL(url);
            String domain = url1.getHost();
           
            query = "insert bitcoinaddress set archived_status='false' ";
            System.out.println(query);

            st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            int insertID = 0;
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                insertID = rs.getInt(1);
            }
            String urlen = "https://" + domain + "/ReceivingCallBack?address_id=" + insertID;

            System.out.println(urlen);
            String encodeURL = URLEncoder.encode(urlen, "UTF-8");
            Wget w = new Wget();
            String u;
             String q = "select * from wallet_setting where username='admin'";
                 rs = st.executeQuery(q);
               
                if (rs.next()) {
                    
                    public_key = rs.getString("public_key");
                    api_code = rs.getString("api_code");
                    gap_limit = rs.getString("gap_limit");
                    
           
                }
            String aa = public_key;
            System.out.println(aa);
            int i = 0;

            u = "https://api.blockchain.info/v2/receive?xpub=" + aa + "&callback=" + encodeURL + "&key="+api_code+"&gap_limit="+gap_limit;

            System.out.println(u+"uuuuu");
            String output1 = w.wget(u);
            System.out.println(output1);
            System.out.println(output1);
            JSONObject jsonObj1 = new JSONObject(output1);
            String address1 = jsonObj1.getString("address");

            try {
                query = "update bitcoinaddress set bitcoin_public_key='" + aa + "',"
                        + " bitcoin_private_key='None',"
                        + "  bit_address='" + address1 + "' ,url='"+u+"' where id='"+insertID+"'";
                System.out.println(query);

                st.executeUpdate(query);
                if(i>0){
                    out.println(query);
                }
            } catch (SQLException e) {

                System.out.println(e.getMessage());

            }

            //setNewAddress(st, username, domain);
            //System.out.println(i);
        } catch (Exception ex) {
            Logger.getLogger(AddBitaddress.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String setNewAddress(Statement st, String username, String domain) {
  /*      try {
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
                String query1 = "update bitcoinaddress set  bitcoin2='" + address2 + "',  bitcoin3='" + address3 + "',bitcoin_private_key2='none',bitcoin_private_key3='none' ,bitcoin_public_key2='" + bitcoin_public_key2 + "',bitcoin_public_key3='" + bitcoin_public_key3 + "' where username='" + username + "'";
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
        */
        
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

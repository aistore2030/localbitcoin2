/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AngularApi;

import com.google.common.escape.Escaper;
import com.google.common.io.BaseEncoding;
import com.google.common.net.UrlEscapers;
import com.google.common.primitives.Ints;
import com.login.Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
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
public class SetGoogleAuth extends HttpServlet {

   private static final int SCRET_BYTE = 10;
    private static final int SCRATCH_CODES = 5;
    private static final int BYTES_PER_SCRATCH_CODE = 4;

    private static final int TIME_PRECISION = 10;
    private static final String CRYPTO_ALGO = "HmacSHA1";

    private static final String URL_FORMAT = "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl="
            + "otpauth://totp/"
            + "%s@%s%%3Fsecret%%3D%s";

    public static String generateSecretKey() {
        // Allocating the buffer
        byte[] buffer = new byte[SCRET_BYTE + SCRATCH_CODES * BYTES_PER_SCRATCH_CODE];

        // Filling the buffer with random numbers.
        // Notice: you want to reuse the same random generator
        // while generating larger random number sequences.
        new SecureRandom().nextBytes(buffer);

        // Getting the key and converting it to Base32
        byte[] secretKey = Arrays.copyOf(buffer, SCRET_BYTE);
        return BaseEncoding.base32().encode(secretKey);
    }

    public static String getQRBarcodeURL(String user, String host, String secret) {
        Escaper urlEscaper = UrlEscapers.urlFragmentEscaper();
        return String.format(URL_FORMAT, urlEscaper.escape(user), urlEscaper.escape(host), secret);
    }

    public static boolean checkPassword(String secretKey, String userInput)
            throws NoSuchAlgorithmException, InvalidKeyException {
        Integer code = Ints.tryParse(userInput);

        System.out.println("94--" + code);

        if (code == null) {
            //code is not an integer
            return false;
        }

        long currentTime = Calendar.getInstance().getTimeInMillis() / TimeUnit.SECONDS.toMillis(30);
        return check_code(secretKey, code, currentTime);
    }

    private static boolean check_code(String secret, long code, long t)
            throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] decodedKey = BaseEncoding.base32().decode(secret);

        // Window is used to check codes generated in the near past.
        // You can use this value to tune how far you're willing to go.
        int window = TIME_PRECISION;
        for (int i = -window; i <= window; ++i) {
            long hash = verify_code(decodedKey, t + i);

            if (hash == code) {
                return true;
            }
        }

        // The validation code is invalid.
        return false;
    }

    private static int verify_code(byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] data = new byte[8];
        long value = t;
        for (int i = 8; i-- > 0; value >>>= 8) {
            data[i] = (byte) value;
        }

        SecretKeySpec signKey = new SecretKeySpec(key, CRYPTO_ALGO);
        Mac mac = Mac.getInstance(CRYPTO_ALGO);
        mac.init(signKey);
        byte[] hash = mac.doFinal(data);

        int offset = hash[20 - 1] & 0xF;

        // We're using a long because Java hasn't got unsigned int.
        long truncatedHash = 0;
        for (int i = 0; i < 4; ++i) {
            truncatedHash <<= 8;
            // We are dealing with signed bytes:
            // we just keep the first byte.
            truncatedHash |= (hash[offset + i] & 0xFF);
        }

        truncatedHash &= 0x7FFF_FFFF;
        truncatedHash %= 1_000_000;

        return (int) truncatedHash;
    }

    protected void setgoogleAuth(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("setgoogleAuth");
       try (PrintWriter out = response.getWriter();
                Connection con = Util.getConnection();
            Statement st = con.createStatement();) {
            response.setContentType("application/json;charset=UTF-8");
            
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String json = "";
            if (br != null) {
                json = br.readLine();
                System.out.println(11);
            }
            System.out.println(22);
            JSONObject jsonObj = new JSONObject(json);
            // String google_auth_status = request.getParameter("google_auth_status");
            String case1 = jsonObj.getString("case");
            System.out.println(json);
            System.out.println(66);
            HttpSession session = request.getSession();
            String username = String.valueOf(session.getAttribute("username"));
            if (case1.equals("Enable")) {
                String secret = jsonObj.getString("secret");
                String otp = jsonObj.getString("otp");
                System.out.println(secret + "  " + otp);
                boolean b = checkPassword(secret, otp);
                System.out.println(b);
                if (b == true) {
                    System.out.println(77);
                    PreparedStatement stmt = con.prepareStatement("update register set secret_key=?  , google_auth_status=?    where username=?  ");
                    System.out.println(stmt);
                    stmt.setString(1, secret);
                    stmt.setString(2, "Enable");
                    stmt.setString(3, username);
                    int i = stmt.executeUpdate();
                    if (i > 0) {
                        System.out.println(33);
                        out.println("{\"Error\":false,\"Message\": \"Successfully Updated.\" }");

                    } else {
                        out.println("{\"Error\": true ,\"Message\": \"Your Details  are Invalid!!\" }");
                    }
                } else {
                    out.println("{\"Error\": true ,\"Message\": \"Your Otp  are Invalid!!\" }");
                }
            } else if (case1.equals("Disable")) {
                System.out.println("mala1111" + 333);
                String s = "update register set google_auth_status='Disable'    where username='" + username + "'  ";
                System.out.println(s);
                int i = st.executeUpdate(s);
                if (i > 0) {
                    System.out.println("mala" + 333);
                    out.println("{\"Error\":false,\"Message\": \"Disable\" }");
                    //  notification n = new notification();
                    //        n.SaveNotification(parent, username, "Your google_auth_status is 'Disable' ", "http://localhost:16226/adminpapafast/admin.jsp#!/alluser");

                } else {
                    out.println("{\"Error\": true ,\"Message\": \" Error!!\" }");
                }

            } else {
                out.println("{\"Error\": true ,\"Message\": \" EEError!!\" }");
            }
            //out.println("</br><h3>"+secret+"<h3></div>");
        } catch (Exception ex) {
            Logger.getLogger(SetGoogleAuth.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    protected void getgoogleAuth(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter();
                Connection con = Util.getConnection();
            Statement st = con.createStatement();) {
            response.setContentType("application/json;charset=UTF-8");

           
            ResultSet rs;
            
            HttpSession session = request.getSession();
            String username = String.valueOf(session.getAttribute("username"));
            byte[] buffer = new byte[SCRET_BYTE + SCRATCH_CODES * BYTES_PER_SCRATCH_CODE];
            new SecureRandom().nextBytes(buffer);
            byte[] secretKey = Arrays.copyOf(buffer, SCRET_BYTE);
            SetGoogleAuth sa = new SetGoogleAuth();
            String secret = sa.generateSecretKey();
            Escaper urlEscaper = UrlEscapers.urlFragmentEscaper();
            String url = sa.getQRBarcodeURL("localbitcoins@global", "localbitcoins@global", secret);

            out.println("{\"Error\":false,\"key\": \"" + BaseEncoding.base32().encode(secretKey) + "\",\"url\": \"" + url + "\"}");

        } catch (Exception ex) {
            Logger.getLogger(SetGoogleAuth.class.getName()).log(Level.SEVERE, null, ex);
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
        //getgoogleAuth(request, response);
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
        setgoogleAuth(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setgoogleAuth(request, response);
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

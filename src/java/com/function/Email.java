/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javax.servlet.ServletException;

/**
 *
 * @author susheel
 */
public class Email {
public String EmailFooter() {

        String footer = "<br /><br /><br />Regards <br /> Thanking you <br /><br />";
        return footer;

    }
    public boolean sendemail(String message, String email, String subject)
            throws ServletException, IOException {
        System.out.println("sendemail");
        String encoding = "UTF-8";

        System.out.println(message);
        System.out.println(49);
        String data = "apikey=" + URLEncoder.encode("a515c9ec-0156-42d4-a4aa-244400db4a22", encoding);
        data += "&from=" + URLEncoder.encode("sakshamappinternational@gmail.com", encoding);

        data += "&fromName=" + URLEncoder.encode("Trade App", encoding);

        data += "&subject=" + URLEncoder.encode(subject, encoding);
        data += "&bodyHtml=" + URLEncoder.encode(message, encoding);

        data += "&to=" + URLEncoder.encode("" + email + "", encoding);
        data += "&cc=" + URLEncoder.encode("susheel3010@gmail.com", encoding);
        data += "&isTransactional=" + URLEncoder.encode("true", encoding);

        URL url = new URL("https://api.elasticemail.com/v2/email/send");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        BufferedReader rd;
        String result;
        try (OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream())) {
            wr.write(data);
            wr.flush();
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            result = rd.readLine();

            System.out.println(result);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public boolean sendemailf(String[] message_email) throws UnsupportedEncodingException, MalformedURLException, IOException {
        String encoding = "UTF-8";

        String data = "apikey=" + URLEncoder.encode("a515c9ec-0156-42d4-a4aa-244400db4a22", encoding);
        data += "&from=" + URLEncoder.encode("sakshamappinternational@gmail.com", encoding);
       
        data += "&fromName=" + URLEncoder.encode(message_email[0], encoding);
      
        data += "&subject=" + URLEncoder.encode(message_email[1], encoding);
       
        String header = "Welcome To Localbitcoins.club Tradeing App <br /><br /><br /><br />";
        data += "&bodyHtml=" + URLEncoder.encode(header + message_email[2] + EmailFooter(), encoding);
       
        data += "&to=" + URLEncoder.encode("" + message_email[3] + "", encoding);
        
        data += "&isTransactional=" + URLEncoder.encode("true", encoding);

        URL url = new URL("https://api.elasticemail.com/v2/email/send");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        BufferedReader rd;
        String result;
        try (OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream())) {
            wr.write(data);
            wr.flush();
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            result = rd.readLine();

            System.out.println(result);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

}

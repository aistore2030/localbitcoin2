/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system;



import com.login.Util;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author panel2
 */
public class Logo {

    public String logo(String url) {
        try {
            Connection con = null;
            Statement st = null;
            con = Util.getConnection();
            st = con.createStatement();

            String a = null;
            //  String url = request.getRequestURL().toString();

            URL url1 = new URL(url);
            String host = url1.getHost();

            String query = "select * from logo  ";
            System.out.println(query);
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                a = rs.getString("logo_url");
                System.out.println("success");
                System.out.println(a);
                return a;
            } else {
                System.out.println("error");

            }

            return null;
        } catch (Exception ex) {
            Logger.getLogger(Logo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
}

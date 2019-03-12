/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AngularApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.login.Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
public class SetMargin extends HttpServlet {

    private String margin;
    private String bonus;

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
          try (PrintWriter out = response.getWriter();
                Connection con = Util.getConnection();
                Statement st = con.createStatement();) {
            response.setContentType("text/html;charset=UTF-8");
            
            String margin = null;
           

            String query = "select margin ,bonus from register where username='irsantana@msn.com'";
            System.out.println(query);
            ResultSet rs = st.executeQuery(query);
            SetMargin a1 = new SetMargin();
            while (rs.next()) {
                a1.margin = rs.getString("margin");
                a1.bonus = rs.getString("bonus");
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
        } catch (Exception ex) {
            Logger.getLogger(SetMargin.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SetMargin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {

            Connection con = null;
            Statement st = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String json = "";
            if (br != null) {
                json = br.readLine();
            }
            System.out.println(json);
            JSONObject jsonObj = new JSONObject(json);
            
            try{
             margin = jsonObj.getString("margin");
            }
            catch(JSONException e){
                margin="" ;
            }
            try{
             bonus = jsonObj.getString("bonus");
            }
            catch(Exception e){
             bonus="";
            }
            System.out.println(margin);

            HttpSession session = request.getSession();

            int r = Integer.parseInt(String.valueOf(session.getAttribute("roll")));

            con = Util.getConnection();
            st = con.createStatement();
            if (r == 10) {
                String query = "update register set margin='" + margin + "' ,bonus='" + bonus + "' where username='irsantana@msn.com'";
                System.out.println(query);
                int i = st.executeUpdate(query);
                if (i > 0) {
                    out.println("{\"Error\":false,\"Message\": \"Your Setting Successfully!!\"  }");

                } else {
                    out.println("{\"Error\": true ,\"Message\": \"Error\"  }");

                }
            }
        } catch (Exception ex) {
            out.println("{\"Error\": true ,\"Message\": \"Error\"  }");

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

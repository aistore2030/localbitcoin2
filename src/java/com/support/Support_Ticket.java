/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.support;

import AngularApi.Notification;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.login.Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
public class Support_Ticket extends HttpServlet {

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
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String cs = "";

        try  {
            
            cs = request.getParameter("case");
            System.out.println(cs + "57");
            if (cs != null && !cs.trim().equalsIgnoreCase("")) {
            } else {
                cs = "bbbbb";
            }
        } catch (Exception e) {
            cs = "";
            System.out.println(70);
        }
        try(Connection con = Util.getConnection();
            Statement st = con.createStatement();) {

           
            ResultSet rs;
            int i;
            int h;
            String user;
            HttpSession session = request.getSession();

            String username = ((String) session.getAttribute("username")).trim();
            System.out.println(username);
            String roll = ((String) session.getAttribute("roll")).trim();

            i = Integer.parseInt(roll);
            System.out.println(i + "adminid");
            String id = null;


            PreparedStatement stmt;
            String q = "";

            System.out.println("fghhjkszdfg");
            if (cs.equals("newticket")) {
                q = "select * from support_ticket order by id desc limit 4";
                System.out.println(q);
            } else if (cs.equals("closedticket")) {
                q = "select * from support_ticket where status='Disable'";
                System.out.println(q);
            } 
            else {
                if (i > 9) {
                    q = "select * from  support_ticket  order by update_time desc limit 0, 50 ";
                } else {

                    q = "select * from  support_ticket where username = '" + username + "'   order by update_time desc limit 0, 30 ";
                }
            }

            stmt = con.prepareStatement(q);
            System.out.println("sdfhgnghjg");
            System.out.println(stmt);
            //stmt = con.prepareStatement(q);
            rs = stmt.executeQuery();
            ArrayList<Support_Ticket> a;
            a = new ArrayList<>();

            while (rs.next()) {
                Support_Ticket a1 = new Support_Ticket();
                
                a1.id = rs.getString("id");
                a1.username = rs.getString("username");

                a1.subject = rs.getString("subject");
                a1.message = rs.getString("message");
                a1.status = rs.getString("status");
                

                a.add(a1);
            }
            Gson gson = new GsonBuilder().create();
            String jsonArray = gson.toJson(a);
            System.out.println(jsonArray);
            out.write(jsonArray);
            

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void SaveMessage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
       
        HttpSession session = request.getSession();
        
        ResultSet rs;


        PrintWriter out = response.getWriter();

        String json = "";

         try (Connection con = Util.getConnection();
            Statement st = con.createStatement();) {
            
            
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
           
            username = String.valueOf(session.getAttribute("username"));
            String Mobile = String.valueOf(session.getAttribute("mobile"));
            System.out.println("username--" + username);

            if (br != null) {
                json = br.readLine();
            }
            System.out.println(json);
            JSONObject jsonObj = new JSONObject(json);
            try {
                subject = jsonObj.getString("subject");
            } catch (JSONException e) {
                subject = null;
                String message = e.getMessage();
            }
            System.out.println(subject);
            try {
                message = jsonObj.getString("message");
            } catch (JSONException e) {
                message = null;
                String message = e.getMessage();
            }
            try {
                status = jsonObj.getString("status");
            } catch (JSONException e) {
                status = null;
                String message = e.getMessage();
            }
            System.out.println(message);
            try {
                category = jsonObj.getString("category");
            } catch (JSONException e) {
                category = null;
                String message = e.getMessage();
            }
            try {
                priority = jsonObj.getString("priority");
            } catch (JSONException e) {
                priority = null;
                String message = e.getMessage();
            }

            String query = "insert into support_ticket(username,subject,message,status,priority,category)values ('" + username + "', "
                    + "'" + subject + "','" + message + "','" + status + "','" + priority + "','" + category + "') ";
            int id = 0;
            System.out.print(query);
            int i = st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            System.out.print(query);
            rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

            System.out.print("hghdf");

            if (i > 0) {
                String msgs = "Support Ticket Created-\nYour Ticket details\n Username: " + username + " Ticket_id: " + id + " ";
                Notification n = new Notification();
                n.SaveNotification(Mobile, username, msgs, "TicketCreated");

                out.println("{\"Error\": \"False\" ,\"Message\": \" check your email\" }");
            }

        } catch (Exception ex) {
            message = ex.getMessage();

            ex.printStackTrace();

            out.println("{\"Erorr\":  true ,\"Message\": \"Failed '" + message + "'\"}");
            //out.println("Some technical errors in the entered values :'" + message + "'");

        }
    }

    protected void ReplyRequest(HttpServletRequest request, HttpServletResponse response, String json)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        Connection con = null;
        Statement st = null;
        PrintWriter out = response.getWriter();

        try {

            
            JSONObject jsonObj = new JSONObject(json);
            
            con = Util.getConnection();
            st = con.createStatement();
            System.out.println(json + 22);
            
            String q = "";
            HttpSession session = request.getSession();
            
            String username = String.valueOf(session.getAttribute("username")).trim();
            String mobile = String.valueOf(session.getAttribute("mobile")).trim();
            System.out.println(username);
            String roll = String.valueOf(session.getAttribute("roll")).trim();
            System.out.println(roll);

                id = jsonObj.getString("id");
                res = jsonObj.getString("response");
                System.out.println(id+res);
           

            String query = "insert into ticket_response(username,ticket_id,msg_response,roll)values ('" + username + "', "
                    + "'" + id + "','" + res + "','" + roll + "') ";

            System.out.println(query);
            int i = st.executeUpdate(query);
            if (i > 0) {

                out.println("{\"Error\": \"False\" ,\"Message\": \" check your email\" }");
                String msgs = "Reply is sent  successfully-\nYour Ticket details\n Username: " + username + " Ticket_id: " + id + " ";
                Notification n = new Notification();
                n.SaveNotification(mobile, username, msgs, "ReplySent");

            } else {
                out.println("{\"Error\": true,\"Message\": \"failed!\" }");

            }

        } catch (Exception e) {
            out.println("{\"Error\": true,\"Message\": \""+e.getMessage()+"\" }");
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
        SaveMessage(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println(451);
            response.setContentType("application/json;charset=UTF-8");
            Connection con = null;
            PrintWriter out = response.getWriter();
            Statement st = null;
            HttpSession session = request.getSession();
            
            String mobile = String.valueOf(session.getAttribute("mobile"));
            String username = String.valueOf(session.getAttribute("username"));
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String json = "";
            if (br != null) {
                json = br.readLine();
            }

            JSONObject jsonObj = new JSONObject(json);
            System.out.println(json + 49);
            con = Util.getConnection();
            st = con.createStatement();
            System.out.println(467);
            String id = jsonObj.getString("id");
            System.out.println(id);
            String case1 = jsonObj.getString("case");
            System.out.println(case1);
            if (case1.equals("reply")) {
                System.out.println(100);
                ReplyRequest(request, response, json);
                System.out.println(388);
            } else if (case1.equals("Enable")) {
                con = Util.getConnection();
                st = con.createStatement();
                System.out.println("359");
                PreparedStatement stmt = con.prepareStatement("update support_ticket set status=?   where id=? ");

                stmt.setString(1, case1);
                stmt.setString(2, id);
                System.out.println(stmt);
                int i = stmt.executeUpdate();

                if (i > 0) {
                    out.println("{\"Error\": false ,\"Message\": \"success\" }");
                    Notification n = new Notification();
                    String msgs = "Updated Successfully ";

                    n.SaveNotification(mobile, username, "For Enable", msgs);
                } else {
                    System.out.println("{\"Error\":true ,\"Message\": \"Some Error\" }");
                }
            } else {

                con = Util.getConnection();
                st = con.createStatement();

                PreparedStatement stmt = con.prepareStatement("update support_ticket set status=?   where id=?");

                stmt.setString(1, case1);
                stmt.setString(2, id);
                System.out.println(stmt);
                int i = stmt.executeUpdate();

                if (i > 0) {
                    out.println("{\"Error\":false ,\"Message\": \"success\" }");

                    Notification n = new Notification();
                    String msgs = "Update Successfully ";
                    // n.SaveNotification(parent, username, " " + username + " status is " + tempstatus + "", "http://localhost:16226/adminpapafast/admin.jsp#!/alluser");
                    n.SaveNotification(mobile, username, "For Disable", msgs);
                } else {
                    System.out.println("{\"Error\": true ,\"Message\": \"Some Error\" }");
                }

            }
        } catch (JSONException ex) {
            Logger.getLogger(Support_Ticket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Support_Ticket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Support_Ticket.class.getName()).log(Level.SEVERE, null, ex);
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
    public String id, username, subject, domain_name, status, category, roll;
    public String message, res, name, domain_id, priority, email;
}

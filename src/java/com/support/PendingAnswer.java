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
public class PendingAnswer extends HttpServlet {

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

        ResultSet rs;
        try (
                Connection con = Util.getConnection();
                Statement st = con.createStatement();) {

            HttpSession session = request.getSession();
            String roll = ((String) session.getAttribute("roll")).trim();

            String id = request.getParameter("id");

            System.out.println(id);
            PreparedStatement stmt = con.prepareStatement("select * from ticket_response where message_status='unread'  order by id desc limit 20");

            System.out.println(stmt);
            rs = stmt.executeQuery();
            System.out.println(227);
            System.out.println(rs);
            ArrayList<PendingAnswer> a;
            a = new ArrayList<>();

            while (rs.next()) {
                PendingAnswer a1 = new PendingAnswer();
                System.out.println(stmt);
                //    a1.balance = rs.getString(1);
                a1.id = rs.getString("id");
                a1.username = rs.getString("username");
                System.out.println(a1.username);
                a1.ticket_id = rs.getString("ticket_id");
                System.out.println(a1.ticket_id);
                a1.msg_response = rs.getString("msg_response");
                System.out.println(a1.msg_response);
                a1.message_status = rs.getString("message_status");
                a1.roll = rs.getString("roll");
                a.add(a1);
            }

            Gson gson = new GsonBuilder().create();

            System.out.println(gson);
            String jsonArray = gson.toJson(a);
            System.out.println(jsonArray);
            out.write(jsonArray);

            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PendingAnswer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception e) {
                out.println(e);
            }
        } catch (Exception e) {
            out.println(e);
        }
    }

    protected void ReplyRequest(HttpServletRequest request, HttpServletResponse response, String json)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        PrintWriter out = response.getWriter();

        try (
                Connection con = Util.getConnection();
                Statement st = con.createStatement();) {

            System.out.println(json + 22);
            JSONObject jsonObj = new JSONObject(json);

            String q = "";
            HttpSession session = request.getSession();

            String username = String.valueOf(session.getAttribute("username")).trim();
            String mobile = String.valueOf(session.getAttribute("mobile")).trim();
            System.out.println(username);
            String roll = String.valueOf(session.getAttribute("roll")).trim();
            System.out.println(roll);

            id = jsonObj.getString("id");
            res = jsonObj.getString("response");
            System.out.println(res);

            user1 = jsonObj.getString("username");
            System.out.println(user1);

            String query = "insert into ticket_response(username,ticket_id,msg_response,roll)values ('" + username + "', "
                    + "'" + id + "','" + res + "','" + roll + "') ";

            System.out.println(query);
            int i = st.executeUpdate(query);
            if (i > 0) {
                String q2 = "";
                q2 = "update ticket_response set message_status='read' where id='" + id + "'  and username='" + user1 + "'";
                int i2 = st.executeUpdate(q2);

                System.out.println(q2);
                System.out.println(i2 + "i22222222222222222222");
                if (i2 > 0) {
                    out.println("{\"Error\": \"False\" ,\"Message\": \" check your email\" }");
                    String msgs = "Reply is sent  successfully-\nYour Ticket details\n Username: " + username + " Ticket_id: " + id + " ";
                    Notification n = new Notification();
                    n.SaveNotification(mobile, username, msgs, "ReplySent");

                } else {
                    out.println("{\"Error\": true,\"Message\": \"failed!\" }");

                }
            } else {
                out.println("{\"Error\": true,\"Message\": \"failed!\" }");

            }

        } catch (Exception e) {
            e.printStackTrace();
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
        processRequest(request, response);
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

            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String json = "";
            if (br != null) {
                json = br.readLine();
            }

            JSONObject jsonObj = new JSONObject(json);
            System.out.println(json + 49);
            con = Util.getConnection();
            st = con.createStatement();

            String id = jsonObj.getString("id");
            System.out.println(id);
            String case1 = jsonObj.getString("case");
            System.out.println(case1);
            if (case1.equals("reply")) {
                System.out.println(100);
                ReplyRequest(request, response, json);
                System.out.println(388);
            } else {
                System.out.println("{\"Error\": true ,\"Message\": \"Some Error\" }");
            }
        } catch (JSONException ex) {
            Logger.getLogger(PendingAnswer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PendingAnswer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PendingAnswer.class.getName()).log(Level.SEVERE, null, ex);
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
    public String id, username, ticket_id, msg_response, message_status, roll, res, user1;
}

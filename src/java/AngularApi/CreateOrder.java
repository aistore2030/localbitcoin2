/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AngularApi;

import com.system.Wget;
import com.login.Util;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CreateOrder extends HttpServlet {

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
            throws ServletException, IOException, Exception {
       response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        int insertID = 0;
        String q = "";
        String url = request.getRequestURL().toString();
        URL url1 = new URL(url);
        String domain = url1.getHost();
        
        Wget w = new Wget();
        try (PrintWriter out = response.getWriter();
                Connection con = Util.getConnection();
            Statement st = con.createStatement();) {
            HttpSession session = request.getSession();
            String username = String.valueOf(session.getAttribute("username")).trim();
            //Payment p = new Payment();

            String amount = String.valueOf(request.getParameter("amount"));
            String fees = String.valueOf(request.getParameter("fees"));
            String description = "deposit of amount" + amount + "in acount of " + username;
            String btcusd = w.wget("https://api.bitfinex.com/v1/pubticker/btcusd");
            System.out.println(btcusd);
            JSONObject jsonObj = new JSONObject(btcusd);
            Double bitvalue = Double.parseDouble(jsonObj.getString("ask"));
            Double am = Double.parseDouble(amount) / bitvalue;
            
            if (username.equals("irsantana@msn.com")) {
                q = "insert into transactions(coin,username,description,fees,cr,dr,trstatus ,transaction_type,domain) values "
                        + "('bitcoin','" + username + "','" + description + "','" + fees + "','" + am + "','0','Pending','Deposit','" + domain + "')";
                System.out.println(q);
            }
            int i = st.executeUpdate(q, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                insertID = rs.getInt(1);

                session.setAttribute("insertID", insertID);
            }
            if (i > 0) {
                out.println("{\"Error\":false,\"Message\": \"The transaction  Successfully!!\",\"id\": \"" + insertID + "\" }");
            } else {
                out.println("{\"Error\": true ,\"Message\": \"error!!\",\"id\": \"a\"}");
            }
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
            Logger.getLogger(CreateOrder.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CreateOrder.class.getName()).log(Level.SEVERE, null, ex);
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

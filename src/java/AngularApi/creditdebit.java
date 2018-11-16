/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AngularApi;

import com.function.Balance;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.login.Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
public class creditdebit extends HttpServlet {

    private String party;

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        creditdebit a1 = new creditdebit();
        a1.party = username;
        Gson gson = new GsonBuilder().create();
        String jsonArray = gson.toJson(a1);
        //out.println(messages); 
        out.write(jsonArray);
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
        response.setContentType("application/json;charset=UTF-8");
        
        
        PrintWriter out = response.getWriter();
        System.out.println("InsertDebitCredit");
        String party = null;
        String amount = null, coin;
        String url = request.getRequestURL().toString();
        URL url1 = new URL(url);
        String domain = url1.getHost();
        System.out.println(domain);
        HttpSession session = request.getSession();
        String username = String.valueOf(session.getAttribute("username")).trim();
        System.out.println(username);
        ResultSet rs;
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if (br != null) {
            json = br.readLine();
        }
      
          try (
                Connection con = Util.getConnection();
                Statement st = con.createStatement();)
          {

            JSONObject jsonObj = new JSONObject(json);
            String cse = jsonObj.getString("cse");
            System.out.println(json);

            party = jsonObj.getString("party");
            String description = null;
            try {
                description = jsonObj.getString("description");
            } catch (JSONException e) {
                description = null;
            }
            System.out.println(119);
            try {
                amount = jsonObj.getString("cr");
                System.out.println(122);
            } catch (JSONException e) {
                amount = Integer.toString(jsonObj.getInt("cr"));
                System.out.println(125);
            }
            try {
                coin = jsonObj.getString("coin");
                System.out.println(122);
            } catch (JSONException e) {
                coin = Integer.toString(jsonObj.getInt("coin"));
                System.out.println(125);
            }
            Balance b = new Balance();
            
            if (username.equals("admin")) {
                if (cse.equals("credit")) {
//check if parent have enought balance or not
                    System.out.println(144);
                    Balance b1 = b.getBalanceCoinBased(username, domain, coin);
                    System.out.println(151);
                    Double bal = 0.0;
                    System.out.println(b1.BitBal);
                    try {
                        if (coin.equals("bitcoin")) {
                            bal = Double.parseDouble(b1.BitBal);
                        } else {
                            bal = 0.0;
                        }
                    } catch (Exception e) {
                        System.out.print(e.getMessage());
                    }
                    System.out.println(154);
                    System.out.println(Double.parseDouble(amount) + "" + 154 + "" + bal);
                    if (Double.parseDouble(amount) <= bal) {

                        /////////////////////////
                        System.out.println(167);
                        PreparedStatement stmt = con.prepareStatement("insert into transactions  set trstatus=?, dr=? , cr=? ,coin=?,username=?,description=?,domain=?");
                        stmt.setString(1, "Success");
                        stmt.setString(2, "0");  //dr 
                        stmt.setString(3, amount);  //cr 
                        stmt.setString(4, coin);
                        stmt.setString(5, party);  //account of username 
                        stmt.setString(6, description);
                        stmt.setString(7, domain);
                        System.out.println(stmt);
                        System.out.println(105);

                        int i3 = stmt.executeUpdate();
                        PreparedStatement stmt2 = con.prepareStatement("insert into transactions  set trstatus=?, dr=? , cr=? ,coin=?,username=?,description=?,domain=?");
                        stmt2.setString(1, "Success");
                        stmt2.setString(2, amount);  //dr 
                        stmt2.setString(3, "0");  //cr 
                        stmt2.setString(4, coin);
                        stmt2.setString(5, username);  //account of username 
                        stmt2.setString(6, description);
                        stmt2.setString(7, domain);
                        System.out.println(stmt2);

                        int i4 = stmt2.executeUpdate();
                        if (i4 > 0) {
                            System.out.println(106);
                            out.println("{\"Error\":false,\"Message\": \"The transaction  Successfully!!\" }");
                            request.setAttribute("msg", "The transaction  Successfully!!");
                            System.out.println("{\"Error\":false,\"Message\": \"success\" }");
                        } else {
                            out.println("{\"Error\": true ,\"Message\": \"The transaction is Invalid!!\" }");
                            request.setAttribute("msg", "The transaction is Invalid!!");
                            System.out.println("{\"Error\": true ,\"Message\": \"The transaction is Invalid!!\" }");
                            System.out.println("The transaction is Invalid!!");
                        }
                    } else {
                        request.setAttribute("msg", "Low Balance");
                        out.println("{\"Error\": true ,\"Message\": \"Low Balance\" }");
                    }
                } else {
                    System.out.println(206);
                    Balance b1 = b.getBalanceCoinBased(party, domain, coin);
                    Double bal;

                    if (coin.equals("bitcoin")) {
                        bal = Double.parseDouble(b1.BitBal);
                    } else {
                        bal = 0.0;
                    }

                    // Double bal = b.getBalance(party, domain);
                    if (Double.parseDouble(amount) <= bal) {
                        // case of debit to party account
                        PreparedStatement stmt = con.prepareStatement("insert into transactions  set trstatus=?, dr=? , cr=? ,coin=? ,username=? ,description=?,domain=? ");
                        stmt.setString(1, "Success");
                        stmt.setString(2, amount);  //dr 
                        stmt.setString(3, "0");  //cr 
                        stmt.setString(4, coin);
                        stmt.setString(5, party);  //cd ac
                        //account of username 
                        stmt.setString(6, description);
                        stmt.setString(7, domain);

                        int i3 = stmt.executeUpdate();

                        PreparedStatement stmt2 = con.prepareStatement("insert into transactions  set trstatus=?, dr=? , cr=? ,coin=? ,username=?,description=? ,domain=? ");
                        stmt2.setString(1, "Success");
                        stmt2.setString(2, "0");  //dr 
                        stmt2.setString(3, amount);  //cr 
                        stmt2.setString(4, coin);
                        stmt2.setString(5, username);  //cd ac
                        //account of username 
                        stmt2.setString(6, description);
                        stmt2.setString(7, domain);

                        int i4 = stmt2.executeUpdate();
                        if (i4 > 0) {
                            out.println("{\"Error\":false,\"Message\": \"The transaction is Successfully!!\" }");
                        } else {
                            out.println("{\"Error\": true ,\"Message\": \"The transaction is Invalid!!\" }");
                        }
                    } else {
                        out.println("{\"Error\": true ,\"Message\": \"Low Balance!!\" }");
                        request.setAttribute("msg", "The transaction is Invalid!!");
                    }
                }
            } else {
                out.println("{\"Error\": true ,\"Message\": \"Invalid User!!\" }");
            }
        } catch (Exception ex) {
            out.println("{\"Error\": true ,\"Message\": \""+ex.getMessage()+"\" }");
            //Logger.getLogger(creditdebit.class.getName()).log(Level.SEVERE, null, ex);
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

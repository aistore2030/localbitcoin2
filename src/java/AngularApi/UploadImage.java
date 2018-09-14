/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AngularApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oreilly.servlet.MultipartRequest;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author susheel
 */
public class UploadImage extends HttpServlet {

    private String username;
    private String document;
    private String id;
    private boolean Error;
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
        try {
            int i = 0;
            HttpSession session = request.getSession();
            String username = String.valueOf(session.getAttribute("username"));
            String id = request.getParameter("id");

            i = Integer.parseInt(id);

            String directories = "/opt/tomcat/webapps/ROOT/upload/" + username + "/" + id;
            //String directories = "C:\\Users\\susheel\\Documents\\NetBeansProjects\\HDwallet\\web\\upload\\" + username + "\\" + id;
            File file = new File(directories);
            boolean result = file.mkdirs();
            System.out.println("Status = " + result);
            System.out.println("Status = " + file);
            MultipartRequest m = new MultipartRequest(request, "/opt/tomcat/webapps/ROOT/upload/" + username + "/" + id);

            //MultipartRequest m = new MultipartRequest(request, "C:\\Users\\susheel\\Documents\\NetBeansProjects\\HDwallet\\web\\upload\\" + username + "\\" + id);
            // out.print("successfully uploaded");
            out.println("{\"Error\":false,\"Message\": \"successfully uploaded\" }");

        } catch (Exception e) {
            out.println("{\"Error\":false,\"Message\": \"some error\" }");
        }
    }

    protected void getImage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String username;
        try {
            try {
                username = request.getParameter("username");
                System.out.println(username);
            } catch (Exception e) {
                System.out.println(156);
                username = null;

            }
            System.out.println(157);

            HttpSession session = request.getSession();
            username = String.valueOf(session.getAttribute("username"));
            String id = request.getParameter("id");
            System.out.println(username + "1");
            File folder = new File("/opt/tomcat/webapps/ROOT/upload/" + username + "\\" + id);
            //File folder = new File("C:\\Users\\susheel\\Documents\\NetBeansProjects\\HDwallet\\web\\upload\\" + username + "\\" + id);
            File[] listOfFiles = folder.listFiles();
            System.out.println(folder + "2");
            ArrayList<UploadImage> a;
            a = new ArrayList<>();

            for (File file : listOfFiles) {
                if (file.isFile()) {
                    //out.println("{\"username\": \""+username+"\" ,\"file\": \""+file.getName()+"\" }");
                    UploadImage a1 = new UploadImage();
                    a1.username = username;
                    a1.id = id;
                    a1.document = file.getName() + "";
                    a1.Error = false;
                    a.add(a1);
                }
            }

            Gson gson = new GsonBuilder().create();
            String jsonArray = gson.toJson(a);
            //out.println(messages);
            out.write(jsonArray);
        } catch (Exception e) {
            out.println("{\"Error\":false,\"Message\": \"success\" }");
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
        getImage(request, response);
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

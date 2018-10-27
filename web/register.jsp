<%-- 
    Document   : register
    Created on : Sep 12, 2018, 2:16:27 PM
    Author     : susheel
--%>

<%@ include file="nheader.jsp" %>

<div class="container-fluid">
    <div class="container">


        <div class="row"> 
            <div class="col-md-6">

                <p>
                    <%
                        if (request.getAttribute("msg") != null) {
                            out.print("<div  class='alert alert-success alert-safe alert-success' >"+String.valueOf(request.getAttribute("msg"))+"</div>");
                        }
                    %>
                </p> 

                <form class="register-form" action="RegisterServlet" method="post">
                    <h3 class="font-green">Register a new account</h3>
                    <p class="hint"> Sign up for a user account to buy or buy bitcoins. </p>





                    <div class="form-group">
                        <label class="control-label visible-ie8 visible-ie9">Full Name</label>
                        <input class="form-control placeholder-no-fix" type="text" placeholder="Full Name" name="fullname" /> </div>
                    <div class="form-group">
                        <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                        <label class="control-label visible-ie8 visible-ie9">Email</label>
                        <input class="form-control placeholder-no-fix" type="text" placeholder="Email" name="email" /> </div>

                    <div class="form-group">
                        <label class="control-label visible-ie8 visible-ie9">Password</label>
                        <input class="form-control placeholder-no-fix" type="password" autocomplete="off" id="register_password" placeholder="Password" name="password" /> </div>
                    <div class="form-group">
                        <label class="control-label visible-ie8 visible-ie9">Password(again)*</label>
                        <input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="Re-type Your Password" name="rpassword" /> </div>
                    <div class="form-group">   


                    </div>

                    <div class="form-actions">
                        <div class="g-recaptcha" data-sitekey="6Lc4Z3YUAAAAAMIAc6cExk_kTWnMfYSgXvqq70EO"></div>
                        <button type="submit" id="register-submit-btn" class="btn btn-success uppercase  ">Register</button>
                        <br ><br >
                        <p>already have an account? <a href="login.jsp"> Log in</a><br >
                            <br >
                           Forgot password?  <a href="forget.jsp"> Reset your password.</a><br ><br >
                        </p>


                    </div>
                </form>
            </div></div></div></div>
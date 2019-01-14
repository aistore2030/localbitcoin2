<%-- 
    Document   : login
    Created on : Sep 12, 2018, 2:16:45 PM
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
                <form class="login-form" action="LoginServlet" method="post">
                    <h3 class="form-title font-green">Log in</h3>

                    <div class="form-group">
                        <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                        <label class="control-label visible-ie8 visible-ie9">Email</label>
                        <input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="E mail" name="email" /> </div>
                    <div class="form-group">
                        <label class="control-label visible-ie8 visible-ie9">Password</label>
                        <input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder="Password" name="password" /> </div>
                    <div class="form-actions">
                        <div class="g-recaptcha" data-sitekey="6LdnJogUAAAAADNxgfVjwZ6oBqF20CFCqsveAb20"></div>
                        <button type="submit" class="btn btn-primary   uppercase">Log in</button>


                        <p> New to Trading App?  <a href="register.jsp"> Sign up now!</a><br >   
                            <br >
                            Forgot password?  <a href="forget.jsp">Reset your password</a><br ><br >
                        </p>
                    </div>



                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="ifooter.jsp" %>
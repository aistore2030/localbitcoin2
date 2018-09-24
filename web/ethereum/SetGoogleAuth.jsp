<%-- 
    Document   : SetGoogleAuth
    Created on : Jan 18, 2018, 8:26:03 AM
    Author     : Saksham
--%><%@page import="AngularApi.SetGoogleAuth"%>


<div class="row">
    <div class="panel">
        <div class="panel-heading">


        </div>
        <div class="panel-body">

            <!-- END PAGE BAR -->
            <!-- BEGIN PAGE TITLE-->
            <h1 class="page-title">Enable Google Authentication
                <small>All Details</small>
            </h1>
            <!-- END PAGE TITLE-->
            <div class="col-md-6">
                <%  
            
                  SetGoogleAuth sa= new SetGoogleAuth();
       String secret = sa.generateSecretKey();

                  out.println("<div><img src='" + sa.getQRBarcodeURL("localbitcoins@global", "Blocalbitcoins@global", secret) + "' />");
      out.println("<br></div>");
     
                %>

                <H1>   <%=secret%></H1>   </div> <div class="col-md-6">
                <form>

                    <div class="form-group ">

                        <input class="form-control" id="call_back_url" ng-init="x.secret = '<%=secret%>'" ng-model="x.secret" type="hidden" >
                    </div>


                    <div class="form-group ">
                        <label class="control-label " for="name">
                            Enter OTP
                        </label>
                        <input class="form-control" id="call_back_url" ng-model="x.otp" placeholder="Enter otp" type="text" >
                    </div>
                    <button type="submit" ng-click="Submit(x)" class="btn btn-success">Submit</button>







                </form>

            </div>      </div>


    </div>


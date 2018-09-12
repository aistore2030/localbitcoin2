<%@ include file="iheader.jsp" %>


<div class="container-fluid">
    <div class="container">


        <div class="row">  


            <div class="col-md-12">
                <p>
                    <%
                        if (request.getAttribute("msg") != null) {
                            out.print("<div  class='alert alert-success alert-safe alert-success' >"+String.valueOf(request.getAttribute("msg"))+"</div>");
                        }
                    %>
                </p> 





                <div ng-controller="VerifyEmailController">
                    <center ng-show="x.email_verified === 'Unverified'">
                        <button  ng-click="VerifyEmailStatus(x)" class="btn btn-success">Validate Your Email</button>
                    </center></div> 



                <div ui-view></div>            
            </div>
        </div></div></div>

<script src="./lib/angular.js"></script>
<script type="text/javascript" src="./js/application.js"></script>
<script type="text/javascript" src="./js/controllers.js"></script>
<script type="text/javascript" src="./js/services.js"></script>
<script type="text/javascript" src="./js/ngClickCopy.js"></script>
<script type="text/javascript" src="./lib/angular-ui-router.min.js"></script>
<script type="text/javascript" src="./lib/angular-resource.min.js"></script>
<script src="./lib/animate.js"></script>
<script src="./lib/sanitize.js"></script>
<script src="./lib/ui-bootstrap.js"></script>
<script src="./lib/angular-file-upload.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!---footer-->
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="./lib/angular-file-upload.min.js"></script>




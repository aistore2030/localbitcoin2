<%-- 
    Document   : ifooter
    Created on : Jan 13, 2018, 1:01:35 PM
    Author     : Saksham
--%>





<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>

<hr>
<footer class="container">



    <div class="row footer-block">
        <div class="col-md-12">
            <div class="col-md-3 hidden-sm">
                <img id="footer-logo" src="" alt="logo" class="img-responsive"/>
            </div>

            <div class="col-md-3" id="col-about">
                <ul class="nav nav-list">
                    <li class="nav-header">ABOUT</li>
                    <li><a href="#">ABOUT us</a></li>



                    <li><a href="#">Terms Of service</a></li>
                </ul>
            </div>
            <div class="col-md-3">
                <ul class="nav nav-list">
                    <li  class="nav-header">SUPPORT</li>
                    <li>  <form action="#" name="f1" method="get" >
                            <select name="lang" id="lang">

                                <option value="en">English</option>
                                <option value="fr">French</option>   
                                <option value="hi">Hindi</option>
                                <option value="pr">Portuguese</option>
                                <option value="ch">chinese Simplified</option>
                                <option value="ct">chinese traditiona</option>
                                <option value="sp">Spanish</option>
                            </select>
                            <input type="submit" name="submit" value="Select Language"/>
                        </form></li>
                    <li><a href="#">Contact us</a></li>


                    <li><a href="#">Forgot Password</a></li>
                </ul>
            </div>

            <div class="col-md-3">






                <ul class="nav nav-list">
                    <li class="nav-header">FOLLOW us</li>
                    <li><a href="#">
                            <i class="fa fa-fw fa-facebook"></i>
                            &nbsp;Facebook
                        </a></li>
                    <li><a href="#">
                            <i class="fa fa-fw fa-twitter"></i>
                            &nbsp;Twitter
                        </a></li>
                    <li><a href="#">
                            <i class="fa fa-fw fa-instagram"></i>
                            &nbsp;Instagram
                        </a></li>
                    <li><a href="#">
                            <a href="https://www.instantssl.com/ssl.html"  style="text-decoration:none; ">
                                <img alt="Secure Sockets Layer" src="https://www.instantssl.com/ssl-certificate-images/support/comodo_secure_100x85_transp.png" style="border: 0px;" /></a><br/>

                        </a></li>

                </ul>
            </div>
        </div>
    </div>



</footer>


<script>
    $('#dynamic-select').bind('change', function () { // bind change event to select
        var url = $(this).val(); // get selected value
        if (url != '') { // require a URL
            window.location = url; // redirect
        }
        return false;
    });
</script>  
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="./lib/angular-file-upload.min.js"></script>


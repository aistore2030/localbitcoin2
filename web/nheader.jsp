<%-- 
    Document   : nheader
    Created on : Sep 12, 2018, 2:08:57 PM
    Author     : susheel
--%>


<%@page import="java.net.URL"%>
<%@page import="com.system.Logo"%>
<html data-ng-app="mApp">
    <head>


        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

        <title> Trading App </title>
        <link href="./css/style.css" rel="stylesheet">
        <script
        src="//code.jquery.com/jquery-3.2.1.min.js" ></script>
        <script type="text/javascript" src='/cached-static/wallet.8d393bd91730.js'></script>

        <script src="./lib/angular.js"></script>
        <script type="text/javascript" src="./js/application.js"></script>
        <script type="text/javascript" src="./js/controllers.js"></script>
        <script type="text/javascript" src="./js/services.js"></script>
        <script type="text/javascript" src="./js/ngClickCopy.js"></script>
        <script type="text/javascript" src="./lib/angular-ui-router.min.js"></script>
        <script type="text/javascript" src="./lib/angular-resource.min.js"></script>
        
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/socket.io/2.0.4/socket.io.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-socket-io/0.7.0/socket.js"></script>

 
 
        <script src="./lib/animate.js"></script>
        <script src="./lib/sanitize.js"></script>
        <script src="./lib/ui-bootstrap.js"></script>
        <script src='https://www.google.com/recaptcha/api.js'></script>
    </head > 

    <!-- END HEAD -->
    <body >
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <!-- Brand and toggle get grouped for better mobile display -->
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                                    <span class="sr-only">Toggle navigation</span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
                                <%             String url = request.getRequestURL().toString();
                                    Logo l = new Logo();
                                    String a = l.logo(url);
                                    URL url1 = new URL(url);
                                    String domain = url1.getHost();
                                    

                                %>
                                <a class="brand" href="index.jsp"><img src="./image/logo2.png" alt="Logo"></a>
                            </div>
                            <%
                                //String lang = "fr";
                                //String lang ="ch";
                                /// ResourceBundle RB = ResourceBundle.getBundle("app", new Locale(lang));
                            %> 
                            <!-- Collect the nav links, forms, and other content for toggling -->
                            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" >
                                 <ul class="nav navbar-nav">
                                    <li><a href="index.jsp#!/buybitcoin" >Buy Bitcoins <span class="sr-only">(current)</span></a></li>

                                    <li><a href="index.jsp#!/sellbitcoin">Sell Bitcoins</a></li>

                                    <li><a  href="index.jsp#!/PostTrade" >Post a Trade</a></li>

                                </ul>
                                <ul class="nav navbar-nav navbar-right">

                                    <li>
                                        <a id="top-register-link" class="register-link" href="register.jsp"><span><i class="fa fa-check-square-o"></i>

                                                Sign up free

                                            </span>
                                        </a>
                                    </li>
                                    <li><a id="top-login-link" href="login.jsp"><i class="fa fa-user"></i>&nbsp;Log in</a></li>

                                </ul>


                                </ul>


                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </nav>
                            
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="./lib/angular-file-upload.min.js"></script>


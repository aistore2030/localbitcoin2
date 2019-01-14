<%-- 
    Document   : iheader
    Created on : Jan 13, 2018, 1:01:23 PM
    Author     : Saksham
--%>

<%@page import="java.net.URL"%>
<%@page import="com.system.Logo"%>

<html data-ng-app="mApp">
    <head>
        <% int roll = 0;
            if (session.getAttribute("username") != null && !String.valueOf(session.getAttribute("username")).trim().equalsIgnoreCase("")) {
                response.setHeader("Pragma", "no-cache");
                response.setHeader("Cache-Control", "no-cache");
                response.setDateHeader("Expires", 0);

                //
            } else {
                //  roll =1;
        %>   <jsp:forward page="login.jsp" />
        <%
            }

            String username = String.valueOf(session.getAttribute("username")).trim();
            String rolls = String.valueOf(session.getAttribute("roll")).trim();

            roll = Integer.parseInt(rolls);


        %>         
        <title> Welcome ... </title>

        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

        <link href="./css/style.css" rel="stylesheet">
        <script
        src="//code.jquery.com/jquery-3.2.1.min.js" ></script>
        <script type="text/javascript" src='/cached-static/wallet.8d393bd91730.js'></script>
        <style>
            td.b {
                word-wrap: break-word;
            } 

        </style>

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
                                <% String url = request.getRequestURL().toString();
                                    Logo l = new Logo();
                                    String a = l.logo(url);
                                    URL url1 = new URL(url);
                                    String domain = url1.getHost();
                                    System.out.println(domain + " domain");

                                    System.out.println(a);

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



                                    <%if (roll == 10) {
                                    %>
                                    <li class="dropdown user-panel-dd"> <a class="dropdown-toggle" data-toggle="dropdown" href="#user_dropdown">
                                            Admin<b class="caret"></b></a>
                                        <ul class="dropdown-menu">
                                            <li><a ui-sref="SetMargin">Set Margin/Bonus</a></li>
                                            <li><a ui-sref="SetMargin">Set Margin/Bonus</a></li>
                                            <li><a ui-sref="AllUser">All User</a></li>
                                            <li><a ui-sref="receivedBtc">Received Bitcoins </a></li>
                                            <li><a  ui-sref="PaymentGateway" >payment</a></li>
                                            <li><a  ui-sref="AllTrade">Trade</a></li>
                                            <li><a ui-sref="SendSetting">Wallet Settings</a></li>
                                        </ul>
                                    </li>
                                    <%}%>
                                    <li><a ui-sref="BuyBitCoin" >Buy Bitcoins<span class="sr-only">(current)</span></a></li>

                                    <li><a ui-sref="SellBitCoin">Sell Bitcoins</a></li>
                                    
                                    <li class="dropdown user-panel-dd"> 
                                        <a class="dropdown-toggle" data-toggle="dropdown" href="#user_dropdown">
                                            Support<b class="caret"></b></a>
                                        <ul class="dropdown-menu">
                                            <li>  <a ui-sref="AddTicket">Add Ticket</a></li>
                                            <li> <a  ui-sref="MesReport">All Ticket</a>  </li> 
                                            <li>  <a ui-sref="NewTicket">New Ticket</a></li>
                                            <%if (roll == 10) {%>  <li>  <a ui-sref="PendingAnswer">Pending Answer</a></li>   
                                            <li><a ui-sref="ClosedTicket">Closed Ticket</a></li>

                                                <%}%>

                                        </ul>
                                    </li>
                                    <%if (roll < 10) {
                                    %>
                                    <li><a  ui-sref="PostTrade">Post a Trade</a></li>

                                    <%}%>


                                </ul>
                                <ul class="nav navbar-nav navbar-right">

                                    <li class="dropdown user-panel-dd notifications_dropdown0" data-notification-url="/accounts/notifications/">

                                        <a class="dropdown-toggle notifications-count-base" data-toggle="dropdown"  href="#notifications_dropdown">
                                            <i class="fa fa-comment fa-lg" ></i>
                                            <% //notification n = new notification();
                                                String row = "";// n.UnreadNotification(username);%>
                                            <span class="badge read-notification-count" ><%out.print(row);%></span>
                                        </a>
                                        <ul  ng-controller="NotificationController" class="dropdown-menu notifications-dropdown scrollable-menu" style="width: 500px;">
                                            <div ng-repeat="x in notification| limitTo : '5 : 1'" >
                                                <li  class="list-group-item"><a href="{{x.link}}">{{x.notification}}  <b>[{{x.id}}]</b><br>{{x.time}}<br> </a></li>
                                            </div>
                                            <a class="btn-more" ui-sref="Notification">View More Notifications <i class="fa fa-long-arrow-right"></i></a>
                                        </ul>
                                        <div class="notification-timestamp" style="display: none" data-timestamp="None" data-need-notifications="false"></div>
                                    </li>
                                    <li>
                                        <a ui-sref="BTC">
                                            Deposit                 
                                        </a>
                                    </li>
                                              
                               
                                    <li class="dropdown user-panel-dd"> <a class="dropdown-toggle" data-toggle="dropdown" href="#user_dropdown">
                                            <i class="fa fa-user fa-fw"></i><b class="caret"></b></a>
                                        <ul class="dropdown-menu">
                                            <li><a  user.username="user.username"><i class="fa fa-user fa-fw"></i>&nbsp; <%=username%></a></li>
                                            <li><a ui-sref="Profile"><i class="fa fa-edit fa-fw"></i>&nbsp;Edit profile</a></li>
                                            <li><a ui-sref="SellBitCoin"><i class="fa fa-dashboard fa-fw"></i>&nbsp;Dashboard</a></li>
                                            <li><a ui-sref="sendbtc"><i class="fa fa-shopping-cart fa-fw"></i>&nbsp;send Transaction</a></li>
                                            <li class="divider"></li>
                                            <li class="user-panel-dd"><a ng-controller="GetBalanceController"><i class="fa fa-btc fa-fw"></i>&nbsp;Wallet: {{systemBalance.bitcoinbalance}}BTC</a></li>
                                            <li class="user-panel-dd"><a ui-sref="Wallet"><i class="fa fa-shopping-cart fa-fw"></i>&nbsp;Transaction History</a>
                                            </li>
                                            <li><a  ui-sref="AllTrade"><i class="fa fa-shopping-cart fa-fw"></i>Trade</a></li>

                                            <li class="divider"></li>
                                            <li><a href="LogoutServlet?lang=jjjk"><i class="fa fa-sign-out fa-fw"></i>&nbsp;Logout</a></li>
                                        </ul>
                                    </li>
                                    <li class="balance" ng-controller="GetBalanceController"> 
                                        <a> BTC. {{systemBalance.balance}} |  USD. {{systemBalance.usdbalance}} </a>

                                    </li> 
                                    <!--<li><a ng-controller="UserBalanceController">Balance:{{x1.balance}}</a></li>-->
                                </ul>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </nav>



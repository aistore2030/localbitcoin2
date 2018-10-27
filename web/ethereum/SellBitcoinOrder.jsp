<%-- 
    Document   : SellBitcoinOrder
    Created on : Jan 12, 2018, 1:14:52 PM
    Author     : Saksham
--%>


<div class="row">
    <h2>Sell BitCoin using {{myTxt.payment_method}} with {{myTxt.currency}} </h2>
    <div class="col-md-6">
        TradeApp user {{myTxt.name}} wishes to buy bitcoins from you. 
        <form class="form-horizontal">


            <div class="row">
                <div class="col-md-4">
                    <h4>Price:</h4>
                </div>
                <div class="col-md-8">
                    <h4 class="price" id="ad_price" > {{myTxt.price}}BRL</h4>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <h4>Payment method:</h4>
                </div>
                <div class="col-md-8">
                    <h4 class="price" id="ad_price"> {{myTxt.payment_method}} </h4>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <h4>User:</h4>
                </div>
                <div class="col-md-8">
                    <h4 class="price" id="ad_price"> {{myTxt.name}} </h4>
                </div>
            </div>

            <div class="row">
                <div class="col-md-4">
                    <h4>Trade Limits:</h4>
                </div>
                <div class="col-md-8">
                    <h4 class="price" id="ad_price">{{myTxt.min_limit}}BRL-{{myTxt.max_limit}}BRL</h4>
                </div>
            </div>      
            <div class="row">
                <div class="col-md-4">
                    <h4>Location:</h4>
                </div>
                <div class="col-md-8">
                    <h4 class="price" id="ad_price">{{myTxt.location}}</h4>
                </div>
            </div> 

            <div class="row">
                <div class="col-md-4">
                    <h4>Payment Window:</h4>
                </div>
                <div class="col-md-8">
                    <h4 class="price" id="ad_price">90min</h4>
                </div>
            </div> 



            <div class="well" id="cactus-wrapper">

                <h4>
                    How much you wish to Sell?
                </h4>





                <div id="amountdiv" class="row">

                    <div class="col-md-6 span amount"> BRL
                        <input autocomplete="off" class="form-control" id="amountinput" ng-change="BTCUSD(myTxt)" ng-model="myTxt.amount" name="amount" placeholder="0.00"  type="text" />
                    </div>
                    <div class="col-md-6 span btc">BTC
                        <input class="form-control" id="btcinput" ng-change="USDBTC(myTxt)" ng-model="myTxt.total_bitcoin"   step="0.01"  name="total_bitcoin" type="text"/>
                    </div>
                </div>  

                <%
                    if (session.getAttribute("username") != null && !String.valueOf(session.getAttribute("username")).trim().equalsIgnoreCase("") && !String.valueOf(session.getAttribute("block_status")).equals("Blocked")) { %>
                <div class=""><br><br/>
                    <button type="submit" ng-click="Submit(myTxt)" class="btn btn-success btn-lg btn-block">Create Trade Request</button>
                </div> 
                <%} else if (String.valueOf(session.getAttribute("block_status")).equals("Blocked")) {
                %>
                <div class="">
                    <button type="submit"   class="btn btn-disable">You are Blocked</button>
                </div> 

                <%} else {
                %>

                Sign up and sell bitcoins instantly.

                </p>
                <p>
                    <a id="ad-register-here" class="megabutton btn btn-success register-link" href="register.jsp">
                        <i class="fa fa-check-square-o"></i>
                        Sign up free
                    </a>
                </p>
                <p>
                    Signing up is free and takes only 30 seconds.
                </p>
                <%}   %>     

            </div>



        </form></div>
    <div class="col-md-6">



        <div class="panel panel-default">
            <div class="panel-heading">   Terms of trade</div>
            <div class="panel-body">{{myTxt.terms}}

            </div>
        </div></div> 
                </div>
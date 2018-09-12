<%-- 
    Document   : BuyBitcoinOrder
    Created on : Jan 12, 2018, 4:29:15 PM
    Author     : Saksham
--%>



<div class="row">
    <div class="col-md-12">
        <h2>Buy BitCoin using {{myTxt.payment_method}} with {{myTxt.currency}}</h2>
    </div>
</div>
<div class="row">
    <div class="col-md-6">
        TradeApp user {{myTxt.name}} wishes to sell bitcoins to you. 
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
                    <h4 class="price" id="ad_price">{{myTxt.min_limit}}BRL - {{myTxt.max_limit}}BRL</h4>
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
                    How much you wish to buy?
                </h4>





                <div id="amountdiv" class="row">

                    <div class="col-md-6 span amount">USD
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

                <p class="ad-call-for-action">

                    Sign up and buy bitcoins instantly.

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

                <%}

                    // out.print(String.valueOf(session.getAttribute("block_status"))+"hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
                %>     

            </div>
            <div class="panel-group" id="accordion-trade-quick-how-to" role="tablist" aria-multiselectable="true">
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="heading-start-trade">
                        <h4 class="panel-title">
                            <a class="accordeon-link collapsed" data-toggle="collapse" data-parent="#accordion-start-trade" href="#collapse-start-trade" aria-expanded="false" aria-controls="collapse-start-trade">
                                <i class="fa fa-chevron-down"></i>&nbsp;How to begin and contact the trader
                            </a>
                        </h4>
                    </div>
                    <div id="collapse-start-trade" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading-start-trade" style="height: 0px;" aria-expanded="false">
                        <div class="panel-body">
                            <p>

                                Read the the terms of the trade and make sure you can comply with them.
                                Use this form to send in a trade request with the sum you wish to trade.
                                After opening the trade request it is possible to discuss with
                                the trader in localbitcoins.global messaging system.

                            </p>
                            <p>


                                Open trade requests and message inbox can be found under <a target="_blank" href="ads">Dashboard</a> under your user profile page. You can send and receive messages with the trader there.

                            </p>
                            <p>


                                For more information see <a target="_blank" href="/guides/how-to-buy-bitcoins"><i class="fa fa-question"></i> quick buy guide</a>.

                            </p>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="heading-how-to-cancel">
                        <h4 class="panel-title">
                            <a class="accordeon-link collapsed" data-toggle="collapse" data-parent="#accordion-receive" href="#collapse-how-to-cancel" aria-expanded="false" aria-controls="collapse-how-to-cancel">
                                <i class="fa fa-chevron-down"></i>&nbsp;Cancelling the trade
                            </a>
                        </h4>
                    </div>
                    <div id="collapse-how-to-cancel" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading-how-to-cancel" style="height: 0px;" aria-expanded="false">
                        <div class="panel-body">
                            <p>


                                You can cancel the trade before making the payment.
                                You find open trades under <a href="/ads" target="_blank">Dashboard</a> in your user profile.

                            </p>
                        </div>
                    </div>
                </div>

            </div>


        </form></div>
    <div class="col-md-6">



        <h3>   Terms of trade</h3>

        <pre>{{myTxt.terms}}</pre>



    </div> 
</div>
<%-- 
    Document   : PostTrade
    Created on : Jan 12, 2018, 5:40:46 AM
    Author     : Saksham
--%>

<div class="container fluid">
    <div class="container">

        <h1 class="page-title">Create a bitcoin trade advertisement
        </h1>
        <!-- END PAGE TITLE-->
        <h2>Advertisement rules and requirements</h2>
        <ul><li>For your ads to display you need to have Bitcoins in your Trade App wallet.
            </li><li>Certain payment methods require you to be ID verified before your ads are visible.
            </li><li>Each completed trade costs advertisers 1% of the total trade amount. See all fees on our fees page.
            </li><li>Once a trade is opened the price is final, except when there is a clear mistake in the pricing.
            </li><li>You are not allowed to buy or sell Bitcoin on behalf of someone else (brokering).
            </li><li>You may only use payment accounts that are registered in your own name (no third party payments!).
            </li><li>You must provide your payement details in the advertisement or in the trade chat.
            </li><li>All communication must happen on Trade App
            </li><li>Payment methods marked High Risk have a significant risk of fraud. Be careful and always ID verify your trading partners when using high risk payment methods.
            </li></ul>
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-3">I Want To</label>
                <div class="col-sm-9">
                    <label class="radio">
                        <input type="radio"  name="type"  ng-model="x.type" id="Radio1" value="Sell_bitcoin"> Sell Bitcoins online
                    </label>
                    <label class="radio">
                        <input type="radio" name="type" ng-model="x.type" id="Radio2" value="Buy_bitcoin"> Buy Bitcoins online
                    </label>
                   
                </div>
            </div>
            <div class="form-group" id="locationField">
                <label class="col-sm-3 control-label">Location</label>


                <div class="col-sm-6" id="locationField">
                    <input type="text" class="form-control" id="autocomplete" ng-model="x.location" name="location" placeholder="Location">
                </div>

            </div>
            <div class="form-group">
                <label for="inputlocation" class="col-sm-3 control-label">Payment Method</label>
                <div class="col-sm-6">
                    <Select class="form-control"  ng-model="x.payment_method" name="payment_method">
                        <option value="NATIONAL_BANK">National bank transfer</option>
                        <option value="SEPA">SEPA (EU) bank transfer</option>
                        <option value="SPECIFIC_BANK">Transfers with specific bank</option>
                        <option value="INTERNATIONAL_WIRE_SWIFT">International Wire (SWIFT)</option>
                        <option value="OTHER">Other online payment</option>
                        <option value="CASH_DEPOSIT">Cash deposit</option>
                        <option value="ECOCASH">EcoCash</option>
                        <option value="HAL_CASH">Hal-cash</option>
                        <option value="SWISH">Swish</option>
                        <option value="VIPPS">Vipps</option>
                        <option value="MOBILEPAY_DANSKE_BANK">MobilePay FI</option>
                        <option value="QIWI">QIWI</option>
                        <option value="CASH_BY_MAIL">Cash by mail</option>
                        <option value="BANK_TRANSFER_IMPS">IMPS Bank Transfer India</option>
                        <option value="PAYTM">PayTM</option>
                        <option value="LYDIA">Lydia</option>
                        <option value="INTERAC">Interac e-transfer</option>
                        <option value="PINGIT">Pingit</option>
                        <option value="PAYM">Paym</option>
                        <option value="PYC">PYC</option>
                        <option value="ALIPAY">Alipay</option>
                        <option value="SUPERFLASH">Superflash</option>
                        <option value="CHASE_QUICKPAY">Chase Quickpay</option>
                        <option value="OKPAY">OKPay</option>
                        <option value="PAYPAL">Paypal</option>
                        <option value="WEBMONEY">WebMoney</option>
                        <option value="MONEYBOOKERS">Moneybookers / Skrill</option>
                        <option value="NETELLER">Neteller</option>
                        <option value="WU">Western Union</option>
                        <option value="PostePay">PostePay</option>
                        <option value="MONEYGRAM">Moneygram</option>
                        <option value="POSTAL_ORDER">Postal order</option>
                        <option value="CASHIERS_CHECK">Cashier&#39;s check</option>
                        <option value="VENMO">Venmo</option>
                        <option value="DWOLLA">Dwolla</option>
                        <option value="PERFECT_MONEY">Perfect Money</option>
                        <option value="CASHU">CashU</option>
                        <option value="PAYSAFECARD">PaySafeCard</option>
                        <option value="PAYZA">Payza</option>
                        <option value="ASTROPAY">AstroPay</option>
                        <option value="MPESA_KENYA">M-PESA Kenya (Safaricom)</option>
                        <option value="MPESA_TANZANIA">M-PESA Tanzania (Vodacom)</option>
                        <option value="BPAY">BPAY Bill Payment</option>
                        <option value="GIFT_CARD_CODE">Gift Card Code</option>
                        <option value="GIFT_CARD_CODE_GLOBAL">Gift Card Code (Global)</option>
                        <option value="OTHER_ONLINE_WALLET">Other Online Wallet</option>
                        <option value="OTHER_ONLINE_WALLET_GLOBAL">Other Online Wallet (Global)</option>
                        <option value="OTHER_REMITTANCE">Other Remittance</option>
                        <option value="OTHER_PRE_PAID_DEBIT">Other Pre-Paid Debit Card</option>
                        <option value="GOOGLEWALLET">Google Wallet</option>
                        <option value="GIFT_CARD_CODE_AMAZON">Amazon Gift Card Code</option>
                        <option value="VANILLA">Vanilla</option>
                        <option value="TRANSFERWISE">Transferwise</option>
                        <option value="PAYPALMYCASH">PayPal My Cash</option>
                        <option value="RIA">RIA Money Transfer</option>
                        <option value="SOLIDTRUSTPAY">SolidTrustPay</option>
                        <option value="XOOM">Xoom</option>
                        <option value="MOBILEPAY_DANSKE_BANK_DK">MobilePay</option>
                        <option value="TELEGRAMATIC_ORDER">Telegramatic Order</option>
                        <option value="PAYEER">Payeer</option>
                        <option value="ADVCASH">advcash</option>
                        <option value="GIFT_CARD_CODE_APPLE_STORE">Apple Store Gift Card Code</option>
                        <option value="NETSPEND">NetSpend Reload Pack</option>
                        <option value="HYPERWALLET">hyperWALLET</option>
                        <option value="GIFT_CARD_CODE_STEAM">Steam Gift Card Code</option>
                        <option value="MOBILEPAY_DANSKE_BANK_NO">MobilePay NO</option>
                        <option value="TIGOPESA_TANZANIA">Tigo-Pesa Tanzania</option>
                        <option value="PAYONEER">Payoneer</option>
                        <option value="ONECARD">OneCard</option>
                        <option value="SQUARE_CASH">Square Cash</option>
                        <option value="PAXUM">Paxum</option>
                        <option value="CASH_AT_ATM">Cash at ATM</option>
                        <option value="CREDITCARD">Credit card</option>
                        <option value="WECHAT">WeChat</option>
                        <option value="RELOADIT">Reloadit</option>
                        <option value="WALMART2WALMART">Walmart 2 Walmart</option>
                        <option value="GIFT_CARD_CODE_WALMART">Walmart Gift Card Code</option>
                        <option value="EASYPAISA">Easypaisa</option>
                        <option value="SERVE2SERVE">Serve2Serve</option>
                        <option value="WORLDREMIT">Worldremit</option>
                        <option value="ALTCOIN_ETH">Ethereum altcoin</option>
                        <option value="GIFT_CARD_CODE_EBAY">Ebay Gift Card Code</option>
                        <option value="GIFT_CARD_CODE_STARBUCKS">Starbucks Gift Card Code</option>
                        <option value="YANDEXMONEY">Yandex Money</option>
                    </select>
                </div>

            </div>
            <div class="form-group">

                <label for="inputlocation" class="col-sm-3 control-label">Currency</label>

                <div class="col-sm-6">
                    <select class="form-control"  ng-model="x.currency" name="currency" >
                        <option ng-repeat="currenc in curr" value="{{currenc.name}}">
                            {{currenc.name}}
                        </option>
                    </select>
                </div>
            </div>
            <div class="form-group">

                <label for="mar" class="col-sm-3 control-label">Margin</label>

                <div class="col-sm-6">
                    <input type="number"  ng-model="x.margin" ng-change="forExConvert(x)" class="form-control" id="mar" name="margin" placeholder="0" value="0">   
                </div>
            </div>
            <div class="form-group">
                <label for="inputnum" class="col-sm-3 control-label">Price Equation</label>
                <div class="col-sm-6">


                    <input type="text"  ng-model="x.price_equation"  class="form-control" id="inputnum" name="price_equation" placeholder="equation" readonly>
                </div>
            </div>
            <div class="form-group">
                <label for="inputtra" class="col-sm-3 control-label">Min Transaction Limit</label>
                <div class="col-sm-6">
                    <input type="number" class="form-control" id="inputtra" step="0.01" ng-model="x.min_tranaction" name="min_tranaction" placeholder="0">
                </div>
            </div>
            <div class="form-group">
                <label for="inputtra" class="col-sm-3 control-label">Max Transaction Limit</label>
                <div class="col-sm-6">
                    <input type="number" class="form-control" id="inputtra" step="0.01" ng-model="x.max_tranaction" name="max_tranaction" placeholder="0">
                </div>
            </div>
            <div class="form-group">
                <label for="inputAmo" class="col-sm-3 control-label">Restricts Amount To</label>
                <div class="col-sm-6">
                    <input type="number" class="form-control" step="0.01" ng-model="x.restrict_amount" name="restrict_amount" id="inputAmo" >
                </div>
            </div>
            <div class="form-group">
                <label for="inputAmo" class="col-sm-3 control-label">Terms Of Trade</label>
                <div class="col-sm-6">
                    <textarea class="form-control" ng-model="x.terms_of_trade" name="terms_of_trade" rows="5"></textarea>
                </div>
            </div>
            <div class="form-group">
                <label for="inputAmo" class="col-sm-3 control-label">Track Liquidity</label> 

                <div class="col-sm-6">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" name="track_liquidity" ng-model="x.track_liquidity" value="Yes">

                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="inputAmo" class="col-sm-3 control-label">identified person only</label> 

                <div class="col-sm-6">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" name="identified_person_only" value="Yes">

                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="inputAmo" class="col-sm-3 control-label"> SMS verification required </label> 
                <div class="col-sm-6">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" name="sms_verification" ng-model="x.sms_verification" value="Yes">

                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="inputAmo" class="col-sm-3 control-label">Trusted person only</label> 
                <div class="col-sm-6">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox"  ng-model="x.trusted_person_only" name="trusted_person_only" value="Yes">

                        </label>
                    </div>
                </div>
            </div>
            <br>

            <%
                if (session.getAttribute("username") != null && !String.valueOf(session.getAttribute("username")).trim().equalsIgnoreCase("") && !String.valueOf(session.getAttribute("block_status")).equals("Blocked")) {%>

            <button type="submit" ng-click="Submit(x)" class="btn btn-success">Publish Advertisement</button>



            <%} else if (String.valueOf(session.getAttribute("block_status")).equals("Blocked")) {
            %>
            <button type="submit"  class="btn btn-disable">Publish Advertisement</button>

            <%} else {
            %>
            <button type="submit" ng-click="Submit(x)" class="btn " disabled>Publish Advertisement</button>


            <%}
            %>

        </form>



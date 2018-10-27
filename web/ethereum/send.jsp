<!--<a class="btn btn-primary  btn-sm"  ui-sref="PaymentRequest">Withdrawal BitCoins</a>-->
<a class="btn btn-primary  btn-sm"  ui-sref="sendbtc">Withdrawal BitCoins</a>
<a class="btn btn-primary  btn-sm"  ui-sref="BTC">Deposit</a></div> 

<a class="btn btn-primary  btn-sm"  ui-sref="Wallet">Transactions</a></div> 

<%String block_status=String.valueOf(session.getAttribute("block_status"));
 
%>

<h2>Wallet transactions</h2>
<div class="row">    <div class="col-md-9">

        <!--    <h1>{{x.Name}} Public Key</h1>{{x.PublicKey}}
           <h3><b>{{x.Name}} Balance</b> : {{x.Balance}}</h3>-->

        <form>   

            <input class="form-control"  value="{{x.username}}" type="hidden" readonly ng-model="x.username" />
            <div class="form-group">
                <!-- <label  > Select your Address(to send amount) </label>-->
                <input class="form-control"  type="hidden" readonly ng-model="x.Address"   />
                <!-- <select class="form-control" ng-model="x.add">
                     <option value="{{x.Address}}">{{x.Address}}</option>
                     <option value="{{x.Address2}}">{{x.Address2}}</option>
                     <option value="{{x.Address3}}">{{x.Address3}}</option>
                 </select>--></div>
            <div class="form-group">
                <label  > Receiving bitcoin address  : </label>
                <input class="form-control"  type="text"  ng-model="x.receiveraddress" required/></div>
            <div class="form-group">	
                <label  >Amount in bitcoins  </label>
                <input class="form-control"  ng-change="USDBTC(x)" ng-model="x.total_bitcoin"  required/></div>  
            <input ng-model="x.cse" value="{{x.cse}}" class="form-control" type="hidden" />
            <hr>
            <h4>More options</h4>
            <div class="form-group">
                <label for="description">Description </label>
                <textarea class="form-control" rows="2" cols="50" type="text" ng-model="x.description"   required></textarea>
            </div>
            <div class="form-group">
                <label for="description">Amount in USD</label>
                <input autocomplete="off" class="form-control" id="amountinput" ng-change="BTCUSD(x)" ng-model="x.amount" name="amount" placeholder="0.00"  type="text" />
            </div>
            <div class="form-group">
                <%if(block_status.equals("Blocked")){%>
                <input type="button" ng-click="Submit(x)" class="btn btn-success" value="submit" disabled/>
                <%}else{%>
                <input type="button" ng-click="Submit(x)" class="btn btn-success" value="submit" /><%}%>
            </div>

        </form>







    </div></div> 




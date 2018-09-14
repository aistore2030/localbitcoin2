<a class="btn btn-primary  btn-sm"  ui-sref="sendbtc">Withdrawal BitCoins</a>

<a class="btn btn-primary  btn-sm"  ui-sref="BTC">Deposit</a></div> 

<a class="btn btn-primary  btn-sm"  ui-sref="Wallet">Transactions</a></div> 



<h2>Wallet transactions</h2>
<div class="row"> 

    <div class="col-md-3">
        <!--<div class="col-md-6">
   
        <!--   <h1>{{x.Name}} Public Key</h1>{{x.PublicKey}}-->
        <h1>Your Receiving Address</h1>{{x.Address}}
        <!--    

        <br> 
        {{x.Address2}}
        

        <br>
        {{x.Address3}}-->
    </div>
    <div class="col-md-6">

        <div    ng-show="(y| filter:filter).length">
            <table class="table" ng-hide="y.length === 0">

                <thead>
                    <tr>	
                        <th>ID</th>
                        <th>address </th>
                        <th> Amount </th>
                        <th> confirmations </th>
                        <th>Time</th>
                    </tr>
                </thead>

                <tbody>


                    <tr  ng-repeat="y1 in y| orderBy:'id'">   
                        <td>{{y1.id}} </td>
                        <td> {{y1.address}}</td> 
                        <td> {{y1.amount}}</td>
                        <td> {{y1.confirmations}}</td>
                        <td> {{y1.date}}</td>
                    </tr>
                </tbody>	
            </table>  
        </div> 
    </div>
</div>




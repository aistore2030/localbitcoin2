
<a class="btn btn-primary  btn-sm"  ui-sref="sendbtc">Withdrawal BitCoins</a>

<a class="btn btn-primary  btn-sm"  ui-sref="BTC">Deposit</a></div> 

<a class="btn btn-primary  btn-sm"  ui-sref="Wallet">Transactions</a></div> 



<h2>Wallet transactions</h2>

<p>Below is the history of your Trading App wallet.</p>     


<table class="table table-bordered table-condensed">

    <thead>
        <tr>	<th>ID</th>
          
            
            <th>Description </th>
            <th>Credited Amount </th>
            <th>Debited Amount</th>
           
            <th>Time</th>
        </tr>
    </thead>


    <tbody>


        <tr  ng-repeat="x in myTxt">
            
            
            <td> {{x.id}}  </td>
        
           
            <td> {{x.description}} <p ng-if="x.TransactionHash"> To: <br><a href="https://www.blockchain.com/btc/address/{{x.address_to}}" target="_blank">{{x.address_to}} </a>
                    <br /><a href="https://www.blockchain.com/btc/tx/{{x.TransactionHash}}" target="_blank">See this on blockchain </a> </p>
            </td> 
            <td> {{x.cr}}</td>
            <td> {{x.dr}}</td>
          
            <td> {{x.time}}</td>
        </tr>
    </tbody>	</table>





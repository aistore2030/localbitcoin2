<%-- 
    Document   : BuyBitCoin
    Created on : Jan 12, 2018, 12:57:20 PM
    Author     : Saksham
--%>


<h1>Buy Bitcoins online  </h1>
<table id="example"  class="table  table-striped table-hover">

    <thead>
        <tr>	<th>ID</th>
            <th>Seller</th>
            <th>Price/BRL</th>
            <th>Limits</th>
            <th>Action</th>



        </tr>
    </thead>

    <tbody>
        <tr ng-repeat="x in myTxt">
            <td>{{x.id}}</td>
            <td><a ui-sref="PublicProfile({username:x.username})">{{x.name}}</a></td>
            <td >{{x.price}} {{x.currency}}</td>
            <td style="font-size: 16px;font-weight: bold;color: green;">{{x.min_transaction}} {{x.currency}} - {{x.max_transcation}} {{x.currency}}</td>
            <td><a class="btn btn-default megabutton" ui-sref="BuyBitcoinOrder({id:x.id})">Buy</a></td></tr>
    </tbody></table>
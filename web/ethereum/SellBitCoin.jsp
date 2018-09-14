<%-- 
    Document   : SellBitCoin
    Created on : Jan 12, 2018, 6:45:08 AM
    Author     : Saksham
--%>

<h1>Sell Bitcoins online </h1>
<table id="example"  class="table  table-striped table-hover">

    <thead>
        <tr>	<th>ID</th>
            <th>Buyer</th>
            <th>Price/BRL</th>
            <th>Limits</th>
            <th>Action</th>










        </tr>
    </thead>
    <tr ng-repeat="x in myTxt">
        <td>{{x.id}}</td>
        <td><a ui-sref="PublicProfile({username:x.username})">{{x.name}}</a></td>
        <td>{{x.price}} USD</td>
        <td style="font-size: 16px;font-weight: bold;color: green;">{{x.min_transaction}} USD - {{x.max_transcation}} USD</td>
        <td>
            <a class="btn btn-default megabutton" ui-sref="SellBitcoinOrder({id:x.id})">Sell</a></td></tr>

    <tbody></tbody></table>

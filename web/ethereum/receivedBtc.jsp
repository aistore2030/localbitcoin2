<%-- 
    Document   : receivedBtc
    Created on : May 2, 2018, 1:54:19 PM
    Author     : Saksham
--%>



<table class="table">

    <thead>
        <tr>	
            <th>ID</th>
            <th>username </th>
            <th>address </th>
            <th> Amount </th>
            <th> confirmations </th>
            <th>Time</th>
        </tr>
    </thead>

    <tbody>


        <tr  ng-repeat="y1 in x| orderBy:'-id'">   
            <td>{{y1.id}} </td>
            <td>{{y1.username}} </td>
            <td> {{y1.address}}</td> 
            <td> {{y1.amount}}</td>

            <td> {{y1.confirmations}}</td>
            <td> {{y1.date}}</td>
        </tr>
    </tbody>	
</table>  

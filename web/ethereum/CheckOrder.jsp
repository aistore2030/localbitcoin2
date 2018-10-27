<%-- 
    Document   : CheckOrder
    Created on : Jan 13, 2018, 10:50:41 AM
    Author     : Saksham
--%>

<table id="example"  class="table  table-bordered table-condensed">
    <thead>
        <tr>
            <th>ID</th>
            <th>Head</th>
            <th>Client</th>

            <th>Toatal Bitcoin Needes</th>
            <th>Payment Status</th>
            <th>Status</th>
            <th>Chat</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <tr ng-repeat="x in myTxt">
            <td>{{x.id}}</td>
            <td>{{x.head}}</td>
            <td>{{x.client}}</td>
            <td>{{x.btc_needed}}</td>
            <td>{{x.payment_status}}</td>
            <td>{{x.status}}</td>
            <td> <a ui-sref="Chatbox({id:x.id})">Start Chat</a></td>
            <td> <a ng-click="Dispute(x)">Dispute</a><br>
                <a  ng-click="Suspand(x)">Suspand/End Chat</a>
                <br><a  ng-click="Confirm(x)">Confirm</a></td>

        </tr>
    </tbody></table>

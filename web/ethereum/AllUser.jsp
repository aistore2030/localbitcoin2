<h3>All Registered User in the system </h3>
<div class="row">
    <div class="col-md-4">
        <form>
            <input class="form-control" type="text" placeholder="Search" ng-model="q">
        </form>
    </div>
    <div class="col-md-8"></div>
</div>

<table id="example"  class="table  table-bordered table-condensed">

    <thead>
        <tr>
            <th>ID</th>
            <th>Name<br /> Email <br /> Bitcoin Address</th>  <th>Balance in (BTC | USD)</th>





            <th>Action</th>

        </tr>
    </thead>

    <tbody>
        <tr ng-repeat="x in myTxt| orderBy:'-id'|filter:q" >
            <td>{{x.id}}</td>
            <td><b>{{x.name}} <br />{{x.email}} <br /> {{x.bitcoinaddress}}</b></td>
            <td> 
                {{x.BTC}} BTC | {{x.USD}} USD</td>



            <td> <a ng-click="Suspand(x)" >Suspand</a> | <a ng-click="Verified(x)" >Verified</a> | 
                <a ng-click="Block(x)" >Block payment</a> | <a ng-click="Delete(x)" >Delete</a> | <a ng-click="resetGoogleAuth(x)" >Remove Google Auth</a> | <a ui-sref="CreditToUser({username:x.email})" >Credit</a> | <a ui-sref="DebitToUser({username:x.email})" >Debit</a> 

            </td>
        </tr>
    </tbody>
</table>
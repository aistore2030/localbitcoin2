<%-- 
    Document   : PaymentGateway
    Created on : Apr 23, 2018, 8:20:30 AM
    Author     : Saksham
--%>


<form >
    <div class="row">
        <div class="col-md-6">
            <div class="form-group">
                <input class="form-control" type="text" name="fees" ng-model="x.fees" placeholder="fees">
            </div>
            <div class="form-group">
                <input class="form-control" type="text" name="amount"  ng-model="x.amount" placeholder="amount in USD">
            </div>
            <button type="submit" ng-click="Submit(x)" class="btn btn-success">Submit</button>
        </div>
    </div>

</form>

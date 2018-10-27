<%-- 
    Document   : profile
    Created on : Nov 15, 2017, 3:34:00 PM
    Author     : mala singh
--%>
<div class="note note-info">




    <div class="row">

        <div class="col-md-6">
            <div class="panel">
                <div class="panel-body">
                    <form> <h3> All Details </h3><b>Balance:</b>{{x.balance}}
                        <div class="form-group">
                            <label for="name">Name </label>
                            <input type="text" class="form-control" id="call_back_url" ng-model="x.name" value= "{{x.name}}">
                        </div>

                        <div class="form-group">
                            <label for="email">Email </label>
                            <input type="text" class="form-control" id="call_back_url" ng-model="x.email"  value= "{{x.email}}" >
                        </div>


                        <div class="form-group">
                            <label for="mobile">Mobile </label>
                            <input type="text" class="form-control" id="call_back_url"  ng-model="x.phone"  value= "{{x.phone}}" >
                        </div>
                        <!-- <div class="form-group">
                                                <label for="account">Account Number </label>
                                                <input type="text" class="form-control" id="call_back_url"  ng-model="x.account" required>
                                            </div>-->
                        <button type="submit" ng-click="UpdateDetails(x)" class="btn btn-success">Submit</button>

                    </form>


                </div>
            </div> </div><div class="col-md-6"> </br>
            </br><h2> <a class="dropdown-item" ui-sref="CheckOrder">Check ongoing order</a> </h2></br>
            <h1 ng-show="x.google_auth_status === 'Disable'">Enable <a  ui-sref="GoogleAuth">Two-factor Authentication </a> </h1></div></div></div> 


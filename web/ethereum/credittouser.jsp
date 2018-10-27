<%-- 
    Document   : credittouser
    Created on : Apr 2, 2018, 5:23:57 PM
    Author     : Saksham
--%>


<h1>Credit form</h1>
<form>
    <div >
        <div class="form-group"> 
            <label  > {{x.cse}} to : </label>
            <input class="form-control" ng-model="x.party" value="{{x.party}}" type="text" readonly name="username" readonly/>
        </div>
        <div class="form-group"> 
            <label  > select coin </label>
            <select class="form-control" ng-model="x.coin">

                <option value="bitcoin">BitCoin</option>
                <!--    <option value="eth">Ethereum</option>
                   <option value="bch">BitCoinCash</option>-->
            </select>
        </div>
        <div class="form-group">	
            <label  >Amount in BTC</label>
            <input class="form-control" ng-model="x.cr"   name="amount" />
        </div>  

        <div class="form-group">	
            <label  >Description </label>
            <input class="form-control" ng-model="x.description" type="text" name="amount" />
        </div> 
        <div class="form-group">
            <input type="submit" class="btn btn-success" ng-click="submit(x)"  name="submit" value="Submit" />
        </div>
    </div></form>
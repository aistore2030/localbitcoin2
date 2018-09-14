


<h2>Wallet Setting</h2>
<div class="row">    <div class="col-md-9">


        <form>   

            <input class="form-control"  value="{{x.username}}" type="hidden" readonly ng-model="x.username" />
            <h3>Outgoing payment settings</h3>
            
            
            <div class="form-group">
                <label  > Wallet Index  : </label>
                <input class="form-control"  type="text"  ng-model="x.wallet_index" required/>
            </div>
            
            
             <div class="form-group">
                <label  >Guid  : </label>
                <input class="form-control"  type="text"  ng-model="x.guid" required/>
            </div>
             <div class="form-group">
                <label  > Wallet Password  : </label>
                <input class="form-control"  type="text"  ng-model="x.password" required/>
            </div>
             <div class="form-group">
                <label > Transaction fee (value in satoshi)  : </label>
                <input class="form-control"  type="text"  ng-model="x.fee" required/>
            </div>
            
            
             <h3>Incoming Payment Setting</h3>
            
            <div class="form-group"> 
                <label  > Public key : </label>
                <input class="form-control"  type="text"  ng-model="x.public_key" required/>
            </div>
            <div class="form-group">
                <label  >Gap limit : </label>
                <input class="form-control"  type="text"  ng-model="x.gap_limit" required/>
            </div>
            
            <h3>Blockchain API Code</h3>
            
            
            <div class="form-group">
                <label  > Api code : </label>
                <input class="form-control"  type="text"  ng-model="x.api_code" required/>
            </div>
            
            
           
            <div class="form-group">
                
                <input type="button" ng-click="Submit(x)" class="btn btn-success" value="Apply" />
            </div>

        </form>







    </div></div> 




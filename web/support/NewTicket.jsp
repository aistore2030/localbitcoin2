<%-- 
    Document   : Message_report
    Created on : Feb 9, 2018, 3:25:33 PM
    Author     : susheel
--%>
  
 <section class="site-content site-section">
                <div class="container">
                    <div class="row">

<div   class="col-5" ng-repeat="x in records">


     
    <div class="z-depth-2"  >
        <div class="card-body" >
            <div class="blue-text mb-0" ><h4>{{x.username}}</h4><p>{{x.message}}</p>
                <p><a ng-show="x.status === 'Enable'" class="btn btn-primary  btn-sm" ui-sref="MsgResponse({id:x.id,username:x.username})">Reply</a>
                <a ng-show="x.status === 'Enable'" class="btn btn-primary   btn-sm"  ng-click="Close(x)">Close</a>
                                    <a ng-show="x.status === 'Disable'" class="btn btn-primary   btn-sm"  ng-click="Open(x)">Open</a> </p>
            </div>
        </div> 


    </div>


</div>

</div></div>

            </section>

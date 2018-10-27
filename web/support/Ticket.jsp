<%-- 
    Document   : message
    Created on : Feb 9, 2018, 1:58:08 PM
    Author     : susheel
--%>

<section class="site-content site-section">
    <div class="container">
        <div class="row">

            <div class="col-md-6">
                <div class="panel">
                    <div class="panel-body">
                        <h3 class="page-title">Add Ticket
                            <small> </small>
                        </h3>

                        <form>
                            <div class="form-group">

                                <div class="form-group">
                                    <b>Subject</b>:<input class="form-control" type = "text" placeholder = "Subject (required)" ng-model="x.subject"  id = "name" style="width:296px ; height:40px">
                                </div>

                                <div class="form-group">
                                    <b>Category</b>:<input class="form-control" type = "text" placeholder = "Category (required)" ng-model="x.category"  id = "name" style="width:296px ; height:40px">
                                </div>          
                                <div class="form-group">
                                    <b>Message</b>: <textarea class="form-control" rows="2" cols="50" type="text" placeholder = "Message (required)" ng-model="x.message" style="width:296px ; height:97px" ></textarea>
                                </div> 
                                <div class="form-group"> <b>priority</b>

                                    <select class="form-control" type="text" ng-model="x.priority"  required>
                                        <option ng-repeat="x in priority" ng-selected="x.priority" placeholder="Type Status"  value="{{x.priority}}">
                                            {{x.priority}}
                                        </option>
                                    </select></div>
                                <div class="form-group"> <label  >Status</label>
                                    <select class="form-control" type="text" ng-model="x.status"  required>
                                        <option ng-repeat="x in status" ng-selected="x.status" placeholder="Type Status" value="{{x.status}}"required >
                                            {{x.status}}
                                        </option>
                                    </select>
                                </div>   

                                <div class="form-group">

                                    <input type="button" ng-click="submit(x)" class="btn btn-success" value="submit" />
                                </div>

                        </form>  
                    </div>
                </div> </div></div><div class="col-md-6"></div></div>
</div>

</section>
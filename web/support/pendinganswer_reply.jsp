<%-- 
    Document   : MessageRespons
    Created on : Feb 9, 2018, 5:18:08 PM
    Author     : susheel
--%>
 
 <section class="site-content site-section">
                <div class="container">
                    <div class="row">
                        <div class="col-md-6">
            <div class="panel">
    <div class="panel-body">
        <h3>Ticket Response</h3><br>
           <h4 class="page-title">Write Your query
                <small> </small>
            </h4>
          
            <form>
       
<div class="form-group">
                    <input type="hidden" ng-model="x.id"  value="{{x.id}}"/></div>
    

          <div class="form-group">
                    <input type="hidden" ng-model="x.username" /></div>

                
   <div class="form-group">
      <b>Response</b>: <textarea class="form-control" rows="2" cols="50" type="text" placeholder = "Response (required)" ng-model="x.response" style="width:296px ; height:97px" ></textarea>
        </div> 
                
                
<div class="form-group">

                  <input type="button" ng-click="submit(x)" class="btn btn-success" value="submit" />
</div>
 
</form>  

    </div>  </div> </div> <div class="col-md-6">
            <div class="panel">
                <div class="panel-body">
                     {{x.message}}
                     
                </div></div></div>
    
  <% String username = String.valueOf(session.getAttribute("username"));
int roll = Integer.parseInt(String.valueOf(session.getAttribute("roll")));%>

  
<table class="table table-striped">
    <thead>
   <th>Name</th><th>Response</th></tr>
    </thead>
    <tbody>
        
        
    <tr ng-repeat="x in records">
        
      <td>{{x.username}}</td>
    <td>{{x.msg_response}}</td>
    
    </tr>
    </tbody>
</table>
   </div></div>

            </section>
  


 
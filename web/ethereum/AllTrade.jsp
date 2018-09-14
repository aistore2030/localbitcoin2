<%-- 
    Document   : AllTrade
    Created on : Aug 7, 2018, 4:37:29 PM
    Author     : susheel
--%>
<% 
    int roll = Integer.parseInt(String.valueOf(session.getAttribute("roll")));%>
<h1>All Trade </h1>
<div class="row">
    <div class="col-md-4">
        <form>
            <input class="form-control" type="text" placeholder="Search" ng-model="q">
        </form>
    </div>
    <div class="col-md-8"></div>
</div>
<table id="example"  class="table  table-striped table-hover">

    <thead>
        <tr>

            <th>Id</th>
            <th>Buyer</th>
            <th>Price</th>
            <th>Limits</th>
            <th>Trade Type</th>
                <%if(roll==10){%>
            <th>Action</th>

            <%}%>

        </tr>
    </thead>

    <tbody>
        <tr ng-repeat="x in myTxt| filter:q">
            <td>{{x.id}}</td>
            <td>{{x.username}}</td>
            <td >{{x.price}}BRL</td>
            <td style="font-size: 16px;font-weight: bold;color: green;">{{x.min_transaction}}BRL -- {{x.max_transcation}}BRL</td>
            <td >{{x.trade_type}}</td>
            <%if(roll==10){%>
            <td > <select class="form-control" type="text" ng-model="x.trade_type"  required>
                    <option  value="free">
                        free
                    </option>
                    <option  value="Premium">
                        Premium
                    </option>
                </select><br>
                <div class="form-group">
                    <input type="button" ng-click="Update(x)" class="btn btn-success" value="submit" />
                </div>
            </td>
            <%}%>


    </tbody></table>
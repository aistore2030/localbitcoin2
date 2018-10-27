<%-- 
    Document   : chatbox
    Created on : Jan 12, 2018, 4:33:29 PM
    Author     : Saksham
--%>
<%@page import="java.net.URL"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.login.Util"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
    String username = String.valueOf(session.getAttribute("username"));
    String url = request.getRequestURL().toString();
    URL url1 = new URL(url);
    String domain = url1.getHost();
%>
<div class="row">
    <div class="col-md-12">

        <div ng-show="{{Message}}" id="base-status-messages" class="alert alert-safe alert-success ">
            <a class="close" href="#" data-dismiss="alert">×</a>


            <strong class="alert-heading">Success.</strong>

            Your contact to the advertiser is sent. Watch your email.

        </div>

    </div>
</div>
<div class="row">
    <div class="col-md-12"><h1>{{x1.Message}}</h1>
        <p class="lead">

            Selling in <a href="#">advertisement # {{id}}</a> {{myTxt1.payment_method}} to 
            <a href="/p/dssmsg/">{{myTxt1.name}}</a> 
        </p>

        <h2>Sell BitCoin using {{myTxt1.payment_method}} with {{myTxt1.currency}} </h2>
    </div>
</div>
<div class="row">
    <div class="col-md-6">
        <div class="panel panel-success">
            <div class="panel-heading">Send Message</div>
            <div class="panel-body">     
                <form>
                    <div class="form-group">

                        <b>Add Response</b>: 
                        <textarea class="form-control" rows="2" cols="50" type="text" ng-model="u.message" style="width:296px ; height:97px" ></textarea></br>
                    </div>    
                </form> 
                <h4 ng-show="uploadimage">Select files</h4>

                <p ng-show="uploadimage" class="display-none" id="attach_doc" style="display: block;">
                    <small>Attach document (PNG and JPEG files only, take a screenshot if necessary): </small>
                    <input type="file" nv-file-select="" uploader="uploader" />
                <div class="form-group">
                    <label for="name"></label>
                    <input  class="form-control" type="hidden" ng-model="u.filename"  required>
                </div>

                <p ng-show="uploadimage">Queue length: {{ uploader.queue.length}}</p>
                <table class="table" ng-show="uploadimage">
                    <thead>
                        <tr>
                            <th width="50%">Name</th>

                            <th>Status</th>
                            <th>Actions</th>

                        </tr>
                    </thead>
                    <tbody>
                        <tr ng-repeat="item in uploader.queue">
                            <td><strong>{{ item.file.name}}</strong></td>

                            <td class="text-center">
                                <span ng-show="item.isSuccess"><i class="fa fa-check" aria-hidden="true"></i>
                                </span>
                                <span ng-show="item.isCancel"><i class="fas fa-ban"></i></span>
                                <span ng-show="item.isError"><i class="glyphicon glyphicon-remove"></i></span>
                            </td>
                            <td nowrap>

                                <div class="panel-footer">
                                    <button type="button" class="btn btn-success btn-sm" ng-click="item.upload()" ng-disabled="item.isReady || item.isUploading || item.isSuccess">
                                        <span class="glyphicon glyphicon-upload"></span> Upload
                                    </button>
                                </div> 

                            </td>
                        </tr>
                    </tbody>
                </table>
                <button ng-show="uploadimage" type="submit" ng-click="Upload(u)" class="btn btn-success">Upload</button>
                </p>
            </div>


            <div class="panel-footer">
                <div class="form-group">

                    <input type="button" ng-click="submit(u)" class="btn btn-success" value="submit" />
                    <input ng-show="noupload" type="button" ng-click="Attachdocument()" class="btn btn-default" value="Attach document" />
                    <input ng-show="upload" type="button" ng-click="Canceldocument()" class="btn btn-default" value="Don't attach document" />
                </div>


            </div>
        </div>


        <div ng-repeat="x in myTxt" class="panel panel-default">
            <div class="panel-body">

                <b>{{x.id}}.</b>&nbsp; {{x.message}}

                <a  href="http://<%=domain%>/upload/{{x.username}}/{{x.id}}/{{x.image}}">{{x.image}}</a>

            </div>
            <div class="panel-footer">{{x.username}}&nbsp;&nbsp;&nbsp;&nbsp; {{x.time}}</div>
        </div > <hr>
    </div>

    <div class="col-md-6">   
        <div class="panel panel-default">
            <div class="panel-heading"><h3 class="form-title font-green">Step 1:- Pay the seller</h3>
                <h5><b>Send message</b> to compelete payment</h5></div>
            <div class="panel-body">


            </div>
            <div class="panel-footer">  

                <form class="login-form" >
                    <div class="panel-group" id="accordion_terms_of_trade" role="tablist" aria-multiselectable="true">
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="heading_terms_of_trade">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion_terms_of_trade" data-target="#demo" href="#collapse_terms_of_trade" aria-expanded="true" aria-controls="collapse-cancel">
                                        <i class="fa fa-chevron-right"></i>&nbsp;Terms of trade with <i ng-show="myTxt1.type === 'buyBit'">{{myTxt1.host}}</i>
                                        <i ng-show="myTxt1.type === 'sellBit'">{{myTxt1.client}}</i>
                                    </a>
                                </h4>
                            </div>
                            <div id="demo" class="collapse" role="tabpanel" aria-labelledby="heading_terms_of_trade">
                                <div class="panel-body messagetext">
                                    {{myTxt1.terms}}

                                </div>
                            </div>
                        </div>
                    </div>

                    <h3 class="form-title font-green">Step 2:- Confirm the payment</h3>


                    <div class="form-actions" ng-show="(myTxt1.status === 'Paid/NotConfirm') && (myTxt1.host === '<%=username%>' || '<%=username%>' === 'admin')">
                        <div class=" text-center" ng-show="myTxt1.type === 'buyBit'"> 
                            <button type="submit" ng-click="ReleaseInCaseBuy(z)" class="btn btn-primary center">Release</button>
                        </div>
                        <div class=" text-center" ng-show="myTxt1.type === 'sellBit'"> 
                            <button type="submit" ng-click="ReleaseInCaseSell(z)" class="btn btn-primary center">Release</button>
                        </div>

                    </div>
                    <div class=" text-center" >
                        <p ng-show="myTxt1.client === '<%=username%>' && myTxt1.status === 'Paid/NotConfirm'">You have paid for this coin</p>
                    </div>

                    <div class=" text-center" >
                        <p ng-show="(myTxt1.host === '<%=username%>' || '<%=username%>' === 'admin') && (myTxt1.status === 'Release')">You have confirmed the payment</p>
                    </div>

                    <div class="form-actions" ng-show="myTxt1.status === 'Started' && myTxt1.client === '<%=username%>'">
                        <div class=" text-center"> 
                            <button type="submit" ng-click="paymnet_bycllient(z)" class="btn btn-primary center">I have paid</button> 

                        </div>

                    </div>





                </form>
            </div> 
        </div>





        <hr>

        <div class="well">
            <h3>Resolving trade issues</h3>

            <div class="accordion" id="accordion-issues">

                <p>

                    See <i>More information about trading</i> below for common issue resolutions.

                </p>


                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">

                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingCancel">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapse-cancel" aria-expanded="false" aria-controls="collapse-cancel" class="collapsed">
                                    <i class="fa fa-chevron-right"></i>&nbsp;Canceling the trade
                                </a>
                            </h4>
                        </div>

                        <div id="collapse-cancel" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingCancel" aria-expanded="false" style="height: 0px;">
                            <div class="panel-body">
                                <p>
                                    Made a mistake with payment or want to try another seller?
                                    <strong>Never cancel if you already paid the seller.</strong>
                                </p>
                                <div class=" text-center" >
                                    <p ng-show="myTxt1.client === '<%=username%>' && myTxt1.status === 'Cancel'">You have cancel this order</p>
                                </div>

                                <div class=" text-center" >
                                    <p ng-show="myTxt1.host === '<%=username%>' && myTxt1.status === 'Cancel'">This order has been cancelled by user</p>
                                </div>
                                <div class="form-actions" ng-show="myTxt1.status === 'Started' && myTxt1.client === '<%=username%>'">
                                    <div class=" text-center"> 

                                        <button ng-show="myTxt1.type === 'buyBit'" type="submit" ng-click="Cancel_in_case_Buy(z)" class="btn btn-danger center">Cancel purchase</button>
                                        <button ng-show="myTxt1.type === 'sellBit'" type="submit" ng-click="Cancel_in_case_Sell(z)" class="btn btn-danger center">Cancel purchase</button>

                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr>
        <div class="well">





            <h3>Resolving trade issues</h3>

            <div class="accordion" id="accordion-issues">

                <p>

                    See <i>More information about trading</i> below for common issue resolutions.

                </p>


                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">

                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingCancel">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapse-dispute" aria-expanded="false" aria-controls="collapse-dispute" class="collapsed">
                                    <i class="fa fa-chevron-right"></i>&nbsp;Dispute the trade
                                </a>
                            </h4>
                        </div>

                        <div id="collapse-dispute" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingCancel" aria-expanded="false" style="height: 0px;">
                            <div class="panel-body">
                                <p>
                                    Made a mistake with payment or want to try another seller?
                                    <strong>Never cancel if you already paid the seller.</strong>
                                </p>
                                <form>
                                    <div class="form-actions">
                                        <div class=" text-center" ng-hide="myTxt1.status === 'Dispute'"> 
                                            <button type="submit" ng-click="Dispute(z)" class="btn btn-danger center">Dispute</button>
                                        </div>

                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>


                </div>





            </div>




        </div>

    </div>

</div>

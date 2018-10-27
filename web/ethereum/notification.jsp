<%-- 
    Document   : notification
    Created on : Jan 16, 2018, 12:08:30 PM
    Author     : Saksham
--%>
<div class="note note-info">




    <div class="row">

        <div class="col-md-6">
            <div class="panel">
                <div class="panel-body">

                    <h1>All Notification</h1>
                    <ul class="nav nav-tabs nav-justified" role="tablist">
                        <div ng-repeat="x in notification| orderBy : 'id'">
                            <li class="list-group-item"><b>{{x.id}}.</b>{{x.notification}}</li>
                        </div>
                    </ul>
                </div>
            </div>
        </div>
    </div>
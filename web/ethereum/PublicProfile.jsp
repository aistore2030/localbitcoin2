<%-- 
    Document   : PublicProfile
    Created on : Jan 17, 2018, 9:00:34 AM
    Author     : Saksham
--%>

<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h1>{{x.username}}

            </h1>
            <div id="profile-info">
                <h2>
                    Information on {{x.username}}
                </h2>
                <div class="row shaded" id="trade_volume_row">
                    <div class="col-md-5 profile-label">
                        Trade volume
                    </div>
                    <div class="col-md-6 profile-value">
                        20-50 BTC
                    </div>
                </div>
                <div class="row" id="confirmed_trades_row">
                    <div class="col-md-5 profile-label">
                        Number of confirmed trades
                    </div>
                    <div class="col-md-6 profile-value">
                        <strong class="F100_score"> 70+ </strong>

                    </div>
                </div>

                <div class="row shaded" id="feedback_score_row">
                    <div class="col-md-5 profile-label">
                        Feedback score
                    </div>
                    <div class="col-md-6 profile-value">
                        <strong class="F100_score">100 %</strong>
                    </div>
                </div><div class="row" id="first_buy_row">
                    <div class="col-md-5 profile-label">
                        First purchase
                    </div>
                    <div class="col-md-6 profile-value">
                        1 month ago
                    </div>
                </div>
                <div class="row" id="date_joined_row">
                    <div class="col-md-5 profile-label">
                        Account created
                    </div>
                    <div class="col-md-6 profile-value">
                        <abbr title={{x.date}}>
                            {{x.date}}
                        </abbr>
                    </div>
                </div>
                <!--   <div class="row shaded" id="last_seen_on_row">
                       <div class="col-md-5 profile-label">Last seen</div>
                       <div class="col-md-6 profile-value">
                           <abbr title="2018-01-17T08:18:36+00:00">
                               1 hour, 1 minute ago
                           </abbr>
                       </div>
                   </div>-->
                <div class="row shaded" id="trusted_row">
                    <div class="col-md-5 profile-label"> Language</div><div class="col-md-6 profile-value"> English</div>
                </div><div class="row" id="email_verified_row">
                    <div class="col-md-5 profile-label">
                        Email
                    </div>
                    <div class="col-md-6 profile-value">
                        <i class="good fa fa-check-square"></i> {{x.email_verified}}
                    </div>
                </div><div class="row shaded" id="phone_verified_row">
                    <div class="col-md-5 profile-label">
                        Phone number
                    </div>
                    <div class="col-md-6 profile-value">
                        <i class="good fa fa-check-square"></i> {{x.sms_verified}}
                    </div>
                </div><div class="row" id="identity_verified_row">
                    <div class="col-md-5 profile-label">
                        ID, Passport or Drivers license
                    </div>
                    <div class="col-md-6 profile-value">
                        <i class="good fa fa-check-square"></i> 
                        {{x.identified}}{{x.identification_document}}
                    </div>
                </div>
                <div class="row" id="trusted_row">
                    <div class="col-md-5 profile-label">
                        Trust Marked
                    </div>
                    <div class="col-md-6 profile-value">
                        {{x.trusted}} 
                    </div>
                </div>
                <div class="row shaded" id="trusted_row">
                    <div class="col-md-5 profile-label">
                        Blocks
                    </div>
                    <div class="col-md-6 profile-value">
                        Blocked by <strong>0</strong> people
                    </div>
                </div></div>
        </div><div class="col-md-5 profile-col-right">
            <div class="row well">
                <div class="col-md-12">
                    <button class="btn btn-profile-trust " disabled="disabled">
                        <i class="fa fa-star"></i> Trust {{x.username}}
                    </button>

                </div>
            </div>
            <div class="row well">
                <div class="col-md-12">
                    <h2>Seller escrow release times</h2>
                    <h3>Median: 19 min</h3>
                    <h3>Average: 17 min</h3>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 pull-right">
                    <small>
                        <a id="flag-user" href="#"
                           title="Notify the site administration about improper user">
                            <i class="fa fa-flag"></i>
                            Report this user
                        </a>
                    </small>
                </div>
            </div>
        </div>
    </div>



    <div class="row">
        <div class="col-md-12">
            <h2>Sell bitcoins online from {{x.username}}</h2>








            <table id="example"  class="table table-striped table-condensed table-bitcoins " >
                <thead>
                    <tr>	<th>ID</th>
                        <th>Seller</th>
                        <th>Price/BTC </th>
                        <th>Limits</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="x in y| filter:{ type :'Sell' }">
                        <td>{{x.id}}</td>
                        <td><a ui-sref="PublicProfile({username:x.username})">{{x.name}}</a></td>
                        <td >{{x.price}}$</td>
                        <td style="font-size: 16px;font-weight: bold;color: green;">{{x.min_transaction}}$ -- {{x.max_transcation}}$</td>
                        <td>
                            <a ng-show="x.type === 'Sell'" class="btn btn-default megabutton" ui-sref="SellBitcoinOrder({id:x.id})">{{x.type}}</a></td></tr>
                </tbody></table>
            <h2>Buy bitcoins online from {{x.username}}</h2>








            <table id="example"  class="table table-striped table-condensed table-bitcoins " >
                <thead>
                    <tr>	<th>ID</th>
                        <th>Seller</th>
                        <th>Price/BTC </th>
                        <th>Limits</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="x in y| filter:{ type :'Buy' }">
                        <td>{{x.id}}</td>{{x.type}}hgjhj
                        <td><a ui-sref="PublicProfile({username:x.username})">{{x.name}}</a></td>
                        <td >{{x.price}}$</td>
                        <td style="font-size: 16px;font-weight: bold;color: green;">{{x.min_transaction}}$ -- {{x.max_transcation}}$</td>
                        <td><a ng-show="x.type === 'Buy'" class="btn btn-default megabutton" ui-sref="BuyBitcoinOrder({id:x.id})">{{x.type}}</a>
                        </td></tr>
                </tbody></table>

        </div>
    </div>
  </div>








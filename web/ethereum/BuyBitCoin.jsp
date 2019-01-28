<%-- 
    Document   : BuyBitCoin
    Created on : Jan 12, 2018, 12:57:20 PM
    Author     : Saksham
--%>
<style>
    .a {
    margin-left:400px;
  }
    </style>
<uib-tabset active="activeForm">
    
    <uib-tab index="1" heading="QUICK BUY" ui-sref="BuyBitCoin" class="a">
        <div class="tab-pane active">
        <form>
            <div class="row">
                <div class="col-md-2">
                    <div class="form-group">
          <input  class="form-control" ng-model="x.price" type="text" name="amount" placeholder="Amount">
        </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                    <select class="form-control"  ng-model="x.currency" name="currency" >
                        <option ng-repeat="currenc in curr" ng-selected="currency===currenc.code" value="{{currenc.code}}">
                            {{currenc.name}}
                        </option>
                    </select>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
         <select class="form-control"  name="place_country">
<option value="AF">Afghanistan</option>
<option value="AL">Albania</option>
<option value="DZ">Algeria</option>
<option value="AS">American Samoa</option>
<option value="AD">Andorra</option>
<option value="AO">Angola</option>
<option value="AI">Anguilla</option>
<option value="AQ">Antarctica</option>
<option value="AG">Antigua and Barbuda</option>
<option value="AR">Argentina</option>
<option value="AM">Armenia</option>
<option value="AW">Aruba</option>
<option value="AU">Australia</option>
<option value="AT">Austria</option>
<option value="AZ">Azerbaijan</option>
<option value="BS">Bahamas</option>
<option value="BH">Bahrain</option>
<option value="BD">Bangladesh</option>
<option value="BB">Barbados</option>
<option value="BY">Belarus</option>
<option value="BE">Belgium</option>
<option value="BZ">Belize</option>
<option value="BJ">Benin</option>
<option value="BM">Bermuda</option>
<option value="BT">Bhutan</option>
<option value="BO">Bolivia</option>
<option value="BQ">Bonaire, Sint Eustatius and Saba</option>
<option value="BA">Bosnia and Herzegovina</option>
<option value="BW">Botswana</option>
<option value="BV">Bouvet Island</option>
<option value="BR">Brazil</option>
<option value="IO">British Indian Ocean Territory</option>
<option value="BN">Brunei Darussalam</option>
<option value="BG">Bulgaria</option>
<option value="BF">Burkina Faso</option>
<option value="BI">Burundi</option>
<option value="CV">Cabo Verde</option>
<option value="KH">Cambodia</option>
<option value="CM">Cameroon</option>
<option value="CA">Canada</option>
<option value="KY">Cayman Islands</option>
<option value="CF">Central African Republic</option>
<option value="TD">Chad</option>
<option value="CL">Chile</option>
<option value="CN">China</option>
<option value="CX">Christmas Island</option>
<option value="CC">Cocos (Keeling) Islands</option>
<option value="CO">Colombia</option>
<option value="KM">Comoros</option>
<option value="CG">Congo</option>
<option value="CD">Congo, The Democratic Republic of the</option>
<option value="CK">Cook Islands</option>
<option value="CR">Costa Rica</option>
<option value="HR">Croatia</option>
<option value="CU">Cuba</option>
<option value="CW">Curaçao</option>
<option value="CY">Cyprus</option>
<option value="CZ">Czechia</option>
<option value="CI">Côte d'Ivoire</option>
<option value="DK">Denmark</option>
<option value="DJ">Djibouti</option>
<option value="DM">Dominica</option>
<option value="DO">Dominican Republic</option>
<option value="EC">Ecuador</option>
<option value="EG">Egypt</option>
<option value="SV">El Salvador</option>
<option value="GQ">Equatorial Guinea</option>
<option value="ER">Eritrea</option>
<option value="EE">Estonia</option>
<option value="ET">Ethiopia</option>
<option value="FK">Falkland Islands (Malvinas)</option>
<option value="FO">Faroe Islands</option>
<option value="FJ">Fiji</option>
<option value="FI">Finland</option>
<option value="FR">France</option>
<option value="GF">French Guiana</option>
<option value="PF">French Polynesia</option>
<option value="TF">French Southern Territories</option>
<option value="GA">Gabon</option>
<option value="GM">Gambia</option>
<option value="GE">Georgia</option>
<option value="DE">Germany</option>
<option value="GH">Ghana</option>
<option value="GI">Gibraltar</option>
<option value="GR">Greece</option>
<option value="GL">Greenland</option>
<option value="GD">Grenada</option>
<option value="GP">Guadeloupe</option>
<option value="GU">Guam</option>
<option value="GT">Guatemala</option>
<option value="GG">Guernsey</option>
<option value="GN">Guinea</option>
<option value="GW">Guinea-Bissau</option>
<option value="GY">Guyana</option>
<option value="HT">Haiti</option>
<option value="HM">Heard Island and McDonald Islands</option>
<option value="VA">Holy See (Vatican City State)</option>
<option value="HN">Honduras</option>
<option value="HK">Hong Kong</option>
<option value="HU">Hungary</option>
<option value="IS">Iceland</option>
<option value="IN">India</option>
<option value="ID">Indonesia</option>
<option value="IR">Iran, Islamic Republic of</option>
<option value="IQ">Iraq</option>
<option value="IE">Ireland</option>
<option value="IM">Isle of Man</option>
<option value="IL">Israel</option>
<option value="IT">Italy</option>
<option value="JM">Jamaica</option>
<option value="JP">Japan</option>
<option value="JE">Jersey</option>
<option value="JO">Jordan</option>
<option value="KZ">Kazakhstan</option>
<option value="KE">Kenya</option>
<option value="KI">Kiribati</option>
<option value="KP">Korea, Democratic People's Republic of</option>
<option value="KR">Korea, Republic of</option>
<option value="KW">Kuwait</option>
<option value="KG">Kyrgyzstan</option>
<option value="LA">Lao People's Democratic Republic</option>
<option value="LV">Latvia</option>
<option value="LB">Lebanon</option>
<option value="LS">Lesotho</option>
<option value="LR">Liberia</option>
<option value="LY">Libya</option>
<option value="LI">Liechtenstein</option>
<option value="LT">Lithuania</option>
<option value="LU">Luxembourg</option>
<option value="MO">Macao</option>
<option value="MK">Macedonia, Republic of</option>
<option value="MG">Madagascar</option>
<option value="MW">Malawi</option>
<option value="MY">Malaysia</option>
<option value="MV">Maldives</option>
<option value="ML">Mali</option>
<option value="MT">Malta</option>
<option value="MH">Marshall Islands</option>
<option value="MQ">Martinique</option>
<option value="MR">Mauritania</option>
<option value="MU">Mauritius</option>
<option value="YT">Mayotte</option>
<option value="MX">Mexico</option>
<option value="FM">Micronesia, Federated States of</option>
<option value="MD">Moldova</option>
<option value="MC">Monaco</option>
<option value="MN">Mongolia</option>
<option value="ME">Montenegro</option>
<option value="MS">Montserrat</option>
<option value="MA">Morocco</option>
<option value="MZ">Mozambique</option>
<option value="MM">Myanmar</option>
<option value="NA">Namibia</option>
<option value="NR">Nauru</option>
<option value="NP">Nepal</option>
<option value="NL">Netherlands</option>
<option value="NC">New Caledonia</option>
<option value="NZ">New Zealand</option>
<option value="NI">Nicaragua</option>
<option value="NE">Niger</option>
<option value="NG">Nigeria</option>
<option value="NU">Niue</option>
<option value="NF">Norfolk Island</option>
<option value="MP">Northern Mariana Islands</option>
<option value="NO">Norway</option>
<option value="OM">Oman</option>
<option value="PK">Pakistan</option>
<option value="PW">Palau</option>
<option value="PS">Palestine, State of</option>
<option value="PA">Panama</option>
<option value="PG">Papua New Guinea</option>
<option value="PY">Paraguay</option>
<option value="PE">Peru</option>
<option value="PH">Philippines</option>
<option value="PN">Pitcairn</option>
<option value="PL">Poland</option>
<option value="PT">Portugal</option>
<option value="PR">Puerto Rico</option>
<option value="QA">Qatar</option>
<option value="RO">Romania</option>
<option value="RU">Russian Federation</option>
<option value="RW">Rwanda</option>
<option value="RE">Réunion</option>
<option value="BL">Saint Barthélemy</option>
<option value="SH">Saint Helena, Ascension and Tristan da Cunha</option>
<option value="KN">Saint Kitts and Nevis</option>
<option value="LC">Saint Lucia</option>
<option value="MF">Saint Martin (French part)</option>
<option value="PM">Saint Pierre and Miquelon</option>
<option value="VC">Saint Vincent and the Grenadines</option>
<option value="WS">Samoa</option>
<option value="SM">San Marino</option>
<option value="ST">Sao Tome and Principe</option>
<option value="SA">Saudi Arabia</option>
<option value="SN">Senegal</option>
<option value="RS">Serbia</option>
<option value="SC">Seychelles</option>
<option value="SL">Sierra Leone</option>
<option value="SG">Singapore</option>
<option value="SX">Sint Maarten (Dutch part)</option>
<option value="SK">Slovakia</option>
<option value="SI">Slovenia</option>
<option value="SB">Solomon Islands</option>
<option value="SO">Somalia</option>
<option value="ZA">South Africa</option>
<option value="GS">South Georgia and the South Sandwich Islands</option>
<option value="SS">South Sudan</option>
<option value="ES">Spain</option>
<option value="LK">Sri Lanka</option>
<option value="SD">Sudan</option>
<option value="SR">Suriname</option>
<option value="SJ">Svalbard and Jan Mayen</option>
<option value="SZ">Swaziland</option>
<option value="SE">Sweden</option>
<option value="CH">Switzerland</option>
<option value="SY">Syrian Arab Republic</option>
<option value="TW">Taiwan</option>
<option value="TJ">Tajikistan</option>
<option value="TZ">Tanzania</option>
<option value="TH">Thailand</option>
<option value="TL">Timor-Leste</option>
<option value="TG">Togo</option>
<option value="TK">Tokelau</option>
<option value="TO">Tonga</option>
<option value="TT">Trinidad and Tobago</option>
<option value="TN">Tunisia</option>
<option value="TR">Turkey</option>
<option value="TM">Turkmenistan</option>
<option value="TC">Turks and Caicos Islands</option>
<option value="TV">Tuvalu</option>
<option value="UG">Uganda</option>
<option value="UA">Ukraine</option>
<option value="AE">United Arab Emirates</option>
<option value="GB">United Kingdom</option>
<option value="US">United States</option>
<option value="UM">United States Minor Outlying Islands</option>
<option value="UY">Uruguay</option>
<option value="UZ">Uzbekistan</option>
<option value="VU">Vanuatu</option>
<option value="VE">Venezuela</option>
<option value="VN">Vietnam</option>
<option value="VG">Virgin Islands, British</option>
<option value="VI">Virgin Islands, U.S.</option>
<option value="WF">Wallis and Futuna</option>
<option value="EH">Western Sahara</option>
<option value="YE">Yemen</option>
<option value="ZM">Zambia</option>
<option value="ZW">Zimbabwe</option>
<option value="AX">Åland Islands</option>
</select>
     </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
          <select class="form-control" name="online_provider"><optgroup label="Popular in your country"><option value="BANK_TRANSFER_IMPS">IMPS Bank Transfer India</option><option value="CASH_DEPOSIT">Cash deposit</option><option value="MONEYBOOKERS">Moneybookers / Skrill</option><option value="NATIONAL_BANK">National bank transfer</option><option value="NETELLER">Neteller</option><option value="OTHER">Other online payment</option><option value="OTHER_ONLINE_WALLET">Other Online Wallet</option><option value="PAYEER">Payeer</option><option value="PERFECT_MONEY">Perfect Money</option><option value="SPECIFIC_BANK">Transfers with specific bank</option><option value="XOOM">Xoom</option></optgroup><optgroup label="All payment methods"><option value="ALL_ONLINE">All online offers</option><option value="ADVCASH">advcash</option><option value="ALTCOIN_ETH">Ethereum altcoin</option><option value="BANK_TRANSFER_IMPS">IMPS Bank Transfer India</option><option value="CASHU">CashU</option><option value="CASH_DEPOSIT">Cash deposit</option><option value="CREDITCARD">Credit card</option><option value="EGOPAY">EGOPAY</option><option value="GIFT_CARD_CODE">Gift Card Code</option><option value="GIFT_CARD_CODE_AMAZON">Amazon Gift Card Code</option><option value="GIFT_CARD_CODE_GLOBAL">Gift Card Code (Global)</option><option value="INTERNATIONAL_WIRE_SWIFT">International Wire (SWIFT)</option><option value="MONEYBOOKERS">Moneybookers / Skrill</option><option value="MONEYGRAM">Moneygram</option><option value="NATIONAL_BANK">National bank transfer</option><option value="NETELLER">Neteller</option><option value="OKPAY">OKPay</option><option value="ONECARD">OneCard</option><option value="OTHER">Other online payment</option><option value="OTHER_ONLINE_WALLET">Other Online Wallet</option><option value="PAXUM">Paxum</option><option value="PAYEER">Payeer</option><option value="PAYONEER">Payoneer</option><option value="PAYPAL">Paypal</option><option value="PAYTM">PayTM</option><option value="PAYZA">Payza</option><option value="PERFECT_MONEY">Perfect Money</option><option value="RIA">RIA Money Transfer</option><option value="SOLIDTRUSTPAY">SolidTrustPay</option><option value="TRANSFERWISE">Transferwise</option><option value="WEBMONEY">WebMoney</option><option value="WECHAT">WeChat</option><option value="WORLDREMIT">Worldremit</option><option value="WU">Western Union</option></optgroup><optgroup label="Altcoins"><option value="ALTCOIN_DASH">Dash altcoin</option><option value="ALTCOIN_XRP">Ripple altcoin</option><option value="ALTCOIN_LTC">Litecoin altcoin</option><option value="ALTCOIN_XMR">Monero altcoin</option><option value="ALTCOIN_ETH">Ethereum altcoin</option></optgroup><optgroup label="In-person"><option value="CASH">Cash</option></optgroup></select>
                    </div>
                </div>
                <div class="col-md-1">
                    <div class="form-group">
          <input type="button" ng-click="Search(x)" class="btn btn-success" value="Search" />
        </div>
                </div>
            </div>
        
        </form>
            </div>
    </uib-tab>
    
    <uib-tab index="2" heading="QUICK SELL" ui-sref="SellBitCoin">
        <form>
            <div class="row">
                <div class="col-md-2">
                    <div class="form-group">
          <input  class="form-control" ng-model="x.amount" type="text" name="amount" placeholder="Amount">
        </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                    <select class="form-control"  ng-model="x.currency" name="currency" >
                        <option ng-repeat="currenc in curr" ng-selected="currency===currenc.name" value="{{currenc.code}}">
                            {{currenc.name}}
                        </option>
                    </select>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
         <select class="form-control"  name="place_country">
<option value="AF">Afghanistan</option>
<option value="AL">Albania</option>
<option value="DZ">Algeria</option>
<option value="AS">American Samoa</option>
<option value="AD">Andorra</option>
<option value="AO">Angola</option>
<option value="AI">Anguilla</option>
<option value="AQ">Antarctica</option>
<option value="AG">Antigua and Barbuda</option>
<option value="AR">Argentina</option>
<option value="AM">Armenia</option>
<option value="AW">Aruba</option>
<option value="AU">Australia</option>
<option value="AT">Austria</option>
<option value="AZ">Azerbaijan</option>
<option value="BS">Bahamas</option>
<option value="BH">Bahrain</option>
<option value="BD">Bangladesh</option>
<option value="BB">Barbados</option>
<option value="BY">Belarus</option>
<option value="BE">Belgium</option>
<option value="BZ">Belize</option>
<option value="BJ">Benin</option>
<option value="BM">Bermuda</option>
<option value="BT">Bhutan</option>
<option value="BO">Bolivia</option>
<option value="BQ">Bonaire, Sint Eustatius and Saba</option>
<option value="BA">Bosnia and Herzegovina</option>
<option value="BW">Botswana</option>
<option value="BV">Bouvet Island</option>
<option value="BR">Brazil</option>
<option value="IO">British Indian Ocean Territory</option>
<option value="BN">Brunei Darussalam</option>
<option value="BG">Bulgaria</option>
<option value="BF">Burkina Faso</option>
<option value="BI">Burundi</option>
<option value="CV">Cabo Verde</option>
<option value="KH">Cambodia</option>
<option value="CM">Cameroon</option>
<option value="CA">Canada</option>
<option value="KY">Cayman Islands</option>
<option value="CF">Central African Republic</option>
<option value="TD">Chad</option>
<option value="CL">Chile</option>
<option value="CN">China</option>
<option value="CX">Christmas Island</option>
<option value="CC">Cocos (Keeling) Islands</option>
<option value="CO">Colombia</option>
<option value="KM">Comoros</option>
<option value="CG">Congo</option>
<option value="CD">Congo, The Democratic Republic of the</option>
<option value="CK">Cook Islands</option>
<option value="CR">Costa Rica</option>
<option value="HR">Croatia</option>
<option value="CU">Cuba</option>
<option value="CW">Curaçao</option>
<option value="CY">Cyprus</option>
<option value="CZ">Czechia</option>
<option value="CI">Côte d'Ivoire</option>
<option value="DK">Denmark</option>
<option value="DJ">Djibouti</option>
<option value="DM">Dominica</option>
<option value="DO">Dominican Republic</option>
<option value="EC">Ecuador</option>
<option value="EG">Egypt</option>
<option value="SV">El Salvador</option>
<option value="GQ">Equatorial Guinea</option>
<option value="ER">Eritrea</option>
<option value="EE">Estonia</option>
<option value="ET">Ethiopia</option>
<option value="FK">Falkland Islands (Malvinas)</option>
<option value="FO">Faroe Islands</option>
<option value="FJ">Fiji</option>
<option value="FI">Finland</option>
<option value="FR">France</option>
<option value="GF">French Guiana</option>
<option value="PF">French Polynesia</option>
<option value="TF">French Southern Territories</option>
<option value="GA">Gabon</option>
<option value="GM">Gambia</option>
<option value="GE">Georgia</option>
<option value="DE">Germany</option>
<option value="GH">Ghana</option>
<option value="GI">Gibraltar</option>
<option value="GR">Greece</option>
<option value="GL">Greenland</option>
<option value="GD">Grenada</option>
<option value="GP">Guadeloupe</option>
<option value="GU">Guam</option>
<option value="GT">Guatemala</option>
<option value="GG">Guernsey</option>
<option value="GN">Guinea</option>
<option value="GW">Guinea-Bissau</option>
<option value="GY">Guyana</option>
<option value="HT">Haiti</option>
<option value="HM">Heard Island and McDonald Islands</option>
<option value="VA">Holy See (Vatican City State)</option>
<option value="HN">Honduras</option>
<option value="HK">Hong Kong</option>
<option value="HU">Hungary</option>
<option value="IS">Iceland</option>
<option value="IN">India</option>
<option value="ID">Indonesia</option>
<option value="IR">Iran, Islamic Republic of</option>
<option value="IQ">Iraq</option>
<option value="IE">Ireland</option>
<option value="IM">Isle of Man</option>
<option value="IL">Israel</option>
<option value="IT">Italy</option>
<option value="JM">Jamaica</option>
<option value="JP">Japan</option>
<option value="JE">Jersey</option>
<option value="JO">Jordan</option>
<option value="KZ">Kazakhstan</option>
<option value="KE">Kenya</option>
<option value="KI">Kiribati</option>
<option value="KP">Korea, Democratic People's Republic of</option>
<option value="KR">Korea, Republic of</option>
<option value="KW">Kuwait</option>
<option value="KG">Kyrgyzstan</option>
<option value="LA">Lao People's Democratic Republic</option>
<option value="LV">Latvia</option>
<option value="LB">Lebanon</option>
<option value="LS">Lesotho</option>
<option value="LR">Liberia</option>
<option value="LY">Libya</option>
<option value="LI">Liechtenstein</option>
<option value="LT">Lithuania</option>
<option value="LU">Luxembourg</option>
<option value="MO">Macao</option>
<option value="MK">Macedonia, Republic of</option>
<option value="MG">Madagascar</option>
<option value="MW">Malawi</option>
<option value="MY">Malaysia</option>
<option value="MV">Maldives</option>
<option value="ML">Mali</option>
<option value="MT">Malta</option>
<option value="MH">Marshall Islands</option>
<option value="MQ">Martinique</option>
<option value="MR">Mauritania</option>
<option value="MU">Mauritius</option>
<option value="YT">Mayotte</option>
<option value="MX">Mexico</option>
<option value="FM">Micronesia, Federated States of</option>
<option value="MD">Moldova</option>
<option value="MC">Monaco</option>
<option value="MN">Mongolia</option>
<option value="ME">Montenegro</option>
<option value="MS">Montserrat</option>
<option value="MA">Morocco</option>
<option value="MZ">Mozambique</option>
<option value="MM">Myanmar</option>
<option value="NA">Namibia</option>
<option value="NR">Nauru</option>
<option value="NP">Nepal</option>
<option value="NL">Netherlands</option>
<option value="NC">New Caledonia</option>
<option value="NZ">New Zealand</option>
<option value="NI">Nicaragua</option>
<option value="NE">Niger</option>
<option value="NG">Nigeria</option>
<option value="NU">Niue</option>
<option value="NF">Norfolk Island</option>
<option value="MP">Northern Mariana Islands</option>
<option value="NO">Norway</option>
<option value="OM">Oman</option>
<option value="PK">Pakistan</option>
<option value="PW">Palau</option>
<option value="PS">Palestine, State of</option>
<option value="PA">Panama</option>
<option value="PG">Papua New Guinea</option>
<option value="PY">Paraguay</option>
<option value="PE">Peru</option>
<option value="PH">Philippines</option>
<option value="PN">Pitcairn</option>
<option value="PL">Poland</option>
<option value="PT">Portugal</option>
<option value="PR">Puerto Rico</option>
<option value="QA">Qatar</option>
<option value="RO">Romania</option>
<option value="RU">Russian Federation</option>
<option value="RW">Rwanda</option>
<option value="RE">Réunion</option>
<option value="BL">Saint Barthélemy</option>
<option value="SH">Saint Helena, Ascension and Tristan da Cunha</option>
<option value="KN">Saint Kitts and Nevis</option>
<option value="LC">Saint Lucia</option>
<option value="MF">Saint Martin (French part)</option>
<option value="PM">Saint Pierre and Miquelon</option>
<option value="VC">Saint Vincent and the Grenadines</option>
<option value="WS">Samoa</option>
<option value="SM">San Marino</option>
<option value="ST">Sao Tome and Principe</option>
<option value="SA">Saudi Arabia</option>
<option value="SN">Senegal</option>
<option value="RS">Serbia</option>
<option value="SC">Seychelles</option>
<option value="SL">Sierra Leone</option>
<option value="SG">Singapore</option>
<option value="SX">Sint Maarten (Dutch part)</option>
<option value="SK">Slovakia</option>
<option value="SI">Slovenia</option>
<option value="SB">Solomon Islands</option>
<option value="SO">Somalia</option>
<option value="ZA">South Africa</option>
<option value="GS">South Georgia and the South Sandwich Islands</option>
<option value="SS">South Sudan</option>
<option value="ES">Spain</option>
<option value="LK">Sri Lanka</option>
<option value="SD">Sudan</option>
<option value="SR">Suriname</option>
<option value="SJ">Svalbard and Jan Mayen</option>
<option value="SZ">Swaziland</option>
<option value="SE">Sweden</option>
<option value="CH">Switzerland</option>
<option value="SY">Syrian Arab Republic</option>
<option value="TW">Taiwan</option>
<option value="TJ">Tajikistan</option>
<option value="TZ">Tanzania</option>
<option value="TH">Thailand</option>
<option value="TL">Timor-Leste</option>
<option value="TG">Togo</option>
<option value="TK">Tokelau</option>
<option value="TO">Tonga</option>
<option value="TT">Trinidad and Tobago</option>
<option value="TN">Tunisia</option>
<option value="TR">Turkey</option>
<option value="TM">Turkmenistan</option>
<option value="TC">Turks and Caicos Islands</option>
<option value="TV">Tuvalu</option>
<option value="UG">Uganda</option>
<option value="UA">Ukraine</option>
<option value="AE">United Arab Emirates</option>
<option value="GB">United Kingdom</option>
<option value="US">United States</option>
<option value="UM">United States Minor Outlying Islands</option>
<option value="UY">Uruguay</option>
<option value="UZ">Uzbekistan</option>
<option value="VU">Vanuatu</option>
<option value="VE">Venezuela</option>
<option value="VN">Vietnam</option>
<option value="VG">Virgin Islands, British</option>
<option value="VI">Virgin Islands, U.S.</option>
<option value="WF">Wallis and Futuna</option>
<option value="EH">Western Sahara</option>
<option value="YE">Yemen</option>
<option value="ZM">Zambia</option>
<option value="ZW">Zimbabwe</option>
<option value="AX">Åland Islands</option>
</select>
     </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
          <select class="form-control" name="online_provider"><optgroup label="Popular in your country"><option value="BANK_TRANSFER_IMPS">IMPS Bank Transfer India</option><option value="CASH_DEPOSIT">Cash deposit</option><option value="MONEYBOOKERS">Moneybookers / Skrill</option><option value="NATIONAL_BANK">National bank transfer</option><option value="NETELLER">Neteller</option><option value="OTHER">Other online payment</option><option value="OTHER_ONLINE_WALLET">Other Online Wallet</option><option value="PAYEER">Payeer</option><option value="PERFECT_MONEY">Perfect Money</option><option value="SPECIFIC_BANK">Transfers with specific bank</option><option value="XOOM">Xoom</option></optgroup><optgroup label="All payment methods"><option value="ALL_ONLINE">All online offers</option><option value="ADVCASH">advcash</option><option value="ALTCOIN_ETH">Ethereum altcoin</option><option value="BANK_TRANSFER_IMPS">IMPS Bank Transfer India</option><option value="CASHU">CashU</option><option value="CASH_DEPOSIT">Cash deposit</option><option value="CREDITCARD">Credit card</option><option value="EGOPAY">EGOPAY</option><option value="GIFT_CARD_CODE">Gift Card Code</option><option value="GIFT_CARD_CODE_AMAZON">Amazon Gift Card Code</option><option value="GIFT_CARD_CODE_GLOBAL">Gift Card Code (Global)</option><option value="INTERNATIONAL_WIRE_SWIFT">International Wire (SWIFT)</option><option value="MONEYBOOKERS">Moneybookers / Skrill</option><option value="MONEYGRAM">Moneygram</option><option value="NATIONAL_BANK">National bank transfer</option><option value="NETELLER">Neteller</option><option value="OKPAY">OKPay</option><option value="ONECARD">OneCard</option><option value="OTHER">Other online payment</option><option value="OTHER_ONLINE_WALLET">Other Online Wallet</option><option value="PAXUM">Paxum</option><option value="PAYEER">Payeer</option><option value="PAYONEER">Payoneer</option><option value="PAYPAL">Paypal</option><option value="PAYTM">PayTM</option><option value="PAYZA">Payza</option><option value="PERFECT_MONEY">Perfect Money</option><option value="RIA">RIA Money Transfer</option><option value="SOLIDTRUSTPAY">SolidTrustPay</option><option value="TRANSFERWISE">Transferwise</option><option value="WEBMONEY">WebMoney</option><option value="WECHAT">WeChat</option><option value="WORLDREMIT">Worldremit</option><option value="WU">Western Union</option></optgroup><optgroup label="Altcoins"><option value="ALTCOIN_DASH">Dash altcoin</option><option value="ALTCOIN_XRP">Ripple altcoin</option><option value="ALTCOIN_LTC">Litecoin altcoin</option><option value="ALTCOIN_XMR">Monero altcoin</option><option value="ALTCOIN_ETH">Ethereum altcoin</option></optgroup><optgroup label="In-person"><option value="CASH">Cash</option></optgroup></select>
                    </div>
                </div>
                <div class="col-md-1">
                    <div class="form-group">
          <input type="button" ng-click="Search(x)" class="btn btn-success" value="Search" />
        </div>
                </div>
            </div>
        
        </form>
    </uib-tab>
           
</uib-tabset>

<h1>Buy Bitcoins online  </h1>
<table id="example"  class="table  table-striped table-hover">

    <thead>
        <tr>	<th>ID</th>
            <th>Seller</th>
            <th>Price/BRL</th>
            <th>Limits</th>
            <th>Action</th>



        </tr>
    </thead>

    <tbody>
        <tr ng-repeat="x in myTxt">
            <td>{{x.id}}</td>
            <td><a ui-sref="PublicProfile({username:x.username})">{{x.name}}</a></td>
            <td >{{x.price}} {{x.currency}}</td>
            <td style="font-size: 16px;font-weight: bold;color: green;">{{x.min_transaction}} {{x.currency}} - {{x.max_transcation}} {{x.currency}}</td>
            <td><a class="btn btn-default megabutton" ui-sref="BuyBitcoinOrder({id:x.id})">Buy</a></td></tr>
    </tbody></table>
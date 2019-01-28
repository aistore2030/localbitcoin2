angular.module('mApp.controllers', [])
        .controller('ProfileController', function ($scope, profile) {

            $scope.x = profile.get();
            $scope.UpdateDetails = function (x) {
                x.$update(function () {
                    alert("Success");
                    $scope.x = profile.get();

                });


            };

        }).controller('WalletController', function ($scope, wallet, transreport) {

    $scope.x1 = wallet.get();
    $scope.myTxt = transreport.query();




}).controller('creditController', function ($scope, trans) {

    $scope.x = trans.get();
    $scope.Submit = function (x) {
        x.cse = "credit";
        x.$update(function () {
            alert("Success");
            $scope.x = trans.get();

        });

    };

}).controller('SendEscrowPaymentController', function ($scope, escrowtrans) {

    $scope.x = escrowtrans.get();
    $scope.Submit = function (x) {
        x.cse = "credit";
        x.$update(function () {
            alert("Success");
            $scope.x = escrowtrans.get();

        });

    };

}).controller('debitController', function ($scope, transreport) {


    $scope.receivepayment = transreport.query();

}).controller('TransactionreportsController', function ($scope, transreport) {

    $scope.myTxt = transreport.query();


}).controller('EscrowTransactionreportController', function ($scope, escrowtransreport, escrowcredit) {
    $scope.myTxt = escrowtransreport.query();
    $scope.Success = function (x) {
        x.cse = "Success";
        x.$update(function () {
            $scope.myTxt = escrowtransreport.query();
        });
    };
    $scope.Cancel = function (x) {
        x.cse = "Cancel";
        x.$update(function () {
            $scope.myTxt = escrowtransreport.query();
        });
    };
}).controller('TransactionhashDetailsController', function ($scope, $stateParams, transreportdetails) {
    $scope.TransactionHashesdetails = transreportdetails.query({
        addr: $stateParams.addr
    });
}).controller('PaymentRequestController', function ($scope, PaymentRequest, $stateParams) {
    console.log("PaymentRequestController");
    $scope.x = PaymentRequest.get({
        coin: $stateParams.coin
    });
    $scope.Submit = function (x) {
        console.log(x);
        console.log(86);
        $scope.x.$update(function (msg) {
            console.log(88);
            alert(msg.Message);
        });
    };
}).controller('DepositRequestController', function ($scope, Deposit) {

    $scope.x = Deposit.query();

}).controller('PostTradeController', function ($scope, $state, PostTrade) {
    console.log("PostTradeController");
    $scope.curr = PostTrade.query();
    $scope.aa = "123";
    $scope.forExConvert = function (x) {
        $scope.x.price_equation = $scope.x.margin / 100;
        if (x.type === "Sell_bitcoin")
            $scope.x.price_equation = "btc_in_usd*" + $scope.x.price_equation;

        else if (x.type === "Buy_bitcoin")
            $scope.x.price_equation = "btc_in_usd*" + $scope.x.price_equation;
        else if (x.type === "Sell_imobicash")
            $scope.x.price_equation = "imobicash_in_usd*" + $scope.x.price_equation;
        else if (x.type === "Buy_imobicash")
            $scope.x.price_equation = "imobicash_in_usd*" + $scope.x.price_equation;

    };
    $scope.x = new PostTrade();
    $scope.Submit = function (x) {
        $scope.x.$update(function (msg) {
            alert(msg.Message);
            $state.go('SellBitCoin');
        });
    };
}).controller('AllTradeController', function ($scope, $state, AllTrade) {
    console.log("AllTradeController");
    $scope.myTxt = AllTrade.query();
    $scope.Update = function (x) {
        $scope.id = x.id;
        $scope.trade_type = x.trade_type;
        x.$update(function (msg) {
            alert(msg.Message);
            $scope.myTxt = AllTrade.query();
        });
    };
}).controller('SellBitCoinController', function ($scope, SellBitCoin,PostTrade,BuyBitCoinfilter) {
    console.log("SellBitCoinController");
    $scope.myTxt = SellBitCoin.query();
    $scope.curr = PostTrade.query();
     $scope.Search = function (x) {
      $scope.myTxt = BuyBitCoinfilter.query({cs:'Buy_bitcoin',currency:x.currency});
    };
}).controller('BuyBitCoinController', function ($scope, PostTrade, BuyBitCoin,BuyBitCoinfilter) {
    console.log("BuyBitCoinController");
    $scope.myTxt = BuyBitCoin.query();
    $scope.curr = PostTrade.query();
     $scope.Search = function (x) {
      $scope.myTxt = BuyBitCoinfilter.query({cs:'Sell_bitcoin',currency:x.currency});
    };


}).controller('SellBitcoinOrderController', function ($scope, $stateParams, SellBitcoinOrder, $http, $state) {
    console.log("SellBitcoinOrderController");
    $scope.myTxt = SellBitcoinOrder.get({
        id: $stateParams.id
    });
    $scope.BTCUSD = function (myTxt) {
        console.log(284);
        $http.get("converter").then(function (response) {
            $scope.ab = response.data.usd;
            $scope.myTxt.total_bitcoin = $scope.myTxt.amount / response.data.usd;
        });
    };
    $scope.USDBTC = function (myTxt) {
        $http.get("converter").then(function (response) {
            $scope.ab = response.data.usd;
            $scope.myTxt.amount = $scope.myTxt.total_bitcoin * response.data.usd;
        });
    };
    $scope.Submit = function (myTxt) {
        console.log(myTxt);
        myTxt.case = "sellBit";
        myTxt.host = myTxt.us;
        myTxt.client = myTxt.username;

        $scope.myTxt.$update(function (msg) {
            alert(msg.Message);
            if (msg.Error === false)
            {
                $state.go('Chatbox', {
                    id: msg.id, Message: true//selectedItem and id is defined
                });
            } else {
                $state.go('SellBitCoin');
            }
        });
    };

}).controller('BuyBitcoinOrderController', function ($scope, $stateParams, SellBitcoinOrder, $http, $state) {
    console.log("BuyBitcoinOrderController");
    $scope.myTxt = SellBitcoinOrder.get({
        id: $stateParams.id
    });
    $scope.BTCUSD = function (myTxt) {
        console.log(284);
        $http.get("converter").then(function (response) {
            $scope.ab = response.data.usd;
            $scope.myTxt.total_bitcoin = $scope.myTxt.amount / response.data.usd;

        });
    };
    $scope.USDBTC = function (myTxt) {
        $http.get("converter").then(function (response) {
            $scope.ab = response.data.usd;
            $scope.myTxt.amount = $scope.myTxt.total_bitcoin * response.data.usd;
        });
    };
    $scope.Submit = function (myTxt) {
        console.log(myTxt);
        myTxt.case = "buyBit";
        myTxt.host = myTxt.us;
        myTxt.client = myTxt.username;

        $scope.myTxt.$update(function (msg) {
            alert(msg.Message);

            if (msg.Error === false)
            {
                $state.go('Chatbox', {
                    id: msg.id, Message: true//selectedItem and id is defined
                });
            } else {
                $state.go('BuyBitCoin');
            }
        });
    };

}).controller('ChatboxController', function ($scope, Chatbox, $stateParams, $state, CheckOrder, $interval,
        getbitorder, SellBitcoinOrder, FileUploader, $http) {
    console.log("ChatboxController");

    /* mySocket.on('newChat', function () {
     console.log("mySocket called");
     
     $scope.myTxt = Chatbox.query({
     id: $stateParams.id
     
     });
     console.log($scope.myTxt);
     });*/
    $scope.myTxt = Chatbox.query({
        id: $stateParams.id

    });




    $scope.theTime = new Date().toLocaleTimeString();



    $scope.x = getbitorder.query({id: $stateParams.id});

    console.log($scope.x);

    $scope.myTxt = Chatbox.query({
        id: $stateParams.id

    });
    $scope.x1 = getbitorder.get({id: $stateParams.id});
    $scope.myTxt1 = SellBitcoinOrder.get({id: $stateParams.id});
    $scope.id = $stateParams.id;
    $scope.Message = $stateParams.Message;


    $scope.u = Chatbox.get();
    $scope.submit = function (u) {
        u.case = "chat";
        u.contactId = $stateParams.id;
        u.$update(function (msg) {
            // alert(msg.Message);
            $scope.myTxt = Chatbox.query({
                id: $stateParams.id

            });


            $http({
                method: "GET",
                url: "https://localbitcoins.global:3020/newChat"
            }).then(function mySuccess(response) {
                console.log(response);
            }, function myError(response) {
                console.log(response);
            });


        });

    };
    $scope.upload = false;
    $scope.noupload = true;
    $scope.uploadimage = false;
    $scope.Attachdocument = function (u) {
        $scope.noupload = false;
        $scope.uploadimage = true;
        $scope.upload = true;

    };
    var filename;
    $scope.Upload = function (u) {
        u.case = "upload";
        u.message = filename;
        u.contactId = $stateParams.id;
        console.log(u.message + "1032" + u.contactId);
        u.$update(function (msg) {
            alert(msg.Message);
            $scope.myTxt = Chatbox.query({
                id: $stateParams.id

            });


        });
    };
    var uploader = $scope.uploader = new FileUploader({url: 'UploadImage?id=' + $stateParams.id});



    uploader.onWhenAddingFileFailed = function (item /*{File|FileLikeObject}*/, filter, options) {
        //  console.log('onWhenAddingFileFailed', item, filter, options);
    };
    uploader.onAfterAddingFile = function (fileItem) {
        /// console.log('onAfterAddingFile', fileItem);
    };
    uploader.onAfterAddingAll = function (addedFileItems) {
        // console.log('onAfterAddingAll', addedFileItems);
    };
    uploader.onBeforeUploadItem = function (item) {
        //   console.log('onBeforeUploadItem', item);
    };
    uploader.onProgressItem = function (fileItem, progress) {
        //   console.log('onProgressItem', fileItem, progress);
    };
    uploader.onProgressAll = function (progress) {
        //   console.log('onProgressAll', progress);
    };
    uploader.onSuccessItem = function (fileItem, response, status, headers) {
        console.log('onSuccessItem', fileItem, response, status, headers);
        alert("Successfully uploaded");
    };
    uploader.onErrorItem = function (fileItem, response, status, headers) {
        // console.log('onErrorItem', fileItem, response, status, headers);
    };
    uploader.onCancelItem = function (fileItem, response, status, headers) {
        //console.log('onCancelItem', fileItem, response, status, headers);
    };
    uploader.onCompleteItem = function (fileItem, response, status, headers) {
        //console.log('onCompleteItem', fileItem, response, status, headers);
        console.log(fileItem._file.name + "76444444444");
        filename = fileItem._file.name;
    };

    // $scope.x = new allproduct();
    uploader.onCompleteAll = function (fileItem, response, status, headers) {
        console.log(fileItem._file.name + "76444444444");
    };

    console.info('uploader', uploader);



    $scope.Canceldocument = function (u) {
        $scope.noupload = true;
        $scope.uploadimage = false;
        $scope.upload = false;

    };
    $scope.z = new CheckOrder();
    $scope.Dispute = function (z) {
        z.case = "Dispute";
        z.id = $stateParams.id;
        console.log(z);
        z.$update(function (msg) {
            alert(msg.Message);
            $scope.myTxt = Chatbox.query({
                id: $stateParams.id

            });
            $scope.myTxt1 = SellBitcoinOrder.get({id: $stateParams.id});

        });
    };

    $scope.z = new CheckOrder();
    $scope.paymnet_bycllient = function (z) {
        z.case = "paymnet_bycllient";
        z.id = $stateParams.id;
        console.log(z);
        z.$update(function (msg) {
            alert(msg.Message);
            $scope.myTxt = Chatbox.query({
                id: $stateParams.id

            });
            $scope.myTxt1 = SellBitcoinOrder.get({id: $stateParams.id});

        });
    };
    $scope.ReleaseInCaseSell = function (z) {
        z.case = "release";
        z.cs = "sellBit";
        z.id = $stateParams.id;
        console.log(z);
        z.$update(function (msg) {
            alert(msg.Message);
            $scope.myTxt = Chatbox.query({
                id: $stateParams.id

            });
            $scope.myTxt1 = SellBitcoinOrder.get({id: $stateParams.id});
        });
    };

    $scope.ReleaseInCaseBuy = function (z) {
        z.case = "release";
        z.cs = "buyBit";
        z.id = $stateParams.id;
        console.log(z);
        z.$update(function (msg) {
            alert(msg.Message);
            $scope.myTxt = Chatbox.query({
                id: $stateParams.id

            });
            $scope.myTxt1 = SellBitcoinOrder.get({id: $stateParams.id});
        });
    };
    $scope.Cancel_in_case_Buy = function (z) {
        z.case = "cancel";
        z.cs = "buyBit";
        z.id = $stateParams.id;
        console.log(z);
        z.$update(function (msg) {
            alert(msg.Message);
            $scope.myTxt = Chatbox.query({
                id: $stateParams.id

            });
            $scope.myTxt1 = SellBitcoinOrder.get({id: $stateParams.id});
        });
    };
    $scope.Cancel_in_case_Sell = function (z) {
        z.case = "cancel";
        z.cs = "sellBit";
        z.id = $stateParams.id;
        z.$update(function (msg) {
            alert(msg.Message);
            $scope.myTxt = Chatbox.query({
                id: $stateParams.id

            });
            $scope.myTxt1 = SellBitcoinOrder.get({id: $stateParams.id});
        });
    };

}).controller('CheckOrderController', function ($scope, CheckOrder, $state) {
    console.log("CheckOrderController");
    $scope.myTxt = CheckOrder.query();
    $scope.x = new CheckOrder();
    $scope.Dispute = function (x) {
        x.case = "Dispute";
        console.log(x);
        x.$update(function (msg) {
            alert(msg.Message);
            $scope.myTxt = CheckOrder.query();
        });
    };
    $scope.Confirm = function (x) {
        x.case = "Confirm";
        x.$update(function (msg) {
            alert(msg.Message);
            if (msg.Error === false)
            {
                $state.go('Feesback', {
                    id: msg.id //selectedItem and id is defined
                });
            } else {
                $scope.myTxt = CheckOrder.query();
            }
        });
    };
    $scope.Suspand = function (x) {
        x.case = "Suspand";
        x.$update(function (msg) {
            alert(msg.Message);
            $scope.myTxt = CheckOrder.query();
        });
    };
}).controller('FeesbackController', function ($scope, Feesback, $stateParams) {
    console.log("FeesbackController");
    $scope.myTxt = Feesback.get({
        id: $stateParams.id
    });

    $scope.x = new Feesback();
    $scope.Submit = function (x) {
        x.name = $scope.myTxt.us;
        console.log($scope.myTxt.us + "FeesbackController");
        $scope.x.$update(function (msg) {
            alert(msg.Message);
        });
    };
}).controller('invoicedetailController', function ($scope, Feesback, $stateParams) {
    console.log("invoicedetailController");
    $scope.myTxt = Feesback.get({
        id: $stateParams.id
    });

    $scope.x = new Feesback();
    $scope.Submit = function (x) {
        x.name = $scope.myTxt.us;
        console.log($scope.myTxt.us + "invoicedetailController");
        $scope.x.$update(function (msg) {
            alert(msg.Message);
        });
    };
}).controller('BTCController', function ($scope, btc, Transaction_Detail,$interval) {

    $scope.x = btc.get();
    $scope.y = Transaction_Detail.query();
    //console.log($scope.y);
    console.log($scope.y.length);

    $scope.Submit = function (x) {
        $scope.x.$update(function (msg) {
            alert(msg.Message);
            
        });
        $scope.theTime = new Date().toLocaleTimeString();
            $interval(function () {

                $scope.x = btc.get();

            }, 10000);
    };
}).controller('IMCController', function ($scope, imc) {

    $scope.x = imc.get();
    $scope.Submit = function (x) {
        $scope.x.$update(function (msg) {
            alert(msg.Message);
        });
    };
}).controller('ETHController', function ($scope, eth) {
    $scope.x = eth.get();
    $scope.Submit = function (x) {
        console.log("298");
        console.log(x);
        console.log("300");
        $scope.x.$update(function (msg) {
            alert(msg.Message);
        });
    };

}).controller('BCHController', function ($scope, bch) {

    $scope.x = bch.get();

    $scope.Submit = function (x) {
        console.log("298");
        console.log(x);
        console.log("300");
        $scope.x.$update(function (msg) {
            alert(msg.Message);
        });
    };
}).controller('XRPController', function ($scope, xrp) {

    $scope.x = xrp.get();

    $scope.Submit = function (x) {
        console.log("298");
        console.log(x);
        console.log("300");
        $scope.x.$update(function (msg) {
            alert(msg.Message);
        });
    };
}).controller('SetMarginController', function ($scope, SetMargin) {

    $scope.x = SetMargin.get();

    $scope.Submit = function (x) {

        $scope.x.$update(function (msg) {
            alert(msg.Message);
            $scope.x = SetMargin.get();
        });
    };
}).controller('AllUserController', function ($scope, AllUser) {
    console.log("AllUserController");
    $scope.myTxt = AllUser.query();
    $scope.Suspand = function (x) {
        x.case = "Suspand";
        console.log(x);
        x.$update(function (msg) {
            alert(msg.Message);
            $scope.myTxt = AllUser.query();
        });
    };
    $scope.Verified = function (x) {
        x.case = "Verified";
        console.log(x);
        x.$update(function (msg) {
            alert(msg.Message);

            $scope.myTxt = AllUser.query();
        });
    };



    $scope.resetGoogleAuth = function (x) {
        x.case = "resetGoogleAuth";
        //  alert("resetGoogleAuth");
        console.log(x);
        x.$update(function (msg) {
            alert(msg.Message);

            $scope.myTxt = AllUser.query();
        });
    };

    $scope.Block = function (x) {
        x.case = "Blocked";
        console.log(x);
        x.$update(function (msg) {
            alert(msg.Message);

            $scope.myTxt = AllUser.query();
        });
    };
    $scope.Delete = function (x) {
        x.case = "Delete";
        console.log(x);
        x.$update(function (msg) {
            alert(msg.Message);

            $scope.myTxt = AllUser.query();
        });
    };
}).controller('GetBalanceController', function ($scope, $window, $interval, $http, balance) {
    console.log(balance.get());
    var m = balance.get();
    $scope.systemBalance = m;
    /*
     $scope.theTime = new Date().toLocaleTimeString();
     $interval(function () {
     $scope.theTime = new Date().toLocaleTimeString();
     $http.get("Session_validation").then(function (response) {
     $scope.x = response.data;
     console.log($scope.x);
     if ($scope.x.session === true) {
     $window.location.href = '/login.jsp';
     } else {
     
     }
     });
     
     }, 10000);*/
}).controller('NotificationController', function ($scope, notification) {
    console.log("NotificationController");
    $scope.notification = notification.query();
    console.log($scope.notification);
    $scope.show = function (n) {
        n.$update(function (msg) {
            alert(msg.Message);
            $scope.notification = notification.query();
        });
    };
    $scope.updateUser = function (x) {
        alert("Submitted");
        x.$update(function (msg) {
            alert(msg.Message);

        });


    };

}).controller('ViewMoreNotificationController', function ($scope, notification) {
    console.log("ViewMoreNotificationController");
    $scope.notification = notification.query();



    $scope.show = function (n) {
        n.$update(function (msg) {
            alert(msg.Message);
            $scope.notification = notification.query();
        });


    };


}).controller('googleauthController', function ($scope, googleauth, $state) {
    console.log("googleauthController");
    $scope.x = googleauth.get();


    $scope.Submit = function (x) {
        x.case = "Enable";
        console.log(x);
        x.$update(function (msg) {
            alert(msg.Message);
            $scope.x = googleauth.get();
            $scope.qrcode = "googleauth.get()";
            $state.go('Profile');
        });


    };

}).controller('VarifyEmailController', function ($scope, VarifyEmail) {
    $scope.x = VarifyEmail.get();
    $scope.VarifyEmailStatus = function (x) {
        x.$update(function (msg) {
            alert(msg.Message);


        });

    };


}).controller('PublicProfileController', function ($scope, AllUser, Tradebyusername, $stateParams) {
    console.log("PublicProfileController");
    $scope.x = AllUser.get({
        username: $stateParams.username
    });

    $scope.y = Tradebyusername.query({
        username: $stateParams.username
    });
    $scope.Submit = function (x) {
        console.log(x);
        console.log(86);
        $scope.x.$update(function (msg) {
            console.log(88);
            alert(msg.Message);
        });
    };
}).controller('VerifyEmailController', function ($scope, $interval, emailverify) {
    $scope.x = emailverify.get();

    $scope.theTime = new Date().toLocaleTimeString();
    /* $interval(function () {
     $scope.theTime = new Date().toLocaleTimeString();
     
     $scope.x = emailverify.get();
     
     }, 10000);*/


    $scope.VerifyEmailStatus = function (x) {

        x.$update(function (msg) {
            alert(msg.Message);


        });

    };

}).controller('SendBTCController', function ($scope, $http,PostTrade,converter, btc, $state) {
    $scope.x = btc.get();

    $scope.y = btc.get();
$scope.curr = PostTrade.query();
console.log($scope.curr);
    $scope.BTCUSD = function (x) {
        var currency=x.currency;
        var amount=x.amount;
        //alert(currency+amount);
        $http.get("converter?currency="+currency+"&amount="+amount).then(function (response) {
            $scope.ab = response.data.value;
            console.log($scope.ab+"111111111111111");
           //$scope.data = converter.query({currency:currency});
           console.log($scope.ab.currency+"111111111111111");
            $scope.x.total_bitcoin = ($scope.x.amount / $scope.ab).toFixed(6);

        });
    };
    $scope.USDBTC = function (x) {
        //var currency=x.currency;
        var amount=x.amount;
        $http.get("converter?currency=USD&amount="+amount).then(function (response) {
            $scope.ab = response.data.value;
            $scope.x.amount = $scope.x.total_bitcoin * $scope.ab;
            $scope.x.currency="US Dollar";
            $scope.currency="US Dollar";
             console.log($scope.ab+"111111111111111"+$scope.x.currency+$scope.x.amount);
        });
    };
    $scope.Submit = function (y) {
        console.log(y);
        y.coin = "bitcoin";
        y.cse = "credit";
        y.total_bitcoin = $scope.x.total_bitcoin;

        console.log(y);
        console.log(y.total_bitcoin);

        y.$update(function (msg) {
            alert(msg.Message);
            if (msg.Error === false) {
                $state.go('Wallet');
            } else {
                $scope.x = btc.get();
            }
        });
    };

}).controller('WalletSettingController', function ($scope, walletSetting) {

    $scope.x = walletSetting.get();

    $scope.Submit = function (x) {
        $scope.x.$update(function (msg) {
            alert(msg.Message);
            $scope.x = walletSetting.get();
        });
    };

}).controller('SendImobiCashController', function ($scope, imc, send) {
    $scope.x = imc.get();

    $scope.y = imc.get();

    $scope.Submit = function (y) {
        console.log(y);
        y.coin = "imc";
        y.cse = "credit";

        console.log(y);
        console.log($scope.y);

        y.$update(function (msg) {
            alert(msg.Message);
        });
    };

}).controller('SendETHController', function ($scope, eth, send) {
    $scope.x = eth.get();

    $scope.y = new send();
    $scope.Submit = function (y) {
        console.log(y);
        $scope.y.coin = "eth";
        $scope.y.cse = "credit";
        $scope.y.$update(function (msg) {
            alert(msg.Message);
        });
    };

}).controller('SendBCHController', function ($scope, bch, send) {

    $scope.x = bch.get();

    $scope.y = new send();
    $scope.Submit = function (y) {
        $scope.y.coin = "bch";
        $scope.y.cse = "credit";
        $scope.y.$update(function (msg) {
            alert(msg.Message);
        });
    };

}).controller('SendXRPController', function ($scope, xrp, send) {
    console.log("SendXRPController");
    $scope.x = xrp.get();

    console.log($scope.x);
    console.log(xrp.get());
    $scope.y = new send();
    $scope.Submit = function (y) {
        $scope.y.coin = "xrp";

        $scope.y.$update(function (msg) {
            alert(msg.Message);
        });
    };

}).controller('SendController', function ($scope, send) {
    $scope.y = send.query();


    $scope.Submit = function (y) {
        $scope.y.cse = "credit";
        $scope.y.$update(function (msg) {
            alert(msg.Message);


        });

    };
}).controller('SellBitCoinCashController', function ($scope, SellBitCoinCash) {

    $scope.myTxt = SellBitCoinCash.query();
}).controller('BuyBitCoinCashController', function ($scope, BuyBitCoinCash) {

    $scope.myTxt = BuyBitCoinCash.query();
}).controller('SellEthereumController', function ($scope, SellEthereumm) {

    $scope.myTxt = SellEthereumm.query();
}).controller('BuyEthereumController', function ($scope, BuyEthereum) {

    $scope.myTxt = BuyEthereum.query();
}).controller('SellImobicashController', function ($scope, SellImobicash) {

    $scope.myTxt = SellImobicash.query();
}).controller('BuyImobicashController', function ($scope, BuyImobicash) {

    $scope.myTxt = BuyImobicash.query();
}).controller('SellRippleController', function ($scope, SellRipple) {

    $scope.myTxt = SellRipple.query();
}).controller('BuyRippleController', function ($scope, BuyRipple) {

    $scope.myTxt = BuyRipple.query();
}).controller('SellTetherController', function ($scope, SellTether) {

    $scope.myTxt = SellTether.query();
}).controller('BuyTetherController', function ($scope, BuyTether) {

    $scope.myTxt = BuyTether.query();

}).controller('SellBCHOrderController', function ($scope, $stateParams, SellBitcoinOrder, $http, $state) {
    console.log("SellBCHOrderController");
    $scope.myTxt = SellBitcoinOrder.get({
        id: $stateParams.id
    });
    $scope.BCHUSD = function (myTxt) {
        console.log(284);
        $http.get("ethconverter?coin=bchusd").then(function (response) {
            $scope.ab = response.data.usd;
            $scope.myTxt.total_bitcoin = $scope.myTxt.amount / response.data.usd;
        });
    };
    $scope.USDBCH = function (myTxt) {
        $http.get("ethconverter?coin=bchusd").then(function (response) {
            $scope.ab = response.data.usd;
            $scope.myTxt.amount = $scope.myTxt.total_bitcoin * response.data.usd;
        });
    };
    $scope.Submit = function (myTxt) {
        console.log(myTxt);
        myTxt.case = "sellBCH";
        myTxt.host = myTxt.us;
        myTxt.client = myTxt.username;

        $scope.myTxt.$update(function (msg) {
            alert(msg.Message);
            if (msg.Error === false)
            {
                $state.go('Chatbox', {
                    id: msg.id //selectedItem and id is defined
                });
            } else {
                $state.go('SellBitCoinCash');
            }
        });
    };
}).controller('BuyBCHOrderController', function ($scope, $stateParams, SellBitcoinOrder, $http, $state) {
    console.log("BuyBCHOrderController");
    $scope.myTxt = SellBitcoinOrder.get({
        id: $stateParams.id
    });
    $scope.BCHUSD = function (myTxt) {
        console.log(284);
        $http.get("ethconverter?coin=bchusd").then(function (response) {
            $scope.ab = response.data.usd;
            $scope.myTxt.total_bitcoin = $scope.myTxt.amount / response.data.usd;

        });
    };
    $scope.USDBCH = function (myTxt) {
        $http.get("ethconverter?coin=bchusd").then(function (response) {
            $scope.ab = response.data.usd;
            $scope.myTxt.amount = $scope.myTxt.total_bitcoin * response.data.usd;
        });
    };
    $scope.Submit = function (myTxt) {
        console.log(myTxt);
        myTxt.case = "buyBCH";
        myTxt.host = myTxt.us;
        myTxt.client = myTxt.username;

        $scope.myTxt.$update(function (msg) {
            alert(msg.Message);
            if (msg.Error === false)
            {
                $state.go('Chatbox', {
                    id: msg.id //selectedItem and id is defined
                });
            } else {
                $state.go('BuyBitCoinCash');
            }
        });
    };

}).controller('SellETHOrderController', function ($scope, $stateParams, SellBitcoinOrder, $http, $state) {
    console.log("SellETHOrderController");
    $scope.myTxt = SellBitcoinOrder.get({
        id: $stateParams.id
    });
    $scope.ETHUSD = function (myTxt) {
        console.log(284);
        $http.get("ethconverter?coin=ethusd").then(function (response) {
            $scope.ab = response.data.usd;
            $scope.myTxt.total_bitcoin = $scope.myTxt.amount / response.data.usd;
        });
    };
    $scope.USDETH = function (myTxt) {
        $http.get("ethconverter?coin=ethusd").then(function (response) {
            $scope.ab = response.data.usd;
            $scope.myTxt.amount = $scope.myTxt.total_bitcoin * response.data.usd;
        });
    };
    $scope.Submit = function (myTxt) {
        console.log(myTxt);
        myTxt.case = "sellETH";
        myTxt.host = myTxt.us;
        myTxt.client = myTxt.username;

        $scope.myTxt.$update(function (msg) {
            alert(msg.Message);
            if (msg.Error === false)
            {
                $state.go('Chatbox', {
                    id: msg.id //selectedItem and id is defined
                });
            } else {
                $state.go('SellEthereum');
            }
        });
    };
}).controller('BuyETHOrderController', function ($scope, $stateParams, SellBitcoinOrder, $http, $state) {
    console.log("BuyETHOrderController");
    $scope.myTxt = SellBitcoinOrder.get({
        id: $stateParams.id
    });
    $scope.ETHUSD = function (myTxt) {
        console.log(284);
        $http.get("ethconverter?coin=ethusd").then(function (response) {
            console.log("ethconverter?coin=ethusd");
            console.log(response + "  " + 678);
            $scope.ab = response.data.usd;
            console.log($scope.ab);
            console.log(response.data.usd);
            $scope.myTxt.total_bitcoin = $scope.myTxt.amount / response.data.usd;
        });
    };
    $scope.USDETH = function (myTxt) {
        $http.get("ethconverter?coin=ethusd").then(function (response) {
            $scope.ab = response.data.usd;
            $scope.myTxt.amount = $scope.myTxt.total_bitcoin * response.data.usd;
        });
    };
    $scope.Submit = function (myTxt) {
        console.log(myTxt);
        myTxt.case = "buyETH";
        myTxt.host = myTxt.us;
        myTxt.client = myTxt.username;

        $scope.myTxt.$update(function (msg) {
            alert(msg.Message);
            if (msg.Error === false)
            {
                $state.go('Chatbox', {
                    id: msg.id //selectedItem and id is defined
                });
            } else {
                $state.go('BuyEthereum');
            }
        });
    };

}).controller('SellImobicashOrderController', function ($scope, $stateParams, SellBitcoinOrder, $http, $state) {

    $scope.myTxt = SellBitcoinOrder.get({
        id: $stateParams.id
    });
    $scope.BTCUSD = function (myTxt) {

        $scope.myTxt.total_bitcoin = $scope.myTxt.amount;

    };
    $scope.USDBTC = function (myTxt) {

        $scope.myTxt.amount = $scope.myTxt.total_bitcoin;
    };


    $scope.Submit = function (myTxt) {
        console.log(myTxt);
        myTxt.case = "sellimobicash";
        myTxt.host = myTxt.us;
        myTxt.client = myTxt.username;

        $scope.myTxt.$update(function (msg) {
            alert(msg.Message);
            if (msg.Error === false)
            {
                $state.go('Chatbox', {
                    id: msg.id //selectedItem and id is defined
                });
            } else {
                $state.go('SellImobicash');
            }
        });
    };
}).controller('BuyImobicashOrderController', function ($scope, $stateParams, SellBitcoinOrder, $http, $state) {

    $scope.myTxt = SellBitcoinOrder.get({
        id: $stateParams.id
    });
    $scope.BTCUSD = function (myTxt) {

        $scope.myTxt.total_bitcoin = $scope.myTxt.amount;

    };
    $scope.USDBTC = function (myTxt) {

        $scope.myTxt.amount = $scope.myTxt.total_bitcoin;
    };

    $scope.Submit = function (myTxt) {
        console.log(myTxt);
        myTxt.case = "buyimobicash";
        myTxt.host = myTxt.us;
        myTxt.client = myTxt.username;

        $scope.myTxt.$update(function (msg) {
            alert(msg.Message);
            if (msg.Error === false)
            {
                $state.go('Chatbox', {
                    id: msg.id //selectedItem and id is defined
                });
            } else {
                $state.go('BuyImobicash');
            }
        });
    };

}).controller('SellRippleOrderController', function ($scope, $stateParams, SellBitcoinOrder, $http, $state) {

    $scope.myTxt = SellBitcoinOrder.get({
        id: $stateParams.id
    });
    $scope.RIPPLEUSD = function (myTxt) {
        console.log(284);
        $http.get("ethconverter?coin=xrpusd").then(function (response) {
            $scope.ab = response.data.usd;
            $scope.myTxt.total_bitcoin = $scope.myTxt.amount / response.data.usd;
        });
    };
    $scope.USDRIPPLE = function (myTxt) {
        $http.get("ethconverter?coin=xrpusd").then(function (response) {
            $scope.ab = response.data.usd;
            $scope.myTxt.amount = $scope.myTxt.total_bitcoin * response.data.usd;
        });
    };
    $scope.Submit = function (myTxt) {
        console.log(myTxt);
        myTxt.case = "sellBCH";
        myTxt.host = myTxt.us;
        myTxt.client = myTxt.username;

        $scope.myTxt.$update(function (msg) {
            alert(msg.Message);
            if (msg.Error === false)
            {
                $state.go('Chatbox', {
                    id: msg.id //selectedItem and id is defined
                });
            } else {
                $state.go('SellRipple');
            }
        });
    };
}).controller('BuyRippleOrderController', function ($scope, $stateParams, SellBitcoinOrder, $http, $state) {

    $scope.myTxt = SellBitcoinOrder.get({
        id: $stateParams.id
    });
    $scope.RIPPLEUSD = function (myTxt) {
        console.log(284);
        $http.get("ethconverter?coin=xrpusd").then(function (response) {
            $scope.ab = response.data.usd;
            $scope.myTxt.total_bitcoin = $scope.myTxt.amount / response.data.usd;
            //$scope.BTC= String.format( "%.2f", $scope.BTC ) ;
        });
    };
    $scope.USDRIPPLE = function (myTxt) {
        $http.get("ethconverter?coin=xrpusd").then(function (response) {
            $scope.ab = response.data.usd;
            $scope.myTxt.amount = $scope.myTxt.total_bitcoin * response.data.usd;
        });
    };
    $scope.Submit = function (myTxt) {
        console.log(myTxt);
        myTxt.case = "buyBCH";
        myTxt.host = myTxt.us;
        myTxt.client = myTxt.username;

        $scope.myTxt.$update(function (msg) {
            alert(msg.Message);
            if (msg.Error === false)
            {
                $state.go('Chatbox', {
                    id: msg.id //selectedItem and id is defined
                });
            } else {
                $state.go('BuyRipple');
            }
        });
    };

}).controller('SellTetherOrderController', function ($scope, $stateParams, SellBitcoinOrder, $http, $state) {

    $scope.myTxt = SellBitcoinOrder.get({
        id: $stateParams.id
    });
    $scope.USDTUSD = function (myTxt) {
        console.log(284);
        $http.get("ethconverter?coin=xrpusd").then(function (response) {
            $scope.ab = response.data.usd;
            $scope.myTxt.total_bitcoin = $scope.myTxt.amount / response.data.usd;
        });
    };
    $scope.USDUSDT = function (myTxt) {
        $http.get("ethconverter?coin=xrpusd").then(function (response) {
            $scope.ab = response.data.usd;
            $scope.myTxt.amount = $scope.myTxt.total_bitcoin * response.data.usd;
        });
    };
    $scope.Submit = function (myTxt) {
        console.log(myTxt);
        myTxt.case = "sellBCH";
        myTxt.host = myTxt.us;
        myTxt.client = myTxt.username;

        $scope.myTxt.$update(function (msg) {
            alert(msg.Message);
            if (msg.Error === false)
            {
                $state.go('Chatbox', {
                    id: msg.id //selectedItem and id is defined
                });
            } else {
                $state.go('SellTether');
            }
        });
    };
}).controller('BuyTetherOrderController', function ($scope, $stateParams, SellBitcoinOrder, $http, $state) {

    $scope.myTxt = SellBitcoinOrder.get({
        id: $stateParams.id
    });
    $scope.USDTUSD = function (myTxt) {
        console.log(284);
        $http.get("ethconverter?coin=xrpusd").then(function (response) {
            $scope.ab = response.data.usd;
            $scope.myTxt.total_bitcoin = $scope.myTxt.amount / response.data.usd;
        });
    };
    $scope.USDUSDT = function (myTxt) {
        $http.get("ethconverter?coin=xrpusd").then(function (response) {
            $scope.ab = response.data.usd;
            $scope.myTxt.amount = $scope.myTxt.total_bitcoin * response.data.usd;
        });
    };
    $scope.Submit = function (myTxt) {
        console.log(myTxt);
        myTxt.case = "buyBCH";
        myTxt.host = myTxt.us;
        myTxt.client = myTxt.username;

        $scope.myTxt.$update(function (msg) {
            alert(msg.Message);
            if (msg.Error === false)
            {
                $state.go('Chatbox', {
                    id: msg.id //selectedItem and id is defined
                });
            } else {
                $state.go('BuyTether');
            }
        });
    };

}).controller('UserBalanceController', function ($scope, userbalance) {

    $scope.x1 = userbalance.get();

}).controller('credittouserController', function ($scope, $state, creditdebit, $stateParams) {

    $scope.x = creditdebit.get({
        username: $stateParams.username
    });

    console.log($scope.x);
    $scope.submit = function (x) {
        x.cse = "credit";
        console.log(x);
        x.$update(function (msg) {
            alert(msg.Message);
            if (msg.Error === false)
            {
                $state.go('AllUser');
            } else {
                $state.go('CreditToUser', {
                    username: $stateParams.username
                });
            }
        });

    };

}).controller('debittouserController', function ($scope, $state, creditdebit, $stateParams) {

    $scope.x = creditdebit.get({
        username: $stateParams.username
    });


    console.log($scope.x);
    $scope.submit = function (x) {

        x.cse = "debit";
        x.$update(function (msg) {
            alert(msg.Message);
            if (msg.Error === false)
            {
                $state.go('AllUser');
            } else {
                $state.go('CreditToUser', {
                    username: $stateParams.username
                });
            }
        });

    };

}).controller('InvoiceController', function ($scope, $http, $interval, invoice, $stateParams) {
    console.log("InvoiceController");

    $scope.x = invoice.get({
        id: $stateParams.id
    });

    $scope.z = new invoice();
    $scope.paid = function (z) {

        $scope.z.id = $stateParams.id;
        z.$update(function (msg) {
            alert(msg.Message);

        });

    };
}).controller('PaymentGatewayController', function ($scope, $http, $state) {
    console.log("PaymentGatewayController.x");

    $scope.Submit = function (x) {

        localStorage.amount = x.amount;

        localStorage.fees = x.fees;

        $http.get("CreateOrder?amount=" + x.amount + "&fees=" + x.fees)
                .then(function (response) {
                    console.log(response);
                    console.log(response.data.id);
                    console.log(response.data.Error);
                    if (response.data.Error === true)
                    {
                    } else {
                        localStorage.id = response.data.id;
                        $state.go('PaymentGatewaySend');
                    }
                });

    };

}).controller('SendBTCNewController', function ($scope, walletSetting) {

    $scope.x = walletSetting.get();
    $scope.Submit = function (x) {
        $scope.x.$update(function (msg) {
            alert(msg.Message);
            $scope.x = walletSetting.get();
        });
    };



}).controller('sendpaymnetController', function ($scope, $http, $state) {
    console.log("sendpaymnetController");
    $scope.amount = localStorage.amount;
    $scope.fees = localStorage.fees;





}).controller('DiscussController', function ($scope, Chatbox, $stateParams, $state, CheckOrder, $interval,
        getbitorder, SellBitcoinOrder, FileUploader, mySocket, $http) {
    console.log("DiscussControllerChatboxController");

    mySocket.on('newChat', function () {
        console.log("mySocket called");

        $scope.myTxt = Chatbox.query({
            id: $stateParams.id

        });

    });



    $scope.myTxt = Chatbox.query({
        id: $stateParams.id

    });


    $scope.id = $stateParams.id;
    $scope.Message = $stateParams.Message;


    $scope.u = Chatbox.get();
    $scope.submit = function (u) {
        u.case = "chat";
        u.contactId = $stateParams.id;
        u.$update(function (msg) {
            // alert(msg.Message);
            //$scope.myTxt = Chatbox.query({
            //id: $stateParams.id

            //});


            $http({
                method: "GET",
                url: "https://localbitcoins.global:3020/newChat"
            }).then(function mySuccess(response) {
                console.log(response);
            }, function myError(response) {
                console.log(response);
            });


        });

    };
    $scope.upload = false;
    $scope.noupload = true;
    $scope.uploadimage = false;
    $scope.Attachdocument = function (u) {
        $scope.noupload = false;
        $scope.uploadimage = true;
        $scope.upload = true;

    };
    var filename;
    $scope.Upload = function (u) {
        u.case = "upload";
        u.message = filename;
        u.contactId = $stateParams.id;
        console.log(u.message + "1032" + u.contactId);
        u.$update(function (msg) {
            alert(msg.Message);
            $scope.myTxt = Chatbox.query({
                id: $stateParams.id

            });


        });
    };
    var uploader = $scope.uploader = new FileUploader({url: 'UploadImage?id=' + $stateParams.id});



    uploader.onWhenAddingFileFailed = function (item /*{File|FileLikeObject}*/, filter, options) {
        //  console.log('onWhenAddingFileFailed', item, filter, options);
    };
    uploader.onAfterAddingFile = function (fileItem) {
        /// console.log('onAfterAddingFile', fileItem);
    };
    uploader.onAfterAddingAll = function (addedFileItems) {
        // console.log('onAfterAddingAll', addedFileItems);
    };
    uploader.onBeforeUploadItem = function (item) {
        //   console.log('onBeforeUploadItem', item);
    };
    uploader.onProgressItem = function (fileItem, progress) {
        //   console.log('onProgressItem', fileItem, progress);
    };
    uploader.onProgressAll = function (progress) {
        //   console.log('onProgressAll', progress);
    };
    uploader.onSuccessItem = function (fileItem, response, status, headers) {
        console.log('onSuccessItem', fileItem, response, status, headers);
        alert("Successfully uploaded");
    };
    uploader.onErrorItem = function (fileItem, response, status, headers) {
        // console.log('onErrorItem', fileItem, response, status, headers);
    };
    uploader.onCancelItem = function (fileItem, response, status, headers) {
        //console.log('onCancelItem', fileItem, response, status, headers);
    };
    uploader.onCompleteItem = function (fileItem, response, status, headers) {
        //console.log('onCompleteItem', fileItem, response, status, headers);
        console.log(fileItem._file.name + "76444444444");
        filename = fileItem._file.name;
    };

    // $scope.x = new allproduct();
    uploader.onCompleteAll = function (fileItem, response, status, headers) {
        console.log(fileItem._file.name + "76444444444");
    };

    console.info('uploader', uploader);

    $scope.Canceldocument = function (u) {
        $scope.noupload = true;
        $scope.uploadimage = false;
        $scope.upload = false;

    };

}).controller('receivedBtcController', function ($scope, Transaction_Detail) {
    console.log("receivedBtcController");

    $scope.x = Transaction_Detail.query();

}).controller('TicketController', function ($scope, report) {
    $scope.records = report.query();
    $scope.priority = [{
            priority: 'high '
        },
        {
            priority: ' medium'
        },
        {
            priority: 'low'
        }
    ];
    $scope.status = [{
            status: 'Enable'
        },
        {
            status: 'Disable'
        }

    ];
    $scope.x = new report();
    $scope.submit = function (x) {
        //alert("hgfhe");
        console.log(x);
        $scope.x.$save(function (msg) {

            alert(msg.Message);

        });

    };

}).controller('TicketReportController', function ($scope, report) {


    $scope.records = report.query();
    $scope.Close = function (x) {
        x.case = "Disable";
        x.$update(function () {
            $scope.records = report.query();
        });
    };
    $scope.Open = function (x) {
        alert("dfdfgb");
        x.case = "Enable";
        x.$update(function () {
            console.log(x);
            $scope.records = report.query();
        });
    };


}).controller('TicketResponseController', function ($scope, $stateParams, $interval, reply, message) {

    $scope.x = reply.get({
        username: $stateParams.username, id: $stateParams.id});


    $scope.x.username = $stateParams.username;

    $scope.x.id = $stateParams.id;

    //   $scope.x = msgcont.get({id: $stateParams.id});
    $scope.submit = function (x) {
        x.case = "reply";

        console.log($scope.x);
        x.id = $stateParams.id;
        console.log(x.id);
        console.log(x.response);
        x.$update(function (msg) {
            alert(msg.Message);
            $scope.records = message.query({id: $stateParams.id, username: $stateParams.username});
        });


    };
    $scope.records = message.query({id: $stateParams.id, username: $stateParams.username});
    $scope.theTime = new Date().toLocaleTimeString();
    $interval(function () {

        $scope.records = message.query({id: $stateParams.id, username: $stateParams.username});

    }, 10000);


}).controller('NewTicketcontroller', function ($scope, newticket, report) {

    $scope.records = newticket.query();

    var x = new report();
    $scope.Close = function (x) {
        x.case = "Disable";
        x.$update(function () {
            $scope.records = newticket.query();
        });
    };
    $scope.Open = function (x) {
        
        x.case = "Enable";
        x.$update(function () {
            console.log(x);
            $scope.records = newticket.query();
        });
    };
}).controller('ClosedTicketcontroller', function ($scope, closedticket, report) {

    $scope.records = closedticket.query();
    var x = new report();
    $scope.Close = function (x) {
        x.case = "Disable";
        x.$update(function () {
            $scope.records = closedticket.query();
        });
    };
    $scope.Open = function (x) {
        alert("dfdfgb");
        x.case = "Enable";
        x.$update(function () {
            console.log(x);
            $scope.records = closedticket.query();
        });
    };

})
        .controller('PendingAnswercontroller', function ($scope, pendinganswer, report) {

            $scope.records = pendinganswer.query();

            var x = new report();
            $scope.Close = function (x) {
                x.case = "Disable";
                x.$update(function () {
                    $scope.records = pendinganswer.query();
                });
            };
            $scope.Open = function (x) {
                alert("dfdfgb");
                x.case = "Enable";
                x.$update(function () {
                    console.log(x);
                    $scope.records = pendinganswer.query();
                });
            };
        }).controller('PendingAnswerReplyController', function ($scope, $stateParams, pendinganswer) {

    $scope.x = pendinganswer.get({
        username: $stateParams.username, id: $stateParams.id});
    $scope.x.username = $stateParams.username;

    $scope.x.id = $stateParams.id;

    //   $scope.x = msgcont.get({id: $stateParams.id});
    $scope.submit = function (x) {
        x.username = $stateParams.username;
        x.case = "reply";
        console.log(x);
        console.log($scope.x);
        x.id = $stateParams.id;
        $scope.x.$update(function (msg) {
            //alert("mdfdgf");
            alert(msg.Message);
        });

    };
    //$scope.records = message.query({id: $stateParams.id, username: $stateParams.username});

}).controller('LoginController',function($window){
    $window.open("http://biticash.club/login.jsp#!/login", "_self");
});
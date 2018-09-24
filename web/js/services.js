angular.module('mApp.services', [])
  
		.factory('mySocket', function (socketFactory) {
  var myIoSocket =  io.connect('https://localbitcoins.global:3020/');

  mySocket = socketFactory({
    ioSocket: myIoSocket
  });

  return mySocket;
}) 
        .factory('profile', function ($resource) {

            return $resource('./Profile/:id', {id: '@_id'}, {
                update: {
                    method: 'PUT'
                }
            });

        }).factory('trans', function ($resource) {

    return $resource('./Transaction/:id', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('escrowtrans', function ($resource) {

    return $resource('./EscrowTransaction/:id', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('escrowcredit', function ($resource) {

    return $resource('./EscrowCredit/:id', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('escrowtransreport', function ($resource) {

    return $resource('./EscrowTransactionhistory/:id', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('hash', function ($resource) {

    return $resource('./getTransactionHashes/:id', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('transreport', function ($resource) {

    return $resource('./Transactionhistory/:id', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('transreportdetails', function ($resource) {

    return $resource('./TransactionhashDetails/:addr', {addr: '@_addr'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('wallet', function ($resource) {

    return $resource('./wallet/:id', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('PaymentRequest1', function ($resource) {

    return $resource('./PaymentRequestForm/:id', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('btc', function ($resource) {

    return $resource('./Bitaddress?coin=bitcoin', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('imc', function ($resource) {

    return $resource('./Bitaddress?coin=imc', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('xrp', function ($resource) {

    return $resource('./Bitaddress?coin=xrp', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('eth', function ($resource) {

    return $resource('./Bitaddress?coin=eth', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('bch', function ($resource) {
    return $resource('./Bitaddress?coin=bch', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });
    // return $resource('./Bitaddress?coin=bch');


}).factory('PostTrade', function ($resource) {

    return $resource('./PostTrade/:id', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('AllTrade', function ($resource) {

    return $resource('./GetTrade?cs=Buy_bitcoin/:id', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('SellBitCoin', function ($resource) {

    return $resource('./SellBitCoin?cs=Buy_bitcoin&username=/:id', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('BuyBitCoin', function ($resource) {

    return $resource('./SellBitCoin?cs=Sell_bitcoin&username=/:id', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('SellBitcoinOrder', function ($resource) {

    return $resource('./SellBitcoinOrder?id=:id', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('Chatbox', function ($resource) {

    return $resource('./Chatbox?id=:id', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('getimage', function ($resource) {

    return $resource('./UploadImage', {
        update: {
            method: 'PUT'
        }
    });
}).factory('FileUploader', function ($resource) {

    return $resource('./UploadImage', {
        update: {
            method: 'PUT'
        }
    });
}).factory('CheckOrder', function ($resource) {

    return $resource('./CheckOrderUser/:id', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('Feesback', function ($resource) {

    return $resource('./Feesback?id=:id', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('balance', function ($resource) {

    return $resource('./Balance/:id', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('SetMargin', function ($resource) {

    return $resource('./SetMargin/:id', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('AllUser11', function ($resource) {

    return $resource('./AllUser?username=:id', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('notification', function ($resource) {

    return $resource('./Notification/:username', {
        username: '@_username'
    }, {
        update: {
            method: 'PUT'
        }
    });

}).factory('PaymentRequest', function ($resource) {

    return $resource('./Bitaddress?coin=:coin', {coin: '@_coin'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('AllUser', function ($resource) {

    return $resource('./AllUser?username=:username', {username: '@_username'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('Tradebyusername', function ($resource) {

    return $resource('./SellBitCoin?cs=Sell&username=:username', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('googleauth', function ($resource) {
    return $resource('./SetGoogleAuth', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });
}).factory('VarifyEmail', function ($resource) {

    return $resource('./VerfiyEmail', {

    }, {
        update: {
            method: 'PUT'
        }
    });

})
        .factory('emailverify', function ($resource) {
            return $resource('./EmailVerify/:id', {id: '@_id'}, {
                update: {
                    method: 'PUT'
                }
            });
        })
        .factory('getbitorder', function ($resource) {
            return $resource('./bitorder?id=:id', {id: '@_id'}, {
                update: {
                    method: 'PUT'
                }
            });
        })
        .factory('send', function ($resource) {
            return $resource('./Send?id=:id', {id: '@_id'}, {
                update: {
                    method: 'PUT'
                }
            })
                    ;
        })
        .factory('SellBitCoinCash', function ($resource) {

            return $resource('./SellBitCoin?cs=Buy_bitcoincash&username=/:id', {id: '@_id'}, {
                update: {
                    method: 'PUT'
                }
            });

        }).factory('BuyBitCoinCash', function ($resource) {

    return $resource('./SellBitCoin?cs=Sell_bitcoincash&username=/:id', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

})
        .factory('SellEthereumm', function ($resource) {

            return $resource('./SellBitCoin?cs=Buy_ethereum&username=/:id', {id: '@_id'}, {
                update: {
                    method: 'PUT'
                }
            });

        })
        .factory('BuyEthereum', function ($resource) {

            return $resource('./SellBitCoin?cs=Sell_ethereum&username=/:id', {id: '@_id'}, {
                update: {
                    method: 'PUT'
                }
            });

        })
        .factory('SellImobicash', function ($resource) {

            return $resource('./SellBitCoin?cs=Buy_imobicash&username=/:id', {id: '@_id'}, {
                update: {
                    method: 'PUT'
                }
            });

        })
        .factory('BuyImobicash', function ($resource) {

            return $resource('./SellBitCoin?cs=Sell_imobicash&username=/:id', {id: '@_id'}, {
                update: {
                    method: 'PUT'
                }
            });

        })
        .factory('userbalance', function ($resource) {

            return $resource('./userbalance', {id: '@_id'}, {
                update: {
                    method: 'PUT'
                }
            });

        })
        .factory('SellBCHOrder', function ($resource) {

            return $resource('./SellBCHOrder?id=:id', {id: '@_id'}, {
                update: {
                    method: 'PUT'
                }
            });

        })
        .factory('SellRipple', function ($resource) {

            return $resource('./SellBitCoin?cs=Buy_Ripple&username=/:id', {id: '@_id'}, {
                update: {
                    method: 'PUT'
                }
            });

        }).factory('BuyRipple', function ($resource) {

    return $resource('./SellBitCoin?cs=Sell_Ripple&username=/:id', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

})
        .factory('SellTether', function ($resource) {

            return $resource('./SellBitCoin?cs=Buy_tether&username=/:id', {id: '@_id'}, {
                update: {
                    method: 'PUT'
                }
            });

        }).factory('BuyTether', function ($resource) {

    return $resource('./SellBitCoin?cs=Sell_tether&username=/:id', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('creditdebit', function ($resource) {

    return $resource('./creditdebit?username=:username', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('invoice', function ($resource) {

    return $resource('./Invoice?id=:id', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('Transaction_Detail', function ($resource) {

    return $resource('./Transaction_Detail', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('walletSetting', function ($resource) {

    return $resource('./WalletSetting', {id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

}).factory('report', function ($resource) {
   return $resource('./Support_Ticket/:id', {id: '@_id'},{
        update: {
            method: 'PUT'
        }
    });
}).factory('newticket', function ($resource) {
    return $resource('./Support_Ticket?case=newticket',{id: '@_id'},{
        update: {
            method: 'PUT'
        }
    });
}).factory('reply', function ($resource) {
   return $resource('./Support_Ticket?id=:id&username=:username', {username: '@_username',id: '@_id'},{
        update: {
            method: 'PUT'
        }
    });
}).factory('closedticket', function ($resource) {
    return $resource('./Support_Ticket?case=closedticket',{id: '@_id'},{
        update: {
            method: 'PUT'
        }
    });
}).factory('pendinganswer', function ($resource) {
    return $resource('./PendingAnswer?id:id&username:username',{id: '@_id'},{
        update: {
            method: 'PUT'
        }
    });
}).factory('message', function ($resource) {
   return $resource('./SaveMessage?id=:id',{id: '@_id'},{
        update: {
            method: 'PUT'
        }
    });
}).service('popupService', function ($window) {
    this.showPopup = function (message) {
        return $window.confirm(message);
    };
});
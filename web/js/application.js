/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


angular.module('mApp', ['ui.router', 'ngResource', 'mApp.controllers', 'mApp.services',
    'ngClickCopy', 'ngAnimate', 'ngSanitize', 'ui.bootstrap',
    'angularFileUpload']);

angular.module('mApp').config(function ($stateProvider) {
    $stateProvider.state('Profile', {
        url: '/profile',
        templateUrl: 'ethereum/profile.jsp',
        controller: 'ProfileController'

    }).state('Wallet', {
        url: '/wallet',
        templateUrl: 'ethereum/wallet.jsp',
        controller: 'WalletController'

    }).state('Balance', {
        url: '/Balance',
        templateUrl: 'ethereum/Balance.jsp',
        controller: 'GetBalanceController'

    }).state('sendpayment', {
        url: '/sendpayment',
        templateUrl: 'ethereum/credit.jsp',
        controller: 'creditController'

    }).state('sendescrowpayment', {
        url: '/sendescrowpayment',
        templateUrl: 'ethereum/sendescrowpayment.jsp',
        controller: 'SendEscrowPaymentController'

    }).state('receivepayment', {
        url: '/receivepayment',
        templateUrl: 'ethereum/debit.jsp',
        controller: 'debitController'

    }).state('Transactionreport', {
        url: '/transactionreport',
        templateUrl: 'ethereum/Transactionhistory.jsp',
        controller: 'TransactionreportsController'

    }).state('EscrowTransactionreport', {
        url: '/escrowtransactionreport',
        templateUrl: 'ethereum/EscrewTransactionhistory.jsp',
        controller: 'EscrowTransactionreportController'

    }).state('PaymentRequest', {
        url: '/paymentrequest/:coin/paymentrequest',
        templateUrl: 'ethereum/paymentrequestform.jsp',
        controller: 'PaymentRequestController'

    }).state('Deposit', {
        url: '/depositrequest',
        templateUrl: 'ethereum/deposit.jsp'
                // controller:'DepositRequestController'
    }).state('BTC', {
        url: '/btcaddress',
        templateUrl: 'ethereum/deposit.jsp',
        controller: 'BTCController'

    }).state('IMC', {
        url: '/imobicash',
        templateUrl: 'ethereum/imdeposit.jsp',
        controller: 'IMCController'

    }).state('XRP', {
        url: '/xrpaddress',
        templateUrl: 'ethereum/deposit.jsp',
        controller: 'XRPController'

    }).state('BCH', {
        url: '/bch',
        templateUrl: 'ethereum/deposit.jsp',
        controller: 'BCHController'

    }).state('Litecoin', {
        url: '/litecoin',
        templateUrl: 'ethereum/deposit.jsp',
        //controller:'BTCController'
    }).state('Ethereum', {
        url: '/Ethereum',
        templateUrl: 'ethereum/deposit.jsp',
        controller: 'ETHController'
    }).state('EthereumClass', {
        url: '/EthereumClass',
        templateUrl: 'ethereum/ethereumclass.jsp'
                //controller:'BTCController'

    }).state('Zcash', {
        url: '/zcash',
        templateUrl: 'ethereum/Zcash.jsp'
                //controller:'BTCController'

    }).state('Monero', {
        url: '/monero',
        templateUrl: 'ethereum/monero.jsp'
                //controller:'BTCController'

    }).state('Dash', {
        url: '/dash',
        templateUrl: 'ethereum/dash.jsp'
                //controller:'BTCController'

    }).state('Ripple', {
        url: '/ripple',
        templateUrl: 'ethereum/ripple.jsp'
                //controller:'BTCController'

    }).state('Iota', {
        url: '/iota',
        templateUrl: 'ethereum/iota.jsp'
                //controller:'BTCController'

    }).state('EOS', {
        url: '/eos',
        templateUrl: 'ethereum/eos.jsp'
                //controller:'BTCController'

    }).state('Santiment', {
        url: '/EthereumClass',
        templateUrl: 'ethereum/santiment.jsp'
                //controller:'BTCController'

    }).state('OmiseGO', {
        url: '/omisego',
        templateUrl: 'ethereum/omise.jsp'
                //controller:'BTCController'

    }).state('NEO', {
        url: '/neo',
        templateUrl: 'ethereum/neo.jsp'
                //controller:'BTCController'

    }).state('ETP', {
        url: '/etp',
        templateUrl: 'ethereum/etp.jsp'
                //controller:'BTCController'

    }).state('BTG', {
        url: '/btg',
        templateUrl: 'ethereum/btg.jsp'
                //controller:'BTCController'

    }).state('QASH', {
        url: '/qash',
        templateUrl: 'ethereum/qash.jsp'
                //controller:'BTCController'

    }).state('QTUM', {
        url: '/qtum',
        templateUrl: 'ethereum/qtum.jsp'
                //controller:'BTCController'

    }).state('PostTrade', {
        url: '/PostTrade',
        templateUrl: 'ethereum/PostTrade.jsp',
        controller: 'PostTradeController'

    }).state('AllTrade', {
        url: '/AllTrade',
        templateUrl: 'ethereum/AllTrade.jsp',
        controller: 'AllTradeController'

    }).state('SellBitCoin', {
        url: '/sellbitcoin',
        templateUrl: 'ethereum/SellBitCoin.jsp',
        controller: 'SellBitCoinController'

    }).state('BuyBitCoin', {
        url: '/buybitcoin',
        templateUrl: 'ethereum/BuyBitCoin.jsp',
        controller: 'BuyBitCoinController'

    }).state('SellBitcoinOrder', {
        url: '/sellbitcoinorder/:id',
        templateUrl: 'ethereum/SellBitcoinOrder.jsp',
        controller: 'SellBitcoinOrderController'

    }).state('BuyBitcoinOrder', {
        url: '/buybitcoinorder/:id',
        templateUrl: 'ethereum/BuyBitcoinOrder.jsp',
        controller: 'BuyBitcoinOrderController'

    }).state('Chatbox', {
        url: '/chatbox/:id/:Message',
        templateUrl: 'ethereum/chatbox.jsp',
        controller: 'ChatboxController'

    }).state('CheckOrder', {
        url: '/checkorder',
        templateUrl: 'ethereum/CheckOrder.jsp',
        controller: 'CheckOrderController'

    }).state('Feesback', {
        url: '/feedback/:id',
        templateUrl: 'ethereum/Feesback.jsp',
        controller: 'FeesbackController'

    }).state('invoice', {
        url: '/invoice/:id',
        templateUrl: 'ethereum/invoicedetail.jsp',
        controller: 'invoicedetailController'

    }).state('SetMargin', {
        url: '/setmargin',
        templateUrl: 'ethereum/SetMargin.jsp',
        controller: 'SetMarginController'

    }).state('AllUser', {
        url: '/alluser',
        templateUrl: 'ethereum/AllUser.jsp',
        controller: 'AllUserController'

    }).state('Notification', {
        url: '/notification',
        templateUrl: 'ethereum/notification.jsp',
        controller: 'ViewMoreNotificationController'

    }).state('PublicProfile', {
        url: '/publicprofile/:username',
        templateUrl: 'ethereum/PublicProfile.jsp',
        controller: 'PublicProfileController'

    }).state('GoogleAuth', {
        url: '/GoogleAuth',
        templateUrl: 'ethereum/SetGoogleAuth.jsp',
        controller: 'googleauthController'

    }).state('Send', {
        url: '/send',
        templateUrl: 'ethereum/send.jsp',
        controller: 'SendBTCController'
    }).state('sendbtc', {
        url: '/sendBTC',
        templateUrl: 'ethereum/send.jsp',
        controller: 'SendBTCController'
    }).state('sendethereum', {
        url: '/sendETH',
        templateUrl: 'ethereum/send.jsp',
        controller: 'SendETHController'
    }).state('sendbch', {
        url: '/sendBCH',
        templateUrl: 'ethereum/send.jsp',
        controller: 'SendBCHController'
    }).state('sendxrp', {
        url: '/sendXRP',
        templateUrl: 'ethereum/send.jsp',
        controller: 'SendXRPController'
    }).state('SendImobicash', {
        url: '/SendImobicash',
        templateUrl: 'ethereum/SendImobiCash.jsp',
        controller: 'SendImobiCashController'
    }).state('SellBitCoinCash', {
        url: '/sellbitcoincash',
        templateUrl: 'ethereum/SellBitCoinCash.jsp',
        controller: 'SellBitCoinCashController'
    }).state('BuyBitCoinCash', {
        url: '/buybitcoincash',
        templateUrl: 'ethereum/BuyBitcoincash.jsp',
        controller: 'BuyBitCoinCashController'
    }).state('SellEthereum', {
        url: '/sellethereum',
        templateUrl: 'ethereum/SellEthereum.jsp',
        controller: 'SellEthereumController'
    }).state('BuyEthereum', {
        url: '/buyethereum',
        templateUrl: 'ethereum/BuyEthereum.jsp',
        controller: 'BuyEthereumController'
    }).state('SellImobicash', {
        url: '/sellimobicash',
        templateUrl: 'ethereum/SellImobicash.jsp',
        controller: 'SellImobicashController'
    }).state('BuyImobicash', {
        url: '/buyimobicash',
        templateUrl: 'ethereum/BuyImobicash.jsp',
        controller: 'BuyImobicashController'
    }).state('SellBCHOrder', {
        url: '/sellbchorder/:id',
        templateUrl: 'ethereum/SellBCHOrder.jsp',
        controller: 'SellBCHOrderController'
    }).state('BuyBCHOrder', {
        url: '/buybchorder/:id',
        templateUrl: 'ethereum/BuyBCHOrder.jsp',
        controller: 'BuyBCHOrderController'
    }).state('SellETHOrder', {
        url: '/sellethorder/:id',
        templateUrl: 'ethereum/SellETHOrder.jsp',
        controller: 'SellETHOrderController'
    }).state('BuyETHOrder', {
        url: '/buyethorder/:id',
        templateUrl: 'ethereum/BuyETHOrder.jsp',
        controller: 'BuyETHOrderController'
    }).state('SellimobicashOrder', {
        url: '/sellimobicashorder/:id',
        templateUrl: 'ethereum/SellimobicashOrder.jsp',
        controller: 'SellImobicashOrderController'
    }).state('BuyimobicashOrder', {
        url: '/buyimobicashorder/:id',
        templateUrl: 'ethereum/BuyimobicashOrder.jsp',
        controller: 'BuyImobicashOrderController'
    }).state('SellRipple', {
        url: '/sellripple',
        templateUrl: 'ethereum/SellRipple.jsp',
        controller: 'SellRippleController'
    }).state('BuyRipple', {
        url: '/buyripple',
        templateUrl: 'ethereum/BuyRipple.jsp',
        controller: 'BuyRippleController'
    }).state('SellRippleOrder', {
        url: '/sellrippleorder/:id',
        templateUrl: 'ethereum/SellRippleOrder.jsp',
        controller: 'SellRippleOrderController'
    }).state('BuyRippleOrder', {
        url: '/buyrippleorder/:id',
        templateUrl: 'ethereum/BuyRippleOrder.jsp',
        controller: 'BuyRippleOrderController'
    }).state('SellTether', {
        url: '/selltether',
        templateUrl: 'ethereum/SellTether.jsp',
        controller: 'SellTetherController'
    }).state('BuyTether', {
        url: '/buytether',
        templateUrl: 'ethereum/BuyTether.jsp',
        controller: 'BuyTetherController'
    }).state('SellTetherOrder', {
        url: '/selltetherorder/:id',
        templateUrl: 'ethereum/SellTetherOrder.jsp',
        controller: 'SellTetherOrderController'
    }).state('BuyTetherOrder', {
        url: '/buytetherorder/:id',
        templateUrl: 'ethereum/BuyTetherOrder.jsp',
        controller: 'BuyTetherOrderController'
    }).state('CreditToUser', {
        url: '/CreditFromUser/:username',
        templateUrl: 'ethereum/credittouser.jsp',
        controller: 'credittouserController'
    }).state('DebitToUser', {
        url: '/DebitFromUser/:username',
        templateUrl: 'ethereum/debittouser.jsp',
        controller: 'debittouserController'
    }).state('PaymentGateway', {
        url: '/PaymentGateway',
        templateUrl: 'ethereum/PaymentGateway.jsp',
        controller: 'PaymentGatewayController'
    }).state('Invoice', {
        url: '/Invoice/:id',
        templateUrl: 'ethereum/invoice.jsp',
        controller: 'InvoiceController'
    }).state('PaymentGatewaySend', {
        url: '/sendpaymnet',
        templateUrl: 'ethereum/sendpaymnet.jsp',
        controller: 'sendpaymnetController'
    }).state('receivedBtc', {
        url: '/receivedbtc',
        templateUrl: 'ethereum/receivedBtc.jsp',
        controller: 'receivedBtcController'



 }).state('Discuss', {
        url: '/discuss/:id',
        templateUrl: 'ethereum/discuss.jsp',
        controller: 'DiscussController'
        
        
        

    }).state('SendSetting', {
        url: '/SendSetting',

        templateUrl: 'ethereum/sendBTC.jsp',
        controller: 'SendBTCNewController'


    }).state('AddTicket',{
        url:'/ticket',
       templateUrl:'support/Ticket.jsp',
        controller:'TicketController'
        
        
    }).state('MesReport',{
        url:'/ticketreport',
       templateUrl:'support/Ticket_report.jsp',
        controller:'TicketReportController'
        
        
    }).state('MsgResponse',{
        url:'/ticketresponse/:id/:username',
       templateUrl:'support/ticketresponse.jsp',
        controller:'TicketResponseController'
        
        
    }).state('NewTicket', {
        url: '/New_ticket',
        templateUrl: 'support/NewTicket.jsp',
        controller: 'NewTicketcontroller'
    })
    .state('ClosedTicket', {
        url: '/ClosedTicket',
        templateUrl: 'support/closed_ticket.jsp',
        controller: 'ClosedTicketcontroller'
    })
     .state('PendingAnswer', {
        url: '/Pending_answer',
        templateUrl: 'support/pending_answer.jsp',
        controller: 'PendingAnswercontroller'
    }).state('Reply',{
        url:'/reply/:id/:username',
       templateUrl:'support/pendinganswer_reply.jsp',
        controller:'PendingAnswerReplyController'
    }).state('login',{
        url:'/login',
       templateUrl:'ethereum/login.jsp',
        controller:'LoginController'
    });
}).run(function ($state) {
    $state.go('login');
});

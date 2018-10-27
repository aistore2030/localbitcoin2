<%-- 
    Document   : sendpaymnet
    Created on : Apr 24, 2018, 2:20:02 PM
    Author     : Saksham
--%>
<%@page import="java.net.URL"%>
<% String username = String.valueOf(session.getAttribute("username")).trim();
    String insertID = String.valueOf(session.getAttribute("insertID")).trim();
    String url = request.getRequestURL().toString();
    URL url1 = new URL(url);
    String domain = url1.getHost();
    System.out.println(domain);%>
<form action="http://realstatecoin.trade/Payment" method="post"> 
    <input type="hidden" name="email" value="<%=username%>">

    <!-- Specify a Buy Now button. -->

    <!-- Specify details about the item that buyers will purchase. -->
    <input type="hidden" name="fees" placeholder="fees" value="{{fees}}">
    <input type="hidden" name="amount" placeholder="amount in BTC" value="{{amount}}">
    <input type="hidden" name="currency_code" value="BTC">
    <input type="hidden" name="orderid" value="<%=insertID%>">
    <input type="hidden" name="root_url" value="http://<%=domain%>">
    <input type="hidden" name="notify_url" value="http://<%=domain%>/AcceptResponse">

    <!-- Display the payment button. -->
    <input type="image" name="submit" border="0"
           src="https://www.paypalobjects.com/en_US/i/btn/btn_buynow_LG.gif"
           alt="Buy Now">
    <img alt="" border="0" width="1" height="1"
         src="https://www.paypalobjects.com/en_US/i/scr/pixel.gif" >

</form>

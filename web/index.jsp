<%-- 
    Document   : index.jsp
    Created on : Sep 12, 2018, 2:05:41 PM
    Author     : susheel
--%>

<%@ include file="nheader.jsp" %>
<center id="error_msg" >
    <h2>
        <%
            if (request.getAttribute("msg") != null) {
                out.print(String.valueOf(request.getAttribute("msg")));
            }
        %>
    </h2>  
</center>
<div class="container-fluid">
    <div class="container">


        <div class="row"> 
            <div class="col-md-12">
                <div ui-view></div>

            </div>

        </div>

    </div>

</div>
<%@ include file="ifooter.jsp" %>
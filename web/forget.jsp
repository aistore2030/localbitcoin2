<%@ include file="nheader.jsp" %>

<div class="container-fluid">
    <div class="container">


        <div class="row"> 
            <div class="col-md-6">


                <div class="panel-title">Provide your email we will send you reset password link</div>




                <form action="ForgotPassword" method="post">
                    <div class="form-group mb10">


                        <input type="text" class="form-control" placeholder="Enter email" name="email">

                    </div>
                    <button class="btn btn-primary" type="submit">Submit</button>

                </form>
                <center id="error_msg" >
                    <%                    if (request.getAttribute("msg") != null) {
                            out.print(String.valueOf(request.getAttribute("msg")));
                        }
                    %>
                </center>

            </div></div>

    </div>
</div><!-- panel -->

<%@ include file="ifooter.jsp" %>

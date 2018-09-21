<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="springwork.model.*, java.util.*, springwork.dao.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>VemnoX</title>
    
        <style>
    	<%@ include file="/WEB-INF/resources/css/bootstrap.min.css"%>
    	<%@ include file="/WEB-INF/resources/css/ie10-viewport-bug-workaround.css"%>
    	<%@ include file="/WEB-INF/resources/css/blog.css"%>
	</style> 
		
	<script>
		<%@ include file="/WEB-INF/resources/js/ie-emulation-modes-warning.js"%>
	</script>    

  </head>

  <body>
	<%@include file="/WEB-INF/views/header.jsp"%>
    <div class="container" style="margin-top:5%;">

      <div class="blog-header">
        <h3 class="lead blog-description" style="color:black;font-size: 35px;text-align:center">Send money and make purchases</h3>
      </div>

      <div class="row">

        <div class="col-sm-8 blog-main">

          <div class="blog-post">
            <h5 style="font-size: 30px;padding-left:40px;">Latest Payments</h5>
            
	        <% if(session.getAttribute("paymentSuccess") != null) %>
				<h4 id="message" style="color:green;text-align:center">${paymentSuccess}</h4>

<% 	UserAccount userInfo = (UserAccount) request.getAttribute("isUserLogin");
	UserAccountDAO userAccountDAO = new UserAccountDAO();
	PaymentDAO paymentDAO = new PaymentDAO();
	List<UserAccount> userPayments = new ArrayList<UserAccount>();
	userPayments = paymentDAO.showALlPayments(userInfo.getUserID());
%>
	<table style="width:100%;border-spacing:12px;border-collapse: separate;">
	<%if (userPayments.size() != 0) { %>
<%  for (UserAccount p : userPayments) {%>
		<tr>
			<td style="padding:10px 0 0 10px; border:2px solid #dcdcdc; width:500px; box-shadow: 3px 5px #dcdcdc;">
				<p style="color:#ccc"><%=(p.getPayment().getDateTime())%></p>
				<p><span style="color:blue"><%=(userAccountDAO.findUserByID(p.getPayment().getFromUserID()).getFirstName()+
							" "+userAccountDAO.findUserByID(p.getPayment().getFromUserID()).getLastName()) %></span>
							paid <strong>$ <%=(p.getPayment().getPaymentAmount()) %></strong> to 
					<span style="color:blue"><%=(userAccountDAO.findUserByID(p.getPayment().getToUserID()).getFirstName()+
							" "+userAccountDAO.findUserByID(p.getPayment().getToUserID()).getLastName()) %></span>
		  			</p>
		  		<% if(p.getPayment().getDescription() != null)  {%>
		   		<p style="padding-left:10px;"><em><%=(p.getPayment().getDescription()) %></em></p>
		   		<%} %>
		   	</td>
		</tr>
<% } 
}
else {%>
	<tr>
		<td style="padding:10px 0 0 10px; border:2px solid #dcdcdc; width:500px; box-shadow: 3px 5px #dcdcdc;">
			<h4>No payments to show</h4>
		</td>
	</tr>
<%}%>

		</table>    	
      </div><!-- /.blog-post -->

        </div><!-- /.col-sm-8 blog-main -->

        <div class="col-sm-3 col-sm-offset-1 blog-sidebar">
          <div class="sidebar-module sidebar-module-inset">
       <table style="border-spacing:5px;border-collapse: separate; padding-left:40px">
          <tr>
          	<td style="text-align:center">
				<p>
            		<img src="data:image/png;base64,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" alt="image" style="width:100px; height: 100px"/>
            	</p>
            	<h4>Welcome, ${isUserLogin.firstName} </h4>
           </td>
          </tr>
		  <tr>
           <td>
				<p>Member since: ${isUserLogin.joinDate}</p>
		   </td>
		  </tr>
		  <tr>
			<td style="text-align: center">
            	<h5 style="color:#006400;font-size: 16px;">Your Balance: <span><strong>$ ${isUserLogin.balance}</strong></span></h5>
            	
            	<% if (userInfo.getBalance() > 0) %>
            		<p><a href="transfer" style="font-size: 18px;">Transfer to the Bank</a></p>
            	
            </td>
		  </tr>
		</table>
        </div>
      </div><!-- /.row -->  
      </div>
      </div>
<%@include file="/WEB-INF/views/footer.jsp" %>


<script>
	setTimeout(function() {
	    $('#message').fadeOut('fast');
	}, 3000);
</script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery ||
document.write('<script src="<c:url value="/resources/js/jquery-1.11.3.min.js" />"><\/script>')</script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
  </body>
</html>
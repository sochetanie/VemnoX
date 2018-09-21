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

		<title>Transfer</title>

	<style>
    	<%@ include file="/WEB-INF/resources/css/bootstrap.min.css"%>
    	<%@ include file="/WEB-INF/resources/css/ie10-viewport-bug-workaround.css"%>
    	<%@ include file="/WEB-INF/resources/css/blog.css"%>
	</style> 
		
	<script>
		<%@ include file="/WEB-INF/resources/js/ie-emulation-modes-warning.js"%>
	</script>
	
<style>
	.amountInput {
		margin: 5% 0 1% 14%;
		width: 50%;
		float:left;
		display:inline-block;
	}
	
</style>

	</head>
	
	<body>
	<%@include file="/WEB-INF/views/header.jsp"%>	
	
<% 	
	List<BankAccount> bankAccountInfo = (List<BankAccount>) request.getAttribute("allBankAccounts");
	UserAccount userInfo = (UserAccount) request.getAttribute("isUserLogin");
%>	
<div class="container" style="margin-top:8%;">

	<div class="col-xs-6" style="margin-left:-12%;">
		<h4 style="color:#006400; text-align:center">Your Balance:<span><strong>$ ${isUserLogin.balance}</strong></span></h4>
		<table style="width:100%; border-spacing:12px; border-collapse: separate;">

		<form action="transferToBank" method="POST" modelAttribute="user" >
		
			<% 	if(session.getAttribute("errorTransfer") != null) %>
		    	<tr>
		        	<td> <h4 id="message" style="color:red;text-align:center">${errorTransfer}</h4></td>
		        </tr>
	
		   	<% if(session.getAttribute("successTransfer") != null) %>
				<tr>
		           	<td><h4 id="message" style="color:green;text-align:center">${successTransfer}</h4></td>
				</tr>
			
			<tr>	
				<td>Amount to transfer</td>
				<td><input type="text" name="transferAmount" /></td>
			</tr>
			<tr>
				<td>Bank ID</td>
				<td>
					<input type="text" name="banckAccountID" />
				</td>
			</tr>
			<tr>
				<td></td>
				<td style="text-align:center;">
					<input style="background-color:#5bc0de; padding: 5px; border-radius: 8px; border:0 none;" type="submit" value="Submit" />
				</td>
			</tr>
		</form>
		</table>
	</div>
	
	
	<div class="col-xs-6" style="margin-left:8%;">
		<table style="width:100%;border-spacing:12px;border-collapse: separate;">
			<tr>
				<td style="text-align:center"><h4><strong>Bank Accounts</strong></h4></td>
			</tr>
	<%if (bankAccountInfo.size() != 0) { %>
<%  for (BankAccount ba : bankAccountInfo) {%>
		<tr >
			<td style="padding:10px 0 5px 10px; border:2px solid #dcdcdc; width:300px; box-shadow: 3px 5px #dcdcdc;">
				<p>Bank ID: <strong><%=(ba.getBankAccountID()) %></strong></p>
				<p>Routing Number: <strong><%=(ba.getRoutingNumber()) %></strong></p>
				<p>Account Number: <strong><%=(ba.getAccountNumber()) %></strong></p>
				<p style="display: inline-block;">Amount Available: <strong>$ <%=(ba.getAmountAvailable()) %></strong></p>
		   	</td>
		</tr>
<% } 
}
else {%>
	<tr>
		<td style="padding:10px 0 0 10px; border:2px solid #dcdcdc; width:500px; box-shadow: 3px 5px #dcdcdc;">
			<h4>No Bank Accounts to show</h4>
		</td>
	</tr>
<%}%>

		</table>
	</div>
</div> <!-- /.container -->

<%@include file="/WEB-INF/views/footer.jsp" %>

<script>
	setTimeout(function() {
	    $('#message').fadeOut('fast');
	}, 3000);
</script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery ||
document.write('<script src="<c:url value="/resources/js/jquery-1.11.3.min.js" />"><\/script>')</script>
<%-- <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script> --%>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
  </body>
</html>
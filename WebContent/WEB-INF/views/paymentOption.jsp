<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="springwork.model.*, java.util.*, springwork.dao.*, java.time.*, java.time.format.*" %>
<!DOCTYPE html>, 
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">
    
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
    <div class="container" style="margin-top:5%; min-height: 600px;">
 		<div class="row">   
		    <div class="col-xs-12" style="text-align:center; margin-bottom:2%">
		    	<h2>Payment Options</h2>
		    	<h4><a href="addPaymentOption">+ Add New Payment Option</a></h4>
		    	
				<% 	if(session.getAttribute("errorPayOpt") != null) %>
		            <h4 id="message" style="color:red;text-align:center">${errorPayOpt}</h4>
		            

		        <% if(session.getAttribute("successPayOpt") != null) %>
					<h4 id="message" style="color:green;text-align:center">${successPayOpt}</h4>
				
		    </div>
<% 	
	List<CreditCards> creditCardsInfo = (List<CreditCards>) request.getAttribute("allCreditCards"); 
	List<BankAccount> bankAccountInfo = (List<BankAccount>) request.getAttribute("allBankAccounts");
%>
	
	<!-- Credit Cards  -->
	<div class="col-xs-6">
		<table style="width:100%;border-spacing:12px;border-collapse: separate;">
			<tr>
				<td style="text-align:center"><h4><strong>Credit Cards</strong></h4></td>
			</tr>
	<%if (creditCardsInfo.size() != 0) { %>
<%  for (CreditCards cc : creditCardsInfo) {%>
		<tr>
			<td style="padding:10px 0 5px 10px; border:2px solid #dcdcdc; width:500px; box-shadow: 3px 5px #dcdcdc;">
				<p>Card Holder: <strong><%=(cc.getCardHolder()) %></strong></p>
				<% 
				String lastFour  = cc.getCardNumber().substring(cc.getCardNumber().length()-4, cc.getCardNumber().length());
				String ccNumber = "****"+lastFour;
				%>
				<p>Card Number: <strong><%=ccNumber %></strong></p>
				
				<% 
					String exDate = cc.getExpirationDate().split(" ")[0];
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
					DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("MM/yy", Locale.ENGLISH);
					LocalDate ld = LocalDate.parse(exDate, dtf);
					exDate = dtf2.format(ld);
				%>
				<p>Expiration Day: <strong><%=exDate %></strong></p>
				<p>CVV: <strong><%=(cc.getCVV()) %></strong></p>
				<p>Zip Code: <strong><%=(cc.getZipCode()) %></strong></p>
				<p style="display: inline-block;">Amount Available: <strong>$ <%=(cc.getAmountAvailable()) %></strong></p>
				<a href="deleteCreditCard?creditCardID=<%=(cc.getCreditCardID()) %>" style="margin: 0 0 0 20px;color: black;">
					<input 
					style="background-color:#FA8072; padding: 5px; border-radius: 8px; border:0 none; float:right; margin: 0 10px 0 0;" 
					type="submit" class="button" value="Delete" >
				</a>
		   	</td>
		</tr>
<% } 
}
else {%>
	<tr>
		<td style="padding:10px 0 0 10px; border:2px solid #dcdcdc; width:500px; box-shadow: 3px 5px #dcdcdc;">
			<h4>No Credit Cards to show</h4>
		</td>
	</tr>
<%}%>

		</table>  
	</div>
	
	<!-- Bank Accounts  -->
	<div class="col-xs-6">
		<table style="width:100%;border-spacing:12px;border-collapse: separate;">
			<tr>
				<td style="text-align:center"><h4><strong>Bank Accounts</strong></h4></td>
			</tr>
	<%if (bankAccountInfo.size() != 0) { %>
<%  for (BankAccount ba : bankAccountInfo) {%>
		<tr>
			<td style="padding:10px 0 5px 10px; border:2px solid #dcdcdc; width:500px; box-shadow: 3px 5px #dcdcdc;">
				<p>Routing Number: <strong><%=(ba.getRoutingNumber()) %></strong></p>
				<% 
					String lastFour  = ba.getAccountNumber().substring(ba.getAccountNumber().length()-4, ba.getAccountNumber().length());
					String baNumber = "****"+lastFour;
				%>
				<p>Account Number: <strong><%=baNumber %></strong></p>
				<p style="display: inline-block;">Amount Available: <strong>$ <%=(ba.getAmountAvailable()) %></strong></p>
				<a href="deleteBankAccount?bankAccountID=<%=(ba.getBankAccountID()) %>" style="margin: 0 0 0 20px; color: black;" >
					<input 
					style="background-color:#FA8072; padding: 5px; border-radius: 8px; border:0 none; float:right; margin: 0 10px 0 0;" 
					type="submit" class="button" value="Delete" >
				</a>
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
	</div> <!-- /.row -->
</div><!-- /.container -->
      
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



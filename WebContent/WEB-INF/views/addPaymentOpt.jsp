<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
    	<%@ include file="/WEB-INF/resources/css/CCstyle.css"%>
	</style> 
	
	<script>
		<%@ include file="/WEB-INF/resources/js/ie-emulation-modes-warning.js"%>
	</script>

<body>
	<%@include file="/WEB-INF/views/header.jsp"%>

<div class="login-wrap">
	<div class="login-html">
		<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Add Credit Card</label>
		<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Add Bank</label>
<!-- Credit Card form -->
		<div class="login-form">
		<form action="newCreditCard" method="POST">
			<div class="sign-in-htm">
				<div class="group">
					<label for="user" class="label">Amount Available on a Card</label>
					<input type="text" name="amountAvailable" id="email" class="input" required="required" style="outline: none;"/>
				</div>
				<div class="group">
					<label for="pass" class="label">Card Holder</label>
					<input type="text" name="cardHolder" id="cardHolder" class="input" required="required" style="outline: none;" /> 
				</div>
				<div class="group">
					<label for="pass" class="label">Card Number</label>
					<input type="text" name="cardNumber" id="cardNumber" class="input" required="required" style="outline: none;" /> 
				</div>
				<div class="group">
					<label for="pass" class="label">Expiration Date</label>
					<input type="text" name="expirationDate" id="expirationDate" class="input" required="required" style="outline: none;" /> 
				</div>
				<div class="group">
					<label for="pass" class="label">Zip Code</label>
					<input type="text" name="zipCode" id="zipCode" class="input" required="required" style="outline: none;" /> 
				</div>
				<div class="group">
					<label for="pass" class="label">CVV</label>
					<input type="text" name="CVV" id="CVV" class="input" required="required" style="outline: none;" /> 
				</div>
				<div class="group">
					<input type="submit" class="button" value="Add Credit Card" >
				</div>
				<div class="hr"></div>
 				<div class="foot-lnk">

				</div> 
			</div>
 		</form>
 <!-- Bank Account form -->
			<div class="sign-up-htm">
		<form action="newBankAccount" method="POST"  > 
				<div class="group">
					<label for="firstName" class="label">Amount Available on a Bank Account</label>
				 	<input type="text" name="amountAvailable" id="firstName" class="input" required="required"/> 
				</div>
				<div class="group">
					<label for="laststName" class="label">Routing Number</label>
					<input type="text" name="routingNumber" id="lastName" class="input" required="required"/>  
				</div>
				<div class="group">
					<label for="pass" class="label">Account Number</label>
	 				<input type="text" name="accountNumber" id="password"  class="input" required="required"/>
				</div>
				<div class="group">
					<input type="submit" class="button" value="Add Bank Account" >
				</div>
		</form>
				<div class="hr"></div>
				<div class="foot-lnk">
				</div>
			</div>
		</div>
	</div>
</div>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery ||
document.write('<script src="<c:url value="/resources/js/jquery-1.11.3.min.js" />"><\/script>')</script>
<%-- <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script> --%>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
  </body>
</html>
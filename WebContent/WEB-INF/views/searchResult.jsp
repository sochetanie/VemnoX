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
 		<div class="row">  
 		
 <% 	
	UserAccount userInfo = (UserAccount) request.getAttribute("isUserLogin");
	List<UserAccount> searchUsers = (List<UserAccount>) request.getAttribute("searchUsers"); 
	List<CreditCards> creditCardsInfo = (List<CreditCards>) request.getAttribute("allCreditCards"); 
	List<BankAccount> bankAccountInfo = (List<BankAccount>) request.getAttribute("allBankAccounts");
%>	


 <div class="col-xs-6">
		<table style="width:100%;border-spacing:12px;border-collapse: separate;">
			<h2>Find <%=(searchUsers.size()) %> users</h2>
<%if (searchUsers.size() != 0) {
	for (UserAccount user : searchUsers) {%>
		<tr>
			<td>
			<div class="media" style="padding: 10px 0 0 10px; width: 400px;border: 2px solid #dcdcdc; box-shadow: 3px 5px #5bc0de;">
			  <div class="media-left media-top">
			       <img class="media-object" src="http://www.rosalindmaroney.co.uk/wp-content/uploads/2016/05/EB-books-for-instagram-02.jpg" style="width:60px; height: 60px" alt="image"/>
			  </div>
			  <div class="media-body" style="width: 200px;">
			    <h4 class="media-heading" style="margin-top: 8px;"><strong><%=(user.getFirstName()) %> <%=(user.getLastName()) %> </strong></h4>
			    <h5>Member since: <%=(user.getJoinDate()) %> </h5>
			    <p>User ID: <strong><%=(user.getUserID()) %> </strong></p>
			  </div>
			</div>
			</td>
		</tr>
<% } 
}
else {%>
		<tr>
			<td>
			<div class="media" style="padding: 10px 0 0 10px; width: 400px;border: 2px solid #DEB887; box-shadow: 3px 5px #DEB887;">
			  <div class="media-left media-top">
			       <img class="media-object" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcToyAq37PWQbAiixbnf7Yc_oYFrNBEcjZC9rbemmpSdahZ61cEMUw" style="width:60px; height: 60px" alt="image"/>
			  </div>
			  <div class="media-body" style="width: 200px;">
			    <h4 class="media-heading" style="margin-top: 8px;">No Users have been founded</h4>
			  </div>
			</div>
			</td>
		</tr>
<%}%>

		</table>  
</div>

 <div class="col-xs-6">
		<table style="width:100%;border-spacing:12px;border-collapse: separate;">
			<h3>Fill out the form to send money</h3>
			
			<% 	if(session.getAttribute("paymentFailed") != null) %>
	            <h4 id="message" style="color:red;text-align:center">${paymentFailed}</h4>
			
		<form action="payMoney" method="POST" modelAttribute="user" >
			<tr>
				<td>User ID you want to send money</td>
				<td><input type="text" name="toUserID" required="required" /></td>
			</tr>
			<tr>
				<td>Amount you want to send</td>
				<td><input type="text" name="paymentAmount" required="required" /></td>
			</tr>
			<tr>
				<td>Message</td>
				<td><input type="text" name="description" /></td>
			</tr>
			<tr>
				<td>Your Balance</td>
				<td>
					<input type="radio" name="paymentOptionID" value="0"/>&ensp;$ ${isUserLogin.balance}
				</td>
			</tr>
			
			<tr>
				<td>Credit Cards</td>
<%if (creditCardsInfo.size() != 0) { %>
	<%  for (CreditCards cc : creditCardsInfo) {%>
			<td style="display: block;">
				<% String lastFour  = cc.getCardNumber().substring(cc.getCardNumber().length()-4, cc.getCardNumber().length());
					String ccNumber = "****"+lastFour; %>
				<input type="checkbox" name="paymentOptionID" value="<%=(cc.getCreditCardID()) %>"/><%=ccNumber %>
			</td>
	<% } 
}%>			
			</tr>
			
			<tr>
				<td>Bank Accounts</td>
<%if (bankAccountInfo.size() != 0) { %>
	<%  for (BankAccount ba : bankAccountInfo) {%>
			<td style="display: block;">	
				<% String lastFour  = ba.getAccountNumber().substring(ba.getAccountNumber().length()-4, ba.getAccountNumber().length());
					String baNumber = "****"+lastFour;%>
				<input type="checkbox" name="paymentOptionID" value="<%=(ba.getBankAccountID()) %>"/><%=baNumber %>
			</td>
	<% } 
}%>			
			</tr>
			
			<tr>
				<td></td>
				<td style="float:right">
					<input style="background-color:#5bc0de; padding: 5px; border-radius: 8px; border:0 none;" type="submit" value="Submit" />
				</td>
			</tr>
		</table>  
</div>

		</div>	<!-- /.row -->		 
	
</div><!-- /.container -->

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
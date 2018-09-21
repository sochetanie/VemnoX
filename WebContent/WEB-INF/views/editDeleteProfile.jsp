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

		<title>Profile</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	<style>
    	<%@ include file="/WEB-INF/resources/css/bootstrap.min.css"%>
    	<%@ include file="/WEB-INF/resources/css/ie10-viewport-bug-workaround.css"%>
    	<%@ include file="/WEB-INF/resources/css/blog.css"%>
	</style> 
		
	<script>
		<%@ include file="/WEB-INF/resources/js/ie-emulation-modes-warning.js"%>
	</script>
	
	
<style>
	.profile {
    	margin: 7% 0 0 20%;
	}
	.errorMSG {
		margin: 1% 0 3% 4%;
	
	}
	#edit {
		margin-bottom: 2%;
	}
	form {
		width: 55%;
	}

</style>

	</head>
	
	<body>
	<%@include file="/WEB-INF/views/header.jsp"%>
	
<% UserAccount user = (UserAccount) session.getAttribute("isUserLoggin"); %> 
<div class="container" style="margin-top:5%;">

<div class="profile">
	<div id="edit"><h3>Edit Your Account Information</h3></div>
	
	<div class="errorMSG">
		<% if(session.getAttribute("error") != null) %>
			<h4 id="message" style="color:green;">${faildToUpdateUserInfo}</h4>
		<% if(session.getAttribute("success") != null) %>
			<h4 id="message" style="color:green;">${userInfoUpdated}</h4>
	 </div>
	 
	<form action="updateUserInfo" method="POST" modelAttribites="user">
		<div class="form-group has-success has-feedback">
		  <label class="control-label" for="inputGroupSuccess1">First Name</label>
		  <div class="input-group">
		    <span class="input-group-addon">&#128053;</span>
		    <input type="text" placeholder="${isUserLogin.firstName}" name="firstName" required="required" class="form-control" aria-describedby="inputGroupSuccess1Status">
		  </div>
		</div>
		<div class="form-group has-success has-feedback">
		  <label class="control-label" for="inputGroupSuccess1">Last Name</label>
		  <div class="input-group">
		    <span class="input-group-addon">&#128053;</span>
		    <input type="text" placeholder="${isUserLogin.lastName}" name="lastName" required="required" class="form-control" id="inputGroupSuccess1" aria-describedby="inputGroupSuccess1Status">
		  </div>
		</div>
		<div class="form-group has-success has-feedback">
		  <label class="control-label" for="inputGroupSuccess1">Phone Number</label>
		  <div class="input-group">
		    <span class="input-group-addon">&#128222;</span>
		    <input type="text" placeholder="${isUserLogin.phoneNumber}" name="phoneNumber" required="required" class="form-control" id="inputGroupSuccess1" aria-describedby="inputGroupSuccess1Status">
		  </div>
		</div>
		<div class="form-group has-success has-feedback">
		  <label class="control-label" for="inputGroupSuccess1">Email address</label>
		  <div class="input-group">
		    <span class="input-group-addon">&#9993;@</span>
		    <input type="text" placeholder="${isUserLogin.email}" name="email" class="form-control" required="required" id="inputGroupSuccess1" aria-describedby="inputGroupSuccess1Status">
		  </div>
		</div>

		<div class="form-group has-success has-feedback">
		  <label class="control-label" for="inputGroupSuccess1">Password</label>
		  <div class="input-group">
		    <span class="input-group-addon">&#128274;</span>
		    <input type="password" placeholder="Password" name="password" class="form-control" required="required" id="inputGroupSuccess1" aria-describedby="inputGroupSuccess1Status">
		  </div>
		</div>
			<button type="submit" name="submit" class="btn btn-info">Save Changes</button>
	</form>
		<br>
	<form action="deleteUser" method="POST" modelAttribites="user">
		<button type="submit" name="delete" value="Delete" class="btn btn-danger">
			Delete Account
		</button>
	</form>
	
</div>
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
  </body>
</html>
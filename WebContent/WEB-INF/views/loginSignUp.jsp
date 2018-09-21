<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" >

<head>
  <meta charset="UTF-8">
  <title>Login/SignUp</title>
  
  <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans:600'>
  
   <style>
     <%@ include file="/WEB-INF/resources/css/loginStyle.css"%>
	</style> 
	
</head>

<body>

  <div class="login-wrap">
	<div class="login-html">
		<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
		<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
<!-- LogIn form -->
		<div class="login-form">
		<form:form action="login" method="POST" modelAttribute="user">
			<div class="sign-in-htm">
				<div class="group">
				
					<label for="user" class="label">Email</label>
					<form:input type="text" path="email" id="email" class="input" style="outline: none;" required="required"/>
				</div>
				<div class="group">
					<label for="pass" class="label">Password</label>
					<form:input type="password" path="password" id="password" class="input" style="outline: none;" required="required" /> 
				</div>
				<div class="group">
					<input type="submit" class="button" value="Sign In" >
				</div>
				<% 	if(session.getAttribute("errorLogin") != null) %>
		            <h4 id="message" style="color:red;text-align:center">${errorLogin}</h4>
		        <% 	if(session.getAttribute("successSignUp") != null) %>
		        	<h4 id="message" style="color:green;text-align:center">${successSignUp}</h4>
		        <% if(session.getAttribute("errorSugnUp") != null) %>
					<h4 id="message" style="color:red;text-align:center">${errorSugnUp}</h4>
				<div class="hr"></div>
 				<div class="foot-lnk" style="color:white;line-height:1.6">
					<h4><strong>VemnoX</strong> is a free digital wallet that lets you make and share payments with friends. You can easily split the bill, cab fare, or much more.</h4>
					<p>***Both the sender and receiver have to live in the U.S.</p>
				</div> 
			</div>
 		</form:form>
 <!-- SignUp form -->
			<div class="sign-up-htm">
		<form:form action="signUp" method="POST" modelAttribute="user"> 
				<div class="group">
					<label for="firstName" class="label">First Name</label>
				 	<form:input type="text" path="firstName" id="firstName" class="input" required="required"/> 
				</div>
				<div class="group">
					<label for="laststName" class="label">Last Name</label>
					<form:input type="text" path="lastName" id="lastName" class="input" required="required"/>  
				</div>
				<div class="group">
					<label for="user" class="label">Email</label>
	 				<form:input type="text" path="email" id="email"  class="input" required="required"/>
				</div>
				<div class="group">
					<label for="pass" class="label">Password</label>
	 				<form:input type="password" path="password" id="password" class="input" required="required"/>  
				</div>
				<div class="group">
					<label for="phoneNumber" class="label">Phone Number</label>
	 				<form:input type="phoneNumber" path="phoneNumber" id="phoneNumber" class="input" required="required"/>  
				</div>
				<div class="group">
					<input type="submit" class="button" value="Sign Up" >
				</div>
		</form:form>
				<div class="hr"></div>
				<div class="foot-lnk">
					<label for="tab-1">Already Member?</a>
				</div>
			</div>
		</div>
	</div>
</div>
 
 <script>

	setTimeout(function() {
	    $('#message').fadeOut('fast');
	}, 3000); 

 </script>

</body>
</html>

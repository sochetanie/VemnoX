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
	<title>Statements</title>
	
	<style>
    	<%@ include file="/WEB-INF/resources/css/bootstrap.min.css"%>
    	<%@ include file="/WEB-INF/resources/css/ie10-viewport-bug-workaround.css"%>
    	<%@ include file="/WEB-INF/resources/css/blog.css"%>
	</style> 
		
	<script>
		<%@ include file="/WEB-INF/resources/js/ie-emulation-modes-warning.js"%>
	</script>

<style>
input[type="date"]::-webkit-clear-button {
    display: none;
}

input[type="date"]::-webkit-inner-spin-button { 
    display: none;
}

input[type="date"]::-webkit-calendar-picker-indicator {
    color: #2c3e50;
}

input[type="date"] {
    appearance: none;
    -webkit-appearance: none;
    color: white;
    font-family: "Helvetica", arial, sans-serif;
    font-size: 18px;
    border:1px solid #3d95ce;
    background:#3d95ce;
    padding:5px;
    height: 28px;
    margin-top: 10px;
    text-align: center;
    display: inline-block !important;
    visibility: visible !important;
}

input[type="date"], focus {
    color: white;
    box-shadow: none;
    -webkit-box-shadow: none;
    -moz-box-shadow: none;
}
.settings-page {
	margin: 10% 0 0 10%;
	font-family: Helvetica Neue,Helvetica,Arial,sans-serif;
}
h3 {
	font-size: 32px;
    font-weight: 300;
    line-height: 36px;
}
h4 {
	font-size: 20px;
    font-weight: 400;
    line-height: 24px;
    margin-bottom: 12px;
    margin-top: 40px;
}
.date-pickers {
	display: flex;
	font-size: 16px;
    line-height: 24px;
}
#separator {
	padding: 0 9px 0 14px;
	color: #7f7f7f;
}
p {
	margin-right: 10px;
	color: #7f7f7f;
	font-size: 18px;
}

.button {
  height: 42px;
  width: 200px;
  margin: 0 0 0 5%; 
  font-size: 22px;
  text-align: center;
  cursor: pointer;
  outline: none;
  color: #fff;
  background-color: #4CAF50;
  border: none;
  border-radius: 15px;
  box-shadow: 0 9px #999;
}

.button:hover {background-color: #3e8e41}

.button:active {
  background-color: #3e8e41;
  box-shadow: 0 5px #666;
  transform: translateY(4px);
}
</style>
</head>
<body>
<%@include file="/WEB-INF/views/header.jsp"%>

<% 	UserAccount userInfo = (UserAccount) request.getAttribute("isUserLogin");
	List<Statement> statement = (List<Statement>) request.getAttribute("statements"); 
%>

<div class="container">

		<div class="settings-page">
			<form action="specificStatement" method="POST" modelAttribute="user" >
			<h3 class="settings-title" style="margin-bottom:5%;">Account Statement</h3>
			<div class="settings-body">
				<div class="date-pickers">
					<p style="margin-top:2%;">Dates: </p>
					<input type="text" name="dateFrom" required="required" placeholder="MM-DD-YY">
					<p id="separator" style="margin-top:2%;"> - </p>
					<input type="text" name="dateTo" required="required" placeholder="MM-DD-YY">
					<input type="submit" class="button" value="Submit" >
				</div>
			</div>
			</form>
		</div>
		
		<div>
		<table class="table table-striped" style="margin-top:5%;">
		  <thead>
		    <tr>
		      <th>Transaction ID</th>
		      <th>Date</th>
		      <th>Amount</th>
		      <th>Type of Transaction</th>
		      <th>Deposit/Withdrawal</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<%if (statement.size() != 0) { %>
				<%  for (Statement st : statement) {%>
		    <tr>
		    	<th><%=(st.getId()) %></th>
		    	<th><%=(st.getDatetime()) %></th>
		    	<th>$ <%=(st.getAmount()) %></th>
		      	<th><%=(st.getType()) %></th>
		      	<th><%=(st.getStatus()) %></th>
		    </tr>
		   		<% } 
			}%>

		  </tbody>
		</table>	
		</div>

</div> <!-- /.container -->
<%@include file="/WEB-INF/views/footer.jsp" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery ||
document.write('<script src="<c:url value="/resources/js/jquery-1.11.3.min.js" />"><\/script>')</script>
<%-- <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script> --%>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
  </body>
</html>
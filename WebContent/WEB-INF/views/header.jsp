<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- NAVBAR  -->
<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">VemnoX</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li class="active"><a href="<c:url value="/home" />"> Home</a></li>
            <li><a href="<c:url value="/profile" />">Profile</a></li>
            <li><a href="<c:url value="/paymentOptions" />">Payment Methods</a></li>
			<li><a href="<c:url value="/statement" />">Statements</a></li>
			<li><a href="<c:url value="/logOut" />">LogOut</a></li>
          </ul>
          <form class="navbar-form navbar-right" action="searchresult" method="POST">
            <input type="text" class="form-control" placeholder="Search for user..." name="searchInput" > 
            <!-- name is the att that pass value   -->
          </form>
        </div>
      </div>
</nav>




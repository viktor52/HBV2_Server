<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
	<head>
		<title>Progress</title>
		<link rel="stylesheet" href="../css/bootstrap.min.css">
		<script src="../js/bootstrap.min.js"></script>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	</head>
	<body>
		<div class= "container">
			</p>
			<ul class="nav nav-tabs">
  				<li role="presentation"><a href="homepage">Home</a></li>
				<li role="presentation"><a href="myProfile">My Profile</a></li>
				<li role="presentation"><a href="logout">Log out</a></li>
			</ul>
			<div class="jumbotron">
				<h1>Your Statistics!</h1>
  				<p>Now you can view your progress.</p>
			</div>
			<h3 class="list-group-item-heading">${progressHeader}</h3>	 
			<ul class="list-group">
	     		<c:forEach var="stat" items="${stats}">
	   				<li class="list-group-item">${stat.date} : ${stat.average}</li>
	    		</c:forEach>
	 		</ul>
	 		<div style="display: ${display};">
				<img src='<c:url value="/resources/img/${username}.jpeg" />' alt=""> 
			</div>				
		</div>
		</p>
	</body>
</html>
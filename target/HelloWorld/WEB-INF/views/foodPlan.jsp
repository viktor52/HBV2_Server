<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
	<head>
		<title>Suggested foodplan</title>
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
				<h1>Your Diet Plan!</h1>
  				<p>Now you can start eating right.</p>
			</div>						
			<h3>Breakfast</h3>
			<p>${breakfast}</p>
			<h3>Lunch</h3>
			<p>${lunch}</p>
			<h3>Dinner</h3>
			<p>${dinner}</p>
		</div>
		</p>
	</body>
</html>

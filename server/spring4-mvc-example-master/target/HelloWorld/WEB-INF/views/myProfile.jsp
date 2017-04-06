<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
	<head>
		<title>my profile</title>
		<link rel="stylesheet" href="../css/bootstrap.min.css">         
		<script src="../js/bootstrap.min.js"></script>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	</head>
	<body>
		<div class="container">
			</p>
			<ul class="nav nav-tabs">
  				<li role="presentation"><a href="homepage">Home</a></li>
				<li role="presentation" class="active"><a href="myProfile">My Profile</a>
				<li role="presentation"><a href="logout">Log out</a></li>
				</li>
			</ul>
			<div class="jumbotron">
				<h1>Your Profile</h1>
  				<p>....</p>
			</div>
			<form method="POST" role="form">
				<div class="form-group">
					<h3>Name</h3>
					<p>${name}<p>
					<h3>Email</h3>
					<p>${email}</p>
					<h3>Age</h3>
					<p>${age}</p>
					<h3>Goal</h3>
					<p>${goal}</p>
					<h3>Gender</h3>
					<p>${gender}</p>
					<h3>Weight</h3>
					<p>${weight}</p>
				</div>
				<input class="btn btn-primary" type="submit" name="update" value="Update Profile"/>
			</form>
		</p>
		</div>
	</body>
</html>
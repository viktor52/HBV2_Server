<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
	<head>
		<title>Update your user information</title>
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
				<li role="presentation"><a href="myProfile">My Profile</a></li>
				<li role="presentation"><a href="logout">Log out</a></li>
			</ul>
			<div class="jumbotron">
				<h1>Update Your information!</h1>
  				<p>...</p>
			</div>
			<form method="POST" role="form">
				<div class="form-group">
					<label for="age">Type your age</label>
					<input value="${age}" type="text" class="form-control" name="age" required/>
				</div>
				<div class="form-group">
					<label for="goal">Select Goal</label>
					<select name="goal" placeholder="${goal}">
	  					<option value="stronger"  selected=${stronger == goal ? 'selected' : ''}>Stronger</option>
	  					<option value="buffer" selected=${buffer == goal ? 'selected' : ''}>Buffer</option>
						<option value="leaner" selected=${leaner == goal ? 'selected' : ''}>Leaner</option>
					</select>
				</div>
				<div class="form-group">
					<label for="weight">Type your weight</label>
					<input value="${weight}" type="double" pattern="^[0-9]+(\.[0-9]+)?" class="form-control" name="weight" required/>
				</div>
				<p>If a Goal is changed then you will get a new program on this date ${update}</p>
				<input class="btn btn-primary" type="submit" name="submit" value="Update"/>
			</form>
		</div>
	</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
	<head>
		<title>workout of today</title>
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
				<li role="presentation"><a href="stats">Progress</a></li>
				<li role="presentation"><a href="logout">Log out</a></li>
			</ul>
			<div class="jumbotron">
				<h1>Fill in your workout weights!</h1>
  				<p>That will help you keep track of your progress.</p>
			</div>
			<form method="POST" role="form">
				<div class="form-group">
		 			<table class="table">
	  					<tr>
	    					<th>Name</th>
	    					<th>Set</th> 
	    					<th>Reps</th>
	    					<th>Dumbbell weight</th>
	  					</tr>
	  					<c:set var="count" value="0" scope="page" />
	  					<c:forEach var="exercise" items="${exercises}">
		  					<tr>
		    					<td colspan="4">${exercise.name}</td>
		   						<c:forEach var="set" items="${exercise.set}">
		   						<c:set var="count" value="${count + 1}" scope="page"/>
		   							<tr>
		   								<td></td>
		   								<td>${set.number}</td> 
			   							<td>${set.rep}</td>
			   							<td>
			   								<div class="col-xs-3">
			    								<input type="text" name="${count}" value="${set.weight}" required="required" pattern="^[0-9]+(\.[0-9]+)?"class="form-control">
			   								</div>
			    						</td>
			    					</tr>
		    					</c:forEach>
		  					</tr>
	  					</c:forEach>
					</table>
				</div>
				<input class="btn btn-primary" type="submit" name="button" value="Submit"/>
			</form>
		</p>
		</div>
	</body>
</html>
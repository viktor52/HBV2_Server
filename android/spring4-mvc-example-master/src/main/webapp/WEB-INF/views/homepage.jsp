<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
	<head>
		<title>Homepage</title>
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
  				<li role="presentation" class="active"><a href="homepage">Home</a></li>
				<li role="presentation"><a href="myProfile">My Profile</a></li>
				<li role="presentation"><a href="logout">Log out</a></li>
			</ul>
			<div class="jumbotron">
				<h1>Welcome to your homepage!</h1>
  				<p>Now you can start exercising.</p>
			</div>
			<div class="row">
				<form method="POST", role="form">
		  			<div class="col-sm-6">
		   				<div class="card card-block">
		      				<h3 class="card-title">${day1Weekday}</h3>
		      				<p>${day1Date}</p>
		     				 <ul class="list-group list-group-flush">
		     				 	<c:forEach var="i" items="${day1Ex}">
	    							<li class="list-group-item">${i.name}</li>
	    						</c:forEach>
	 				 		</ul>
		      				<input type="submit" name="day1" class="btn btn-primary" value="Fill in results"></input>
		    			</div>
		  			</div>
		 			 <div class="col-sm-6">
		    			<div class="card card-block">
		      				<h3 class="card-title">${day2Weekday}</h3>
		      				<p>${day2Date}</p>
		      				<ul class="list-group list-group-flush">
		     				 	<c:forEach var="i" items="${day2Ex}">
	    							<li class="list-group-item">${i.name}</li>
	    						</c:forEach>
	 				 		</ul>
		      				<input type="submit" name="day2" class="btn btn-primary" value="Fill in results"></input>
		    			</div>
		  			</div>
		  			<div class="col-sm-6">
		    			<div class="card card-block">
		      				<h3 class="card-title">${day3Weekday}</h3>
		      				<p>${day3Date}</p>
		      				<ul class="list-group list-group-flush">
		     				 	<c:forEach var="i" items="${day3Ex}">
	    							<li class="list-group-item">${i.name}</li>
	    						</c:forEach>
	 				 		</ul>
		      				<input type="submit" name="day3" class="btn btn-primary" value="Fill in results"></input>
		    			</div>
		  			</div>
		  			<div class="col-sm-6">
		    			<div class="card card-block">
		      				<h3 class="card-title">${day4Weekday}</h3>
		      				<p>${day4Date}</p>
		      				<ul class="list-group list-group-flush">
		     				 	<c:forEach var="i" items="${day4Ex}">
	    							<li class="list-group-item">${i.name}</li>
	    						</c:forEach>
	 				 		</ul>
		      				<input type="submit" name="day4" class="btn btn-primary" value="Fill in results"></input>
		    			</div>
		  			</div>
		  			<div class="col-sm-6">
		    			<div class="card card-block">
		      				<h3 class="card-title">${day5Weekday}</h3>
		      				<p>${day5Date}</p>
		      				<ul class="list-group list-group-flush">
		     				 	<c:forEach var="i" items="${day5Ex}">
	    							<li class="list-group-item">${i.name}</li>
	    						</c:forEach>
	 				 		</ul>
		      				<input type="submit" name="day5" class="btn btn-primary" value="Fill in results"></input>
		    			</div>
		  			</div>
		  			<div class="col-sm-6">
		    			<div class="card card-block">
		      				<h3 class="card-title">Diet Plan</h3>
		      				<p>Suggested diet plan just for you.</p>
		      				<ul class="list-group list-group-flush">
		      					<li class="list-group-item">Breakfast</li>
		      					<li class="list-group-item">Lunch</li>
		      					<li class="list-group-item">Dinner</li>
		      				</ul>
		      				<input type="submit" name="food" class="btn btn-primary" value="Check it out"></input>
		    			</div>
		  			</div>
		  		</form>
			</div>			
		</div>
		</p>
	</body>
</html>
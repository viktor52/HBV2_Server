<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
	<head>
		<title>get workoutplan</title>
		<link rel="stylesheet" href="../css/bootstrap.min.css">         
		<script src="../js/bootstrap.min.js"></script>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	</head>
	<body>
				<form method="POST" role="form">
					<div class="form-group">
						<label for="username">Type your userName</label>
						<input type="text" class="form-control" name="username"/>
						<label for="date">Type your date</label>
						<input type="text" class="form-control" name="date"/>
					</div>
					<input type="submit" name="button" value="Submit"/>
				</form>
	</body>
</html>
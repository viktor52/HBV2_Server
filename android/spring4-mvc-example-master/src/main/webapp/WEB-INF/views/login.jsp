<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
	<head>
		<title>Login</title>
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
			<form method="POST" role="form">
				<div class="form-group">
					<label for="person_id">Type Your Username</label>
					<input type="text" class="form-control" name="person_id"/>
				</div>
				<div class="form-group">
					<label for="pw">Type Your Password</label>
					<input type="password" class="form-control" name="pw"/>
				</div>
				<div>
					<p>${error}</p>
				</div>
				<input class="btn btn-primary" type="submit" name="login" value="Login"/>
			</form>
		</div>
	</body>
</html>
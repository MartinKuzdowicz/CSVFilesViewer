<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/main.css">
<title>csv viewer</title>

<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>

</head>
<body>

	<div class="container-fluid">
		<p class="pull-right">${localeTime}</p>
		<header class="text-center">
			<h1>CSV VIEWER</h1>
		</header>
		<form class="form-horizontal col-md-8 col-md-offset-2"
			action="/CSVViewer/view-csv" method="POST"
			enctype="multipart/form-data">
			<div class="form-group">
				<input class="form-control" type="file" name="csvFile" />
			</div>
			<div class="form-group">
				<div class="checkbox">
					<label><input type="checkbox" name="withHeader" value="">with
						header</label>
				</div>
			</div>
			<div class="form-group">
				<input class="btn btn-success btn-group-justified" type="submit"
					value="add your csv file" />
			</div>

		</form>

		<div class="row">
			<div class="col-md-12">
				<hr>
			</div>
		</div>

		<c:choose>
			<c:when test="${withHeaderFlag}">
				<jsp:include page="tables/TableWithHeader.jsp"></jsp:include>
			</c:when>
			<c:otherwise>
				<jsp:include page="tables/TableWithoutHeader.jsp"></jsp:include>
			</c:otherwise>
		</c:choose>



	</div>
</body>
</html>
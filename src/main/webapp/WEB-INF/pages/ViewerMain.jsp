<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/main.css">
<title>csv viewer</title>
</head>
<body>

	<h1>csv viewer</h1>
	<p id="site-title">titttl</p>

	form
	<form action="/CSVViewer/view-csv" method="POST"
		enctype="multipart/form-data">
		<input type="file" name="csvFile" /> <input type="submit" />

	</form>

	<hr>

	<h3>${fileContent}</h3>

</body>
</html>
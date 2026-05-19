<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Registration Confirmation</title>
<style>
body {
	font-family: Arial;
	text-align: center;
	margin-top: 30px;
}

table {
	margin: 20px auto;
	border-collapse: collapse;
	width: 60%;
}

th, td {
	border: 1px solid #ddd;
	padding: 12px;
	text-align: left;
}

th {
	background-color: #4CAF50;
	color: white;
}
</style>
</head>
<body>
	<h1>Registration Successful!</h1>

	<table>
		<tr>
			<th>Field</th>
			<th>Details</th>
		</tr>
		<tr>
			<td>Student Name</td>
			<td>${studentName}</td>
		</tr>
		<tr>
			<td>Email</td>
			<td>${email}</td>
		</tr>
		<tr>
			<td>Age</td>
			<td>${age}</td>
		</tr>
		<tr>
			<td>Course Name</td>
			<td>${courseName}</td>
		</tr>
		<tr>
			<td>Preferred Batch Time</td>
			<td>${batchTime}</td>
		</tr>
	</table>

	<br>
	<br>
	<a
		href="${pageContext.request.contextPath}/StudentAssignment1/index.html">
		Go Back to Home </a>
</body>
</html>
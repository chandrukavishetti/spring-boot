<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exam Result Form</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 40px;
	text-align: center;
}

form {
	max-width: 500px;
	margin: 0 auto;
	text-align: left;
}

input, button {
	padding: 10px;
	margin: 8px 0;
	width: 100%;
}

button {
	background: #4CAF50;
	color: white;
	border: none;
	font-size: 16px;
	cursor: pointer;
}

.error {
	color: red;
	font-weight: bold;
}
</style>
</head>
<body>
	<h1>Online Exam Result Preview</h1>
	<h2>Assignment 3</h2>

	<%
	if (request.getAttribute("errorMsg") != null) {
	%>
	<p class="error"><%=request.getAttribute("errorMsg")%></p>
	<%
	}
	%>

	<form action="../onlineexam" method="post">
		<label>Student Name:</label> <input type="text" name="studentName"
			required><br> <label>Roll Number:</label> <input
			type="number" name="rollNumber" required><br> <label>Subject
			1 Marks (0-100):</label> <input type="number" name="subj1" min="0" max="100"
			required><br> <label>Subject 2 Marks (0-100):</label> <input
			type="number" name="subj2" min="0" max="100" required><br>

		<label>Subject 3 Marks (0-100):</label> <input type="number"
			name="subj3" min="0" max="100" required><br>

		<button type="submit">Submit & View Result</button>
	</form>
</body>
</html>
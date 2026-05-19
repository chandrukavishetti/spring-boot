<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Course Registration Form</title>
<style>
body {
	font-family: Arial;
}

table {
	margin: 20px auto;
}

td {
	padding: 10px;
}

.error {
	color: red;
	font-weight: bold;
	text-align: center;
}
</style>
</head>
<body>
	<h2 align="center">Student Course Registration Form</h2>

	<% 
        String errorMsg = (String) request.getAttribute("errorMsg");
        if (errorMsg != null) { 
    %>
	<p class="error"><%= errorMsg %></p>
	<% } %>

	<form action="${pageContext.request.contextPath}/StudentRegistration"
		method="post">
		<table>
			<tr>
				<td>Student Name:</td>
				<td><input type="text" name="studentName"
					value="${param.studentName}" required></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input type="email" name="email" value="${param.email}"
					required></td>
			</tr>
			<tr>
				<td>Age:</td>
				<td><input type="number" name="age" value="${param.age}"
					min="1" required></td>
			</tr>
			<tr>
				<td>Course Name:</td>
				<td><select name="courseName" required>
						<option value="">-- Select Course --</option>
						<option value="Java Full Stack"
							${param.courseName == 'Java Full Stack' ? 'selected' : ''}>Java
							Full Stack</option>
						<option value="Python Full Stack"
							${param.courseName == 'Python Full Stack' ? 'selected' : ''}>Python
							Full Stack</option>
						<option value="MERN Stack"
							${param.courseName == 'MERN Stack' ? 'selected' : ''}>MERN
							Stack</option>
						<option value="Data Analytics"
							${param.courseName == 'Data Analytics' ? 'selected' : ''}>Data
							Analytics</option>
				</select></td>
			</tr>
			<tr>
				<td>Preferred Batch Time:</td>
				<td><select name="batchTime" required>
						<option value="">-- Select Batch --</option>
						<option value="Morning (9 AM - 12 PM)"
							${param.batchTime == 'Morning (9 AM - 12 PM)' ? 'selected' : ''}>Morning
							(9 AM - 12 PM)</option>
						<option value="Afternoon (2 PM - 5 PM)"
							${param.batchTime == 'Afternoon (2 PM - 5 PM)' ? 'selected' : ''}>Afternoon
							(2 PM - 5 PM)</option>
						<option value="Evening (6 PM - 9 PM)"
							${param.batchTime == 'Evening (6 PM - 9 PM)' ? 'selected' : ''}>Evening
							(6 PM - 9 PM)</option>
				</select></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Submit Registration"></td>
			</tr>
		</table>
	</form>
</body>
</html>
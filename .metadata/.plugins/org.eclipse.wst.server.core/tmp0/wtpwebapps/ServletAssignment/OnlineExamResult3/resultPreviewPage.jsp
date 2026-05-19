<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result Preview</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 40px;
	text-align: center;
}

.result {
	max-width: 600px;
	margin: 0 auto;
	padding: 20px;
	border: 2px solid #4CAF50;
	border-radius: 10px;
}

h1 {
	color: #4CAF50;
}
</style>
</head>
<body>
	<div class="result">
		<h1>Result Preview</h1>
		<h2>Student Details</h2>
		<p>
			<strong>Name:</strong> ${studentName}
		</p>
		<p>
			<strong>Roll Number:</strong> ${rollNumber}
		</p>

		<h2>Marks</h2>
		<p>Subject 1: ${subj1} / 100</p>
		<p>Subject 2: ${subj2} / 100</p>
		<p>Subject 3: ${subj3} / 100</p>

		<h2>Final Result</h2>
		<p>
			<strong>Total Marks:</strong> ${totalMarks} / 300
		</p>
		<p>
			<strong>Percentage:</strong> ${percentage} %
		</p>
		<p>
			<strong>Result:</strong> <b>${resultStatus}</b>
		</p>

		<br> <a href="<%=request.getContextPath()%>/onlineexam">Go
			Back to Form</a>
	</div>
</body>
</html>
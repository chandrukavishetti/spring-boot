<!DOCTYPE html>
<html>
<head>
<title>Leave Form</title>
</head>
<body>

	<h2>Employee Leave Form</h2>

	<form action="<%=request.getContextPath()%>/leave" method="post">
		<!-- 	<form action="../leave" method="post"> -->

		Name: <input type="text" name="empName"><br> <br>
		ID: <input type="text" name="empId"><br> <br>
		Department: <input type="text" name="department"><br> <br>
		Leave Type: <select name="leaveType">
			<option value="Sick">Sick Leave</option>
			<option value="Casual">Casual Leave</option>
			<option value="Emergency">Emergency Leave</option>
			<option value="WFH">Work From Home</option>
		</select><br> <br> Days: <input type="number" name="leaveDays"><br>
		<br> Reason:<br>
		<textarea name="reason"></textarea>
		<br> <br> <input type="submit" value="Apply Leave">

	</form>

</body>
</html>
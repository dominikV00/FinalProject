<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
</head>
<div align="center">
    <h1>You have logged in successfully</h1>
    <h1>User is ADMIN</h1>
</div>
<body>
<center>
    <h1>Vehicle Management</h1>
    <h2>
        <a href="/new">Add New Vehicle</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/list">List All Vehicle</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Vehicle</h2></caption>
        <tr>
            <th>ID</th>
            <th>Brand</th>
            <th>Year</th>
            <th>Mileage</th>
        </tr>
        <c:forEach var="vehicle" items="${listAllVehicles}">
            <tr>
                <td><c:out value="${vehicle.vehicleID}"/></td>
                <td><c:out value="${vehicle.vehicleBrand}"/></td>
                <td><c:out value="${vehicle.vehicleYear}"/></td>
                <td><c:out value="${vehicle.vehicleMileage}"/></td>
                <td>
                    <a href="/edit?id=<c:out value='${vehicle.vehicleID}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/delete?id=<c:out value='${vehicle.vehicleID}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<div align="center">
    <a href ="logout.jsp">LOGOUT</a >
</div>
</body>
</html>
</html
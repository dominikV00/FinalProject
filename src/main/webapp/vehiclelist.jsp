<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Vehicle Fleet Management</title>
</head>
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
        <c:forEach var="vehicle" items="${vehicleList}">
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
</body>
</html>
<%--@elvariable id="user" type="User"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Vehicle Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">
        <div>
            <a href="" class="navbar-brand"> Vehicle
                Management App <br> Welcome ${user.username} !</a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/listadmin"
                   class="nav-link">Vehicle list</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">

    <div class="container">
        <h3 class="text-center">List of Vehicle Admin Menu</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/newadmin" class="btn btn-success">Add
                New Vehicle</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Brand</th>
                <th>Year</th>
                <th>Mileage</th>
            </tr>
            </thead>
            <tbody>

            <%--@elvariable id="listVehicle" type="Vehicle"--%>
            <c:forEach var="vehicle" items="${listVehicle}">

                <tr>
                    <td><c:out value="${vehicle.vehicleID}"/></td>
                    <td><c:out value="${vehicle.vehicleBrand}"/></td>
                    <td><c:out value="${vehicle.vehicleYear}"/></td>
                    <td><c:out value="${vehicle.vehicleMileage}"/></td>
                    <td><a href="edit?id=<c:out value='${vehicle.vehicleID}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                                href="delete?id=<c:out value='${vehicle.vehicleID}' />">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>
</div>
<div style="text-align: center">
<a class="btn btn-success" href ="logout.jsp">Logout</a>
</div>
</body>
</html>

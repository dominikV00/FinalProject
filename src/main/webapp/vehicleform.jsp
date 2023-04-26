<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
            <a href="https://www.javaguides.net" class="navbar-brand"> User Management App </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">Vehicles</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <%--@elvariable id="vehicle" type="Vehicle"--%>
            <c:if test="${vehicle != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${vehicle == null}">
                <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${vehicle != null}">
                                Edit vehicle
                            </c:if>
                            <c:if test="${vehicle == null}">
                                Add New vehicle
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${vehicle != null}">
                        <input type="hidden" name="id" value="<c:out value='${vehicle.vehicleID}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>Vehicle brand</label> <label>
                        <input type="text"
                                                        value="<c:out value='${vehicle.vehicleBrand}' />" class="form-control"
                                                        name="brand" required="required">
                    </label>
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Vehicle year</label> <label>
                        <input type="text"
                                                         value="<c:out value='${vehicle.vehicleYear}' />" class="form-control"
                                                         name="year">
                    </label>
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Vehicle mileage</label> <label>
                        <input type="text"
                                                           value="<c:out value='${vehicle.vehicleMileage}' />" class="form-control"
                                                           name="mileage">
                    </label>
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>
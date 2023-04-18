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
    <c:if test="${vehicle != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${vehicle == null}">
        <form action="insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${vehicle != null}">
                            Edit Vehicle
                        </c:if>
                        <c:if test="${vehicle == null}">
                            Add New Vehicle
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${vehicle != null}">
                    <input type="hidden" name="id" value="<c:out value='${vehicle.vehicleID}' />"/>
                </c:if>
                <tr>
                    <th>Brand:</th>
                    <td>
                        <input type="text" name="brand" size="45"
                               value="<c:out value='${vehicle.vehicleBrand}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Year:</th>
                    <td>
                        <input type="text" name="year" size="45"
                               value="<c:out value='${vehicle.vehicleYear}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Mileage:</th>
                    <td>
                        <input type="text" name="mileage" size="45"
                               value="<c:out value='${vehicle.vehicleMileage}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save"/>
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>

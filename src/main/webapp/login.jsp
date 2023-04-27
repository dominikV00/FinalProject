<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Vehicle Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <script type="text/javascript"
            src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
    <meta charset="utf-8">
    <title>Vehicle Fleet Management Login</title>
</head>
<body>
<div align="center">
    <h1>Vehicle Fleet Management Login</h1>
    <form action="login" method="post">
        <table style="with: 100%">
            <tr>
                <td>Username</td>
                <td><input type="text" name="username"/></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password"/></td>
            </tr>
        </table>
        <br>${message}<br>
        <input type="submit" value="Login"/>
    </form>
</div>
</body>
<script type="text/javascript">

    $(document).ready(function () {
        $("#loginForm").validate({
            rules: {
                username: {
                    required: true,
                    username: true
                },
                password: "required",
            },
            messages: {
                username: {
                    required: "Please enter username",
                    username: "Please enter a valid username "
                },
                password: "Please enter password"
            }
        });
    });
</script>
</html>

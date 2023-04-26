<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
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
<div style="text-align: center">
    <h1>Vehicle Fleet Management Login</h1>
    <form action="login" method="post">
        <%--@declare id="password"--%>
        <%--@declare id="username"--%><label for="username">Username:</label>
        <label>
            <input name="username" size="30"/>
        </label>
        <br><br>
        <label for="password">Password:</label>
        <label>
            <input type="password" name="password" size="30"/>
        </label>
        <br>${message}
        <br><br>
        <button type="submit">Login</button>
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
                email: {
                    required: "Please enter username",
                    email: "Please enter a valid username "
                },

                password: "Please enter password"
            }
        });

    });
</script>
</html>

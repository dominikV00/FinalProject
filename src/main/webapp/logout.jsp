<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%

    HttpSession s = (HttpSession) request.getSession();
    s.invalidate();
    response.sendRedirect("login.jsp");

%>
</body>
</html>

<html>
<head>
    <title>Hello Servlet</title>
</head>
<body>
    <%
        System.out.println("");
    %>
    <h2>Hello how are you <%=request.getAttribute("msg")%></h2>
</body>
</html>

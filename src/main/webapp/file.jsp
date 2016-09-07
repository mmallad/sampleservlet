<html>
<head>
    <title>File Upload</title>
</head>
<body>
    <form method="post" enctype="multipart/form-data" action="<%=String.format("%s/file",request.getContextPath())%>">
        <input type="file" name="file">
        <input type="submit" value="Upload">
    </form>
</body>
</html>

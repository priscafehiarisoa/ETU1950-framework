<%@  page import="test.Personne" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h2>getform2</h2>
<% Personne p=(Personne) request.getAttribute("personne");%>

<%= p.getNom()%>

<%--<%= p.getPhotos().getFile_name()%>--%>
<%= request.getAttribute("birth2")%>
</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "List Emp" %>
</h1>
<br/>
<a href="hello-servlet">emp</a>

<form action="getform" method="post">
    <input type="text" name="nom" placeholder="nom">
    <input type="text" name="prenom" placeholder="prenom">
    <input type="number" name="numbers" id="r" placeholder="Integer">
    <input type="number" name="number" id="t" placeholder="int">
    <input type="date" name="birth" id="">
    <input type="submit" value="valider">
</form>
</body>
</html>
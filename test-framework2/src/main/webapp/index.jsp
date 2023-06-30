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
    <input type="text" name="nom" placeholder="nom" value="prisca">
    <input type="text" name="prenom" placeholder="prenom" value="fehiarisoa">
    <input type="number" name="numbers" id="r" placeholder="Integer" value="1">
    <input type="number" name="number" id="t" placeholder="int" value="2">
    <input type="date" name="birth" id="" value="">
    <input type="submit" value="valider">
</form>


<a href="/method_variables!">test fonction avec variable </a>
</body>
</html>
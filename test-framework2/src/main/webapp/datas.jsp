<%--
  Created by IntelliJ IDEA.
  User: priscafehiarisoadama
  Date: 17/04/2023
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@  page import="test.Personne" %>

<html>
<head>
    <title>datas</title>
</head>
<body>
<h1>test</h1>
<% Personne p=(Personne) request.getAttribute("personne");%>

<%= p.getNom()%>

</body>
</html>

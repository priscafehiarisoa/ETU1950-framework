
<%@page import="objet.Emp" contentType="text/html" pageEncoding="UTF-8"%>

<%
    Emp emp = (Emp) request.getAttribute("emp");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        Nombre d'appel <%=emp.getNombredappel()%>
        <br>
        Prénom/ Profil <%=emp.getPrenom()%> <br>
        Photo name <%=emp.getPhoto().getName()%><br>
        Byte <%=emp.getPhoto().getBytes()%>
    </body>
</html>
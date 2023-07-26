<%@ page import="java.util.List" %>
<%@ page import="test.MySessionListener" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>exam</title>
</head>
<body>
</h1>
<br/>
<%--<a href="hello-servlet">emp</a>--%>

<%--<form action="getform" method="post"--%>
<%--&lt;%&ndash;      enctype="multipart/form-data"&ndash;%&gt;--%>
<%-->--%>
<%--    <input type="text" name="nom" placeholder="nom" value="prisca">--%>
<%--    <input type="text" name="prenom" placeholder="prenom" value="fehiarisoa">--%>
<%--&lt;%&ndash;    <input type="text" name="it[]" placeholder="prenom" value="fehiarisoa">&ndash;%&gt;--%>
<%--&lt;%&ndash;    <input type="text" name="it[]" placeholder="prenom" value="fehiarisoa">&ndash;%&gt;--%>
<%--    <input type="number" name="numbers" id="r" placeholder="Integer" value="1">--%>
<%--    <input type="number" name="number" id="t" placeholder="int" value="2">--%>
<%--    <input type="date" name="birth"  value="">--%>
<%--    <input type="date" name="birth2"  value="">--%>
<%--    <input type="file" name="photos"  value="">--%>
<%--    <input type="submit" value="valider">--%>
<%--</form>--%>


<a href="/newTestFramework/method_variables!2!3">test fonction avec variable </a>
<h1>login</h1>
<form action="log_in" method="post"  enctype="multipart/form-data">
    <input type="text" name="nom" value="dama">
    <input type="text" name="prenom" value="prisca">
    <input type="submit" value="submit">
</form>

<%--<h1>tsotra </h1>--%>
<%--<form action="list_emp" method="post"  enctype="multipart/form-data">--%>
<%--    <input type="text" name="nom" value="dama">--%>
<%--    <input type="text" name="prenom" value="prisca">--%>
<%--    <input type="submit" value="submit">--%>
<%--</form>--%>
</body>

<%--<h1>liste des sessions </h1>--%>
<%--<%--%>

<%--    // Retrieving an attribute:--%>
<%--//    String username = (String) session.getAttribute("isConnected");--%>
<%--%>--%>
<%--&lt;%&ndash;<%=username%>&ndash;%&gt;--%>

<%--<h1>Active Sessions:</h1>--%>
<%--<ul>--%>
<%--    <%--%>
<%--        List<HttpSession> activeSessions = MySessionListener.getActiveSessions();--%>
<%--        for (HttpSession sessions : activeSessions) {--%>
<%--    %>--%>
<%--    <li><%= sessions.getId() %></li>--%>
<%--    <% } %>--%>
<%--</ul>--%>

<%--<h1>Session Attribute Names:</h1>--%>
<%--<ul>--%>
<%--    <%--%>
<%--        HttpSession session2 = request.getSession();--%>
<%--        java.util.Enumeration<String> attributeNames = session2.getAttributeNames();--%>
<%--        while (attributeNames.hasMoreElements()) {--%>
<%--            String attributeName = attributeNames.nextElement();--%>
<%--            Object sess = session2.getAttribute(attributeName);--%>
<%--            out.println(sess);--%>
<%--    %>--%>
<%--    <li><%= attributeName %></li>--%>
<%--    <% } %>--%>
<%--</ul>--%>

</html>
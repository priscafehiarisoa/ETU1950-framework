<%@ page import="test.Categorie" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: priscafehiarisoadama
  Date: 26/07/2023
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>

<%--get all categories from modelView--%>
<%
    List<Categorie> categories= request.getAttribute("categorie")!=null?(List<Categorie>) request.getAttribute("categorie"):new ArrayList<>();

%>

<h1>formulaire d'insertion </h1>

<form action="addProduct" enctype="multipart/form-data" method="post">
<%--    serie --%>
    <div>
        <label>numero de serie </label>
        <input type="text" name="numero_serie">
    </div>
<%--    liste categories--%>
    <div>
        <label>categories </label>

        <select name="idCategorie" >
            <% try{
                for (int i = 0; i < categories.size(); i++) {
            %>
            <option value="<%=categories.get(i).getId()%>"><%=categories.get(i).getNomCategorie()%></option>
            <%}

            }catch (Exception e){e.printStackTrace();}%>
        </select>
    </div>
<%--    frequence proc--%>
    <div>
        <label>processeur</label>

        <input type="number" name="frequenceProcesseur" >
    </div>
<%--    ram--%>
    <div>
        <label>ram </label>

        <input type="number" name="ram" >
    </div>
<%--    file --%>
    <div>
        <label>image du produit  </label>

        <input type="file" name="image">
    </div>
    
    <div>
        <input type="submit" value="ok">
    </div>
</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

</body>
</html>

<%@ page import="test.Produit" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: priscafehiarisoadama
  Date: 26/07/2023
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>

<%
List<Produit> produitList=request.getAttribute("produits")!=null?(List<Produit>) request.getAttribute("produits"):new ArrayList<>();
%>

<div class="container">
    <h1>liste produit</h1>

    <table class="table">
        <tr>
            <th >numero de serie </th>
            <th>categorie </th>
            <th>frequence </th>
            <th>ram </th>
            <th>image </th>
            <th>-</th>
        </tr>
        <%
            try{
                for (int i = 0; i < produitList.size(); i++) {
        %>
        <tr>
            <td><%=produitList.get(i).getNumero_serie()%></td>
            <td><%=produitList.get(i).getIdCategorie()%></td>
            <td><%=produitList.get(i).getFrequenceProcesseur()%></td>
            <td><%=produitList.get(i).getRam()%></td>
            <td><%=produitList.get(i).getImagePath()%></td>
            <%
            String path=produitList.get(i).getImagePath();
            %>
            <td>
                <form action="download" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="filepath" value="<%=path%>">
                    <input type="submit" value="telecharger" class="btn btn-outline-primary">
                </form>

            </td>
        </tr>
        <%}
        }catch (Exception e){e.printStackTrace();}%>
    </table>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

</body>
</html>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Bonjour</h1>
        <form action="add-emp" method="post" enctype="multipart/form-data">
            Entrer votre nom: <input type="text" name="nom" value="u">
            <br>
            Prenom <input type="text" name="prenom" value="u">
            <br><!-- comment -->
            Numero <input type="number" name="numero" value=111>
            <input type="file" name="photo">
            <input type="submit" value="Valider">
        </form>
        <a href="etudiant?">Etudiant</a>
        <a href="get-emp">Afficher emp</a>
        <a href="login.jsp">Login</a>
        <a href="get-connected">get-connected</a>
        <a href="etudiant">get-connected</a>
        <a href="testApi">Test api</a>
    </body>
</html>

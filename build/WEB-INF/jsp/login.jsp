<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form action="/auth" method="post">
        <label for="nom">Nom :</label><br>
        <input type="text" name="nom" id="nom"><br><br>
        <label for="password">password</label><br>
        <input type="password" name="pwd" id="pwd"><br>
        <input type="submit" value="se connecter"><br><br><br>
    </form>

    <p th:if="${error}" th:text="${error}" style="color: red;"></p>
</body>
</html>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <title>Document</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/auth" method="post">
        <label for="nom">Nom :</label><br>
        <input type="text" name="nom" id="nom"><br><br>
        <label for="password">password</label><br>
        <input type="password" name="pwd" id="pwd"><br>
        <input type="submit" value="se connecter"><br><br><br>
    </form>

    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
</body>
</html>
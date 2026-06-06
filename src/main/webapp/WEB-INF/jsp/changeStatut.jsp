<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>
<form action="${pageContext.request.contextPath}/demandes/changeStatut" method="post">
    <label for="demande">Demande:</label><br>
    <select name="demande" id="demande" onclick="StatutActuel()">
        <c:forEach var="demande" items="${demandes}">
            <option value="${demande.id}">Demande numero ${demande.id}</option>
        </c:forEach>
    </select><br><br>
    <label for="observation">Observation:</label><br>
    <textarea name="observation" id="observation"></textarea><br><br>
    <label for="statut">Statut:</label><br>
    <select name="statut" id="statut">
        <c:forEach var="statut" items="${statuts}">
            <option value="${statut.id}">${statut.nomStatut}</option>
        </c:forEach>
    </select><br><br>
    <label for="date">Date:</label><br>
    <input type="datetime-local" name="date" id="date"><br><br>
    <input type="submit" value="Modifier le statut">
</form>
</body>
<script>
    function StatutActuel(){
        const id = document.getElementById("demande").value;

           fetch('${pageContext.request.contextPath}/demandes/statut/' + id)

           .then(response => response.json())
           .then(data =>{
            document.getElementById("statut").value=data.statut
           });
    }
</script>
</html>
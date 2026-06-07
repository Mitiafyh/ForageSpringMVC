<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <title>Modifier DemandeStatut</title>
</head>
<body>
    <h2>Modifier DemandeStatut</h2>

    <form action="${pageContext.request.contextPath}/demandeStatuts/update" method="post">
        <input type="hidden" name="id" value="${demandeStatut.id}">

        <p>Demande: ${demandeStatut.demande.id}</p>

        <label for="statut">Statut :</label><br>
        <select name="statut" id="statut">
            <c:forEach var="statut" items="${statuts}">
                <option value="${statut.id}" <c:if test="${demandeStatut.statut.id == statut.id}">selected</c:if>>
                    ${statut.nomStatut}
                </option>
            </c:forEach>
        </select><br><br>

        <label for="date">Date :</label><br>
        <input type="datetime-local" name="date" id="date" value="${dateValue}"><br><br>

        <label for="observation">Observation :</label><br>
        <textarea name="observation" id="observation">${demandeStatut.observation}</textarea><br><br>

        <input type="submit" value="Mettre a jour">
    </form>

    <p>
        <a href="${pageContext.request.contextPath}/demandeStatuts">Retour a la liste</a>
    </p>
</body>
</html>
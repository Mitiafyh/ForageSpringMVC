<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <title>Liste des DemandeStatut</title>
</head>
<body>
    <h2>Liste des DemandeStatut</h2>

    <p>
        <a href="${pageContext.request.contextPath}/demandes">Retour aux demandes</a>
    </p>

    <table border="1" cellpadding="6" cellspacing="0">
        <thead>
            <tr>
                <th>ID</th>
                <th>Demande</th>
                <th>Statut</th>
                <th>Date</th>
                <th>DT</th>
                <th>Observation</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="demandeStatut" items="${demandeStatuts}">
                <tr>
                    <td>${demandeStatut.id}</td>
                    <td>${demandeStatut.demande.id}</td>
                    <td>${demandeStatut.statut.nomStatut}</td>
                    <td>${demandeStatut.date}</td>
                    <td>${demandeStatut.dt}</td>
                    <td>${demandeStatut.observation}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/demandeStatuts/edit/${demandeStatut.id}">Modifier</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
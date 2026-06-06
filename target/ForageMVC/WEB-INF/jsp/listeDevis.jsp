<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <title>Liste des Devis</title>
</head>
<body>
    <h2>Liste des Devis</h2>
    <p>
        <a href="${pageContext.request.contextPath}/devis/insert">Creer un nouveau devis</a> | 
        <a href="${pageContext.request.contextPath}/demandes">Voir les demandes</a>
    </p>

    <c:if test="${empty devisList}">
        <p>Aucun devis disponible.</p>
    </c:if>

    <c:if test="${not empty devisList}">
        <table border="1" cellpadding="10" cellspacing="0" style="width: 100%;">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Demande</th>
                    <th>Client</th>
                    <th>Lieu</th>
                    <th>Prix Total</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="devis" items="${devisList}">
                    <tr>
                        <td>${devis.id}</td>
                        <td>Numero ${devis.demande.id}</td>
                        <td>${devis.demande.client.nom}</td>
                        <td>${devis.demande.lieu}</td>
                        <td>
                            <c:set var="total" value="0.0"/>
                            <c:forEach var="sousDevis" items="${devis.sousDevis}">
                                <c:set var="total" value="${total + (sousDevis.prixUnitaire * sousDevis.quantite)}"/>
                            </c:forEach>
                            ${String.format("%.2f", total)} Ar
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/devis/edit/${devis.id}">Modifier</a> 
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>
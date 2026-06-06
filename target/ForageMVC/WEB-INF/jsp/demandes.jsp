<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <title>Demandes</title>
    </head>

    <body>
       

        <a href="${pageContext.request.contextPath}/devis/insert">Inserer devis</a>

        <h2>Liste des demandes</h2>
        <p>
            <a href="${pageContext.request.contextPath}/demandes/new">Nouvelle demande</a>
        </p>
        <p>
            <a href="${pageContext.request.contextPath}/demandes/changeStatutForm">Modifier le statut d'une demande</a>
        </p>


        <table border="1" cellpadding="6" cellspacing="0">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Client</th>
                    <th>Commune</th>
                    <th>Lieu</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>

                <c:forEach var="demande" items="${demandes}">
                    <tr>
                        <td>${demande.id}</td>
                        <td>${demande.client.nom}</td>
                        <td>
                            <c:choose>
                                <c:when test="${not empty communeNames[demande.idCommune]}">
                                    ${communeNames[demande.idCommune]}
                                </c:when>
                                <c:otherwise>
                                    ${demande.idCommune}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${demande.lieu}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/demandes/edit/${demande.id}">Modifier</a>
                            |
                            <a href="${pageContext.request.contextPath}/demandes/delete/${demande.id}">Supprimer</a>
                            |
                            <a target="_blank" href="http://localhost/parametres/alerte.php?idDemande=${demande.id}">voir alertes</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>

    </html>0
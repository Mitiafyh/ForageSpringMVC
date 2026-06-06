<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
        <title>Modifier Devis</title>
    </head>

    <body>
        <h2>Modifier Devis Numero ${devis.id}</h2>

        <form action="${pageContext.request.contextPath}/devis/update" method="post">
            <input type="hidden" name="id" value="${devis.id}">

            <label for="demande">Demande:</label><br>
            <select name="demande" id="demande" required>
                <c:forEach var="d" items="${demandes}">
                    <option value="${d.id}" <c:if test="${d.id == devis.demande.id}">selected</c:if>>
                        Demande Numero ${d.id} - ${d.client.nom}
                    </option>
                </c:forEach>
            </select><br><br>

            <h3>Details</h3>
            <p><strong>Client:</strong> ${devis.demande.client.nom}</p>
            <p><strong>Lieu:</strong> ${devis.demande.lieu}</p>

            <h3>Sous-Devis</h3>
            <button type="button" onclick="addSousDevis()">Ajouter un sous-devis</button>
            <div class="sousDevisContainer"></div>

            <c:if test="${not empty sousDevisList}">
                <h4>Sous-devis actuels</h4>
                <c:forEach var="sd" items="${sousDevisList}">
                    <div style="border: 1px solid #ddd; padding: 10px; margin: 10px 0;">
                        <label>Description :</label><br>
                        <input type="text" value="${sd.description}" name="description" required><br><br>

                        <label>Prix unitaire:</label><br>
                        <input type="number" value="${sd.prixUnitaire}" step="0.01" name="prix" onchange="updatePrixTotal()" required><br><br>

                        <label>Quantite:</label><br>
                        <input type="number" value="${sd.quantite}" name="quantite" onchange="updatePrixTotal()" required><br><br>
                        <button type="button" onclick="this.parentElement.remove(); updatePrixTotal();">Supprimer</button>
                        <br><br>
                       
                    </div>
                </c:forEach>
            </c:if>

            <h3>Prix Total: <span id="affichageTotal">0.00 Ariary</span></h3>

            <input type="submit" value="Enregistrer les modifications"><br><br>
            <a href="${pageContext.request.contextPath}/devis/liste">Retour a la liste</a>
        </form>

        <script>
            let index = 0;

            function addSousDevis() {
                const container = document.querySelector('.sousDevisContainer');
                const bloc = document.createElement('div');
                bloc.style.border = '1px dashed #ddd';
                bloc.style.padding = '10px';
                bloc.style.margin = '10px 0';
                bloc.innerHTML = `
                <label>Description :</label><br>
                <input type="text" name="description" required><br><br>

                <label>Prix unitaire:</label><br>
                <input type="number" step="0.01" name="prix" onchange="updatePrixTotal()" required><br><br>

                <label>Quantite:</label><br>
                <input type="number" name="quantite" onchange="updatePrixTotal()" required><br><br>

                <button type="button" onclick="this.parentElement.remove(); updatePrixTotal();">Supprimer</button>
                <br><br>
            `;
                container.appendChild(bloc);
                index++;
            }

            function updatePrixTotal() {
                let total = 0;
                const prixInputs = document.querySelectorAll('input[name="prix"]');
                const qteInputs = document.querySelectorAll('input[name="quantite"]');

                for (let i = 0; i < prixInputs.length; i++) {
                    const prix = parseFloat(prixInputs[i].value) || 0;
                    const quantite = parseInt(qteInputs[i].value) || 0;
                    total += (prix * quantite);
                }

                document.getElementById('affichageTotal').innerText = total.toFixed(2) + " Ariary";
            }

            document.addEventListener('DOMContentLoaded', () => {
                updatePrixTotal();
            });
        </script>
    </body>

    </html>
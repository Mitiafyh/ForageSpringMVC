<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
        <title>Creer un Devis</title>
    </head>

    <body>

        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>


        <form action="${pageContext.request.contextPath}/devis/insert" method="post">
            <label for="demande">Demande:</label><br>


            <select name="demande" id="demande" onblur="chargerDetails()">
                <c:forEach var="demande" items="${demandes}">
                    <option value="${demande.id}">Demande numero ${demande.id}</option>
                </c:forEach>
            </select><br><br>

            <label for="TypeDevis">Type de devis:</label><br>
            <select name="TypeDevis" id="TypeDevis" required>
                <option value="">Choisir un type</option>
            </select><br><br>


            <div id="detailsDemande"></div>

            <label for="date">Date :</label><br>
            <input type="datetime-local" name="date" id="date" required><br><br>


            <button type="button" onclick="addSousDevis()">Ajouter un sous-devis</button>
            <div class="sousDevis" style="display: none;"> </div>

            <h3>Prix Total: <span id="affichageTotal">0.00 Ariary</span></h3>

            <input type="submit" value="Enregistrer le devis">
        </form>
        <br>
        <a href="${pageContext.request.contextPath}/devis/liste">Voir la liste des devis</a> |
        <a href="${pageContext.request.contextPath}/demandes">Retour aux demandes</a>


    </body>
    <script>



        function chargerDetails() {

            const id = document.getElementById("demande").value;

            if (!id) {
                document.getElementById("detailsDemande").innerHTML = "";
                return;
            }



            fetch('${pageContext.request.contextPath}/demandes/detail/' + id)

                .then(response => response.json())

                .then(data => {
                    const optionsHTML = data.typesDevis.map(type => `<option value="\${type.id}">\${type.nomType}</option>`).join('');
                    document.getElementById("TypeDevis").innerHTML = '<option value="">Choisir un type</option>' + optionsHTML;

                    document.getElementById("detailsDemande").innerHTML = `
            <h3>Details demande</h3>
            
            <p>
                <strong>Client :</strong>
                \${data.client}
            </p>

            <p>
                <strong>Lieu :</strong>
                \${data.lieu}
            </p>

            <p>
                <strong>Commune :</strong>
                \${data.commune}
            </p>

            <p>
                <strong>Statut actuel :</strong>
                \${data.statut}
            </p>

            <p>
                <strong>Date du statut :</strong>
                \${data.dateStatut}
            </p>

        `;
                });


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

        let index = 0;

        function addSousDevis() {

            const container = document.querySelector('.sousDevis');

            const bloc = document.createElement('div');

            bloc.innerHTML = `
        <hr>

        <label>Description :</label><br>
        <input type="text"
               name="description"
               required><br><br>

        <label>Prix unitaire:</label><br>
        <input type="number"
               step="0.01"
               name="prix"
               onchange="updatePrixTotal()"
               required><br><br>

        <label>Quantite:</label><br>
        <input type="number"
               name="quantite"
               onchange="updatePrixTotal()"
               required><br><br>

        <button type="button"
                onclick="this.parentElement.remove(); updatePrixTotal();">
                Supprimer
        </button>
        <br><br> `;

            container.appendChild(bloc);

            container.style.display = 'block';

            index++;
        }


    </script>

    </html>
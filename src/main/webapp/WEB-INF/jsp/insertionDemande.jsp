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

        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/insertdemande" method="post">
            <label for="idClient">idClient :</label><br>
            <select name="idClient" id="idClient">
                <c:forEach var="client" items="${clients}">
                    <option value="${client.id}">${client.nom}</option>
                </c:forEach>
            </select>
            <label>Region :</label>
            <select id="regionSelect" name="regionId">
                <option value="">-- Choisir une region --</option>
                <c:forEach var="region" items="${regions}">
                    <option value="${region.id}">${region.nomRegion}</option>
                </c:forEach>
            </select>
            <label>District :</label>
            <select id="districtSelect" name="districtId">
                <option value="">-- Choisir un district --</option>
            </select>

            <label>Commune :</label>
            <select id="communeSelect" name="communeId">
                <option value="">-- Choisir une commune --</option>
            </select>
            <label for="lieu">lieu :</label><br>
            <input type="text" name="lieu" id="lieu"><br><br>
            <label for="date">Date :</label><br>
            <input type="datetime-local" name="date" id="date" required><br><br>
            <input type="submit" value="Ajouter une demande"><br><br><br>
        </form>
    </body>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const regionSelect = document.getElementById("regionSelect");
            const districtSelect = document.getElementById("districtSelect");
            const communeSelect = document.getElementById("communeSelect");

            const baseUrl = "${pageContext.request.contextPath}";

            regionSelect.addEventListener("change", function () {
                const regionId = this.value;
                districtSelect.innerHTML = '<option value="">-- Choisir un district --</option>';
                communeSelect.innerHTML = '<option value="">-- Choisir une commune --</option>';

                if (!regionId) {
                    return;
                }

                districtSelect.innerHTML = '<option value="">Chargement...</option>';

                fetch(baseUrl + '/districts/' + regionId)
                    .then(response => {
                        if (!response.ok) {
                            throw new Error("Erreur chargement districts");
                        }
                        return response.json();
                    })
                    .then(data => {
                        districtSelect.innerHTML = '<option value="">-- Choisir un district --</option>';
                        data.forEach(d => {
                            districtSelect.innerHTML += '<option value="' + d.id + '">' + d.nomDistrict + '</option>';
                        });
                    })
                    .catch(() => {
                        districtSelect.innerHTML = '<option value="">-- Choisir un district --</option>';
                    });
            });

            districtSelect.addEventListener("change", function () {
                const districtId = this.value;
                communeSelect.innerHTML = '<option value="">-- Choisir une commune --</option>';

                if (!districtId) {
                    return;
                }

                communeSelect.innerHTML = '<option value="">Chargement...</option>';

                fetch(baseUrl + '/communes/' + districtId)
                    .then(response => {
                        if (!response.ok) {
                            throw new Error("Erreur chargement communes");
                        }
                        return response.json();
                    })
                    .then(data => {
                        communeSelect.innerHTML = '<option value="">-- Choisir une commune --</option>';
                        data.forEach(c => {
                            communeSelect.innerHTML += '<option value="' + c.id + '">' + c.nomCommune + '</option>';
                        });
                    })
                    .catch(() => {
                        communeSelect.innerHTML = '<option value="">-- Choisir une commune --</option>';
                    });
            });
        });
    </script>

    </html>
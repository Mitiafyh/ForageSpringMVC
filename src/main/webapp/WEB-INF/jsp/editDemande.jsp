<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <title>Modifier Demande</title>
</head>
<body>
    <h2>Modifier demande</h2>

    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/demandes/update" method="post">
        <input type="hidden" name="id" value="${demande.id}">

        <label for="idClient">Client :</label><br>
        <select name="idClient" id="idClient">
            <c:forEach var="client" items="${clients}">
                <option value="${client.id}" <c:if test="${client.id == demande.client.id}">selected</c:if>>
                    ${client.nom}
                </option>
            </c:forEach>
        </select>
        <br><br>

        <label>Region :</label>
        <select id="regionSelect" name="regionId">
            <option value="">-- Choisir une region --</option>
            <c:forEach var="region" items="${regions}">
                <option value="${region.id}" <c:if test="${selectedRegion != null && selectedRegion.id == region.id}">selected</c:if>>${region.nomRegion}</option>
            </c:forEach>
        </select>
        <br>

        <label>District :</label>
        <select id="districtSelect" name="districtId">
            <option value="">-- Choisir un district --</option>
            <c:if test="${districts != null}">
                <c:forEach var="dist" items="${districts}">
                    <option value="${dist.id}" <c:if test="${selectedDistrict != null && selectedDistrict.id == dist.id}">selected</c:if>>${dist.nomDistrict}</option>
                </c:forEach>
            </c:if>
        </select>
        <br>

        <label>Commune :</label>
        <select id="communeSelect" name="communeId">
            <option value="">-- Choisir une commune --</option>
            <c:if test="${communes != null}">
                <c:forEach var="com" items="${communes}">
                    <option value="${com.id}" <c:if test="${selectedCommune != null && selectedCommune.id == com.id}">selected</c:if>>${com.nomCommune}</option>
                </c:forEach>
            </c:if>
        </select>
        <br><br>

        <label for="lieu">Lieu :</label><br>
        <input type="text" name="lieu" id="lieu" value="${demande.lieu}"><br><br>

        <label for="date">Date :</label><br>
        <input type="datetime-local" name="date" id="date" value="${dateValue}" required><br><br>

        <input type="submit" value="Mettre a jour">
    </form>

    <p>
        <a href="${pageContext.request.contextPath}/demandes">Retour a la liste</a>
    </p>

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

                if (!regionId) return;

                districtSelect.innerHTML = '<option value="">Chargement...</option>';

                fetch(baseUrl + '/districts/' + regionId)
                    .then(response => response.json())
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

                if (!districtId) return;

                communeSelect.innerHTML = '<option value="">Chargement...</option>';

                fetch(baseUrl + '/communes/' + districtId)
                    .then(response => response.json())
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
</body>
</html>

<?php

$idDemande = $_GET['idDemande'];

// echo "Demande : ".$idDemande;
$json = file_get_contents("http://localhost:8080/ForageMVC/api/alertes/".$idDemande);

$alertes = json_decode($json,true);
foreach($alertes as $a){
    echo $a['idStatut'];
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>

</body>
</html>
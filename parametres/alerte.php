<?php

$idDemande = isset($_GET['idDemande']) ? (int) $_GET['idDemande'] : 0;

$alertesJson = @file_get_contents("http://localhost:8080/ForageMVC/api/alertes/" . $idDemande);
$alertes = json_decode($alertesJson, true);
if (!is_array($alertes)) {
    $alertes = [];
}

$resumeJson = @file_get_contents("http://localhost:8080/ForageMVC/api/alertes/" . $idDemande . "/resume");
$resume = json_decode($resumeJson, true);
if (!is_array($resume)) {
    $resume = [];
}

$isTermine = (($resume['isTermine'] ?? 'false') === 'true');
$totalDt = $resume['totalDt'] ?? '0.0';

function alertColor($alerte) {
    if ($alerte === 'Rouge') {
        return '#FF4D4D';
    }

    if ($alerte === 'Jaune') {
        return '#FFD84D';
    }

    return '#74D680';
}
?>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alertes</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h1>Alertes</h1>

    <?php if ($isTermine): ?>
        <p><strong>Forage terminé (FT).</strong> Total DT de cette demande : <?php echo htmlspecialchars($totalDt); ?></p>
    <?php endif; ?>

    <?php if (empty($alertes)): ?>
        <p>Aucune alerte pour cette demande.</p>
    <?php else: ?>
        <p>Alertes pour la demande <?php echo htmlspecialchars((string) $idDemande); ?> :</p>
        <table border="1">
            <tr>
                <th>Statut 1</th>
                <th>Statut 2</th>
                <th>DT</th>
                <th>Alerte</th>
            </tr>
            <?php foreach ($alertes as $a): ?>
                <tr>
                    <td><?php echo htmlspecialchars($a['Statut1'] ?? ''); ?></td>
                    <td><?php echo htmlspecialchars($a['Statut2'] ?? ''); ?></td>
                    <td><?php echo htmlspecialchars($a['DT'] ?? ''); ?></td>
                    <td style="background-color: <?php echo alertColor($a['Alerte'] ?? ''); ?>;">
                        <?php echo htmlspecialchars($a['Alerte'] ?? ''); ?>
                    </td>
                </tr>
            <?php endforeach; ?>
        </table>
    <?php endif; ?>
</body>
</html>
<?php

// Vérifie si la requête est de type POST
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Récupére le montant envoyé via la requête POST
    $testAmount = $_POST["amount"];

    function giveChange($amount) {
        
        $solution = array(); // Initialise un tableau pour stocker la solution
        $initialAmount = $amount;
        $numberOfBills = 0;

        // Boucle tant que le montant est supérieur à 13 ou égal à 12 ou 10, plus d'explication dans le code java
        while ($amount > 13 || $amount == 12 || $amount == 10) {
            $numberOfBills++;
            $solution[10] = $numberOfBills;
            $amount -= 10;
        }

        // Vérifie si le montant restant est impair
        if ($amount % 2 != 0) {
            $solution[5] = 1;
            $amount -= 5;
        }

        // Gérer les cas exception pour 6 et 8
        if ($amount == 6) {
            $solution[2] = 3;
            $amount = 0;
        } elseif ($amount == 8) {
            $solution[4] = 2;
            $amount = 0;
        } else {
            $numberOfBills = 0;
            // Boucle tant que le montant restant est supérieur à 0 pour la monnaie 2
            while ($amount > 0) {
                $numberOfBills++;
                $solution[2] = $numberOfBills;
                $amount -= 2;
            }
        }

        // Construit la chaîne de résultat
        $result = "<div class='result'>";
        $result .= "<h2>Montant à rendre : $" . $initialAmount . "</h2>";

        // Vérifie si le montant restant est devenu 0
        if ($amount == 0) {
            $result .= "<div class='solution'>Solution optimale :</div>";
            $result .= "<ul>";
            foreach ($solution as $key => $value) {
                $result .= "<li>" . $value . " * $" . $key . "</li>";
            }
            $result .= "</ul>";
        } else {
            // Affiche un message d'erreur si le montant ne peut pas être rendu pour 1 & 3
            $result .= "<div class='error'>Impossible de rendre la monnaie.</div>";
        }

        // Termine la construction de la chaîne de résultat
        $result .= "</div>";
        return $result;
    }

    // Appel de la fonction et récupération du résultat
    $output = giveChange($testAmount);
    // Afficher le résultat
    echo $output;
}
?>

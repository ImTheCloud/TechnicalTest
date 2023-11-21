<?php

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $testAmount = $_POST["amount"];

    function giveChange($amount) {
        $solution = array();
        $initialAmount = $amount;
        $numberOfBills = 0;

        while ($amount > 13 || $amount == 12 || $amount == 10) {
            $numberOfBills++;
            $solution[10] = $numberOfBills;
            $amount -= 10;
        }

        if ($amount % 2 != 0) {
            $solution[5] = 1;
            $amount -= 5;
        }

        if ($amount == 6) {
            $solution[2] = 3;
            $amount = 0;
        } elseif ($amount == 8) {
            $solution[4] = 2;
            $amount = 0;
        } else {
            $numberOfBills =0;
            while ($amount > 0) {
                $numberOfBills++;
                $solution[2] = $numberOfBills;
                $amount -= 2;
            }
        }

        $result = "<div class='result'>";
        $result .= "<h2>Amount to return: $" . $initialAmount . "</h2>";

        if ($amount == 0) {
            $result .= "<div class='solution'>Optimal solution:</div>";
            $result .= "<ul>";
            foreach ($solution as $key => $value) {
                $result .= "<li>" . $value . " * $" . $key . "</li>";
            }
            $result .= "</ul>";
        } else {
            $result .= "<div class='error'>Unable to give change.</div>";
        }

        $result .= "</div>";
        return $result;
    }

    // Appel de la fonction et affichage du résultat
    $output = giveChange($testAmount);
    echo $output;
}
?>

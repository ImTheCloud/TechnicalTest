import java.util.HashMap;
import java.util.Map;

public class ChangeReturn {

    public static void main(String[] args) { // point d'entrée du programme
        giveChange(1L); // test
        giveChange(6L);
        giveChange(10L);
        //giveChange(9007199254740991L);
    }

    public static Map<Long, Long> giveChange(long amount) {
        Map<Long, Long> solution = new HashMap<>(); // hashmap pour avoir le nombre de pièces pour chaque pièce possible
        long initialAmount = amount; // juste pour l'affichage
        long numberOfBills = 0; // montant nécessaire pour chaque pièce pour avoir une solution optimale

        while (amount > 13 || amount == 12 || amount == 10) {
            // on commence par une boucle while à 13 car c'est le plus gros problème dans cet exercice
            // car si on a 13, on va vouloir diviser par 10 et il restera 3, et ce sera impossible de rendre la monnaie
            // similaire pour 11, donc on va enlever 10 du montant jusqu'à avoir quelque chose en dessous ou égal à 13
            // pour le 12, c'est une exception pour avoir le montant le plus optimal si c'est pile 12 ou 10
            numberOfBills++; // ajoute le nombre de fois que l'on enlève 10
            solution.put(10L, numberOfBills); // ajoute dans la hashmap
            amount -= 10; // soustraction pour la boucle
        }

        if (amount % 2 != 0) { // pour tous les nombres impairs, on enlève 5 car il y a d'office 1 billet de 5 dans un nombre impair
            // mais il n'y aura jamais 2 billets de 5, pour cela il n'est pas dans le tableau de billets, ça ne sert à rien
            solution.put(5L, 1L);
            amount -= 5; // on enlève forcément 5
        }

        if (amount == 6) { // si on arrive à un 6, on ne peut pas enlever 5 car il restera 1 et ce sera impossible
            // alors 6 est une exception et par défaut, il aura 3 pièces de 2
            solution.put(2L, 3L);
            amount = 0;
        } else if (amount == 8) { // similaire pour le 6, la 2e exception c'est pour le 8
            solution.put(4L, 2L);
            amount = 0;
        } else {
            while (amount > 0) { // ici on enlève que par 2 ce qu'il reste
                numberOfBills++;
                solution.put(numberOfBills,2L);
                amount -= 2;
            }
        }

        // affichage
        System.out.println("Amount to return: " + initialAmount);
        if (amount == 0) {
            System.out.println("Optimal solution: ");
            for (Map.Entry<Long, Long> entry : solution.entrySet()) { // affichage de chaque montant 2, 5, 10 en fonction de ce qui a été ajouté
                System.out.println(entry.getValue() + " * $" + entry.getKey());
            }
        } else {
            System.out.println("Unable to give change.");
        }
        return solution;
    }
}

package com.isograd.exercise;

/**
 * Enoncé
 *
 * Lors de votre promenade au salon de l’agriculture, vous tombez sur l’évènement phare : le concours de la race limousine. Vous ne connaissez pas exactement quels sont les critères de sélection utilisés par le jury mais vous décidez d’inventer les vôtres.
 *
 * Pour prétendre au titre, les taureaux doivent absolument :
 * - Être âgés entre 2 et 5 ans
 * - Peser entre 1250Kg et 1500Kg
 *
 * Pour les départager, vous leur attribuez :
 * - Une note sur 20 basée sur leur démarche
 * - Une note sur 20 basée sur la courbure de leur dos
 *
 * Celui qui répond aux critères et obtient la meilleure moyenne de notes gagne votre concours.
 *
 *
 * Format des données
 *
 * Entrée
 *
 * Ligne 1 : un entier N compris entre 2 et 1000 correspondant au nombre de taureaux.
 * Ligne 2 à N+1 : une chaîne S comprenant 7 lettres majuscules et 4 entiers A, B, C et D séparés par un espace où
 * S correspond à l’identifiant d’un prétendant,
 * A à son âge en année,
 * B à son poids en kilogramme,
 * C à la note sur 20 de sa démarche et
 * D à la note sur 20 de sa courbure.
 *
 * Sortie
 *
 * Une chaîne de caractères correspondant à l’identifiant du gagnant du concours. S'il y a plusieurs gagnants ex-aequos, affichez les tous séparés par un espace.
 */

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CowConcourse {

    public static void main( String[] argv ) throws Exception {
        System.out.println(solve(System.in));
    }

    public static String solve(InputStream in) {
        Scanner sc = new Scanner(in);
        String  line;

        int currentScore = Integer.MIN_VALUE;
        List<String> result = Collections.emptyList();

        line = sc.nextLine();
        System.err.println(line);

        while(sc.hasNextLine()) {
            line = sc.nextLine();
            System.err.println(line);
            /* Lisez les données et effectuez votre traitement */
            String[] tokens = line.split(" ");

            String identifier = tokens[0];
            int age = Integer.valueOf(tokens[1]);
            int weight = Integer.valueOf(tokens[2]);
            int walk = Integer.valueOf(tokens[3]);
            int curve = Integer.valueOf(tokens[4]);

            if (age < 2 || age > 5 ){
                continue;
            }
            if (weight < 1250 || weight > 1500){
                continue;
            }

            int score = walk + curve;
            if (score > currentScore) {
                result = new ArrayList(Collections.singletonList(identifier));
                currentScore = score;
            }
            else if (score == currentScore) {
                result.add(identifier);
            }
            System.err.println(identifier+" "+currentScore+" "+result);
        }

        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
        return result.stream().collect(Collectors.joining(" "));
    }
}

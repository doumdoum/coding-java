package com.isograd.exercise;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

/**
 * Énoncé
 *
 * Pour les 80 ans de votre grand-mère, vous êtes chargé de préparer le cadeau des invités :
 * un cadre carré en bois pour y coller une photo de la star de la soirée.
 *
 * Votre budget étant assez serré, vous décidez de fabriquer les cadres dans votre atelier.
 * Et tant qu’à faire, si vous vous en chargez vous -même, autant utiliser de la récup.
 * Vous avez un carton plein de fines planches de bois de même épaisseur et de même largeur mais de longueurs différentes.
 * Votre grand-mère ne vous en voudra pas si les cadres ne sont pas tous de la même taille, par-contre elle insiste pour qu’ils soient carrés.
 *
 * Vous décidez alors de prendre au hasard 4 planches dans le carton et de les découper, si besoin, de manière à avoir un cadre carré le plus grand possible.
 *
 * À noter que lorsque vous découpez une planche, vous gardez la partie qui vous intéresse et vous jetez la seconde partie dans le carton,
 * vous ne pouvez plus l’utiliser pour ce cadre.
 *
 * L’objectif est de déterminer combien de centimètres de bois vous aurez jeté dans le carton au total si vous construisez le cadre carré le plus grand possible.
 *
 *
 * Format des données
 *
 * Entrée
 *
 * Lignes 1 à 4 : un entier compris entre 1 et 1 000 correspondant à la longueur d'une planche en bois en centimètres.
 *
 * Sortie
 *
 * Un entier correspondant au nombre de centimètres de bois que vous jetez dans le carton si vous réalisez le plus grand cadre carré possible.
 *
 * 19:36 : Une coquille s'est glissée dans l'énoncé de la question 2. La longueur des planches en bois est comprise entre 1 et 1 000 centimètres.
 */
public class PhotoFrame {

    public static void main( String[] argv ) throws Exception {
        System.out.println(solve(System.in));
    }

    public static int solve(InputStream in) throws Exception {
        Scanner sc = new Scanner(in);
        String line;

        Integer minLength = Integer.MAX_VALUE;
        Integer total = 0;

        while(sc.hasNextLine()) {
            line = sc.nextLine();
            System.err.println(line);

            /* Lisez les données et effectuez votre traitement */
            Integer length = Integer.valueOf(line);
            total += length;
            if (length < minLength) {
                minLength = length;
            }
        }

        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/

        return total - 4*minLength;
    }
}
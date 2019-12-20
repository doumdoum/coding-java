/**
 * Vous travaillez au service d'un puissant baron de la drogue,
 * dont le passe-temps favori et de vous envoyer au casino pour jouer ses gains (importants) à la roulette.
 * C'est une forme de roulette un peu particulière où la mise minimale est fixée à chaque tour.
 * Votre patron a un chiffre fétiche, le 18, et il vous oblige à miser dessus à chaque tour.
 *
 * Ce petit jeu vous ennuie et, ivre de pouvoir, vous décidez de le mener à sa perte grâce à
 * la complicité de votre collègue travaillant au casino qui vous indique d'avance
 * combien vous allez gagner en misant sur le 18 ou
 * combien vous allez perdre (c'est à dire la mise initiale).
 * Vous essayez de déterminer à quels tours vous devriez participer pour dilapider le plus rapidement possible la fortune de votre boss,
 * sachant que pour ne pas vous faire repérer vous décidez de ne participer qu'à des parties consécutives.
 *
 * Vous pouvez donc vous asseoir à la table au moment où vous le souhaitez,
 * puis vous devez jouer à tous les tirages jusqu'au moment où vous décidez de quitter la table.
 *
 * Données
 *
 * Entrée
 *
 * Ligne 1 : un entier N compris entre 1 et 10000 indiquant
 * le nombre de tirages qui seront effectués ce jour là.
 * Lignes 2 à N+1 :
 * un entier compris entre -3000 et 3000 décrivant le montant que vous gagnerez ou que vous perdrez si vous jouez le 18 à ce tirage
 * (un nombre négatif indique l'argent que vous perdez).
 *
 * Sortie
 *
 * Un entier négatif ou nul indiquant le montant maximum d'argent que vous pouvez faire perdre à votre patron en jouant une série de parties consécutives.
 *
 * Note : Vous vous refusez obstinément à lui faire gagner de l'argent.
 * S'il n'est pas possible de perdre de l'argent, vous ne jouez à aucune partie, et devez donc indiquer donc un résultat de 0 !
 */
package com.isograd.exercise;

import java.io.InputStream;
import java.util.Scanner;

public class MinLoss {

    public static void main( String[] argv ) throws Exception {
        System.out.println(solve(System.in));
    }

    public static long solve(InputStream in) {
        Scanner sc = new Scanner(in);
        String  line;

        line = sc.nextLine();
        System.err.println(line);

        int rounds = Integer.valueOf(line);
        int[] amount = new int[rounds];

        int i = 0;
        while(sc.hasNextLine()) {
            line = sc.nextLine();
            System.err.println(line);

            /* Lisez les données et effectuez votre traitement */
            amount[i] = Integer.valueOf(line);
            ++i;
        }

        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
        long minLoss = 0;
        for (int first = 0; first < rounds; ++first) {
            long minLossIdx = 0;
            long currentLoss = 0;
            for (int j= first; j < rounds; ++j) {
                currentLoss += amount[j];
                minLossIdx = Long.min(minLossIdx, currentLoss);
            }
            minLoss = Long.min(minLossIdx, minLoss);
        }

        return minLoss > 0 ? 0 : minLoss;
    }
}
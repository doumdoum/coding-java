package com.isograd.exercise;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Objectif
 *
 * Vous voilà presque au bout, mais on nous informe à l'oreille que des problèmes en salle machine sont survenus.
 * On vous transmet à travers le système de concours ce SOS afin que vous puissiez nous aider.
 *
 * La Battle Dev utilise un grand serveur avec beaucoup de processeurs gérant les milliers de processus générés par les codes des candidats.
 * Le problème est que le système d'exploitation que les organisateurs a choisi n'est pas très malin en ordonnancement de processus et
 * il y a un risque de plus en plus fort d'accès concurrent illégal des ressources.
 * Il semblerait que quelqu'un essaye activement d'exploiter cette faille de sécurité,
 * vous devez l'en empêcher sinon le concours sera biaisé et il ne se reproduira plus jamais : vous imaginez ?
 * Plus de concours annuel où vous pourrez mettre la pâté à votre collègue à coup de parcours de graphe entre deux Club Maté ™.
 *
 * Pour simplifier, on va considérer que
 * chaque processus i consomme une quantité de ressources C(i) ≥ 0 et que
 * la quantité de ressource disponible vaut V.
 * Les processus sont exécutés 2 par 2.
 * Le système d'exploitation a un algorithme prédictif génial analysant le code des candidats.
 * Il trouve une quantité de ressource minimale V qui fonctionne à condition de respecter la liste des exclusions.
 * La liste des exclusions est la liste exhaustive des processus qui ne peuvent pas être exécutés simultanément.
 * L'OS respecte bien sûr cette liste. Il y a juste un souci...
 * au lieu de vous donner V qui vous permettrait de dimensionner en temps réel la capacité du serveur auprès de votre fournisseur d'infrastructure.
 * Il ne vous donne que la liste des exclusions, petite erreur du programmeur, grave conséquence.
 *
 * Mais tout n'est pas perdu.
 * En fait, vous pouvez trouver V à partir de la liste d'exclusion et comme la ressource c'est cher, vous cherchez
 * la valeur minimale de V qui peut expliquer la liste fournie par votre algorithme.
 *
 * C'est à dire que :
 * - V doit être supérieur ou égal à tous les C(i)
 * - pour chaque paire de processus (i,j) (avec i différent de j) de la liste d'exclusions on a C(i)+C(j)>V
 * - pour chaque paire de processus (i,j) (avec i différent de j) n'appartenant pas à la liste d'exclusions on a C(i)+C(j)≤V
 *
 *
 * Données
 *
 * Entrée
 *
 * Ligne 1 : N le nombre de processus où 1 ≤ N ≤ 1000.
 * Ligne 2 : N entiers séparés par des espaces, ces entiers seront appelés P(i) avec i allant de 0 à N-1 et indiquent combien de processus ne peuvent pas être exécutés en même temps que le processus i, par construction on a donc 0 ≤ P(i) ≤ N.
 * Lignes i de 3 à N + 2 : P(i-3) entiers séparés par des espaces représentant les numéros des processus qui ne peuvent pas être exécutés en même temps que le processus (i-3). Si P(i-3)=0, la ligne i sera vide.
 *
 * Sortie
 *
 * La valeur minimale de la quantité de ressource qui peut expliquer la situation où -1 s'il n'y a pas de valeur possible.
 *
 * Exemple
 *
 * Entrée
 *
 * 4
 * 1 3 2 2
 * 1
 * 0 2 3
 * 1 3
 * 1 2
 *
 * Sortie
 *
 * 3
 *
 * Commentaires
 *
 * Les processus sont numérotés de 0 à 3.
 *
 * Si on fixe C(0) = 1, C(1)= 3, C(2)= 2, C(3) = 2 et que V=3, alors
 * - Les processus 0 et 1 donnent une consommation de 4 qui est supérieure à 3
 * - Les processus 1 et 2 donnent une consommation de 5 qui est supérieure à 3
 * - Les processus 1 et 3 donnent une consommation de 5 qui est supérieure à 3
 * - Les processus 2 et 3 donnent une consommation de 5 qui est supérieure à 3
 * Par contre, on peut faire tourner les paires de processus (0,2) ou (0,3) qui ne sont pas dans la liste des exclusions.
 *
 * On a donc vérifié tous les cas, V = 3 est une valeur de ressource qui explique la situation, il ne reste plus qu'à voir qu'elle est minimale.
 *
 * V ne peut pas être 1, car dans ce cas tous les processus doivent avoir une valeur égale à 0 ou 1. Par ailleurs :
 * - C(0)+C(1) est exclu donc strictement supérieur à 1 donc C(0)=1 et C(1)=1
 * - C(2)+C(3) est exclu donc strictement supérieur à 1 donc C(2)=1 et C(3)=1
 * Par conséquent (0,2) devrait être exclu et il ne l’est pas.
 * Donc V ne peut être égal à 1.
 *
 * V ne peut pas être 2. En effet on a :
 *
 * - C(0)+C(1) est exclu donc strictement supérieur à 2 donc l’un des deux est égal à 2 et l’autre à 1.
 * - C(2)+C(3) est exclu donc strictement supérieur à 2 donc l’un des deux est égal à 2 et l’autre à 1.
 *
 * Donc aucun processus ne vaut 0.
 * Donc le processus parmi (2,3) qui vaut 2 ne peut être associé à aucun autre.
 * Or les processus 2 et 3 peuvent être associés au processus 0.
 * Donc V ne peut être égal 2.
 *
 *
 *
 * Entrée
 *
 * 4
 * 1 1 1 1
 * 1
 * 0
 * 3
 * 2
 *
 * Sortie
 *
 * -1
 *
 * Commentaires
 *
 * On a (0,1) et (2,3) sont interdits donc C(0) + C(1) > V et C(2) + c(3) > V. Par conséquent C(0)+C(1)+C(2)+C(3)>2*V
 *
 * Mais aussi (0,2) et (1,3) sont autorisés donc C(0) + C(2) ≤ V et C(1) + C(3) ≤ V donc C(0)+C(1)+C(2)+C(3)≤ 2*V
 *
 * Donc V n'existe pas.
 */
public class DevOps {

    public static void main( String[] argv ) throws Exception {
        System.out.println(solve(System.in));
    }

    public static String solve(InputStream in) throws Exception {
        Scanner sc = new Scanner(in);
        String  line;

        while(sc.hasNextLine()) {
            line = sc.nextLine();
            System.err.println(line);

            /* Lisez les données et effectuez votre traitement */
        }

        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/

        return "OK";
    }
}
package com.isograd.exercise;

/**
 * Enoncé
 *
 * Pendant vos vacances avec vos amis, vous retrouvez un vieux jeu de cartes.
 * Malheureusement, le jeu n’est pas complet, il n’y a aucune carte à image (valet, dame et roi) et les cartes à chiffres sont tantôt manquantes, tantôt en trop.
 * Ce n’est pas grave, vous décidez quand même de jouer à une variante du « Président ».
 * Les nouvelles règles sont simples et vous n’avez pas besoin d’un jeu complet pour y jouer :
 * - Les joueurs jouent chacun leur tour en jetant des paquets de 1 à 4 cartes de la même valeur.
 * - Le premier à se débarrasser de toutes ses cartes devient alors « Président ».
 *
 * Chaque joueur a un identifiant numérique.
 * Au début de la partie, on décide qui est le premier joueur à jouer.
 * Le joueur N-1 joue avant le joueur N qui joue avant le joueur N+1.
 * Lorsque le joueur avec le plus grand identifiant a joué, c’est au joueur numéro 1 de jouer.
 *
 * Vous devez déterminer le classement final en supposant que les joueurs jouent de façon optimale.
 *
 * Format des données
 *
 * Entrée
 * Ligne 1 : un entier N compris entre 3 et 20 correspondant au nombre de joueurs.
 * Ligne 2 : un entier C compris entre 2 et 20 correspondant au nombre de cartes de chaque joueur.
 * Ligne 3 : un entier J compris entre 1 et N correspondant à l'identifiant du premier joueur à jouer.
 * Ligne 4 à N+3 : C entiers compris entre 1 et 9 séparés par un espace correspondant aux cartes du (i-3)ième joueur où i représente le numéro de la ligne.
 *
 * Sortie
 * N entiers séparés par un espace correspondant au classement final.
 */
/*
Partie numéro : 1
Assessment error: Votre code n'a rien renvoyé
Résultat attendu : 3 2 1

Partie numéro : 1
Votre code a renvoyé : 1 3 4 2
Résultat attendu : 3 4 1 2

Partie numéro : 2
Votre code a renvoyé : 16 23 27 5 9 15 17 19 20 25 1 2 4 6 11 12 13 21 22 24 3 7 8 10 14 18 26
Résultat attendu : 18 22 2 9 13 15 17 19 24 25 26 1 3 5 6 11 14 16 20 21 23 27 7 8 10 4 12
 */

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class President {

    public static void main( String[] argv ) throws Exception {
        System.out.println(solve(System.in));
    }

    public static String solve(InputStream in) {
        String  line;
        Scanner sc = new Scanner(in);
        sc = new Scanner(FriendsOfFriends.class.getResourceAsStream("/cards2.txt"));

        line = sc.nextLine();
        System.err.println(line);
        int players = Integer.valueOf(line);

        line = sc.nextLine();
        System.err.println(line);
        int cards = Integer.valueOf(line);

        line = sc.nextLine();
        System.err.println(line);
        int first = Integer.valueOf(line);

        System.err.println(""+ players +" "+cards+" "+first);

        Map<Integer, Integer> counts = new HashMap<>();

        int i = 0;
        while(sc.hasNextLine()) {
            line = sc.nextLine();
            System.err.println(line);

            /* Lisez les données et effectuez votre traitement */
            List<String> tokens = Arrays.asList(line.split(" "));

            Map<String, Integer> cardsCount = new HashMap<>();
            tokens.stream().forEach(s -> {
                cardsCount.putIfAbsent(s, 0);
                cardsCount.put(s, 1 + cardsCount.get(s));
            });
            counts.put(i, cardsCount.size());
            ++i;
        }

        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
        System.err.println(""+counts);
        List<Integer> winners = new ArrayList<>();

        List<Integer> tmp = counts.values().stream().distinct().sorted().collect(Collectors.toList());

        for(Integer count: tmp){
            System.err.println("count: "+count);
            for (int p = 0; p < players; p++){
                int current = (p + first-1) % players;
                if (counts.get(current) == count) {
                    System.err.println("winner: "+current);
                    winners.add(current);
                }
            }
        }

        String result = winners.stream()
                .map(v -> v+1)
                .map(v -> v.toString())
                .collect(Collectors.joining(" "));

        return result;
    }
}

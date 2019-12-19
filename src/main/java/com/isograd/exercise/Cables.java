package com.isograd.exercise;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Objectif
 *
 * On vous convoque dans le bureau du président, vous êtes désormais conseiller stratégique réseau de la République libre du Liberland.
 * Vous ne vous attendiez pas à cela, mais vous n'êtes pas trop déçu, compte tenu des maigres efforts fournis en réalité.
 *
 * Vous vous retrouvez dans une salle de commandement, avec des tas de gens trop sérieux pour vous, vous flairez le piège.
 * En effet, on vous charge de coordonner des milliers d'ingénieur réseau à travers le pays afin de mettre en place des liaisons 56k dans le pays.
 * Les ingénieurs réseaux vont dans des endroits avec un câble de diagnostic RJ11, regardent si tout va bien et au moindre pépin, utilisent leur super câble de diagnostic.
 * Or, le hic c'est le Liberland étant un nouveau pays, il y a beaucoup de gens motivés, mais pas beaucoup de ressources,
 * vous avez des câbles de diagnostic avec broches plaqué or, mais vous n'en avez pas beaucoup !
 *
 * Ainsi, votre supérieur vous charge de jeter un coup d'œil au plan journalier d'opérations,
 * une liste de requêtes que tous les ingénieurs réseaux soumettent pour leurs vadrouilles dans le pays dans le but de rendre le Liberland un peu plus moderne.
 * Vous devez attribuer les numéros de câbles à chaque requête d'ingénieur réseau, s'il est possible de satisfaire toutes leurs demandes simultanément,
 * sinon vous devez en informer votre supérieur aussitôt.
 *
 * Bien sûr, vous n'aimez pas faire les choses à la main, donc vous allez automatiser cette tâche.
 *
 * Données
 *
 * Entrée
 *
 * Ligne 1: deux entiers séparés par un espace,
 * N le nombre de câbles RJ11 et
 * M le nombre de requêtes de vos ingénieurs, N est compris entre 1 et 500 et M est compris entre 1 et 3N.
 *
 * Ligne 2 à M+1: deux entiers séparés par un espace représentant la date de début et de fin d'une requête d'usage d'un cable RJ11.
 * Les entiers représentent le nombre de secondes écoulées depuis le 26 novembre 2019. Ces entiers sont compris entre 0 et 2500.
 * Les transferts de cable sont instantanés : si un usage se termine à un temps T, le câble qu'il utilise peut être utilisé pour un autre usage commençant à l'instant T.
 *
 * Sortie
 *
 * Une série de M entiers compris entre 1 et N et séparés par des espaces indiquant le numéro câble attribué à chaque requête
 * ou la chaine pas possible, si a un moment donné vous n'avez pas assez de câble pour satisfaire toutes les requêtes.
 * Il y a bien sûr plusieurs solutions, vous pouvez choisir celle que vous voulez.
 *
 * Exemple
 *
 * Entrée
 *
 * 6 7
 * 1 3
 * 1 4
 * 1 5
 * 1 6
 * 1 7
 * 2 9
 * 3 11
 *
 * Sortie
 *
 * 1 2 3 4 5 6 1
 *
 * En effet vous pouvez attribuer vos 6 câbles aux 6 premières requêtes.
 * Pour la 7ème requête commençant à l'instant 3, vous pouvez utiliser le cable 1 qui a été attribué à une requête se terminant à l'instant 3.
 *
 * Exemple
 *
 * Entrée
 *
 * 6 7
 * 1 3
 * 1 4
 * 2 8
 * 1 5
 * 1 6
 * 1 7
 * 1 9
 *
 * Sortie
 *
 * pas possible
 *
 * En effet, après avoir assigné vos 6 câbles aux 6 requêtes commençant à l'instant 1. Vous n'avez plus de câble libre à l'instant 2 pour fournir la requête commençant à l'instant 2.
 */
public class Cables {

    public static void main( String[] argv ) throws Exception {
        System.out.println(solve(System.in));
    }

    public static String solve(InputStream in) throws Exception {
        Scanner sc = new Scanner(in);
        String  line;

        line = sc.nextLine();
        System.err.println(line);
        String[] tokens = line.split(" ");

        int cables = Integer.valueOf(tokens[0]);
        int requests = Integer.valueOf(tokens[1]);

        List<Integer> avails = new ArrayList<>();
        avails.addAll(Collections.nCopies(cables, 0));
        List<Borrowing> borrowings = new ArrayList<>();

        while(sc.hasNextLine()) {
            line = sc.nextLine();
            System.err.println(line);

            /* Lisez les données et effectuez votre traitement */
            tokens = line.split(" ");

            int begin = Integer.valueOf(tokens[0]);
            int end = Integer.valueOf(tokens[1]);
            borrowings.add(new Borrowing(begin, end));
        }

        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
        Collections.sort(borrowings);
        List<Integer> assignments = new ArrayList<>();

        for(Borrowing borrowing: borrowings){
            boolean managed = false;
            for (int i = 0; i < avails.size(); ++i) {
                if (avails.get(i) <= borrowing.begin) {
                    avails.set(i, borrowing.end);
                    // our numbering starts at 0, test starts at 1
                    assignments.add(i+1);
                    managed = true;
                    break;
                }
            }
            if (! managed) {
                break;
            }
        }

        return assignments.size() < borrowings.size() ?
                "pas possible" :
                assignments.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }

    public static class Borrowing implements Comparable<Borrowing> {
        private final int begin, end;

        public Borrowing(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        public int compareTo(Borrowing borrowing) {
            return this.begin - borrowing.begin;
        }

        @Override
        public String toString() {
            return "Borrowing{" +
                    "begin=" + begin +
                    ", end=" + end +
                    '}';
        }
    }
}
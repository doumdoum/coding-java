package com.isograd.exercise;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Objectif
 *
 * Vous glissez malencontreusement avec vos babouches sur votre tapis volant et
 * tombez à travers des sables mouvants dans une caverne magique où se trouve un fabuleux trésor.
 *
 * Le trésor est composé de différentes épices et poudres plus précieuses les unes que les autres dont
 * on vous donne le prix au kilogramme ainsi que la quantité totale de chacune des poudres contenues dans la caverne.
 * Le trésor comporte également des pierres précieuses, chacune a un poids et une valeur indivisible.
 * Vous n'avez avec vous qu'une vielle lampe à huile de contenance finie (vous avez cru quoi ? ce n'est pas magique hein !) et
 * vous devez indiquer quelles poudres et pierres précieuses emporter dedans pour maximiser la valeur du trésor que vous allez rapporter à la surface (avide que vous êtes) !
 *
 *
 * Indice : si vous avez reconnu ici le "problème du sac à dos" (https://fr.wikipedia.org/wiki/Problème_du_sac_à_dos),
 * vous êtes sur la bonne piste, mais le très grand nombre d'objets change un peu la donne !
 *
 * Données
 *
 * Entrée
 *
 * Ligne 1 : 3 entiers séparés par des espaces :
 * N le nombre de pierres précieuses,
 * M le nombre de types de poudres,
 * C la quantité (en gramme) de pierres ou poudre que peux contenir la lampe, chacun compris entre 1 et 100.
 *
 * Lignes 2 à N + 1 : 2 entiers séparés par des espaces, respectivement
 * la valeur (en pièces d'or) et le poids (en grammes) de chaque pierre précieuse, compris respectivement entre 1 et 1000 et 1 et C
 *
 * Lignes N + 2 à N + M + 2 : 2 entiers séparés par des espaces, respectivement
 * le prix au poids (en pièces d'or par gramme) et la quantité disponible (en grammes) de chaque type de poudre, compris respectivement entre 1 et 100 et 1 et C.
 *
 * Sortie
 *
 * La valeur maximale que peux contenir la lampe en pierres précieuses et poudres !
 *
 * Exemple
 *
 * Entrée
 *
 * 2 2 100
 * 600 40
 * 1000 50
 * 20 40
 * 15 80
 *
 * Sortie
 *
 * 1950
 *
 * Commentaire
 *
 * Dans cet exemple l'optimal est de prendre l'objet de valeur 1000,
 * puis 40 grammes de poudre à 20 pièces d'or par gramme,
 * puis 10 grammes de poudre à 15 pièces d'or par gramme.
 * On arrive alors à une valeur de 1000 + 800 + 150 = 1950
 */
public class GemsAndSpices {

    public static void main( String[] argv ) throws Exception {
        System.out.println(solve(System.in));
    }

    public static int solve(InputStream in) throws Exception {
        Scanner sc = new Scanner(in);
        String  line;

        line = sc.nextLine();
        System.err.println(line);
        String[] tokens = line.split(" ");

        int gems = Integer.valueOf(tokens[0]);
        int powders = Integer.valueOf(tokens[1]);
        int quantity = Integer.valueOf(tokens[2]);

        List<Item> items = new ArrayList<>();

        // gems
        for (int i = 0; i < gems; ++i) {
            line = sc.nextLine();
            System.err.println(line);
            tokens = line.split(" ");
            int price = Integer.valueOf(tokens[0]);
            int weight = Integer.valueOf(tokens[1]);
            items.add(new Item(price*1./weight, price, weight, false));
        }

        // powders
        for (int i = 0; i < powders; ++i) {
            line = sc.nextLine();
            System.err.println(line);
            tokens = line.split(" ");
            int value = Integer.valueOf(tokens[0]);
            int weight = Integer.valueOf(tokens[1]);
            items.add(new Item(value*1., value*weight, weight, true));
        }
        while(sc.hasNextLine()) {
            line = sc.nextLine();
            System.err.println(line);

            /* Lisez les données et effectuez votre traitement */
        }

        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
        Collections.sort(items);
        Collections.reverse(items);
        System.err.println(items);

        int value = 0;
        int weight = 0;
        for(Item item: items) {
            if (weight + item.weight <= quantity) {
                value += item.value;
                weight += item.weight;
            }
            else if (item.separable) {
                int max = quantity - weight;
                value += (max * item.value) / item.weight;
                weight = quantity;
                break;
            }
        }

        return value;
    }

    public static class Item implements Comparable<Item> {
        public final double weightedValue;
        public final int value, weight;
        public final boolean separable;


        public Item(double weightedValue, int value, int weight, boolean separable) {
            this.weightedValue = weightedValue;
            this.value = value;
            this.weight = weight;
            this.separable = separable;
        }

        @Override
        public int compareTo(Item item) {
            if (this.weightedValue == item.weightedValue) {
                return this.separable ? -1 : 1;
            }
            else if (this.weightedValue < item.weightedValue) {
                return -1;
            }
            return 1;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "value=" + value +
                    ", weight=" + weight +
                    ", separable=" + separable +
                    '}';
        }
    }
}
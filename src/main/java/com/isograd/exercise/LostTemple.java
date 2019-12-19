package com.isograd.exercise;

import com.google.common.base.Preconditions;

import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Objectif
 *
 * Vous avez réussi à vous procurer la carte ci-dessous montrant différents temples reliés par d'antiques lignes telluriques.
 * Les noms (indiqués ici par des lettres de A à N) sont donnés dans une langue oubliée.
 *
 *
 *
 *
 * La carte donne aussi le nom du Temple Perdu.
 * Comme il est fourni en langue oubliée, vous ne pouvez savoir où il se trouve dans le monde d'aujourd'hui.
 * Mais heureusement, vous disposez d'une carte du même réseau, rédigée dans une langue plus récente.
 * Il suffit d'identifier les temples de la carte récente avec les temples de la carte antique pour trouver le Temple Perdu !
 *
 *
 * Votre travail est d'écrire un programme prenant en entrée la carte moderne et calculant
 * pour chaque temple indiqué sur la carte à quel temple il correspond sur la carte ancienne et
 * d'en déduire le nom actuel Temple Perdu dont vous connaissez le nom ancien.
 *
 * Données
 *
 * Entrée
 * Ligne 1 : Une lettre entre A et N indiquant l'emplacement du temple perdu sur la vieille carte !
 * Lignes 2 à 22 : deux chaines contenant entre 5 et 30 caractères alphabétiques séparées par un espace représentant les noms de deux temples connectés sur la nouvelle carte.
 *
 * Sortie
 *
 * Le nom, sur la nouvelle carte, du temple correspondant au temple perdu !
 *
 * Exemple
 *
 * Entrée
 *
 * A
 * Earaindir Rithralas
 * Hilad Fioldor
 * Delanduil Rithralas
 * Urarion Elrebrimir
 * Elrebrimir Fioldor
 * Eowul Fioldor
 * Beladrieng Anaramir
 * Urarion Eowul
 * Earaindir Sanakil
 * Delanduil Isilmalad
 * Earylas Isilmalad
 * Rithralas Sanakil
 * Unithral Elrebrimir
 * Earylas Eowul
 * Beladrieng Hilad
 * Isilmalad Sanakil
 * Unithral Earylas
 * Earaindir Anaramir
 * Unithral Beladrieng
 * Hilad Anaramir
 * Delanduil Urarion
 *
 * Sortie
 *
 * Delanduil
 *
 * Dans cet exemple, vous identifiez ainsi que les lettres correspondent aux temples suivants :
 *
 * A -> Delanduil
 * B -> Unithral
 * C -> Beladrieng
 * D -> Hilad
 * E -> Earaindir
 * F -> Urarion
 * G -> Anaramir
 * H -> Earylas
 * I -> Eowul
 * J -> Isilmalad
 * K -> Elrebrimir
 * L -> Fioldor
 * M -> Rithralas
 * N -> Sanakil
 *
 * Le temple perdu, identifié ici par la lettre A correspond donc à Delanduil.
 */
public class LostTemple {

    public static void main( String[] argv ) throws Exception {
        System.out.println(solve(System.in));
    }

    public static String solve(InputStream in) {
        Scanner sc = new Scanner(in);
        String  line;

        line = sc.nextLine();
        System.err.println(line);
        Character temple = line.charAt(0);

        Map<String, Set<String>> newPlan = new HashMap<>();
        while(sc.hasNextLine()) {
            line = sc.nextLine();
            System.err.println(line);

            /* Lisez les données et effectuez votre traitement */
            String[] tokens = line.split(" ");
            String src = tokens[0];
            String dst = tokens[1];

            newPlan.putIfAbsent(src, new HashSet<>());
            newPlan.get(src).add(dst);

            newPlan.putIfAbsent(dst, new HashSet<>());
            newPlan.get(dst).add(src);
        }

        System.err.println(newPlan);

        Map<Character, Set<Character>> oldPlan = new HashMap<>();
        InputStream istream = LostTemple.class.getResourceAsStream("/LostTemple/original.txt");
        sc = new Scanner(istream);
        while(sc.hasNextLine()) {
            line = sc.nextLine();
            System.err.println(line);

            String[] tokens = line.split(" ");
            Character src = tokens[0].charAt(0);
            Character dst = tokens[1].charAt(0);

            oldPlan.putIfAbsent(src, new HashSet<>());
            oldPlan.get(src).add(dst);

            oldPlan.putIfAbsent(dst, new HashSet<>());
            oldPlan.get(dst).add(src);
        }

        System.err.println(oldPlan);

        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
        Preconditions.checkState(newPlan.size() == oldPlan.size());

        Deque<Character> queue = new ArrayDeque<>();
        queue.addAll(oldPlan.keySet());

        Map<String, Character> res = iterate(oldPlan, newPlan, new HashMap<String, Character>(), queue, newPlan.keySet());

        return res == null ? "NULL" : res.entrySet().stream().filter(kv -> kv.getValue() == temple).map(Map.Entry::getKey).findFirst().orElse("Null");
    }

    public static Map<String, Character> iterate(Map<Character, Set<Character>> oldPlan, Map<String, Set<String>> newPlan,
                                                 Map<String, Character> subs, Deque<Character> oldUnManaged, Set<String> newUnmanaged) {
        Preconditions.checkState(oldUnManaged.size() == newUnmanaged.size());
        Preconditions.checkState(oldUnManaged.size() + subs.size() == oldPlan.size());

        if (oldUnManaged.isEmpty()) {
            Preconditions.checkState(newUnmanaged.isEmpty());
            System.err.println("res: " + subs);
            return subs;
        }

        Character nextC = oldUnManaged.removeFirst();

        for(String nextStr: newUnmanaged) {
            System.err.println("test: " + nextC + " " + nextStr + " " + subs + " " + newUnmanaged);

            Set<Character> xx = newPlan.get(nextStr).stream()
                    .map(subs::get)
                    .filter(n -> n != null)
                    .collect(Collectors.toSet());

            if (oldPlan.get(nextC).containsAll(xx)) {
                // move on
                subs.put(nextStr, nextC);
                Set<String> nextUnmanaged = new HashSet<>();
                nextUnmanaged.addAll(newUnmanaged);
                nextUnmanaged.remove(nextStr);

                Map<String, Character> res = iterate(oldPlan, newPlan, subs, oldUnManaged, nextUnmanaged);

                if (res != null) {
                    return res;
                }
                subs.remove(nextStr);
            }
        }

        oldUnManaged.addFirst(nextC);
        return null;
    }
}
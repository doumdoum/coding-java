/**
 * Objectif
 *
 * Vos 2 nouveaux robots, Algotron 2000 et Polynomialator-ZX se sont perdus dans le métro de New York.
 * Pour ne rien arranger, le dispositif qui vous permet de les commander à distance depuis le haut de votre building secret vient de tomber en panne :
 * chaque commande envoyée sera transmise sans discernement à chacun des deux robots.
 *
 * Si seulement ils étaient au même endroit, vous pourriez les faire revenir sans problèmes...
 * Si pouviez trouver une séquence de commande telle que les deux robots se rejoignent en recevant les mêmes ordres depuis leurs positions de départ respectives, tout serait arrangé !
 *
 * Vous décidez d'écrire un programme : celui-ci analysera le plan des tunnels et trouvera une suite de commande adéquates, si elle existe !
 *
 * Données
 *
 * Entrée
 *
 * Ligne 1 : deux entiers N et M séparés par des espaces respectivement compris entre 1 et 100 et entre 1 et 500.
 * N et M représentent respectivement l'identifiant maximal des stations
 * (chaque station est identifiée par un nombre) et le nombre de tunnels sur le plan.
 * Ligne 2 : deux entiers compris entre 0 et N et indiquant
 * le numéro de la station où se trouvent respectivement Algotron 2000 et Polynomiator-ZX.
 * Lignes 3 à M+2 : une ligne composée de 3 éléments, séparés par des espaces représentant un tunnel :
 * - Un nombre entier indiquant le numéro d'une station (entre 0 et N)
 * - Un caractère G, D ou T (pour « gauche », « droite » ou « tout droit »)
 * - Un nombre entier, indiquant le numéro d'une autre station.
 *
 * Ainsi, une ligne de la forme « 4 G 3 » indique qu'en tournant à gauche depuis la station 4, on emprunte un tunnel menant à la station 3.
 * Certains tunnels peuvent boucler (ex. « 4 T 4 »).
 *
 *
 * Attention : Certains tunnels se dédoublent, et il est ainsi possible d'avoir à la fois « 4 G 3 » et « 4 G 2 ».
 * Dans un tel cas, si un robot est situé par exemple dans la station 4 reçoit la commande « G », on considère qu'il peut ensuite se trouver aussi bien en 3 qu'en 2.
 * Il n'est pas nécessaire que votre programme indique dans quelle station il se trouve :
 * pour que les robots se retrouvent, il suffit qu'à l'issue de toutes les commandes
 * il existe une station en commun parmi les stations possibles dans lesquels pourront se trouver les robots.
 * Autrement dit, il suffit qu'il existe un chemin correspondant aux directions indiquées par votre programme permettant aux robots de se retrouver.
 *
 * Note : Il est dangereux de circuler dans le métro, et chaque tunnel est à sens unique !
 *
 * Sortie
 *
 * Une suite de caractères G, D ou T qui, si elle est suivie depuis l'emplacement initial de chaque robot, devrait les mener au même endroit, si elle existe.
 * Si vous ne trouvez pas de telle séquence, renvoyez la chaîne de caractère fail.
 */
package com.isograd.exercise;

import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class LostRobotsReverse {

    public static void main( String[] argv ) throws Exception {
        InputStream in = System.in;
        System.out.println(solve(in));
    }

    public static String solve(InputStream in) throws Exception {
        Scanner sc = new Scanner(in);
        String  line;

        line = sc.nextLine();
        System.err.println(line);

        String[] tokens = line.split(" ");
        int stations = Integer.valueOf(tokens[0]);
        int tunnels = Integer.valueOf(tokens[1]);

        line = sc.nextLine();
        System.err.println(line);

        tokens = line.split(" ");
        int station0 = Integer.valueOf(tokens[0]);
        int station1 = Integer.valueOf(tokens[1]);

        Map<Integer, Set<Integer>> gMap = new HashMap<>();
        Map<Integer, Set<Integer>> dMap = new HashMap<>();
        Map<Integer, Set<Integer>> tMap = new HashMap<>();
        
        while(sc.hasNextLine()) {
            line = sc.nextLine();
            System.err.println(line);

            /* Lisez les données et effectuez votre traitement */
            tokens = line.split(" ");
            int src = Integer.valueOf(tokens[0]);
            int dst = Integer.valueOf(tokens[2]);

            switch (tokens[1]) {
                case "G":
                    gMap.putIfAbsent(dst, new HashSet<>());
                    gMap.get(dst).add(src);
                    break;
                case "D":
                    dMap.putIfAbsent(dst, new HashSet<>());
                    dMap.get(dst).add(src);
                    break;
                case "T":
                default:
                    tMap.putIfAbsent(dst, new HashSet<>());
                    tMap.get(dst).add(src);
                    break;
            }
        }

        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
        System.err.println("G: "+gMap);
        System.err.println("D: "+dMap);
        System.err.println("T: "+tMap);

        TreeMap<String, Set<Integer>> paths = new TreeMap<>(new StringComparator());
        gMap.entrySet().forEach(kv -> paths.put(kv.getKey()+"G", kv.getValue()));
        dMap.entrySet().forEach(kv -> paths.put(kv.getKey()+"D", kv.getValue()));
        tMap.entrySet().forEach(kv -> paths.put(kv.getKey()+"T", kv.getValue()));

        String solution = "fail";

        while (! paths.isEmpty()) {
            // pick first
            Map.Entry<String, Set<Integer>> path = paths.firstEntry();
            System.err.println("path: "+path);

            Set<Integer> reachables = path.getValue();
            if (reachables.contains(station0) && reachables.contains(station1)) {
                // we have found a solution
                solution = new StringBuilder(path.getKey().substring(1)).reverse().toString();
                break;
            }

            if (path.getKey().length() > stations){
                // Not sure for stop condition
                break;
            }

            // expand
            paths.remove(path.getKey());

            // G, D, T
            Set<Integer> next = expand(path.getValue(), gMap);

            if ( ! next.isEmpty()) {
                paths.put(path.getKey()+"G", next);
            }

            next = expand(path.getValue(), dMap);

            if ( ! next.isEmpty()) {
                paths.put(path.getKey()+"D", next);
            }

            next = expand(path.getValue(), tMap);

            if ( ! next.isEmpty()) {
                paths.put(path.getKey()+"T", next);
            }
        }

        return solution;
    }

    static private Set<Integer> expand(Set<Integer> input, Map<Integer, Set<Integer>> map) {
        Set<Integer> result = input.stream()
                .map(v -> map.getOrDefault(v, Collections.emptySet()))
                .flatMap(Set::stream)
                .collect(Collectors.toSet());

        System.err.println(result);

        return result;
    }

    public static class StringComparator implements Comparator<String> {

        @Override
        public int compare(String s, String t1) {
            if (s.length() == t1.length()) {
                return s.compareTo(t1);
            }
            return s.length() - t1.length();
        }
    }
}
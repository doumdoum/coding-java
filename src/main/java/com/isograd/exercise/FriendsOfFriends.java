/**
 * Enoncé
 *
 * Les amis de mes amis sont mes amis. L'expérience prouve que ce n'est pas toujours vrai.
 * Par exemple si on considère votre soeur comme une amie et son mari comme un ami à elle, alors comment dire...
 * Et par ailleurs cette affirmation rend la plupart des questions d'algorithmie beaucoup plus complexes que si on considérait que vos amis sont vos amis et puis c'est tout.
 *
 * Vous souhaitez déterminer qui a le plus d'amis dans votre groupe d'amis en utilisant cette logique simple : mes amis sont mes amis.
 *
 * Format des données
 *
 * Entrée
 * Ligne 1 : un entier N compris entre 1 et 400 indiquant le nombre de relations qui existent dans votre groupe d'amis.
 * Ligne 2 à N+1 : deux chaines de 6 caractères en majuscules séparées par un espace indiquant les prénoms de 2 personnes qui sont amies.
 * On considère que si A est ami avec B alors B est ami avec A.
 * Par ailleurs on vous garantit que la relation entre entre deux amis n'apparaît qu'une seule fois (donc vous ne trouverez qu'une fois "A est ami avec B" dans la liste et dans ce cas, vous ne trouverez pas "B est ami avec A").
 *
 * Sortie
 * Un entier représentant le nombre d'amis qu'a celui de vos amis qui a le plus d'amis. (Il peut y en avoir plusieurs dans ce cas).
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

/*
Votre code a renvoyé : 19
Résultat attendu : 18

 */
public class FriendsOfFriends {

    public static void main( String[] argv ) throws Exception {
        System.out.println(solve(System.in));
    }

    public static int solve(InputStream in) {
        String  line;
        Scanner sc = new Scanner(System.in);
        sc = new Scanner(FriendsOfFriends.class.getResourceAsStream("/testfile1.txt"));

        line = sc.nextLine();
        System.err.println(line);

        Map<String, Set<String>> friends = new HashMap<>();

        while(sc.hasNextLine()) {
            line = sc.nextLine();
            System.err.println(line);
            /* Lisez les données et effectuez votre traitement */
            String[] tokens = line.split(" ");

            String first = tokens[0];
            String second = tokens[1];

            /*Set<String> friendsOfFirst = new HashSet<>(friends.getOrDefault(first, Collections.emptySet()));
            Set<String> friendsOfSecond = new HashSet<>(friends.getOrDefault(second, Collections.emptySet()));

            if (! friendsOfFirst.contains(second)) {
                friendsOfFirst.stream().filter(x -> !friendsOfSecond.contains(x)).forEach(x -> addLink(friends, x, second));
            }
            if (! friendsOfSecond.contains(first) ){
                friendsOfSecond.stream().filter(x -> ! friendsOfFirst.contains(x)).forEach(x -> addLink(friends, x, first));
            }*/

            addLink(friends, first, second);
            addLink(friends, second, first);

            System.err.println(""+first+": "+friends.get(first));
            System.err.println(""+second+": "+friends.get(second));

            assert friends.get(first).contains(second);
            assert friends.get(second).contains(first);

            assert ! friends.get(first).contains(first);
            assert ! friends.get(second).contains(second);
        }
        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
        int result = friends.values().stream().map(Set::size).max(Comparator.comparing(Integer::longValue)).orElse(0);

        friends.entrySet().stream().filter(kv -> kv.getValue().size() == result).findFirst()
                .ifPresent(kv -> System.err.println("got: "+kv.getKey()+": "+kv.getValue()));

        System.err.println(result);
        assert result == 18;
        return result;
    }

    static void addLink(Map<String, Set<String>> friends, String me, String xx) {
        System.err.println("addLink: "+ me + " " + xx);
        Set<String> myFriends = friends.get(me);
        if (me == xx) {
            // NOP
        }
        else if (myFriends == null) {
            friends.put(me, new HashSet(Collections.singletonList(xx)));
        }
        else {
            myFriends.add(xx);
        }
    }

}

package com.isograd.exercise;

import java.io.InputStream;
import java.util.*;

public class IsoContest {

    public static void main( String[] argv ) throws Exception {
        System.out.println(solve(System.in));
    }

    public static String solve(InputStream in) {
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
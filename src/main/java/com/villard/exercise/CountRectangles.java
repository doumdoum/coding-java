package com.villard.exercise;

import com.google.common.collect.Sets;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Count the number of rectangles from a set of points
 * rectangles have to be parallel
 */
public class CountRectangles {

    public static void main( String[] argv ) throws Exception {
        System.out.println(new CountRectangles().solve(System.in));
    }

    public String solve(InputStream in) throws Exception {
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

    public int count(List<Point> points) {
        AtomicInteger count = new AtomicInteger(0);
        Map<Integer, Set<Integer>> srcs2Dsts = new HashMap<>();
        points.stream().forEach(point -> {
            srcs2Dsts.putIfAbsent(point.x, new HashSet<>());
            srcs2Dsts.get(point.x).add(point.y);
        });

        Map<Integer, Set<Integer>> dst2Srcs = new HashMap<>();
        points.stream().forEach(point -> {
            dst2Srcs.putIfAbsent(point.y, new HashSet<>());
            dst2Srcs.get(point.y).add(point.x);
        });

        for (int src: srcs2Dsts.keySet()) {
            System.err.println("src "+src);
            Set<Integer> dsts = srcs2Dsts.get(src);
            Iterator<Integer> iter = dsts.iterator();
            while (iter.hasNext()) {
                Integer y1 = iter.next();
                iter.forEachRemaining(y2 -> {
                    System.err.println("src: "+src +" y1: "+y1+" y2: "+y2);
                    Set<Integer> x1res = dst2Srcs.get(y1);
                    Set<Integer> x2res = dst2Srcs.get(y2);
                    count.addAndGet(Sets.intersection(x1res, x2res).size() -1);
                });
            }
        }
        return count.get() / 2;
    }

    public int countSol2(List<Point> points) {
        List<Point> newPoints = new ArrayList<>();
        newPoints.addAll(points);
        newPoints.sort(new PointComparator());

        System.err.println("points "+newPoints);

        AtomicInteger count = new AtomicInteger(0);
        Map<Integer, Set<Integer>> srcs2Dsts = new HashMap<>();
        points.stream().forEach(point -> {
            srcs2Dsts.putIfAbsent(point.x, new HashSet<>());
            srcs2Dsts.get(point.x).add(point.y);
        });

        Map<Integer, Set<Integer>> dst2Srcs = new HashMap<>();
        points.stream().forEach(point -> {
            dst2Srcs.putIfAbsent(point.y, new HashSet<>());
            dst2Srcs.get(point.y).add(point.x);
        });

        for (int src: srcs2Dsts.keySet()) {
            System.err.println("src "+src);
            Set<Integer> dsts = srcs2Dsts.get(src);
            Iterator<Integer> iter = dsts.iterator();
            while (iter.hasNext()) {
                Integer y1 = iter.next();
                iter.forEachRemaining(y2 -> {
                    System.err.println("src: "+src +" y1: "+y1+" y2: "+y2);
                    Set<Integer> x1res = dst2Srcs.get(y1);
                    Set<Integer> x2res = dst2Srcs.get(y2);
                    count.addAndGet(Sets.intersection(x1res, x2res).size() -1);
                });
            }
        }
        return count.get() / 2;
    }

    public static class Point {
        public final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public class PointComparator implements Comparator<Point> {

        @Override
        public int compare(Point p1, Point p2) {
            if (p1.x != p2.x) {
                return p1.x - p2.x;
            }
            return p1.y - p2.y;
        }
    }
}
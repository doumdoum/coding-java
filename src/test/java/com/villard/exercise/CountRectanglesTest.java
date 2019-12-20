package com.villard.exercise;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static com.villard.exercise.CountRectangles.Point;

public class CountRectanglesTest {

    @Test
    public void count1() {
        List<Point> input = ImmutableList.of(new Point(0, 0), new Point(0, 2), new Point(2, 0), new Point(2, 2));
        assertThat(new CountRectangles().countSol2(input)).isEqualTo(1);
    }

    @Test
    public void count3() {
        List<Point> input = ImmutableList.of(new Point(0, 0), new Point(0, 2), new Point(2, 0), new Point(2, 2), new Point(4, 0), new Point(4,2));
        assertThat(new CountRectangles().count(input)).isEqualTo(3);
    }
}
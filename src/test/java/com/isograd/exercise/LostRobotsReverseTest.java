package com.isograd.exercise;

import org.junit.Test;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;


public class LostRobotsReverseTest {

    @Test
    public void solve1() throws Exception {
        InputStream in = this.getClass().getResourceAsStream("/LostRobots/robots1.txt");
        assertThat(LostRobotsReverse.solve(in)).isEqualTo("GD");
    }

    @Test
    public void solve2() throws Exception {
        InputStream in = this.getClass().getResourceAsStream("/LostRobots/robots2.txt");
        assertThat(LostRobotsReverse.solve(in)).isEqualTo("fail");
    }
    @Test
    public void solve3() throws Exception {
        InputStream in = this.getClass().getResourceAsStream("/LostRobots/robots3.txt");
        assertThat(LostRobotsReverse.solve(in)).isEqualTo("G");
    }
}
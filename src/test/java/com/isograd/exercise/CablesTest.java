package com.isograd.exercise;

import org.junit.Test;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class CablesTest {

    @Test
    public void solve1() throws Exception {
        InputStream stream = CablesTest.class.getResourceAsStream("/Cables/cables1.txt");
        assertThat(Cables.solve(stream)).isEqualTo("1 2 3 4 5 6 1");
    }

    @Test
    public void solve2() throws Exception {
        InputStream stream = CablesTest.class.getResourceAsStream("/Cables/cables2.txt");
        assertThat(Cables.solve(stream)).isEqualTo("pas possible");
    }
}
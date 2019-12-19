package com.isograd.exercise;

import org.junit.Test;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class LostTempleTest {

    @Test
    public void solve1() {
        InputStream stream = LostTempleTest.class.getResourceAsStream("/LostTemple/test1.txt");
        assertThat(LostTemple.solve(stream)).isEqualTo("Delanduil");
    }
}
package com.isograd.exercise;

import org.junit.Test;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class GemsAndSpicesTest {

    @Test
    public void solve1() throws Exception {
        InputStream stream = GemsAndSpicesTest.class.getResourceAsStream("/GemsAndSpices/test1.txt");
        assertThat(GemsAndSpices.solve(stream)).isEqualTo(1950);
    }
}
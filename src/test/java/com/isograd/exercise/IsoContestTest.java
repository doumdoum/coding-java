package com.isograd.exercise;

import org.junit.Test;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class IsoContestTest {

    @Test
    public void solve() {
        InputStream stream = this.getClass().getResourceAsStream("");
        //assertThat(IsoContest.solve(stream)).isEqualTo("OK");
    }
}
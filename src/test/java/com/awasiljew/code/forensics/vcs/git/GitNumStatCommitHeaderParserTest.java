package com.awasiljew.code.forensics.vcs.git;

import org.testng.annotations.Test;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GitNumStatCommitHeaderParserTest {

    @Test
    public void shouldParseHeader() {
        // When
        Collection<String> items = newParser().items("[sdw12d0] Adam Wasiljew 2017-02-10 Some commit message");
        // Then
        assertEquals(items, asList("sdw12d0", "Adam Wasiljew", "2017-02-10", "Some commit message"));
    }

    @Test
    public void shouldParseHeaderWhenCommitMessageIsMissing() {
        // When
        Collection<String> items = newParser().items("[sdw12d0] Adam Wasiljew 2017-02-10 ");
        // Then
        assertEquals(items, asList("sdw12d0", "Adam Wasiljew", "2017-02-10", ""));
    }

    @Test
    public void shouldFailParseHeader() {
        // When
        Collection<String> items = newParser().items("[sdw12d0] Adam Wasiljew 2017-02-10");
        // Then
        assertTrue(items.isEmpty());
    }

    private GitNumStatCommitHeaderParser newParser() {
        return new GitNumStatCommitHeaderParser();
    }

}
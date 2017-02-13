package com.awasiljew.code.forensics.vcs.git;

import org.testng.annotations.Test;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GitNumStatFileParserTest {

    @Test
    public void shouldParseFileDetails() {
        // When
        Collection<String> items = new GitNumStatFileParser().items("1 2 fileName");
        // Then
        assertEquals(items, asList("1", "2", "fileName"));
    }

    @Test
    public void shouldParseEmptyFileDetails() {
        // When
        Collection<String> items = new GitNumStatFileParser().items("2 fileName");
        // Then
        assertTrue(items.isEmpty());
    }

}
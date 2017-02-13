package com.awasiljew.code.forensics.vcs.git;

import org.testng.annotations.Test;

import java.util.Collection;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GitNumStatParserTest {

    @Test
    public void shouldReturnCollectionOfNumbers() {
        // Given
        Pattern pattern = Pattern.compile("([0-9])([0-9])([0-9])");
        // When
        Collection<String> items = newParser(pattern).items("123");
        // Then
        assertEquals(items, asList("1", "2", "3"));
    }

    @Test
    public void shouldReturnEmtyCollectionOfNumbers() {
        // Given
        Pattern pattern = Pattern.compile("([0-9])([0-9])([0-9])");
        // When
        Collection<String> items = newParser(pattern).items("AB1");
        // Then
        assertTrue(items.isEmpty());
    }

    private GitNumStatParser newParser(Pattern pattern) {
        return new GitNumStatParser(pattern);
    }

}
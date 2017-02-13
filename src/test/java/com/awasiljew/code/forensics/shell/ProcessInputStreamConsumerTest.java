package com.awasiljew.code.forensics.shell;

import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

public class ProcessInputStreamConsumerTest {

    private static String SINGLE_LINE_TEXT = "Some text";
    private static String MULTI_LINE_TEXT = "Some text\nAnd some other text\nAnd next one";

    @Test
    public void shouldConsumeSingleLineOfText() throws Exception {
        // Given
        InputStream is = prepareStream(SINGLE_LINE_TEXT);
        // When
        String result = consume(is);
        // Then
        assertEquals(result, SINGLE_LINE_TEXT);
    }

    @Test
    public void shouldConsumeMultipleLinesOfText() throws Exception {
        // Given
        InputStream is = prepareStream(MULTI_LINE_TEXT);
        // When
        String result = consume(is);
        // Then
        assertEquals(result, MULTI_LINE_TEXT);
    }

    @Test
    public void shouldGracefullyCloseStream() throws Exception {
        // Given
        InputStream is = inputStreamWithIOError();
        // When
        String result = consume(is);
        // Then
        assertEquals(result, "");
        verify(is, atLeastOnce()).close();
    }

    private InputStream inputStreamWithIOError() throws IOException {
        InputStream is = mock(InputStream.class);
        when(is.available()).thenReturn(1);
        when(is.read(any())).thenThrow(new IOException("Expected exception"));
        return is;
    }

    private ByteArrayInputStream prepareStream(String text) {
        return new ByteArrayInputStream(text.getBytes());
    }

    private String consume(InputStream is) throws Exception {
        return new ProcessInputStreamConsumer(is).call();
    }

}
package com.jpp.codingex.background;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ContentProcessorTests {


    @Test
    public void retrieveTenthCharacter() {
        String content = "123456789ASDFGHJ";
        char result = ContentProcessor.findCharacterAtPosition(content, 10);
        assertEquals('A', result);
    }

    @Test
    public void retrieveFirstCharacter() {
        String content = "123456789ASDFGHJ";
        char result = ContentProcessor.findCharacterAtPosition(content, 1);
        assertEquals('1', result);
    }

    @Test
    public void retrieveCharacterWithNullContent() {
        try {
            String content = null;
            ContentProcessor.findCharacterAtPosition(content, 10);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void retrieveCharacterWithNegativePosition() {
        try {
            String content = "123456789ASDFGHJ";
            ContentProcessor.findCharacterAtPosition(content, -1);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void retrieveCharacterWithPositionHigherThanZise() {
        try {
            String content = "123456789ASDFGHJ";
            ContentProcessor.findCharacterAtPosition(content, 16);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void retrieveCharacterWithPositionLowerThanOne() {
        try {
            String content = "123456789ASDFGHJ";
            ContentProcessor.findCharacterAtPosition(content, 0);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void retrieveContentEveryTenthCharacter() {
        String content = "123456789A";
        char[] result = ContentProcessor.findCharacterAtEveryPosition(content, 10);
        assertEquals(1, result.length);
        assertEquals('A', result[0]);
    }

    @Test
    public void retrieveContentEveryTenthCharacterWithTwentyChars() {
        String content = "123456789A123456789B";
        char[] result = ContentProcessor.findCharacterAtEveryPosition(content, 10);
        assertEquals(2, result.length);
        assertEquals('A', result[0]);
        assertEquals('B', result[1]);
    }

    @Test
    public void retrieveContentEveryTenthCharacterWithFiftyChars() {
        String content = "123456789A123456789B123456789C123456789D123456789E";
        char[] result = ContentProcessor.findCharacterAtEveryPosition(content, 10);
        assertEquals(5, result.length);
        assertEquals('A', result[0]);
        assertEquals('B', result[1]);
        assertEquals('C', result[2]);
        assertEquals('D', result[3]);
        assertEquals('E', result[4]);
    }

    @Test
    public void retrieveContentEveryTenthCharacterWithNoIntegerModule() {
        String content = "123456789A123456789B123456789C123456789D12345";
        char[] result = ContentProcessor.findCharacterAtEveryPosition(content, 10);
        assertEquals(4, result.length);
        assertEquals('A', result[0]);
        assertEquals('B', result[1]);
        assertEquals('C', result[2]);
        assertEquals('D', result[3]);
    }

    @Test
    public void retrieveContentEveryTenthCharacterWithShortContent() {
        String content = "1234567";
        try {
            ContentProcessor.findCharacterAtEveryPosition(content, 10);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void retrieveContentEveryTenthCharacterWithNullContent() {
        try {
            String content = null;
            ContentProcessor.findCharacterAtEveryPosition(content, 10);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void retrieveContentEveryTenthCharacterWithNegativePosition() {
        try {
            String content = "123456789ASDFGHJ";
            ContentProcessor.findCharacterAtEveryPosition(content, -1);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }
}

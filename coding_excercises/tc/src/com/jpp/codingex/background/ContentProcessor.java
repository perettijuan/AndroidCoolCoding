package com.jpp.codingex.background;

import java.util.StringTokenizer;

/**
 * Processor that takes care of processing String content, finding characters at
 * different positions in that content.
 * 
 * @author Juan P. Peretti (peretti.juan@gmail.com)
 * 
 */
class ContentProcessor {


    /**
     * Finds a character in the content string given by the position parameter.
     * Note that the first character in the content is taken as the position 1
     * (one).
     * 
     * @param content
     *            - the content to look for the character.
     * @param position
     *            - the position of the character to find (always a value higher
     *            than zero and lower that the size of the content) content.
     * @return - the founded value.
     */
    static char findCharacterAtPosition(String content, int position) {
        if (content == null) {
            throw new IllegalArgumentException(
                    "The given content must not be null");
        }
        if (position <= 0) {
            throw new IllegalArgumentException(
                    "You must provide a valid position (always higher than zero)");
        }
        char[] contentAsCharArray = content.toCharArray();
        return findCharacterAtPosition(contentAsCharArray, position);
    }

    /**
     * Finds the character that is at the given position.
     * 
     * @param contentAsCharArray
     *            - the content to use as source for the look up as an array.
     * @param position
     *            - the position to find the character.
     * @return - the character found in the given position.
     */
    private static char findCharacterAtPosition(char[] contentAsCharArray,
            int position) {
        if (position > contentAsCharArray.length) {
            throw new IllegalArgumentException(
                    "You must provide a valid position (lower that the size of the content)");
        }
        // since the array starts a index 0, calculate the correct position
        int positionAdjusted = position - 1;
        char result = contentAsCharArray[positionAdjusted];
        return result;
    }

    /**
     * Finds a character located at every position pattern. If position is ten,
     * then the algorithm will look for the character that is every ten
     * positions in the content.
     * 
     * @param content
     *            - the content to use as source for the look up
     * @param position
     *            - the position to find the character.
     * @return - an array with all the characters found.
     */
    static char[] findCharacterAtEveryPosition(String content, int position) {

        if (content == null) {
            throw new IllegalArgumentException(
                    "The given content must not be null");
        }
        if (position <= 0) {
            throw new IllegalArgumentException(
                    "You must provide a valid position (always higher than zero)");
        }

        char[] contentAsCharArray = content.toCharArray();

        if (contentAsCharArray.length < position) {
            throw new IllegalArgumentException(
                    "The content must be longer that the position to look for");
        }

        // calculate the module -> the amount of expected chars to find
        int occurrences = contentAsCharArray.length / position;

        char[] result = new char[occurrences];
        int jump = position;
        for (int i = 0; i < occurrences; i++) {
            try {
                result[i] = findCharacterAtPosition(contentAsCharArray, jump);
            } catch (IllegalArgumentException e) {
                break;
            }
            jump += position;
        }

        return result;
    }

    /**
     * Finds a word in the content given, splitting the that content with the
     * given pattern.
     * 
     * @param content
     *            - the content to search the key.
     * @param pattern
     *            - the pattern to use when splitting the content.
     * @param key
     *            - the key to look for.
     * @return the number of times that the key repeated in the content split by
     *         the pattern.
     */
    static int findWordRepetition(String content, String pattern, String key) {
        if (content == null) {
            throw new IllegalArgumentException(
                    "The content to use can not be null");
        }

        if (pattern == null) {
            throw new IllegalArgumentException(
                    "The pattern to use can not be null");
        }

        if (key == null) {
            throw new IllegalArgumentException("The key to use can not be null");
        }
        int wordCount = 0;
        StringTokenizer tokenizer = new StringTokenizer(content, pattern);
        while (tokenizer.hasMoreTokens()) {
            String currentToken = tokenizer.nextToken();
            if (key.equalsIgnoreCase(currentToken)) {
                wordCount++;
            }
        }
        return wordCount;
    }
    
    
    static boolean isTheSamePhoneNumber(String originalNumber, String numberToCompare) {
        char[] original = originalNumber.toCharArray();
        char[] compare = numberToCompare.toCharArray();
        char[] created = new char[original.length];
        int j = 0;
        for(int i = 0; i < compare.length; i++) {
            if(j < original.length) {
                char c = compare[i];
                char o = original[j];
                if(c == o) {
                    created[j] = c;
                    j++;
                }
            }           
        }
        String createdString = new String(created);
        return originalNumber.equals(createdString);
    }

}

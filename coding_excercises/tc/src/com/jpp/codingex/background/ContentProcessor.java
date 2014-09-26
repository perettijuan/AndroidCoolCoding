package com.jpp.codingex.background;

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
	 *            than zero and lower that the size of the content)
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

}

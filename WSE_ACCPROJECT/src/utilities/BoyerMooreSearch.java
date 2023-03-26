package utilities;

import java.util.Arrays;

public class BoyerMooreSearch {
	private String pattern;
	private int[] lastOccurrence;

	public BoyerMooreSearch(String pattern) {
		this.pattern = pattern;
		this.lastOccurrence = new int[100000]; // Initialize the table with size 256 for ASCII characters
		Arrays.fill(lastOccurrence, -1); // Fill the table with -1 to indicate character not found
		for (int i = 0; i < pattern.length(); i++) {
			// Fill in the table with the rightmost index of each character in the pattern
			lastOccurrence[pattern.charAt(i)] = i;
		}
	}

	public int search(String text) {
		int textLength = text.length();
		int patternLength = pattern.length();
		int skip;
		for (int i = 0; i <= textLength - patternLength; i += skip) {
			skip = 0;
			for (int j = patternLength - 1; j >= 0; j--) {
				if (pattern.charAt(j) != text.charAt(i + j)) {
					// Calculate the skip value based on the last occurrence table
					skip = Math.max(1, j - lastOccurrence[text.charAt(i + j)]);
					break;
				}
			}
			if (skip == 0) {
				// Pattern found at index i
				return i;
			}
		}
		// Pattern not found
		return -1;
	}
}

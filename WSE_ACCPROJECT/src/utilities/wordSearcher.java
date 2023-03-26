package utilities;

public class wordSearcher {

	String patternMain;

	// the radix
	private final int req;

	// the bad-character skip array
	private static int[] right;

	// pattern provided as a string
	public wordSearcher(String patternMain) {
		this.req = 100000;
		this.patternMain = patternMain;

		// position of rightmost occurrence of c in the pattern
		right = new int[req];
		for (int c = 0; c < req; c++)
			right[c] = -1;
		for (int j = 0; j < patternMain.length(); j++)
			right[patternMain.charAt(j)] = j;
	}

	// return offset of first match and N if no match found
	public int search(String patternMain, String textMain) {
		int M = patternMain.length();
		int N = textMain.length();
		int skip;
		for (int i = 0; i <= N - M; i += skip) {
			skip = 0;
			for (int j = M - 1; j >= 0; j--) {
				if (patternMain.charAt(j) != textMain.charAt(i + j)) {
					skip = Math.max(1, j - right[textMain.charAt(i + j)]);
					break;
				}
			}
			if (skip == 0)
				return i; // found
		}
		return N; // not found
	}

}

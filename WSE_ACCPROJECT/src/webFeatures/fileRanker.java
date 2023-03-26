
package webFeatures;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;
import utilities.BoyerMooreSearch;

public class fileRanker {

	public int findWords(File file, String word) throws IOException, NullPointerException {
		// initialize counter for word occurrences
		int occCounter = 0;

		// read the file and store its contents in a string
		String dataFiles = "";
		BufferedReader buffReader = new BufferedReader(new FileReader(file));
		String line = null;
		while ((line = buffReader.readLine()) != null) {
			dataFiles = dataFiles + line;
		}
		buffReader.close();

		// search for the word using the Boyer-Moore algorithm
		String txt = dataFiles;
		BoyerMooreSearch SW = new BoyerMooreSearch(word);
		int offset = 0;
		for (int j = 0; j <= txt.length(); j += offset + word.length()) {
			// search for the word
			offset = SW.search(txt.substring(j));
			if ((offset + j) < txt.length()) {
				// increment occurrence counter if the word is found
				occCounter++;
			}
		}
		// return the number of word occurrences
		return occCounter;
	}

	public static void rankFiles(Hashtable<String, Integer> fileNameTable, int wordFrequency) {
		// convert the file name table into a list and sort it by word frequency
		ArrayList<Map.Entry<String, Integer>> fileList = new ArrayList<Map.Entry<String, Integer>>(
				fileNameTable.entrySet());
		Collections.sort(fileList, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> obj1, Map.Entry<String, Integer> obj2) {
				return obj1.getValue().compareTo(obj2.getValue());
			}
		});

		// reverse the sorted list to get the files with highest frequency first
		Collections.reverse(fileList);

		// display the ranked file list
		if (wordFrequency != 0) {
			System.out.println("\n------->> Top Searched Results <<-------\n");

			int maxOutput = 10;
			int j = 0;
			while (fileList.size() > j && maxOutput > 0) {
				// extract the HTML file name from the full file name
				int dotPos = fileList.get(j).toString().lastIndexOf(".");
				String tempFileName = fileList.get(j).toString().substring(0, dotPos);
				String htmlFileName = tempFileName + ".html";

				// display the ranked file name
				System.out.println("(" + (j + 1) + ") " + htmlFileName + " file");
				j++;
				maxOutput--;
			}
		} else {
			System.out.println("\nNo Results Found");
		}
	}

	public static void rankingResults(String wordSearch) {

		try {
			fileRanker pageRanker = new fileRanker();
			Hashtable<String, Integer> fileNameTable = new Hashtable<String, Integer>();

			int wordFrequency = 0;
			int wordOccurrence = 0;

			// find the number of files that contain the searched query
			File textFileLoc = new File("src/textPages");
			File[] fileArray = textFileLoc.listFiles();

			for (int i = 0; i < fileArray.length; i++) {
				wordFrequency = pageRanker.findWords(fileArray[i], wordSearch);
				fileNameTable.put(fileArray[i].getName(), wordFrequency);
				if (wordFrequency != 0) {
					wordOccurrence++;
				}
			}

			// display the total occurrence of the search word in files
			System.out.println("\nTotal Occurrence of '" + wordSearch + "' in files is : " + wordOccurrence);

			// rank the files based on the frequency of the search word and display the
			// ranked results
			rankFiles(fileNameTable, wordOccurrence);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
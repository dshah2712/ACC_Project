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
import utilities.wordSearcher;

public class fileRanker {

	// Finding the positions and occurrences of the words in the files
	public int findWords(File filePath, String str) throws IOException, NullPointerException {
		int occCounter = 0;
		String dataFiles = "";

		BufferedReader buffReader = new BufferedReader(new FileReader(filePath));
		String line = null;

		while ((line = buffReader.readLine()) != null) {
			dataFiles = dataFiles + line;
		}

		String txt = dataFiles;

		wordSearcher SW = new wordSearcher(str);

		int offset = 0;
		for (int j = 0; j <= txt.length(); j += offset + str.length()) {
			// search for the word
			offset = SW.search(str, txt.substring(j));
			if ((offset + j) < txt.length()) {
				occCounter++;
			}
		}

		buffReader.close();
		return occCounter;
	}


	public static void rankFiles(Hashtable<String, Integer> fname, int occur) {

		// converters into list and then sorts it in collections 
		ArrayList<Map.Entry<String, Integer>> arrayList = new ArrayList<Map.Entry<String, Integer>>(fname.entrySet());
		Collections.sort(arrayList, new Comparator<Map.Entry<String, Integer>>() {

			public int compare(Map.Entry<String, Integer> obj1, Map.Entry<String, Integer> obj2) {
				return obj1.getValue().compareTo(obj2.getValue());
			}
		});

		// reverse the sorted list
		Collections.reverse(arrayList);

		if (occur != 0) {
			System.out.println("\n------->> Top Searched Results <<-------\n");

			int maxOutput = 10;
			int j = 0;
			while (arrayList.size() > j && maxOutput > 0) {

				int dotPos = arrayList.get(j).toString().lastIndexOf(".");
				
				String tempFileName = arrayList.get(j).toString().substring(0, dotPos);
				String htmlFileName = tempFileName + ".html";

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
			Hashtable<String, Integer> hashTableRank = new Hashtable<String, Integer>();

			int freqCounter = 0;
			int wordOccurrence = 0;

			// Number of files that contains the Searched query
			File textFileLoc = new File("src/textPages");
			File[] fileArray = textFileLoc.listFiles();

			for (int i = 0; i < fileArray.length; i++) {
				freqCounter = pageRanker.findWords(fileArray[i], wordSearch);
				hashTableRank.put(fileArray[i].getName(), freqCounter);
				if (freqCounter != 0) {
					wordOccurrence++;
				}
			}

		System.out.println("\nTotal Occurrence of '" + wordSearch + "' in files is : " + wordOccurrence);
		rankFiles(hashTableRank, wordOccurrence);
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}

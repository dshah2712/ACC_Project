package webFeatures;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class frequencyCount {

	public static void frqCount(String wordtocount) throws FileNotFoundException, IOException {
		try {
			// Get the path to the directory containing the text files
			File filePath = new File("src/textPages");

			// Get a list of all files in the directory
			File[] allPathFiles = filePath.listFiles();

			// Iterate through each file in the directory
			for (int i = 0; i < allPathFiles.length; i++) {
				int count = 0;

				// Only process the file if it is a regular file (not a directory)
				if (allPathFiles[i].isFile()) {
					// Open the file for reading
					File file = new File("src/textPages/" + allPathFiles[i].getName());
					InputStreamReader iReader = new InputStreamReader(new FileInputStream(file), "utf-8");
					BufferedReader bReader = new BufferedReader(iReader);
					StringBuffer wordFind = new StringBuffer();
					String data = null;

					// Read the contents of the file into a buffer
					while ((data = bReader.readLine()) != null) {
						wordFind.append(data);
					}

					// Use a regular expression to search for the given word
					Pattern pattern = Pattern.compile(wordtocount, Pattern.CASE_INSENSITIVE);
					Matcher matcher = pattern.matcher(wordFind);
					while (matcher.find()) {
						// Increment the counter for each match found
						count++;
					}

					// Close the file reader
					bReader.close();

					// Print the count and file name
					System.out.println(
							"Total Count : " + count + "\nHTML file name : " + allPathFiles[i].getName() + "\n");
				}
			}
		} catch (IOException e) {
			// Handle any exceptions that occur while reading the files
			e.printStackTrace();
		}
	}
}

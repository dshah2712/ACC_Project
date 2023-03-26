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

			// path to text files
			File filePath = new File("src/textPages");
			File[] allPathFiles = filePath.listFiles();

			for (int i = 0; i < allPathFiles.length; i++) {
				int count = 0;
				if (allPathFiles[i].isFile()) {

					File mainFile = new File("src/textPages/" + allPathFiles[i].getName());

					InputStreamReader inputReader = new InputStreamReader(new FileInputStream(mainFile), "utf-8");
					BufferedReader bufferIN = new BufferedReader(inputReader);
					StringBuffer wordFind = new StringBuffer();
					String stringToSearch = null;

					// variable to count the occurrences of words
					while ((stringToSearch = bufferIN.readLine()) != null) {
						wordFind.append(stringToSearch);
					}

					Pattern pattern = Pattern.compile(wordtocount);
					Matcher matcher = pattern.matcher(wordFind);
					while (matcher.find()) {
						count++;
					}

					bufferIN.close();
					System.out.println(
							"Total Count : " + count + "\nHTML file name : " + allPathFiles[i].getName() + "\n");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package webFeatures;

import java.io.*;
import org.jsoup.Jsoup;

public class htmlConverter {

	// Method for conversion of HTML pages to Text files
	public static void htmlTextConverter() throws IOException, FileNotFoundException, NullPointerException {

		org.jsoup.nodes.Document docFile = null;

		// Try catch for file reading and writing
		try {

			File htmlFile = new File("src/htmlPages");
			File textFile = new File("src/textPages");

			// clear the directory before the downloading new htmlFile
			for (File fileMain : textFile.listFiles()) {
				if (!fileMain.isDirectory())
					fileMain.delete();
			}

			File[] filesList = htmlFile.listFiles();
			
			// loop to save the converted files in textPages folder
			for (File filesPath : filesList) {

				docFile = Jsoup.parse(filesPath, "UTF-8");
				String textString = docFile.text();
				String filesName = filesPath.getName().substring(0, filesPath.getName().lastIndexOf('.'));
				BufferedWriter bufferWriter = new BufferedWriter(new FileWriter("src/textPages/" + filesName + ".txt"));
				bufferWriter.write(textString);
				bufferWriter.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
package webFeatures;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class pageCrawler {

	private static final int maxDepth = 3;
	static HashSet<String> hashSet = new HashSet<String>();;
<<<<<<< HEAD

	// downloads and parses the URLs
	//page depth stores the current depth of web crawler
=======
	static ArrayList<String> linkArray = new ArrayList<String>();

	// downloads and parses the URLs
>>>>>>> 58cabc0d0fac057c4577be36d2712e0da86ca84f
	public static void downloadURLs(String mainURL, int maxPages, int pageDepth) {

		String[] onlyFileName;
		File fileMain = new File("src/htmlPages");
		onlyFileName = fileMain.list();
<<<<<<< HEAD
 
=======

>>>>>>> 58cabc0d0fac057c4577be36d2712e0da86ca84f
		// condition for the max length for files
		if (onlyFileName.length < maxPages) {
			if ((!hashSet.contains(mainURL) && (pageDepth <= maxDepth))) {
				try {
					if (hashSet.add(mainURL)) {

<<<<<<< HEAD
=======
						// adding URLs in the array
						linkArray.add(mainURL);
>>>>>>> 58cabc0d0fac057c4577be36d2712e0da86ca84f
						// calling new method to save the files in folder
						saveURLs(mainURL);
					}
					// getting the HTML from URL
					Document mainDocument = Jsoup.connect(mainURL).get();
					// parsing the HTML
<<<<<<< HEAD
					//extract all the link
=======
>>>>>>> 58cabc0d0fac057c4577be36d2712e0da86ca84f
					Elements pageLink = mainDocument.select("a[href]");
					pageDepth++;

					// For each extracted mainURL
					for (Element page : pageLink) {
						downloadURLs(page.attr("abs:href"), maxPages, pageDepth);
					}
				} catch (IOException e) {
					
				}
			}
		}
	}

	// saving the parsed URls in folder
<<<<<<< HEAD
	public static void saveURLs(String url) throws IOException {
	    URL urlObj = new URL(url);
	    String filename = "src/htmlPages/" + urlObj.getFile().replace("/", "") + ".html";

	    try (BufferedReader reader = new BufferedReader(new InputStreamReader(urlObj.openStream()));
	         BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            writer.write(line);
	        }
	    }
	}

=======
	public static void saveURLs(final String mainURL2) throws IOException, MalformedURLException {
		{
			try {

				// Create object URL
				URL urlMain = new URL(mainURL2);

				// creating object for BufferedReader
				BufferedReader bufferReader = new BufferedReader(new InputStreamReader(urlMain.openStream()));

				// dotPos is for the position finding of the "/" in the url
				int dotPos = urlMain.toString().lastIndexOf("/");
				String temp_filename = urlMain.toString().substring(dotPos, urlMain.toString().length());

				// filename in which you want to download
				String str = temp_filename + ".html";

				// creating object for BufferedWriter
				BufferedWriter bufferWritter = new BufferedWriter(new FileWriter("src/htmlPages/" + str));

				// read each line from stream till end
				String line;
				while ((line = bufferReader.readLine()) != null) {
					bufferWritter.write(line);
				}

				bufferReader.close();
				bufferWritter.close();

			}

			// Exceptions of the URLs
			catch (MalformedURLException mal) {
				System.out.println("Malformed URL Exception : " + mal.getMessage());
			} catch (IOException i) {
				
			}
		}
	}
>>>>>>> 58cabc0d0fac057c4577be36d2712e0da86ca84f
	
	// main method call for the HTML Crawler
	public static void downloadHTML(String ulrGiven, int maxURLs) {

		File fileMain = new File("src/htmlPages");
		// deletes all files which are already in the folder
		for (File file : fileMain.listFiles()) {
			if (!file.isDirectory())
				file.delete();
		}
		downloadURLs(ulrGiven, maxURLs,0);
	}

}

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

	// downloads and parses the URLs
	//page depth stores the current depth of web crawler
	public static void downloadURLs(String mainURL, int maxPages, int pageDepth) {

		String[] onlyFileName;
		File fileMain = new File("src/htmlPages");
		onlyFileName = fileMain.list();
 
		// condition for the max length for files
		if (onlyFileName.length < maxPages) {
			if ((!hashSet.contains(mainURL) && (pageDepth <= maxDepth))) {
				try {
					if (hashSet.add(mainURL)) {

						// calling new method to save the files in folder
						saveURLs(mainURL);
					}
					// getting the HTML from URL
					Document mainDocument = Jsoup.connect(mainURL).get();
					// parsing the HTML
					//extract all the link
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

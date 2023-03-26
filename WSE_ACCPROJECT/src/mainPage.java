import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import webFeatures.*;

public class mainPage {

	public static void main(String[] args) throws FileNotFoundException, NullPointerException, IOException {

		Scanner scanner = new Scanner(System.in);

		System.out.println("<<--------------- Welcome User --------------->>");

		System.out.println("\n-> Do you want to run web crawler ? Y/N : ");
		String crawlerStart = scanner.next();
		
		if (crawlerStart.equals("Y")|| crawlerStart.equals("y")) {
			System.out.println("\n----->> Starting HTML Crawler on 'www.crawler-test.com' <<-----");
			pageCrawler.downloadHTML("https://crawler-test.com/", 10);
			System.out.println("\n-->> Crawler run on 'www.crawler-test.com' was performed successfully <<--");
		}
		htmlConverter.htmlTextConverter();
		System.out.println("\n-> Conversion from HTML files to Text files is completed <--\n");

		System.out.println("--------------------------------------------------------------\n");

		Boolean flag = true;
		
		while (flag) {
			System.out.println("\nType:\n1 to search word exist or not in file\n2 to count frequency of any word in all files\n3 to exit ");
			int in=scanner.nextInt();
			switch(in) {
				case 1:
					System.out.println("-> Enter your word to search from files : ");
					String searchword = scanner.next();
					Boolean wordCheck = spellingCheck.SpellCheck(searchword);
					if (wordCheck) {
						fileRanker.rankingResults(searchword);
					}
					break;
				case 2:
					System.out.println("-> Enter word to count frequency of the word in all files : ");
					String wordtocount = scanner.next();
					frequencyCount.frqCount(wordtocount);
					break;
				case 3:
					flag=false;
					break;
			}
		}

		System.out.println("--------------------------------------------------------------\n");
		scanner.close();
	}
}

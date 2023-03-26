import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import webFeatures.*;

public class app {

	public static void main(String[] args) throws FileNotFoundException, NullPointerException, IOException {

		Scanner scanner = new Scanner(System.in);

		System.out.print("\n-> Do you want to run web crawler ? Y/N : ");
		String crawlerStart = scanner.next();

		if (crawlerStart.toLowerCase().equals("y")) {
			System.out.print("Enter the URL of the website you want to crawle: ");
			String url = scanner.next();
			pageCrawler.downloadHTML(url, 10);
			System.out.println("\nCrawling '" + url + "' was performed successfully");
			htmlConverter.htmlTextConverter();
			System.out.println("Converting html to text");

			Boolean loopFlag = true;

			while (loopFlag) {
				System.out.println(
						"\nType:\n1 to search word exist or not in file\n2 to count frequency of any word in all files\n3 to exit ");
				int in = scanner.nextInt();
				switch (in) {
					case 1:
						System.out.println("Enter your word to search from files : ");
						String searchword = scanner.next();
						Boolean wordCheck = spellingCheck.SpellCheck(searchword);
						if (wordCheck) {
							fileRanker.rankingResults(searchword);
						}
						break;
					case 2:
						System.out.println("Enter word to count frequency of the word in all files : ");
						String wordtocount = scanner.next();
						frequencyCount.frqCount(wordtocount);
						break;
					case 3:
						loopFlag = false;
						break;
				}
			}
		}
		scanner.close();
	}
}

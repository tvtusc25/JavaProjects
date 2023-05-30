
//Name: Trey Tuscai
//Date: Nov. 8 2021
//File: WCTester.java
//Section: A
//Project: 6
//Course: CS231

package wordFrequences;

public class WCTester {

	public static void main(String[] args) 
	{
		//tests all the methods
		WordCounter wordC = new WordCounter();
		long start = System.currentTimeMillis();
		String fileName = args[0];
		String outputFileName = args[1];
		//wordC.analyze(fileName);
		//wordC.writeWordCountFile(outputFileName + "_beforeCommon");
		wordC.analyzeWithoutCommon(fileName, "/Users/treytuscai/Desktop/CommonWords.txt");
		wordC.writeWordCountFile(outputFileName + "_afterCommon");
		long end = System.currentTimeMillis();
		long time = (end - start)/1000;
		System.out.println("Runtime: " + time + " seconds");
		System.out.println("Unique Word Count: " + wordC.getUniqueWordCount());
		System.out.println("Total Word Count: " + wordC.getTotalWordCount());
		
		//wordC.readWordCountFile(outputFileName);
		//wordC.writeWordCountFile(outputFileName + "_v2");
		//System.out.println("done");
	}

}

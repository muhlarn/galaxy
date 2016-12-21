package co.za.merchant.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class FileUtil {
	
	private static final String FILENAME = "merchantGuide.txt";
	private static final String HOW = "how";
	
	public static void setFileContentIntoList(List<String> inputTextList, List<String> inputQuestionList) {
		
		BufferedReader b_reader = null;
		FileReader fileReader = null;
		
		try {
			
			fileReader = new FileReader(FILENAME);
			b_reader = new BufferedReader(fileReader);			
			String sCurrentLine;
			b_reader = new BufferedReader(new FileReader(FILENAME));

			while ((sCurrentLine = b_reader.readLine()) != null) {
				if (!sCurrentLine.toLowerCase().startsWith(HOW)) {
					inputTextList.add(sCurrentLine);
				} else {
					inputQuestionList.add(sCurrentLine.replace("?", ""));
				}
			}
			
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("I have no idea what you are talking about");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (b_reader != null)
					b_reader.close();

				if (fileReader != null)
					fileReader.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}

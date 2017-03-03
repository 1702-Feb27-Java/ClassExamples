package com.mory.question20;

import java.io.*;

public class Q20 {

	public static void main(String[] args) {
		printFileContent("src/com/mory/question20/Data.txt");
	}

	public static void printFileContent(String file) {

		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
			fileReader.close();
			System.out.println(stringBuffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

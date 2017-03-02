package q20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadTxt {

	public static void main(String[] args) {
		
		Scanner scan;
		File file;
		try {
			//create a file from Data.txt and open a scanner to it
			file = new File("Data.txt");			
			scan = new Scanner(file);
			
			//scan the first line
			String line = scan.nextLine();
			//while it still has lines to read
			while(scan.hasNextLine()){
				
				//split the string by the :
				String[] info = line.split(":");
				//put the name toegether with the first and last name
				String name = info[0] + " " + info[1];
				//get the age and state
				String age = info[2];
				String state = info[3];
				
				//prints out the info
				System.out.print("Name" + ": " + name + "\n" +"Age: " + age + " years\n" + "State: " + state + "\n\n");
				
				//get the next line
				line = scan.nextLine();
			}
			//close the stream
			scan.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		

	}

}

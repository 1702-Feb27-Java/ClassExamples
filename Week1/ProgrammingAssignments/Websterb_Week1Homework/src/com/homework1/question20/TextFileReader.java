package com.homework1.question20;

import java.io.*;

public class TextFileReader {

	public static void main(String[] args) {
			//File fileName = new File("C:\\Users\\Ben\\Documents\\workspace-sts-3.8.3.RELEASE\\Week1Homework\\src\\com\\homework1\\question20\\data.txt");
			File fileName = new File("Data.txt");
			String line = null;
			
			try{
				FileReader fileReader = new FileReader(fileName);
				BufferedReader bufferedReader = new BufferedReader(fileReader);//wrap in buffered reader for efficiency
				
				//loop through every line in text file
				while((line = bufferedReader.readLine()) != null){
					//initialize strings we are looking for in the file
					String firstName = " ";
					String lastName = " ";
					String state = " ";
					int age;
					int location = 0;

					int colonCount = 0;//switch variable to see how many colons you've seen
					
					//loop through every character in the line
					for(int i = 0; i < line.length(); i++){
						
						//when we find a colon or end of line, there is a word
						if(line.charAt(i) == ':' || i == line.length() - 1){
							colonCount++;//increment colon every time we find a word
							//switch dictates what kind of word we found
							switch(colonCount){
								case 1 : 
									firstName = line.substring(location, i);
									location = i + 1;//update location to place to right of colon
									break;
								case 2 : //when we get to the second word print first and last name
									lastName = line.substring(location, i);
									location = i + 1;//update location to place to right of colon
									System.out.println("Name: " + firstName + " " + lastName);
									break;
								case 3 : //after the third string, print age on the next line
									age = Integer.parseInt(line.substring(location, i));
									location = i + 1;//update location to place to right of colon
									System.out.println("Age: " + age);
									break;
								case 4 : //after we find the last word, print the state on the next line
									state = line.substring(location, i + 1 );
									System.out.println("State: " + state + " State" + "\n");
									break;
							}
							
						}
					}
				}
				bufferedReader.close();
			}
			catch(IOException e){
				System.out.println("Can't read file " + fileName);
			}
	}

}

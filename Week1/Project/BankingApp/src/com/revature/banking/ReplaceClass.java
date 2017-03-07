package com.revature.banking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReplaceClass {

	// we have to write a method for this because java doesn't have this
	// functionality... :(

	public static void editID(String ID) {

		// thisLine is the line we're looking at as an entire string
		// value is the string we want to use to replace the old value

		try {
			BufferedReader br = new BufferedReader(new FileReader("customeraccounts.txt"));

			int charCount = 0;
			int count = 0;

			List<String> lineArr = new ArrayList<>();
			String thisLine;

			while ((thisLine = br.readLine()) != null) {

				// splits the string into a string array
				lineArr.add(thisLine); // split it later

			} // CLOSES WHILE LOOP

			br.close(); // close this resource

			BufferedWriter bw = new BufferedWriter(new FileWriter("customeraccounts.txt", false));
			StringBuffer newFile = new StringBuffer();

			for (int j = 0; j < lineArr.size(); j++) {

				String currLine = lineArr.get(j); // save current line into a
													// temp String
				String[] newLine = lineArr.get(j).split(":"); // let's split
																// each string
																// into an array
				String buildAString = null;
				String finalStr = null;

				// looping through every element of the array
				for (int i = 0; i < newLine.length; i++) {

					// if the value is in this line, we will replace it
					if (ID.equals(newLine[0])) {

						// we're looking at the id, we need to remove 'n' from
						// the id

						String temp = newLine[0];
						String substring = newLine[0].substring(1, 9);
						newLine[0] = newLine[0].replace(temp, substring);

						// then we rewrite the entire line

						// build the new string with the value we replaced
						String delim = ":";

						buildAString = newLine[0] + delim + newLine[1] + delim + newLine[2] + delim + newLine[3] + delim
								+ newLine[4] + delim + newLine[5] + delim + newLine[6];

					}

					else { // if the value is NOT in the line, then we add the
							// char length of it to the counter
						charCount = charCount + currLine.length();
						// System.out.println("We didn't change anything in this
						// customer's info");

					} // else {

					if (buildAString != null) { // if we changed something
						finalStr = buildAString; // set new string to final
													// string
					} else { // if not
						finalStr = currLine; // set current string to final
												// string
					}

				} // end of looping through element in newLine

				// we're currently in the for loop for looping through arraylist

				// then we rewrite the entire file

				newFile.append(finalStr);
				newFile.append(System.getProperty("line.separator"));

				// end of writing new file

			} // end of looping through arraylist

			bw.write(newFile.toString());
			bw.flush();

		} catch (IOException e) {
			e.getStackTrace();
		} // TRY-CATCH

	}

	public static void editFirstName(String ID, String newFirstName) {

		// thisLine is the line we're looking at as an entire string
		// value is the string we want to use to replace the old value

		try {
			BufferedReader br = new BufferedReader(new FileReader("customeraccounts.txt"));

			int charCount = 0;
			int count = 0;

			List<String> lineArr = new ArrayList<>();
			String thisLine;

			while ((thisLine = br.readLine()) != null) {

				// splits the string into a string array
				lineArr.add(thisLine); // split it later

			} // CLOSES WHILE LOOP

			br.close(); // close this resource

			BufferedWriter bw = new BufferedWriter(new FileWriter("customeraccounts.txt", false));
			StringBuffer newFile = new StringBuffer();

			for (int j = 0; j < lineArr.size(); j++) {

				String currLine = lineArr.get(j); // save current line into a
													// temp String
				String[] newLine = lineArr.get(j).split(":"); // let's split
																// each string
																// into an array
				String buildAString = null;
				String finalStr = null;

				// looping through every element of the array
				for (int i = 0; i < newLine.length; i++) {

					// if the id is in this line
					if (ID.equals(newLine[0])) {

						// we will replace old name with new name
						newLine[1] = newLine[1].replace(newLine[1], newFirstName);
						// System.out.println(Arrays.toString(newLine));

						// then we rewrite the entire line

						// build the new string with the value we replaced
						String delim = ":";

						buildAString = newLine[0] + delim + newLine[1] + delim + newLine[2] + delim + newLine[3] + delim
								+ newLine[4] + delim + newLine[5] + delim + newLine[6];

					}

					else { // if the value is NOT in the line, then we add the
							// char length of it to the counter
						charCount = charCount + currLine.length();
						// System.out.println("We didn't change anything in this
						// customer's info");

					} // else {

					if (buildAString != null) { // if we changed something
						finalStr = buildAString; // set new string to final
													// string
					} else { // if not
						finalStr = currLine; // set current string to final
												// string
					}

				} // end of looping through element in newLine

				// we're currently in the for loop for looping through arraylist

				// then we rewrite the entire file

				newFile.append(finalStr);
				newFile.append(System.getProperty("line.separator"));

				// end of writing new file

			} // end of looping through arraylist

			bw.write(newFile.toString());
			bw.flush();

		} catch (IOException e) {
			e.getStackTrace();
		} // TRY-CATCH

	}
	
	public static void editLastName(String ID, String newLastName) {

		// thisLine is the line we're looking at as an entire string
		// value is the string we want to use to replace the old value

		try {
			BufferedReader br = new BufferedReader(new FileReader("customeraccounts.txt"));

			int charCount = 0;
			int count = 0;

			List<String> lineArr = new ArrayList<>();
			String thisLine;

			while ((thisLine = br.readLine()) != null) {

				// splits the string into a string array
				lineArr.add(thisLine); // split it later

			} // CLOSES WHILE LOOP

			br.close(); // close this resource

			BufferedWriter bw = new BufferedWriter(new FileWriter("customeraccounts.txt", false));
			StringBuffer newFile = new StringBuffer();

			for (int j = 0; j < lineArr.size(); j++) {

				String currLine = lineArr.get(j); // save current line into a
													// temp String
				String[] newLine = lineArr.get(j).split(":"); // let's split
																// each string
																// into an array
				String buildAString = null;
				String finalStr = null;

				// looping through every element of the array
				for (int i = 0; i < newLine.length; i++) {

					// if the id is in this line
					if (ID.equals(newLine[0])) {

						// we will replace old name with new name
						newLine[2] = newLine[2].replace(newLine[1], newLastName);
						// System.out.println(Arrays.toString(newLine));

						// then we rewrite the entire line

						// build the new string with the value we replaced
						String delim = ":";

						buildAString = newLine[0] + delim + newLine[1] + delim + newLine[2] + delim + newLine[3] + delim
								+ newLine[4] + delim + newLine[5] + delim + newLine[6];

					}

					else { // if the value is NOT in the line, then we add the
							// char length of it to the counter
						charCount = charCount + currLine.length();
						// System.out.println("We didn't change anything in this
						// customer's info");

					} // else {

					if (buildAString != null) { // if we changed something
						finalStr = buildAString; // set new string to final
													// string
					} else { // if not
						finalStr = currLine; // set current string to final
												// string
					}

				} // end of looping through element in newLine

				// we're currently in the for loop for looping through arraylist

				// then we rewrite the entire file

				newFile.append(finalStr);
				newFile.append(System.getProperty("line.separator"));

				// end of writing new file

			} // end of looping through arraylist

			bw.write(newFile.toString());
			bw.flush();

		} catch (IOException e) {
			e.getStackTrace();
		} // TRY-CATCH

	}
	
	public static void editUsername(String ID, String newUsername) {

		// thisLine is the line we're looking at as an entire string
		// value is the string we want to use to replace the old value

		try {
			BufferedReader br = new BufferedReader(new FileReader("customeraccounts.txt"));

			int charCount = 0;
			int count = 0;

			List<String> lineArr = new ArrayList<>();
			String thisLine;

			while ((thisLine = br.readLine()) != null) {

				// splits the string into a string array
				lineArr.add(thisLine); // split it later

			} // CLOSES WHILE LOOP

			br.close(); // close this resource

			BufferedWriter bw = new BufferedWriter(new FileWriter("customeraccounts.txt", false));
			StringBuffer newFile = new StringBuffer();

			for (int j = 0; j < lineArr.size(); j++) {

				String currLine = lineArr.get(j); // save current line into a
													// temp String
				String[] newLine = lineArr.get(j).split(":"); // let's split
																// each string
																// into an array
				String buildAString = null;
				String finalStr = null;

				// looping through every element of the array
				for (int i = 0; i < newLine.length; i++) {

					// if the id is in this line
					if (ID.equals(newLine[0])) {

						// we will replace old name with new name
						newLine[3] = newLine[3].replace(newLine[3], newUsername);
						// System.out.println(Arrays.toString(newLine));

						// then we rewrite the entire line

						// build the new string with the value we replaced
						String delim = ":";

						buildAString = newLine[0] + delim + newLine[1] + delim + newLine[2] + delim + newLine[3] + delim
								+ newLine[4] + delim + newLine[5] + delim + newLine[6];

					}

					else { // if the value is NOT in the line, then we add the
							// char length of it to the counter
						charCount = charCount + currLine.length();
						// System.out.println("We didn't change anything in this
						// customer's info");

					} // else {

					if (buildAString != null) { // if we changed something
						finalStr = buildAString; // set new string to final
													// string
					} else { // if not
						finalStr = currLine; // set current string to final
												// string
					}

				} // end of looping through element in newLine

				// we're currently in the for loop for looping through arraylist

				// then we rewrite the entire file

				newFile.append(finalStr);
				newFile.append(System.getProperty("line.separator"));

				// end of writing new file

			} // end of looping through arraylist

			bw.write(newFile.toString());
			bw.flush();

		} catch (IOException e) {
			e.getStackTrace();
		} // TRY-CATCH

	}
	
	public static void editPassword(String ID, String newPassword) {

		// thisLine is the line we're looking at as an entire string
		// value is the string we want to use to replace the old value

		try {
			BufferedReader br = new BufferedReader(new FileReader("customeraccounts.txt"));

			int charCount = 0;
			int count = 0;

			List<String> lineArr = new ArrayList<>();
			String thisLine;

			while ((thisLine = br.readLine()) != null) {

				// splits the string into a string array
				lineArr.add(thisLine); // split it later

			} // CLOSES WHILE LOOP

			br.close(); // close this resource

			BufferedWriter bw = new BufferedWriter(new FileWriter("customeraccounts.txt", false));
			StringBuffer newFile = new StringBuffer();

			for (int j = 0; j < lineArr.size(); j++) {

				String currLine = lineArr.get(j); // save current line into a
													// temp String
				String[] newLine = lineArr.get(j).split(":"); // let's split
																// each string
																// into an array
				String buildAString = null;
				String finalStr = null;

				// looping through every element of the array
				for (int i = 0; i < newLine.length; i++) {

					// if the id is in this line
					if (ID.equals(newLine[0])) {

						// we will replace old name with new name
						newLine[4] = newLine[4].replace(newLine[4], newPassword);
						// System.out.println(Arrays.toString(newLine));

						// then we rewrite the entire line

						// build the new string with the value we replaced
						String delim = ":";

						buildAString = newLine[0] + delim + newLine[1] + delim + newLine[2] + delim + newLine[3] + delim
								+ newLine[4] + delim + newLine[5] + delim + newLine[6];

					}

					else { // if the value is NOT in the line, then we add the
							// char length of it to the counter
						charCount = charCount + currLine.length();
						// System.out.println("We didn't change anything in this
						// customer's info");

					} // else {

					if (buildAString != null) { // if we changed something
						finalStr = buildAString; // set new string to final
													// string
					} else { // if not
						finalStr = currLine; // set current string to final
												// string
					}

				} // end of looping through element in newLine

				// we're currently in the for loop for looping through arraylist

				// then we rewrite the entire file

				newFile.append(finalStr);
				newFile.append(System.getProperty("line.separator"));

				// end of writing new file

			} // end of looping through arraylist

			bw.write(newFile.toString());
			bw.flush();

		} catch (IOException e) {
			e.getStackTrace();
		} // TRY-CATCH

	}

	// lets overload this method just for depositing and withdrawing
	public static void deposit(String username, String accountType, double newAmount) {

		try {
			BufferedReader br = new BufferedReader(new FileReader("customeraccounts.txt"));

			int charCount = 0;

			List<String> lineArr = new ArrayList<>();
			String thisLine;

			while ((thisLine = br.readLine()) != null) {

				// splits the string into a string array
				lineArr.add(thisLine); // split it later

			} // CLOSES WHILE LOOP

			br.close(); // close this resource

			BufferedWriter bw = new BufferedWriter(new FileWriter("customeraccounts.txt", false));
			StringBuffer newFile = new StringBuffer();

			for (int j = 0; j < lineArr.size(); j++) {

				String currLine = lineArr.get(j); // save current line into a
													// temp String
				String[] newLine = lineArr.get(j).split(":"); // let's split
																// each string
																// into an array
				String buildAString = null;
				String finalStr = null;

				// looping through every element of the array

				// if the username is in this line, we will deposit/withdraw
				if (username.equals(newLine[3])) {

					// checking account balance
					if (accountType.equals("c")) {

						double oldAmount = Double.parseDouble(newLine[5].substring(1, newLine[5].length()));
						System.out.println(oldAmount);
						double sum = oldAmount + newAmount;
						String toAdd = "c" + Double.toString(sum);
						System.out.println("sum");
						newLine[5] = newLine[5].replace(newLine[5], toAdd);
						// System.out.println(Arrays.toString(newLine));

					}

					// savings account balance
					if (accountType.equals("s")) {

						double oldAmount = Double.parseDouble(newLine[6].substring(1, newLine[6].length()));
						double sum = oldAmount + newAmount;
						String toAdd = "s" + Double.toString(sum);
						newLine[6] = newLine[6].replace(newLine[6], toAdd);
						// System.out.println(Arrays.toString(newLine));

					}

					// then we rewrite the entire line

					// build the new string with the value we replaced
					String delim = ":";

					buildAString = newLine[0] + delim + newLine[1] + delim + newLine[2] + delim + newLine[3] + delim
							+ newLine[4] + delim + newLine[5] + delim + newLine[6];

				}

				else { // if the value is NOT in the line, then we add the
						// char length of it to the counter
					charCount = charCount + currLine.length();
					// System.out.println("We didn't change anything in this
					// customer's info");

				} // else {

				if (buildAString != null) { // if we changed something
					finalStr = buildAString; // set new string to final string
				} else { // if not
					finalStr = currLine; // set current string to final string
				}

				// we're currently in the for loop for looping through arraylist

				// then we rewrite the entire file

				System.out.println("printing current NEW line: " + finalStr);

				newFile.append(finalStr);
				newFile.append(System.getProperty("line.separator"));

				// end of writing new file

			} // end of looping through arraylist

			bw.write(newFile.toString());
			bw.flush();
			bw.close();

		} catch (IOException e) {
			e.getStackTrace();
		} // TRY-CATCH

	}

	public static void withdraw(String username, String accountType, double newAmount) {

		try {
			BufferedReader br = new BufferedReader(new FileReader("customeraccounts.txt"));

			int charCount = 0;
			int count = 0;

			List<String> lineArr = new ArrayList<>();
			String thisLine;

			while ((thisLine = br.readLine()) != null) {

				// splits the string into a string array
				lineArr.add(thisLine); // split it later

			} // CLOSES WHILE LOOP

			br.close(); // close this resource

			BufferedWriter bw = new BufferedWriter(new FileWriter("customeraccounts.txt", false));
			StringBuffer newFile = new StringBuffer();

			for (int j = 0; j < lineArr.size(); j++) {

				String currLine = lineArr.get(j); // save current line into a
													// temp String
				String[] newLine = lineArr.get(j).split(":"); // let's split
																// each string
																// into an array
				String buildAString = null;
				String finalStr = null;

				// looping through every element of the array

				// if the username is in this line, we will deposit/withdraw
				if (username.equals(newLine[3])) {

					// checking account balance
					if (accountType.equals("c")) {

						double oldAmount = Double.parseDouble(newLine[5].substring(1, newLine[5].length()));
						double diff = oldAmount - newAmount;

						String toAdd = "c" + Double.toString(diff);
						newLine[5] = newLine[5].replace(newLine[5], toAdd);

						// System.out.println(Arrays.toString(newLine));

					}

					// savings account balance
					if (accountType.equals("s")) {

						double oldAmount = Double.parseDouble(newLine[6].substring(1, newLine[5].length()));
						double diff = oldAmount - newAmount;

						String toAdd = "c" + Double.toString(diff);
						newLine[6] = newLine[6].replace(newLine[6], toAdd);

						// System.out.println(Arrays.toString(newLine));

					}

					// then we rewrite the entire line

					// build the new string with the value we replaced
					String delim = ":";

					buildAString = newLine[0] + delim + newLine[1] + delim + newLine[2] + delim + newLine[3] + delim
							+ newLine[4] + delim + newLine[5] + delim + newLine[6];

				}

				else { // if the value is NOT in the line, then we add the
						// char length of it to the counter
					charCount = charCount + currLine.length();
					// System.out.println("We didn't change anything in this
					// customer's info");

				} // else {

				if (buildAString != null) { // if we changed something
					finalStr = buildAString; // set new string to final string
				} else { // if not
					finalStr = currLine; // set current string to final string
				}

				// we're currently in the for loop for looping through arraylist

				// then we rewrite the entire file

				System.out.println("printing current NEW line: " + finalStr);

				newFile.append(finalStr);
				newFile.append(System.getProperty("line.separator"));

				// end of writing new file

			} // end of looping through arraylist

			bw.write(newFile.toString());
			bw.flush();
			bw.close();

		} catch (IOException e) {
			e.getStackTrace();
		} // TRY-CATCH

	}

}

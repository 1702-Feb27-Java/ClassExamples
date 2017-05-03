package com.revature.weekone.question1;

/**
 * Performs a bubble sort on an integer array of arbitrary length. 
 * 
 * When executed it bubble-sorts the following integer array: { 1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4 }
 * 
 * @author Michael Hobbs
 *
 */
public class Question1 {
	
	/**
	 * The numbers to be sorted.
	 * 
	 */
	int[] numberList;
	
	/**
	 * Initializes the numbers to be sorted.
	 * 
	 * @param numbers the numbers
	 */
	public Question1(int... numbers)
	{
		numberList = new int[numbers.length]; //initialize the store for the numbers
		for (int i = 0; i < numbers.length; ++i) { //insert the numbers into the store
			numberList[i] = numbers[i]; //store each number 
		}
	}

	/**
	 * Bubble sorts the numbers.
	 * 
	 */
	public void bubbleSort() {
		System.out.println("Bubble sorting the numbers in the list...");
		
		for (int j = this.numberList.length - 1; j >= 0; --j) { //run through the unsorted portion of store. because larger numbers pile up at the end it is not necessary to run through that sorted portion.
			for (int i = 0; i < this.numberList.length - 1; ++i) { //run through the entire list so that larger numbers can be moved up
				if (this.numberList[i] > this.numberList[i+1]) { //larger number than the one immediately next to it should be moved up
					// move the larger number up, move the smaller number down
					int largerNumber = this.numberList[i]; //temporary variable for the larger number
					this.numberList[i] = this.numberList[i+1]; //move the larger number up
					this.numberList[i+1] = largerNumber; //move the smaller number down
				}
			}
		}
	}
	
	/**
	 * Prints all the numbers.
	 * 
	 */
	public void display() {
		System.out.println("There are " + this.numberList.length + " numbers in the list.");
		
		String numbers = "These are the numbers in the list: "; //holds the numbers to be printed out
		for (int i : this.numberList) { //run through each number
			numbers += i + " "; //add the number to the string that will be printed out
		}
		System.out.println(numbers); //print out all the numbers
	}
	
	public static void main(String[] args) {
		
		Question1 numbers = new Question1(1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4); //initialize the list
		
		numbers.display(); //display the list before it is sorted
		
		numbers.bubbleSort(); //bubble sort the list
		
		numbers.display(); //display the list after it has been sorted

	}

}

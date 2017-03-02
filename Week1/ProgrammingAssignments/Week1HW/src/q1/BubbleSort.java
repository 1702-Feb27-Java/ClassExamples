package q1;

public class BubbleSort {
	
	public static void main(String[] args) {
		
		//inialize the array
		int[] array = {1,0,5,6,3,2,3,7,9,8,4};
		
		//print the array
		System.out.println("The original array:");		
		printArray(array);
		
		// bubble sort
		bubbleSort(array);
		
		//print sorted array
		System.out.println("The sorted array:");		
		printArray(array);

	}
	
	// processes a bubble sort and prints the result
	public static void bubbleSort(int[] arr){
		//temp varable for swapping elements
		int temp = 0;
		
		// run until all 
		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j < arr.length - 1; j++){
				// if preceding elemet is greater than the next one swap them
				if(arr[j] > arr[j+1]){
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}			
			
		}
		
	}
	
	/*
	 * This function prints out the array
	 */
	public static void printArray(int[] array){	

		//print out the array
		for (int i = 0; i < array.length; i++){
			System.out.print(array[i]);
			//adds a comma unless its not at the end of the array
			if (i != array.length - 1){
				System.out.print(", ");
			}
		}
		
		//adds a new line after the array
		System.out.println("\n");
	}

}

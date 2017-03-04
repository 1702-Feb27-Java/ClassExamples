package Q1;

//Perform a bubble sort on the following integer array:
//1,0,5,6,3,2,3,7,9,8,4
public class BubbleSort {

	static int[] startingArray = { 1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4 };

	public static void main(String[] args) {

		bubbleSort(startingArray);
		// intializing array with values from question
		int[] startingArray = { 1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4 };

		/*
		 * System.out.println(startingArray[0] > startingArray[1]);
		 * System.out.println(startingArray[3]); int tmp = startingArray[3];
		 * startingArray[3] = startingArray[4]; startingArray[4] = tmp;
		 * System.out.println(startingArray[3]);
		 * 
		 * int a = 10; startingArray[a];
		 * 
		 * System.out.println(startingArray[0] + " " + startingArray[1]);
		 * 
		 */

		/*
		 * creating an if statement to compare index 0 and 1 if(startingArray[0]
		 * > startingArray[1]) {
		 * 
		 * //if index 0 > index 1 ----> set index 1 to a temporary var int tmp1
		 * = startingArray[1];
		 * 
		 * // since the value of index 1 is now stored we can set index 1 =
		 * index 0 startingArray[1] = startingArray[0];
		 * 
		 * // replacing the value of index 0 with the temp var that was set to
		 * the value of index 1 startingArray[0] = tmp1;
		 * 
		 * 
		 * 
		 * } System.out.println(startingArray[0] + " " + startingArray[1]);
		 * 
		 * for (int i = startingArray[0]; i < 0; i--) {
		 * System.out.println("HEll");
		 * 
		 * } //for loop which increments by 1 (i++), staring at 0 and stopping
		 * for (int i = 0; i < startingArray.length; i++) {
		 * System.out.print(startingArray[i] + " " ); }
		 */
		System.out.println();
		printArray(startingArray);
		// incr through
		for (int i = 0; i < startingArray.length - 1; i++) {
			for (int j = i + 1; j < startingArray.length; j++) {

				if (startingArray[i] > startingArray[j]) {

					int tmp1 = startingArray[j];

					startingArray[j] = startingArray[i];

					startingArray[i] = tmp1;
				}
			}
		}
		System.out.println();
		printArray(startingArray);

		// for (int i = 0; i < startingArray.length; i++) {
		// System.out.print(startingArray[i] + " " );
		// }

	}

	public static void printArray(int arr[]) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");

		}
	}

	public static void bubbleSort(int messyArray[]) {

		System.out.println();
		printArray(messyArray);

		for (int i = 0; i < messyArray.length - 1; i++) {
			for (int j = i + 1; j < messyArray.length; j++) {

				if (messyArray[i] > messyArray[j]) {

					int tmp1 = messyArray[j];

					messyArray[j] = messyArray[i];

					messyArray[i] = tmp1;
				}
			}
		}
		System.out.println();
		printArray(messyArray);

	}

}

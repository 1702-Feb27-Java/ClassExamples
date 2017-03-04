package Q1;

public class CondencedBubbleSort {

	static int[] startingArray = { 1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4 };

	public static void main(String[] args) {

		bubbleSort(startingArray);

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

//David Lund
package homework.questionone;



public class QuestionOne {

	public static void main(String[] args) {
		System.out.println("Question 1");
		QuestionOne qo = new QuestionOne();
// print array unsorted then print sorted
		int array[] = { 1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4 };
		System.out.println("UnSorted");
		qo.printArray(array);
		qo.bubbleSort(array);
		System.out.println("Sorted");
		qo.printArray(array);
	}

	public void bubbleSort(int bubbleArray[]) {
		int j;
		boolean flag = true; // set flag to true to begin first pass
		int temp; // holding variable

		while (flag) {
			flag = false; 
			for (j = 0; j < bubbleArray.length - 1; j++) {
				if (bubbleArray[j] > bubbleArray[j + 1]) 
				{
					//ex changed places of value
					temp = bubbleArray[j]; 
					bubbleArray[j] = bubbleArray[j + 1];
					bubbleArray[j + 1] = temp;
					flag = true; 
				}
			}
		}

	}

	public void printArray(int arr[]) {

		for (int i = 0; i < arr.length; i++) {

			System.out.print(arr[i] + " ");

		}

		System.out.println();
	}
}

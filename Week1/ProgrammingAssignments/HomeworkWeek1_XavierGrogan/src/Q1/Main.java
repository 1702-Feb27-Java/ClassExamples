package Q1;

public class Main 
{
	public static void main(String[] args)
	{
		int[] myArray = {1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4};
		bubbleSort(myArray);
		
		//the below for loop is for printing the elements of the array
		for(int i = 0; i < myArray.length; i++)
		{
			System.out.println(myArray[i]);
		}
	}
	
	//this method is the actual bubblesort method
	public static void bubbleSort(int[] array)
	{
		int x;
		boolean flag = true;
		int temp;
		
		//initial flag is true so it will pass, then is changed to false
		//if a swap is made, the flag will be set back to true
		//exits when there is no swap made
		while(flag)
		{
			flag = false;
			for(x = 0; x < array.length - 1; x++)
			{
				if(array[x] > array[x+1])
				{
					temp = array[x];
					array[x] = array[x+1];
					array[x+1] = temp;
					flag = true;
				}
			}
		}
	}
}

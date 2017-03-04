package Q12;

public class Main
{
	public static void main(String[] args)
	{
		int[] array = new int[100];
		
		//populates the array with 1-100
		for(int i = 1; i < 101; i++)
		{
			array[i - 1] = i;
		}
		
		//enhanced for loop to print out the even numbers
		for(int j : array)
		{
			if(j % 2 == 0)
			{
				System.out.println(j);
			}
		}
	}
}

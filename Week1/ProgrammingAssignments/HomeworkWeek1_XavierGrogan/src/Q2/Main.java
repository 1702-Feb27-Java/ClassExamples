package Q2;

public class Main 
{
	public static void main(String[] args) 
	{
		int start0 = 0;
		int start1 = 1;
		//calls the fibonacci method
		fibonacci(start0, start1);
	}
	
	//this method gets the Fibonacci numbers for printing based off of the
	//two seeded numbers provided
	public static void fibonacci(int a, int b)
	{
		int[] fibArr = new int[25];
		fibArr[0] = a;
		fibArr[1] = b;
		System.out.println(a);
		System.out.println(b);
		//this for loop will start figuring the numbers of the fibonacci sequence 
		//starting with the first two already given/seeded
		for(int i = 2; i < 25; i++)
		{
			fibArr[i] = fibArr[i-2] + fibArr[i-1];
			System.out.println(fibArr[i]);
		}
	}
}

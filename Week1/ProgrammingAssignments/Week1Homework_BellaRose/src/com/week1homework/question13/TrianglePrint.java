package com.week1homework.question13;

public class TrianglePrint
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		StringBuilder s1 = new StringBuilder();
		
		int prev = 0;
		
		for (int i = 0; i < 4; i++)
		{
			
			for (int j = 0; j < i + 1; j++)
			{
				
				if (i != 0)
				{
					if (prev == 0)
					{
						System.out.print(1);
						prev = 1;
					}
					else
					{
						System.out.print(0);
						prev = 0;
					}
						
				}
				else
				{
					System.out.print(0);
					prev = 0;
				}
				
				
			}
			
			System.out.println("\n");
		}

	}

}

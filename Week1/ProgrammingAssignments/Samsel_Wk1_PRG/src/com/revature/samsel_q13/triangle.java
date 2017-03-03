package com.revature.samsel_q13;

/*
 * Questions : Display the triangle on the console as follows using any type of loop without
 * print statements to accomplish it
 */
public class triangle {

	public static void main(String[] args) {
		
		int j=0;
		int a = 0;
		int b= 1;
		boolean bC = false;
		for(int i=1; i<=4;i++){		//No of rows
			j++;
			for(int k=1;k<=j;k++){	//No of columns vary based on jth element
				if(bC==false)		//Toggle the values to be printed 0-1, 
				{	
					System.out.print(" "+a);
					bC=true;
				}
				else
				{
					System.out.print(" "+b);
					bC=false;
				}
				
				if(k==j)
					System.out.println();
			}
		}
	}
}

package com.revature.samsel_q1;

public class BubbleSort {
	
	public void sortArray(int[] a) {
		
		int aLen = a.length;
		int temp=0;
		
		//iterate the given array
		for(int i=0; i<aLen; i++){
			for(int j=i+1;j<aLen-1;j++){
				//Swap if (j-1)th element is greater to (j)th element
				if(a[j-1]>a[j]){
					temp=a[j-1];
					a[j-1]=a[j];
					a[j] = temp;
				}			
			}
		}
				
	}
}

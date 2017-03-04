package Q13;

import java.util.ArrayList;

public class loop010 {
	static ArrayList<Integer> output = new ArrayList<>(5);
	//static int[] addOn =
	public static void main(String[] args) {
		output.add(0);
		output.add(1);
		output.add(0);
		output.add(1);
		output.add(0);
	//	System.out.println(output);
//		for (int i = 0; i<input.length; i++){
//			System.out.print(input[i] + "\n");
		
		
		runIt(output);
	}

	public static void runIt(ArrayList<Integer> output){
		int temp;
		ArrayList ali = new ArrayList();
		
		
		
		for (int i = 0; i<=4; i++){
//			System.out.println(output.get(i));
			temp = output.get(i);
			ali.add(output.get(i));
			System.out.println(ali);
//			if (i%2==0){
//				output.add(0);
			}
//			else {output.add(1);
				
			}
			
//		}
	//	System.out.println(output.get(i));

//	}
	
}

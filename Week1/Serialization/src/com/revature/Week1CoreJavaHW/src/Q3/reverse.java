
package Q3;
////////////////////////////////////////////////////||
//Goal: 											||
//1. create string									||
//2. set string in to an array						||
//3. view positions backward						||
////////////////////////////////////////////////////||



public class reverse {

	public static void main(String[] args) {
		//1: named string
		String forward = "Can I reverse this?";
		//using a char[] to store chars in an array
		char[] arr = forward.toCharArray();
		//using a loop goes through and displays backwards?
		//not 100% sure why this works 
		for(int i = arr.length - 1; i >= 0; i--){
			System.out.print(arr[i]);
		}
	}

}

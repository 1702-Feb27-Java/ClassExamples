package q6;

public class FindEven {

	public static void main(String[] args) {
		
		System.out.println(isEven(19));

	}
	
	/*
	 * determine if the given number is even
	 * 
	 *@param x the int to determin if if even
	 *@return true if even false if odd
	 */
	public static boolean isEven(int x){
		if ((x & 1) == 0){ // bit wise and checks if binary ones's place is 0, if it is it is even 
			return true;
		}
		
		return false;
	}

}

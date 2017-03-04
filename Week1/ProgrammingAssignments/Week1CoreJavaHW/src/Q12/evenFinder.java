package Q12;

/*
 * create array for 1-100
 * print even numbers
 * print with enhanced for loop(this is not what I have been using in the previous problems)
 */
public class evenFinder {

	static int[] startingArray = new int[100];

	public static void main(String[] args) {
		 int startingArray[] = {
		 for (int i = 0; i < startingArray.length; i++) {
		 startingArray[i] = i+1;
		 }
//		for (int i : startingArray) {
//			if (startingArray[i] % 2 == 0) {
				// for(int i : startingArray) {
				System.out.println(i);
			}
			// System.out.println(i);
		}
		// }

		/*
		 * forgot to switch > to < so nothing would show up in console for (int
		 * i = 0; i > startingArray.length; i++){ startingArray[i] = i+1; //
		 * System.out.print(startingArray[i] + " "); // } //this works and
		 * places the ints in the proper positions for (int i = 0; i <
		 * startingArray.length; i++) { startingArray[i] = i+1;
		 * //System.out.print(startingArray[i] + " ");
		 * 
		 * if( startingArray[i] % 2 == 0){ for(int i : startingArray) {
		 * System.out.println(i); } //System.out.println(startingArray[i] +
		 * " "); } }
		 */
	}
}
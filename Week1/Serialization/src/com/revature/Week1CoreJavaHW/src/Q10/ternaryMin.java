package Q10;

////////////////////////////////////////////////////////////
// 			  I don't really understand this			 //
//////////////////////////////////////////////////////////

public class ternaryMin {
	// generating 2 random numbers to compare
	static int randomA = 0 + (int) (Math.random() * 1000);
	static int randomB = 0 + (int) (Math.random() * 1000);
	// setting them to int

	public static void main(String[] args) {

		// setting first line to print
		System.out.print("The minimum of the given numbers would be: ");

		// comparing the 2 random generators and showing min value
		System.out.println((randomA < randomB) ? randomA : randomB);

		// print the original numbers
		System.out.println((randomA) + " " + (randomB));
		
//		System.out.println(true ? "this is true" : "this is false");
//		System.out.println(false ? "this is true" : "this is false");

	}
}

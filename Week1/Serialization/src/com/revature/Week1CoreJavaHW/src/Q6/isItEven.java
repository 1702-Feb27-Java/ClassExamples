package Q6;

public class isItEven {
	// (lower limit) + (data type)(Math.random() * upper limit);
	// static int min = Integer.MIN_VALUE;
	// static int max = Integer.MAX_VALUE;
	static int random = Integer.MIN_VALUE + (int) (Math.random() * Integer.MAX_VALUE);

	public static void main(String[] args) {
		// System.out.println(random);
		int newrandom = random;
		if (newrandom < 0) {
			newrandom = newrandom * -1;
		}

		// used to identify the original number whuch
		System.out.println("Your number is " + random + ".");

		double modulus = (double) newrandom;
		// System.out.println(modulus);

		while (modulus > 1) {
			modulus = modulus / 2;

			// System.out.println(modulus);
		}
		if ((modulus == 1) || (modulus == 0)) {
			System.out.println(random + " is an even number.");
		} else {
			if (modulus < 1)
				;
			System.out.println(random + " is an odd number.");
		}

	}
	/*
	 * int counter (int num) { int count = 0; // count how many divisions we've
	 * done while (num >= 1) { num = num / 2; count++; }
	 * 
	 * }
	 */

}

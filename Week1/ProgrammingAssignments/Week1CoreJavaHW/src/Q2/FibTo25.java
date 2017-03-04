package Q2;

public class FibTo25 {

	public static void main(String[] args) {
		// set staring value at 0
		int start = 0;
		System.out.println(start);
		// setting 2nd value to 1
		int second = 1;
		System.out.println(second);
		// now that the first 2 numbers have been set we can start the equation
		int nextStep = start + second;
		System.out.println(nextStep);

		// loop to work through the equation 22 time by incrementing i from 0 to 23
		// on 22 since we have already found the first 3 parts of the sequence
		for (int i = 0; i < 23; i++) {
			// moving the values we set above up through sequence
			start = second;
			second = nextStep;
			// finding next position in the sequence
			nextStep = start + second;

			// printing results every time it goes through the loop
			System.out.println(nextStep);
		}
	}
}

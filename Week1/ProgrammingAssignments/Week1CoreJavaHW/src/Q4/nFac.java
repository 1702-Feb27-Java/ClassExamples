package Q4;

/////////////////////////////////////////////////////////////
// int was giving me issues with larger values of n so i  //
// used long											 //
//////////////////////////////////////////////////////////

public class nFac {

	// set to static so that it can be used for the loop in the main
	static long n = 18;

	// variable representing n
	public static void main(String[] arg) {

		// set up a catch so that zero doesnt cause any issues
		if (n <= 1) {
			// print results for 0 and 1
			System.out.println(1);
		}
		// else statement begins if the number is greater than 1
		else {

			// vars that are setting up the equation out side of the loop
			// i did this to limit code in the loop (not really sure if i need
			// to)
			long step1 = 1;
			long step2 = 2;
			long temp = step1 * step2;
			// System.out.println(step3);
			// begin loop starting at 0, until i is less than n (from above)
			// and inc by 1
			for (long i = 0; i < n; i++) {

				// inc var from step2 to create the next number in the sequence
				step2++;
				// creating a temp var that is holding the result of the
				// previous step
				// and until next pass holds value
				temp = temp * step2;
				// prints results for each pass
				System.out.println(temp);

				/////////////////////////////////////////////////////////////
				// the following are the first tries at creating the loop //
				///////////////////////////////////////////////////////////
				/*
				 * int temp = step1 * step2; System.out.println(temp); step2++;
				 * System.out.println(step2); step1++;
				 * System.out.println(step1); int next = temp * step2;
				 * System.out.println(next);
				 */
				/////////////////////////////////////////////////////////////
				/*
				 * step1++; step2++;
				 * 
				 * 
				 * 
				 * int step4 = step3 * step2; int step5 = step4 * step3;
				 * System.out.println(step5);
				 */
				////////////////////////////////////////////////////////////
				// int tmp = step1 *step2;
				// System.out.println(tmp);

				// step1++;
				// step2++;
				/*
				 * step3 = step1 * step2; int temp = step3; int step4 = step3 *
				 * temp; System.out.println(step3);
				 */
				// System.out.println(step4);
				//////////////////////////////////////////////////////////
				// end of trials by loop //
				//////////////////////////////////////////////////////////
			}
		}
	}
}

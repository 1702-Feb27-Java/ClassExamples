package Q19;

import java.util.ArrayList;

public class assortedArrayList {
	// create an arrayList for 1-10
	// add all even numbers
	// add all odd number
	// remove prime numbers ====> print remaining

	static ArrayList<Integer> al = new ArrayList<>();
	static ArrayList<Integer> alPrime = new ArrayList<>();

	public static void main(String[] args) {

		al.add(1);
		al.add(2);
		al.add(3);
		al.add(4);
		al.add(5);
		al.add(6);
		al.add(7);
		al.add(8);
		al.add(9);
		al.add(10);

		int sum1 = 0;
		int sum2 = 0;
		for (int i = 0; i < al.size(); i++) {

			if (i % 2 == 0) {
				sum1 = sum1 + al.get(i);
				// int tempPos = 0;
				// int totalPos = tempPos + i;
				// tempPos = i;
			}

			if (i % 2 != 0) {
				sum2 = sum2 + al.get(i);
				// int tempOdd = 0;
				// int totalOdd = tempOdd + i;
				// tempOdd = i;
				//
				// }
				// System.out.println("The even numbers in thsi array are: ");
				// System.out.println();
			}
			
			if (i == 1){
				alPrime.add(1) ;

			}
			if (i == 2){
				alPrime.add(2) ;

			}
			else{
				boolean isPrime = true;

				for(int j = 2; j<al.get(i)  ; j++){
					
					if (i % j != 0 ){
						isPrime = false;
						break;
					}
					
				}
			if(isPrime){
				alPrime.add(i);
			}
				//	alPrime.add(al.get(i)) ;

			}
		}
		System.out.println("your total for positive numbers is currently " + sum1 + " ");
		System.out.println("your total for odd numbers is currently " + sum2 + " ");
		System.out.println(alPrime);
	}
}

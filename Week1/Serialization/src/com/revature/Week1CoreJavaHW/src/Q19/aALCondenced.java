package Q19;

import java.util.ArrayList;

public class aALCondenced {

	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<>();
		ArrayList<Integer> alPrime = new ArrayList<>();

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

		aALCondenced.evenNumbers(al);
		aALCondenced.oddNumbers(al);
		aALCondenced.primeNumbers(al, alPrime);
		System.out.println(alPrime);
	}

	public static void evenNumbers(ArrayList<Integer> al) {
		int sum1 = 0;

		for (int i = 0; i < al.size(); i++) {

			if (i % 2 == 0) {
				sum1 = sum1 + al.get(i);
			}
		}
		System.out.println("your total for positive numbers is currently " + sum1 + " ");

	}

	public static void oddNumbers(ArrayList<Integer> al) {
		int sum2 = 0;
		for (int i = 0; i < al.size(); i++)

			if (i % 2 != 0) {
				sum2 = sum2 + al.get(i);

			}
		System.out.println("your total for odd numbers is currently " + sum2 + " ");

	}

	public static void primeNumbers(ArrayList<Integer> al, ArrayList<Integer> alPrime) {

		for (int i = 0; i < al.size(); i++) {

			if (al.get(i) == 1) {
				alPrime.add(al.get(i));

			}
			else if (al.get(i) == 2) {
				alPrime.add(al.get(i));

			} else {
				boolean isPrime = true;

				for (int j = 2; j < i; j++) {

					if (al.get(i) % j == 0) {
						isPrime = false;
						break;
					}

				}
				if (isPrime) {
					alPrime.add(al.get(i));
				}

			}
		}
	}
}

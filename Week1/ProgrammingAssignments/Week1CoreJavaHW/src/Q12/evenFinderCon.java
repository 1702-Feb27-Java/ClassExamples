package Q12;

import java.util.ArrayList;

public class evenFinderCon {

	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<>();
		ArrayList<Integer> alPrime = new ArrayList<>();

		start(al);
		primeNumbers(al, alPrime);
		System.out.println(al);
		System.out.println(alPrime);

	}

	public static void start(ArrayList<Integer> al) {
		for (int i = 0; i < 101; i++) {
			al.add(i);

		}
	}

	public static void primeNumbers(ArrayList<Integer> al, ArrayList<Integer> alPrime) {

		for (int i = 0; i < al.size(); i++) {

			if (al.get(i) == 1) {
				alPrime.add(al.get(i));

			} else if (al.get(i) == 2) {
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

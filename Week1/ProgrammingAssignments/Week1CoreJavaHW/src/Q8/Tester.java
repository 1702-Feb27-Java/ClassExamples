package Q8;

import java.util.ArrayList;

public class Tester {
	public static void main(String[] args) {

		ArrayList<String> al = new ArrayList<String>();

		al.add("karan");
		al.add("madam");
		al.add("tom");
		al.add("civic");
		al.add("radar");
		al.add("sexes");
		al.add("jimmy");
		al.add("kayak");
		al.add("john");
		al.add("refer");
		al.add("billy");
		al.add("did");

		ArrayList<String> alPal = new ArrayList<String>();
		StringBuilder sb = null;
		for (int i = 0; i < al.size(); i++) {
			// StringBuilder sb = null;
			sb = new StringBuilder(al.get(i)).reverse();
			String s1 = sb.toString();
			alPal.add(s1);

			System.out.println(alPal.get(i));

		}
	}
}
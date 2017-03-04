package Q8;

import java.util.ArrayList;

public class Palindromes {
	
	public static void main(String[]args){

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
		
		palin(al);
		System.out.println("Starting list: " + al);
		
	}
	public static void palin(ArrayList<String> al){
		ArrayList<String> alPal = new ArrayList<String>();
		
		StringBuilder sb = null;
		for (int i = 0  ; i < al.size(); i++){
			sb = new StringBuilder(al.get(i)).reverse();
			String s1 = sb.toString();
			if( s1.compareTo(al.get(i)) == 0){
				alPal.add(s1);
				
			}

			
//			if (sb == al.get(index)){
//				alpal.
//			}
//			sb = new StringBuilder(alPal.get(i)).reverse();
//			String s1 = sb.toString();
//			alPal.add(s1);
			
			
//			StringBuilder sb = new StringBuilder();
//			System.out.println(al.get(i));
//			alPal.add(al.get(i));
		}
		System.out.println("Palindrome list: " + alPal);

	}

}


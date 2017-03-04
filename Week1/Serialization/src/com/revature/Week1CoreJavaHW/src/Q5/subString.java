package Q5;

public class subString {

	public static void main(String[] args) {

		String s1 = "HELLOWORLD";
		int start = 5;
		int end = 7;
		sub(s1, start, end);
	}
	public static void sub(String s1, int start, int end ){
		
		for (int i = start; i <= end  ;  i++) {
			System.out.print(s1.charAt(i));

			
		}		
	}

}

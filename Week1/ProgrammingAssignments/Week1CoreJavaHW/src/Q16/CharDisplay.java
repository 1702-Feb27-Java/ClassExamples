package Q16;

import java.util.Scanner;

public class CharDisplay {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Type something!");
		String usr = scanner.nextLine();
		//System.out.println(usr.Tokenizer.countTokens);
		System.out.println(usr.length());
	
//	while(usr.){
//	}
	}
}

	/*	String[] sar = {"this", "is", "my", "input"};
	System.out.println();
	NumStr(sar);
}
public static void NumStr (String[] args){
	
	int count = 0;
	
	for (int i =0; i< args.length; i++){
		StringTokenizer tkn = new StringTokenizer(args[i]);
		
		if(tkn.hasMoreTokens()){
			String s1 = tkn.nextToken();
			for (int j =0; j< s1.length(); j++){
				count = count + 1;
				System.out.println(s1.charAt(j));
		}
		
	}
}
	System.out.println(count);
}*/
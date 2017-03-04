package Q15;

public class Tester implements Interops{
	public static void main(String[] args){
		
		Tester o1 = new Tester();
		
		System.out.println("add 3 + 9: " + o1.add(3, 9) + "\n" + "subtract 7-3:" + o1.sub(7, 3) + "\n" + "mulitply 5*6: " + o1.multi(5, 6) + "\n" + "divide 3/2:" + o1.div(3, 2));
	}
	@Override
	public int add(int a, int b) {

		return a+b;
	}

	@Override
	public int sub(int a, int b) {
		
		return a-b;
	}

	@Override
	public int multi(int a, int b) {
		
		return a*b;
	}

	@Override
	public double div(int a, int b) {
		
		return a/b;
	}

	
	

	
}
	
	
	
	
	
	
	
	
	
	
	

/*	//public static void main(String[]args) {
public static int add(){
return;

//	return 2;
}//(2,6);
public static int sub;
public static int multi;
public static int div;
*/
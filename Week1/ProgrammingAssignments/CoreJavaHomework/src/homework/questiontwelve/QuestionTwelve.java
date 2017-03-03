//David Lund
package homework.questiontwelve;

public class QuestionTwelve {

	public static void main(String[] args) {
		
		int []arr = new int [100];
		for(int i = 1; i <=arr.length;i++)
		{
		//fill array
			arr[i-1]=i;
		}
		
		for(int x:arr)
		{//print if even
			
			if(x%2==0)
				System.out.println("Number : "+x+" is Even");
		}
	}

}

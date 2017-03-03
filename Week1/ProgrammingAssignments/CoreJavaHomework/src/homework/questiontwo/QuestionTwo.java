//David Lund
package homework.questiontwo;

public class QuestionTwo {
	public static void main(String[] args) {
		System.out.println("Question 2 \nfirst 25 Fibbonaci Numbers");
		QuestionTwo qt = new QuestionTwo();
		qt.fibbonaciTwentyFive();
	}

	private int firstNum = 0, secondNum = 1, result = 0;

	public void fibbonaciTwentyFive() {

		for (int i = 0; i <= 25; i++) {
//loop to print fib. 0-25.
			if (i == 0) {

				System.out.println(i + ".)" + firstNum);

				continue;

			} else if (i == 1) {
				System.out.println(i + ".)" + secondNum);

				continue;
			}
			result = firstNum + secondNum;

			System.out.println(i + ".)" + result);
			firstNum = secondNum;
			secondNum = result;
		}

	}

}

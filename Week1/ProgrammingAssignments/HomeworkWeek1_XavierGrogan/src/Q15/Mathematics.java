package Q15;

public class Mathematics implements MathematicsInterface
{

	@Override
	public int addition(int a, int b)
	{
		return a+b;
	}

	@Override
	public int subtraction(int a, int b)
	{
		return a-b;
	}

	@Override
	public int multiplication(int a, int b)
	{
		return a*b;
	}

	//the division method casts the ints to doubles for division
	@Override
	public double division(int a, int b)
	{
		double aa = a;
		double bb = b;
		return aa/bb;
	}

}

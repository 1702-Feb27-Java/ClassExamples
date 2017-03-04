package Q18;

public class WorkingClass extends AbstractClass
{
	//This method goes through the string, converting it to a charArray
	//then checks the current character with its uppercase version
	//if found, will then switch the flag to true
	@Override
	public Boolean isThereUpper(String str)
	{
		Boolean flag = false;
		char[] characters = new char[str.length()];
		characters = str.toCharArray();
		for(int i = 0; i < characters.length; i++)
		{
			char b = characters[i];
			b = Character.toUpperCase(b);
			if(characters[i] == b)
			{
				flag =  true;
			}
			else
				flag = flag;
		}
		return flag;
	}

	@Override
	public String allUpper(String str)
	{
		return str.toUpperCase();
	}

	//This one takes each character of the test string
	//converts it to a char, and then casts it to an int to be summed up
	//then adds 10 to that number and returns it
	@Override
	public int stringToInt(String str)
	{
		int result = 0;
		for(int i = 0; i < str.length(); i++)
		{
			char a = str.charAt(i);
			result += (int)a;
		}
		return result + 10;
	}
}

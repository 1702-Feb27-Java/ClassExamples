package Q18;

public class JustDoIt extends AbClas {

	@Override
	public boolean chkUp(String str) {

		boolean chk = false;
		char[] car = new char[str.length()];
		car = str.toCharArray();
		for (int i = 0; i < car.length; i++) {
			char tem = car[i];
			tem = Character.toUpperCase(tem);

			if (car[i] == tem) {
				chk = true;
			} else {
				chk = chk;
			}
		}

		return false;
	}

	@Override
	public String conLow(String str) {
		return str.toUpperCase();
	}

	@Override
	public int sToIn(String str) {
		return Integer.parseInt(str) + 10;
	}

}

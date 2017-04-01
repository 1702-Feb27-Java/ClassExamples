import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class testMain {

	public static void main(String[] args) {
		String datestring = "3/2/2001";
		Date newDate = null;
		try {
			newDate = new SimpleDateFormat("MM/dd/yyyy").parse(datestring);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(newDate);
	}
}

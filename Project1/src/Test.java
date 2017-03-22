import java.util.ArrayList;

import com.revature.dao.DAOImpl;
import com.revature.trms.Event;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//User u = new User("Michael", "Scott", "scott12","mike", "scottstots@yahoo.com", 3, 2, 1);
		
		ArrayList<Event> arrl = DAOImpl.getEventStats(1);
		
		for (Event e : arrl){
			System.out.println(e.getEventId());
		}
	}
}

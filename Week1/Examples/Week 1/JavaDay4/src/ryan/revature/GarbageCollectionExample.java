package ryan.revature;

public class GarbageCollectionExample {

	public static void main(String[] args) {
		int i = 0;
		
		for(i=0; i < 1000000; i++){
			GenericObject g = new GenericObject(i);
			//System.gc();
		}
		
		
	}

}

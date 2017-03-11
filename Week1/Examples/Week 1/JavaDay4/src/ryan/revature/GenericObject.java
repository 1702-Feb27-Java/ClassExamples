package ryan.revature;

public class GenericObject {
	private int id = 0;
	
	public GenericObject(int i){
		id = i;
		System.out.println(id + " CREATED");
	}
	
	@Override
	public void finalize(){
		System.out.println("\t\t" + id + " COLLECTED");
	}
}

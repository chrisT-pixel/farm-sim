package students.items;

public class UntilledSoil extends Item {

	public UntilledSoil(int age, int maturationAge, int deathAge, int monetaryValue) {
		super(age, maturationAge, deathAge, monetaryValue);
		
	}
	
	
	public UntilledSoil() {
		super.age = 0;
		super.maturationAge = -1; 
		super.deathAge = -1; 
		super.monetaryValue = -1;
		
	}

	
	@Override
	public String toString() {
		
		return "/";
		
		
	}

}

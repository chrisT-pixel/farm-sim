package students.items;

public class Soil extends Item{
	
	public Soil(int age, int maturationAge, int deathAge, int monetaryValue) {
		super(age, maturationAge, deathAge, monetaryValue);
		
	}
	
	
	public Soil() {
		super.age = 0;
		super.maturationAge = -1; 
		super.deathAge = -1; 
		super.monetaryValue = 0;
		
	}

	
	@Override
	public String toString() {
		
		return ".";
		
		
	}

}

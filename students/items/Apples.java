package students.items;

public class Apples extends Food{
	
	public static int instantiateCount = 0;

	
	
	public Apples(int age, int maturationAge, int deathAge, int monetaryValue, int costToBuy, boolean isHarvested) {
		super(age, maturationAge, deathAge, monetaryValue, costToBuy, isHarvested);
		
	}
	
	
	public Apples() {
		super.age = 0;
		super.maturationAge = 3;
		super.deathAge = 5;
		super.monetaryValue = 3;
		super.costToBuy = 2;
		
		
	}
	
	
	public static int getGenerationCount() {
		return instantiateCount;
	}


	@Override
	public String toString() {
		
		if(this.getAge() < this.getMaturationAge()) {
			return "a";
		}
		
		else{
			return "A";
		}
		
		
	}
	
	
	
}

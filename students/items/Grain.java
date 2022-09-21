package students.items;

public class Grain extends Food{
	
	public static int instantiateCount = 0;
	
	
	public Grain(int age, int maturationAge, int deathAge, int monetaryValue, int costToBuy, boolean isHarvested) {
		super(age, maturationAge, deathAge, monetaryValue, costToBuy, isHarvested);
		
	}
	
	
	public Grain() {
		super.age = 0;
		super.maturationAge = 2;
		super.deathAge = 6;
		super.monetaryValue = 2;
		super.costToBuy = 1;
		
	}
	
	public static int getGenerationCount() {
		return instantiateCount;
	}

	
	@Override
	public String toString() {
		
		if(this.getAge() < this.getMaturationAge()) {
			return "g";
		}
		
		else{
			return "G";
		}
		
		
	}

	
}

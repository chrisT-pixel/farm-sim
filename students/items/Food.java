package students.items;

public abstract class Food extends Item{
	
	protected int costToBuy;
	protected boolean isHarvested;
	

	public Food(int age, int maturationAge, int deathAge, int monetaryValue, int costToBuy, boolean isHarvested) {
		super(age, maturationAge, deathAge, monetaryValue);
		this.costToBuy = costToBuy;
		this.isHarvested = isHarvested;
		
	}
	
	public Food() {
		super.age = 0;
		super.maturationAge = 0;
		super.deathAge = 0;
		super.monetaryValue = 0;
		this.costToBuy = 0;
		this.isHarvested = false;
		
	}
	
	


	public int getCostToBuy() {
		return costToBuy;
	}

	public void setCostToBuy(int costToBuy) {
		this.costToBuy = costToBuy;
	}

	public boolean isHarvested() {
		return isHarvested;
	}

	public void setHarvested(boolean isHarvested) {
		this.isHarvested = isHarvested;
	}
	
	

	

}

package students.items;

import java.util.Objects;

public abstract class Item implements Cloneable{
	
	protected int age;
	protected int maturationAge;
	protected int deathAge;
	protected int monetaryValue;
	
	
	public Item(int age, int maturationAge, int deathAge, int monetaryValue) {
		this.age = 0;
		this.maturationAge = maturationAge;
		this.deathAge = deathAge;
		this.monetaryValue = monetaryValue;
	}
	

	
	public Item() {
		this.age = 0;
		this.maturationAge = 0;
		this.deathAge = 0;
		this.monetaryValue = 0;
	}

	public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }


	public int getMaturationAge() {
		return maturationAge;
	}


	public void setMaturationAge(int maturationAge) {
		this.maturationAge = maturationAge;
	}


	public int getDeathAge() {
		return deathAge;
	}


	public void setDeathAge(int deathAge) {
		this.deathAge = deathAge;
	}



	public int getMonetaryValue() {
		return monetaryValue;
	}



	public void setMonetaryValue(int monetaryValue) {
		this.monetaryValue = monetaryValue;
	}


	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	
	@Override
	public abstract String toString();
	
	public void tick() {
		int age = this.getAge();
		this.setAge(age + 1);
		
	}

	public boolean died() {
		
		//food items can die
		if(this instanceof Food) {
		
			if(this.getAge() > this.getDeathAge()) {
				
				return true;
			}
			else {
				return false;
			}
		}
		
		//non food items cannot die
		else {
			return false;
		}
	}
	
	public int getValue() {
		
		//food items have value
		if(this instanceof Food) {
		
			if(this.getAge() < this.getMaturationAge()) {
				return 0;
			}
			
			else{
				return this.getMonetaryValue();
			}
		}
		
		//weeds & untilled soil have no impact on field value and have infinite maturation
		else if(this instanceof Weed || this instanceof UntilledSoil) {
			return 0;
		}
		
		//soil has value of 0 and has infinite maturation
		else {
			return 0;
		}
	
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, deathAge, maturationAge, monetaryValue);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return age == other.age && deathAge == other.deathAge && maturationAge == other.maturationAge
				&& monetaryValue == other.monetaryValue;
	}



	
	
	
}

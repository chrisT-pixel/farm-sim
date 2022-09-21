package students;

import java.util.Scanner;

import students.items.Apples;
import students.items.Food;
import students.items.Grain;
import students.items.Item;
import students.items.UntilledSoil;

public class Farm {
	
	Field gameField;
	int funds;
	int difficultyLevel; // new feature defines probability of a weed each till()
	
	public Farm(int fieldWidth, int fieldHeight, int funds, int difficultyLevel){
		
		gameField = new Field(fieldWidth, fieldHeight, difficultyLevel);
		this.funds = funds; // new feature - user can decide on starting funds
	
	}
	
	
	public int getFunds() {
		return funds;
	}

	
	public void setFunds(int funds) {
		this.funds = funds;
	}


	public void run(){
		
		String actionText = "Enter your next action \n";
		actionText += "t x y: till \n";
		actionText += "h x y: harvest \n";
		actionText += "p x y: plant \n";
		actionText += "s: field summary \n";
		actionText += "k: Kill random weeds \n";
		actionText += "w: wait \n";
		actionText += "q: quit";
		
		do {
			
			Scanner scanner = new Scanner(System.in).useDelimiter("\\s");
			System.out.println(gameField); //display the string representation of the field to the user
			System.out.println("Bank Balance $" + this.getFunds());
			System.out.println(actionText);
			
			String firstChar = scanner.next();
			
			if(firstChar.equals("q")) {
				System.out.println("Thanks for Playing!");
				break;
			}
			
			else if(firstChar.equals("t")) { // user wants to till()
				
				boolean validationFlag = false;
				int secondChar = 0;
				int thirdChar = 0;
				
				if(scanner.hasNextInt()) {
					secondChar = scanner.nextInt();
					
					if(secondChar > gameField.getWidth() || secondChar < 1) {
						validationFlag = true; // user selection is out of bounds for array
					}
				}
				
				else {
					validationFlag = true;
				}
				
				if(scanner.hasNextInt()) {
					thirdChar = scanner.nextInt();
					
					if(thirdChar > gameField.getHeight() || thirdChar < 1) {
						validationFlag = true; // user selection is out of bounds for array
					}
				}
				
				else {
					validationFlag = true;
				}
				
				if(!validationFlag) {
					gameField.till(thirdChar - 1, secondChar - 1);
					gameField.tick();
				}
				
				else {
					System.out.print("invalid input - please try again \n");
					continue;
				}
				
				
				
			} //end till()
			
			else if(firstChar.equals("h")) { // user wants to harvest()
				
				boolean validationFlag = false;
				int secondChar = 0;
				int thirdChar = 0;
				
				if(scanner.hasNextInt()) {
					secondChar = scanner.nextInt();
					
					if(secondChar > gameField.getWidth() || secondChar < 1) {
						validationFlag = true; // user selection is out of bounds for array
					}
				}
				
				else {
					validationFlag = true;
				}
				
				if(scanner.hasNextInt()) {
					thirdChar = scanner.nextInt();
					
					if(thirdChar > gameField.getHeight() || thirdChar < 1) {
						validationFlag = true; // user selection is out of bounds for array
					}
				}
				
				else {
					validationFlag = true;
				}
				
				if(!validationFlag) {
					
					Item itemToHarvest = null;
					int val;
					
					try {
						itemToHarvest = gameField.get(thirdChar - 1, secondChar - 1);
					} 
					
					catch (Exception e) {
						
						e.printStackTrace(); //issue creating clone()
					} 
					
					finally {
						val = itemToHarvest.getValue();
						this.funds += val;
						gameField.tick();
						
						if(itemToHarvest instanceof Food) {
						
							UntilledSoil u = new UntilledSoil(); //plant untilled soil after harvest weather or not user has earned funds 
							gameField.plant(thirdChar - 1, secondChar - 1, u);
							
						}
						
						else {
							System.out.print("Harvesting this has no impact \n");
						}
					}
				}
				
				else {
					System.out.print("invalid input - please try again \n");
					continue;
				}
				
				
			} // end harvest()
			
			else if(firstChar.equals("p")) { // user wants to plant()
				
				boolean validationFlag = false;
				int secondChar = 0;
				int thirdChar = 0;
				
				if(scanner.hasNextInt()) {
					secondChar = scanner.nextInt();
					
					if(secondChar > gameField.getWidth() || secondChar < 1) {
						validationFlag = true; // user selection is out of bounds for array
					}
				}
				
				else {
					validationFlag = true;
				}
				
				if(scanner.hasNextInt()) {
					thirdChar = scanner.nextInt();
					
					if(thirdChar > gameField.getHeight() || thirdChar < 1) {
						validationFlag = true; // user selection is out of bounds for array
					}
				}
				
				else {
					validationFlag = true;
				}
				
				if(!validationFlag) {
					System.out.println("Enter \n - 'a' to buy an apple for $2 \n - 'g' to buy grain for $1");
					String plantChoice = scanner.next();
					
					if(plantChoice.equals("a")) {
						Apples a = new Apples();
						int cost = a.getCostToBuy();
						
						if(cost > this.getFunds()) {
							System.out.print("You do not have enough money to plant this apple! \n");
						}
						
						else {
							
							this.funds -= cost;
							gameField.plant(thirdChar - 1, secondChar - 1, a);
							
						}
						
						
					}
					
					else if(plantChoice.equals("g")) {
						Grain g = new Grain();
						int cost = g.getCostToBuy();
						
						if(cost > this.getFunds()) {
							System.out.print("You do not have enough money to plant this grain! \n");
						}
						
						else {
							
							this.funds -= cost;
							gameField.plant(thirdChar - 1, secondChar - 1, g);
							
						}
						
						
					}
					
					gameField.tick();
				}
				
				else {
					System.out.print("invalid input - please try again \n");
					continue;
				}
				
				
			} // end plant()
			
			else if(firstChar.equals("s")) { // user wants a summary()
				
				System.out.print(gameField.getSummary());
			
			} // end summary()
			
			else if(firstChar.equals("w")) { // user wants only to tick()
				
				gameField.tick();
			
			} // end tick()
			
			
			else if(firstChar.equals("k")) { // user wants to sprayPesticide()
				
				
					gameField.tickPesticide();
					gameField.tick();
			}
				
				
				
			else {
				System.out.print("invalid input - please try again \n");
			}
			
			
			
		} while(true);
		
		
		
		
	}
	
	
	
}

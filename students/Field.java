package students;

import java.util.Random;
import students.items.*;


public class Field {
	
	private int height;
	private int width;
	private Integer difficultyLevel;
	
	Item[][] gameBoard;
	
	public Field(int width, int height, Integer difficultyLevel){
		
		this.width = width;
		this.height = height;
		this.difficultyLevel = difficultyLevel;
		gameBoard = new Item[this.height][this.width];
		
		for(int x = 0; x < this.height; x++) {
			
			for(int y = 0; y < this.width; y++) {
				this.gameBoard[x][y] = new Soil();
			}
			
		}
		
	}
	
	
	
	public int getHeight() {
		return height;
	}



	public void setHeight(int height) {
		this.height = height;
	}



	public int getWidth() {
		return width;
	}



	public void setWidth(int width) {
		this.width = width;
	}
	
	


	public Integer getDifficultyLevel() {
		return difficultyLevel;
	}



	public void setDifficultyLevel(int difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}



	//@Override
	public String toString() {
		
		String output = "  ";
		
		for(int x = 0; x < this.getWidth(); x++) {
			
			output += x + 1;
		}
		
		output += "\n";

		for(int x = 0; x < this.getHeight(); x++) {
			
			output += (x + 1) + " ";
			
			for(int y = 0; y < this.getWidth(); y++) {
				
				output += this.gameBoard[x][y];
				
			}
			
			output += "\n";
			
		}
		
		return output;
		
	}
	
	public Item get(int posX, int posY) throws CloneNotSupportedException {
		return (Item) gameBoard[posX][posY].clone(); //return copy of item in game board location
	}
	
	public int getValue() {
		
		int totalValue = 0;
		
		for(int x = 0; x < this.getHeight(); x++) {
			
			for(int y = 0; y < this.getWidth(); y++) {
				
				totalValue += this.gameBoard[x][y].getValue(); //add current item at game board location to total
				
			}
			
		}
		
		return totalValue;
		
	}
	
	
	public void plant(int posX, int posY, Item item) {
		
		if(this.gameBoard[posX][posY] instanceof Soil) { // can plant in soil
		
			this.gameBoard[posX][posY] = item;
			
			if(item instanceof Grain) { // only increase getGenerationCount() value on successful grain plant
				Grain.instantiateCount++;
			}
			
			if(item instanceof Apples) { // only increase getGenerationCount() value on successful apple plant
				Apples.instantiateCount++;
			}
			
			
		}
		
		else if(this.gameBoard[posX][posY] instanceof Apples && this.gameBoard[posX][posY].died()) { // need to plant untilled soil once an apple dies
			
			this.gameBoard[posX][posY] = item;
		}
		
		else if(this.gameBoard[posX][posY] instanceof Grain && this.gameBoard[posX][posY].died()) { // need to plant untilled soil once grain dies 
			
			this.gameBoard[posX][posY] = item;
		}
		
		else if(this.gameBoard[posX][posY] instanceof Food) { //never executed if above conditions are already met
			
			
			this.gameBoard[posX][posY] = item; // catches all harvests of food (mature or not)
			
		}
		
		
		else {
			System.out.print("Cannot plant there - not soil \n");
		}
			
	}
	
	public void sprayPesticide(int posX, int posY, UntilledSoil u) { //planting untilled soil over a weed
		
		if(this.gameBoard[posX][posY] instanceof Weed) {
			
			this.gameBoard[posX][posY] = u;
			
		}
		
	}
	
	public void till(int posX, int posY) { //tilling creates new soil
		
		
		this.gameBoard[posX][posY] = new Soil();
		
	}
	
	public void tick() {
		
		Random rand = new Random();
		
		for(int x = 0; x < this.getHeight(); x++) {
			
			for(int y = 0; y < this.getWidth(); y++) {
				
				this.gameBoard[x][y].tick();
				Item currentItem = this.gameBoard[x][y];
				
				if(currentItem instanceof Soil) {
					
					int randomNum = rand.nextInt((10 - 1) + 1) + 1;
					Integer probability = 0;
					
					if(this.getDifficultyLevel().equals(1)) {
						probability = 1; //game difficulty level 1 - lowest probability for a weed 
					}
					
					else if(this.getDifficultyLevel().equals(2)) {
						probability = 2; //game difficulty level 2 - middle probability for a weed 
					}
					
					else {
						probability = 3; //game difficulty level 2 - high probability for a weed 
					}
					
					if(randomNum <= probability) {
						
						Weed weed = new Weed();
						plant(x,y,weed);
						
					}
				}
				
				if(currentItem.died()) {
					
					UntilledSoil untilledsoil = new UntilledSoil();
					plant(x,y,untilledsoil);
				}
			}
		}
	}
	
	public void  tickPesticide() { // randomly replace weeds with untilled soil without burning a turn 
		
		Random rand = new Random();
		
		for(int x = 0; x < this.getHeight(); x++) {
			
			for(int y = 0; y < this.getWidth(); y++) {
				
				Item currentItem = this.gameBoard[x][y];
				
				if(currentItem instanceof Weed) {
					
					int randomNum = rand.nextInt((10 - 1) + 1) + 1;
					Integer probability = 1;
				
					if(randomNum <= probability) {
						
						UntilledSoil untilledSoil = new UntilledSoil();
						sprayPesticide(x,y,untilledSoil);
						
					}
				}
				
			}
		}
		
		
	}
	
	public String getSummary() {
		
		int appleCount = 0;
		int grainCount = 0;
		int soilCount = 0;
		int untilledCount = 0;
		int weedCount = 0;
		String output = "";
		
		
		for(int x = 0; x < this.getHeight(); x++) {
			
			for(int y = 0; y < this.getWidth(); y++) {
				
				Item currentItem = this.gameBoard[x][y];
				
				if(currentItem instanceof Apples) {
					
					appleCount++;
				
				}
				
				else if(currentItem instanceof Grain) {
					
					grainCount++;
				
				}
				
				else if(currentItem instanceof Soil) {
					
					soilCount++;
				
				}
				
				else if(currentItem instanceof UntilledSoil) {
					
					untilledCount++;
				
				}
				
				else {
					weedCount++;
				}
				
				
			}
		
		}
		
		output += "\n";
		output += "Apples:" + "\t \t" + appleCount + "\n";
		output += "Grain:" + "\t \t" + grainCount + "\n";
		output += "Soil:" + "\t \t" + soilCount + "\n";
		output += "Untilled:" + "\t" + untilledCount + "\n";
		output += "Weed:" + "\t \t" + weedCount + "\n";
		output += "For a total of" + "\t" + "$" + this.getValue() + "\n";
		output += "Total apples created: " + Apples.getGenerationCount() + "\n";
		output += "Total grain created: " + Grain.getGenerationCount() + "\n \n";
		
		return output;
		
		
		
	}
	


} //close Field class

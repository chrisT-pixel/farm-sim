package marking;

import java.util.Scanner;

import students.Farm;


public class Marker {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		int width;
		int height;
		int money;
		int difficulty;
		
		do {
			
			System.out.println("Enter the farm width. Minimum 1, Maximum 10");
			width = input.nextInt();
			
		} while(width < 1 || width > 10);
		
		do {
			
			System.out.println("Enter the farm height. Minimum 1, Maximum 10");
			height = input.nextInt();
			
		} while(height < 1 || height > 10);
		
		do {
			
			System.out.println("Enter your starting balance in $.  Minimum 5 (hard), Maximum 100 (easy)");
			money = input.nextInt();
			
		} while(money < 5 || money > 100);
		
		do {
			
			System.out.println("Enter your diffculty level for weeds created per turn. \n 1: easy \n 2: average \n 3: hard");
			difficulty = input.nextInt();
			
		} while(difficulty < 1 || difficulty > 3);
		
		
		Farm game = new Farm(width, height, money, difficulty);
		game.run();
		input.close();
		
	}

}

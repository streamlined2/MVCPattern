package org.exercise.pattern.mvc;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Random;
import java.util.Scanner;

public class Controller {
	
	private final Model model;
	private final View view;
	private final Input input;
	
	private static final int RAND_MIN=0;
	private static final int RAND_MAX=100;
	
	private static int rand(final int min,final int max) {
		return new Random().nextInt(max-min)+min;
	}
	
	private static int rand() {
		return rand(RAND_MIN,RAND_MAX);
	}

	private class Input {
		
		private final Scanner scanner;
		
		private Input(final Reader reader) {
			this.scanner=new Scanner(reader);
		}
		
		private int readInput() {
			return scanner.nextInt();
		}
	
	}
	
	public int readInput() {
		return input.readInput();
	}

	public Controller(final Model model, final View view) {
		this.model=model;
		this.view=view;
		input=new Input(new InputStreamReader(System.in));
		model.add(new Model.Range(RAND_MIN,RAND_MAX));
	}
	
	public void run() {
		final int seed=rand();
		
		do {
			view.prompt("Please enter number within range (%d,%d): ", 
					model.lastRange().lower(), model.lastRange().higher());
			int choice;
			boolean correctChoice;
			do {
				choice=input.readInput();
				correctChoice=model.lastRange().within(choice);
				if(!correctChoice) {
					view.prompt("You entered wrong guess %d.%nPlease try again%n", choice);
				}
			}while(!correctChoice);

			if(choice==seed) {
				view.prompt("You won by guessing %d!%n",choice);
				view.publishStatistics();
				view.publishHistory();
				break;
			}else {
				model.splitLastRange(choice, seed);
				view.publishChoice(choice);
			}
			
		}while(true);
		
	}

}

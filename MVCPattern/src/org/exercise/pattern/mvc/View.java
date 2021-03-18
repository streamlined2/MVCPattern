package org.exercise.pattern.mvc;

import java.io.PrintWriter;

public class View {
	
	private final Model model;
	private final PrintWriter out;

	public View(final Model model) {
		this(model,new PrintWriter(System.out,true));
	}
	
	public View(final Model model,final PrintWriter out) {
		this.model=model;
		this.out=out;
	}
	
	public void publishHistory() {
		out.println("History");
		out.println("---------");
		for(final var range:model) {
			out.println(range);
		}
	}
	
	public void publishChoice(final int choice) {
		out.printf("You selected %d%n",choice);
	}
	
	public void publishStatistics() {
		out.printf("You entered %d guesses%n",model.getSize());
	}
	
	public void prompt(final String prompt,final Object...args) {
		out.printf(prompt, args);
	}

}

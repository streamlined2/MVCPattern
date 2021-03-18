package org.exercise.pattern.mvc;

public class Runner {

	public static void main(String[] args) {
		Model model=new Model();
		View view=new View(model);
		Controller controller=new Controller(model,view);
		controller.run();
	}

}

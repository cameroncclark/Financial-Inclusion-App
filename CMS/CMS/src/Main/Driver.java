package Main;

import Controller.ActionController;
import Model.Model;
import View.MainContainer;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Create Model
		Model model = new Model();
		//Create our ActionListener
		ActionController actionController = new ActionController(model);
		//Create MainContainer
		MainContainer mainContainer = new MainContainer(model,actionController);
		//Setting view to controller
		actionController.setMainContainer(mainContainer);
	}

}

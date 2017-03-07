package Controller;

import Model.Model;
import View.MainContainer;
import View.NumbersPanel;

public class AddNumberCtrl {
	public AddNumberCtrl(Model model, MainContainer view) {
		NumbersPanel panel = (NumbersPanel) view.getActivePanel();
		if(!panel.getAddTitle().equals("") && !panel.getAddBlurb().equals("") && !panel.getAddNumber().equals("")){
			model.addNumber(panel.getAddTitle(), panel.getAddBlurb(), panel.getAddNumber());
			panel.setAddTitle("");
			panel.setAddBlurb("");
			panel.setAddNumber("");
		}
		
	}
}
package Controller;

import Model.Model;
import View.MainContainer;
import View.NumbersPanel;

public class AddNumberCtrl {
	public AddNumberCtrl(Model model, MainContainer view) {
		NumbersPanel panel = (NumbersPanel) view.getActivePanel();
	}
}

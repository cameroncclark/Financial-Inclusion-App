package Controller;

import Model.Model;
import View.MainContainer;
import View.NumbersPanel;

public class EditNumberCtrl {
	public EditNumberCtrl(Model model, MainContainer view) {
		NumbersPanel panel = (NumbersPanel) view.getActivePanel();
	}
}

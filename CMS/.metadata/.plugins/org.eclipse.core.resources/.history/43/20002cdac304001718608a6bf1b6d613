package Controller;

import Model.Model;
import View.MainContainer;
import View.NumbersPanel;

public class EditNumberCtrl {
	public EditNumberCtrl(Model model, MainContainer view) {
		NumbersPanel panel = (NumbersPanel) view.getActivePanel();
		System.out.println("I've been called");
		model.editNumber(panel.getSelectedEdit(), panel.getEditTitle(), panel.getEditBlurb(), panel.getEditNumber());
	}
}
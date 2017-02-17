package Controller;

import Model.Model;
import View.MainContainer;
import View.NumbersPanel;

public class DeleteNumberCtrl {
	public DeleteNumberCtrl(Model model, MainContainer view) {
		NumbersPanel panel = (NumbersPanel) view.getActivePanel();
		model.deleteNumber(panel.getSelectedDelete());
	}
}

package Controller;

import Model.Model;
import View.MainContainer;
import View.TipsPanel;

public class EditTipCtrl {
	public EditTipCtrl(Model model, MainContainer view) {
		TipsPanel panel = (TipsPanel) view.getActivePanel();
	}
}

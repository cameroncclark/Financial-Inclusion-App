package Controller;

import Model.Model;
import View.MainContainer;
import View.TipsPanel;

public class AddTipCtrl {
	public AddTipCtrl(Model model, MainContainer view) {
		TipsPanel panel = (TipsPanel) view.getActivePanel();
		if (!panel.getAddTipHeader().equals("") && !panel.getAddTipTip().equals("")) {
			model.addTip(panel.getAddTipHeader(), panel.getAddTipTip());
			panel.setAddTipHeader("");
			panel.setAddTipTip("");
		}
	}
}

package Controller;

import Model.Model;
import View.MainContainer;
import View.TipsPanel;

public class DeleteTipCtrl {
	public DeleteTipCtrl(Model model, MainContainer view) {
		TipsPanel panel = (TipsPanel) view.getActivePanel();
		model.deleteTip(panel.getSelectedDelete());
	}
}

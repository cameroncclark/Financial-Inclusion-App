package Controller;

import Model.Model;
import View.MainContainer;
import View.TipsPanel;

public class EditTipSelect {
	public EditTipSelect(Model model, MainContainer view) {
		TipsPanel panel = (TipsPanel) view.getActivePanel();
		
		panel.setEditTipHeader(model.selectTip(panel.getSelectedEdit()).getHeader());
		panel.setEditTipTip(model.selectTip(panel.getSelectedEdit()).getTip());
	}
}

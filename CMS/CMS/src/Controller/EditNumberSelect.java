package Controller;

import Model.Model;
import View.MainContainer;
import View.NumbersPanel;

public class EditNumberSelect {
	public EditNumberSelect(Model model, MainContainer view) {
		NumbersPanel panel = (NumbersPanel) view.getActivePanel();

		panel.setEditTitle(model.selectNumber(panel.getSelectedEdit()).getName());
		panel.setEditBlurb(model.selectNumber(panel.getSelectedEdit()).getBlurb());
		panel.setEditNumber(model.selectNumber(panel.getSelectedEdit()).getNumber());
	}
}

package Controller;

import Model.Model;
import View.AddContentPane;
import View.ContentPanel;
import View.MainContainer;

public class AddContentCtrl {
	public AddContentCtrl(Model model, MainContainer view) {
		ContentPanel panel = (ContentPanel) view.getActivePanel();
		AddContentPane activePanel = (AddContentPane) panel.getActivePanel();
		model.addContentFile(activePanel.getTopicTitle(), model.selectCategoryId(activePanel.getSelectedCategory()), activePanel.getContent());
		activePanel.setVisible(false);
		activePanel.dispose();
		model.clearActiveFile();
	}

}
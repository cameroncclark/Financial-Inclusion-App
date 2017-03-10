package Controller;

import Model.Model;
import View.ContentPanel;
import View.EditContentPane;
import View.MainContainer;

public class EditContentCtrl {
	public EditContentCtrl(Model model, MainContainer view) {
		ContentPanel panel = (ContentPanel) view.getActivePanel();
		EditContentPane activePanel = (EditContentPane) panel.getActivePanel();
		model.editTopic(activePanel.getTopicTitle(), activePanel.getContentText(), activePanel.getSelectedCategory());
		activePanel.setVisible(false);
		activePanel.dispose();
		model.clearActiveFile();
	}
}

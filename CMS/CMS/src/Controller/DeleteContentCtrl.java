package Controller;

import Model.Model;
import View.ContentPanel;
import View.DeleteContentPane;
import View.MainContainer;

public class DeleteContentCtrl {
	public DeleteContentCtrl(Model model, MainContainer view) {
		ContentPanel panel = (ContentPanel) view.getActivePanel();
		DeleteContentPane activePanel = (DeleteContentPane) panel.getActivePanel();
		activePanel.setVisible(false);
		activePanel.dispose();
		model.deleteTopic();
		model.clearActiveFile();
	}

}
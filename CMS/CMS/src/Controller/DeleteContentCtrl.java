package Controller;

import javax.swing.JOptionPane;

import Model.Model;
import View.ContentPanel;
import View.DeleteContentPane;
import View.MainContainer;

public class DeleteContentCtrl {
	public DeleteContentCtrl(Model model, MainContainer view) {
		ContentPanel panel = (ContentPanel) view.getActivePanel();
		DeleteContentPane activePanel = (DeleteContentPane) panel.getActivePanel();
		if (!model.isFileLoaded()) {
			JOptionPane.showMessageDialog(null, "No file has been loaded, therefore a topic cannot be deleted.",
					"No topic loaded", JOptionPane.ERROR_MESSAGE);
		} else {
			int sure = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this topic?",
					"Are You Sure?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (sure == JOptionPane.YES_OPTION) {
				activePanel.setVisible(false);
				activePanel.dispose();
				model.deleteTopic();
				JOptionPane.showMessageDialog(null, "Topic has been successfully deleted.", "Topic deleted",
						JOptionPane.INFORMATION_MESSAGE);
				model.clearActiveFile();
				model.closeContentPane();
			}
		}
	}

}

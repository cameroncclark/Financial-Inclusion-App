package Controller;

import javax.swing.JOptionPane;

import Model.Model;
import View.ContentPanel;
import View.EditContentPane;
import View.MainContainer;

public class EditContentCtrl {
	public EditContentCtrl(Model model, MainContainer view) {
		ContentPanel panel = (ContentPanel) view.getActivePanel();
		EditContentPane activePanel = (EditContentPane) panel.getActivePanel();
		String title = activePanel.getTopicTitle();
		if(!model.isFileLoaded()){
			JOptionPane.showMessageDialog(null, "No file has been loaded, therefore a topic cannot be editted.", "No topic loaded",
					JOptionPane.ERROR_MESSAGE);
		}else if (activePanel.getTopicTitle().replaceAll("\\s+", "").equals("")) {
			JOptionPane.showMessageDialog(null, "Topic titles cannot be empty.", "Blank field",
					JOptionPane.ERROR_MESSAGE);
		} else if (activePanel.getTopicTitle().length() > 70) {
			JOptionPane.showMessageDialog(null, "Topic titles cannot exceed a length of 70 characters.",
					"Topic length title too long", JOptionPane.ERROR_MESSAGE);
		} else if (activePanel.getContentText().replaceAll("\\s+", "").equals("")) {
			JOptionPane.showMessageDialog(null, "Content cannot be empty.", "Blank field", JOptionPane.ERROR_MESSAGE);
		} else if (!activePanel.getContentText().contains("<section>")
				|| !activePanel.getContentText().contains("</section>")
				|| !activePanel.getContentText().contains("<section-title>")
				|| !activePanel.getContentText().contains("</section-title>")) {
			JOptionPane.showMessageDialog(null, "Content must contain at least one section, and one section title.",
					"No section's", JOptionPane.ERROR_MESSAGE);
		} else if (!model.isQuizActive()) {
			JOptionPane.showMessageDialog(null, "You must add a quiz before you complete a topic.", "No quiz",
					JOptionPane.ERROR_MESSAGE);
		} else {
			model.editTopic(activePanel.getTopicTitle(), activePanel.getContentText(),
					activePanel.getSelectedCategory());
			activePanel.setVisible(false);
			activePanel.dispose();
			model.clearActiveFile();
			JOptionPane.showMessageDialog(null, "Topic '" + title + "' has been edited successfully.", "Topic edited",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}

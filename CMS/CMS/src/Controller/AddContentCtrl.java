package Controller;

import javax.swing.JOptionPane;

import Model.Model;
import View.AddContentPane;
import View.ContentPanel;
import View.MainContainer;

public class AddContentCtrl {
	public AddContentCtrl(Model model, MainContainer view) {
		ContentPanel panel = (ContentPanel) view.getActivePanel();
		AddContentPane activePanel = (AddContentPane) panel.getActivePanel();
		String title = activePanel.getTopicTitle();
		if (activePanel.getTopicTitle().replaceAll("\\s+", "").equals("")) {
			JOptionPane.showMessageDialog(null, "Topic titles cannot be empty.", "Blank field",
					JOptionPane.ERROR_MESSAGE);
		} else if (activePanel.getTopicTitle().length() > 70){
			JOptionPane.showMessageDialog(null, "Topic titles cannot exceed a length of 70 characters.", "Topic length title too long",
					JOptionPane.ERROR_MESSAGE);
		}else if (activePanel.getContent().replaceAll("\\s+", "").equals("")) {
			JOptionPane.showMessageDialog(null, "Content cannot be empty.", "Blank field", JOptionPane.ERROR_MESSAGE);
		} else if (!activePanel.getContent().contains("<section>") || !activePanel.getContent().contains("</section>")
				|| !activePanel.getContent().contains("<section-title>")
				|| !activePanel.getContent().contains("</section-title>")) {
			JOptionPane.showMessageDialog(null, "Content must contain at least one section, and one section title.", "No section's", JOptionPane.ERROR_MESSAGE);
		}else if(!model.isQuizActive()){
			JOptionPane.showMessageDialog(null, "You must add a quiz before you complete a topic.", "No quiz", JOptionPane.ERROR_MESSAGE);
		}else{
		model.addContentFile(activePanel.getTopicTitle(), model.selectCategoryId(activePanel.getSelectedCategory()),
				activePanel.getContent());
		activePanel.setVisible(false);
		activePanel.dispose();
		model.clearActiveFile();
		JOptionPane.showMessageDialog(null, "Topic '" + title + "' has been added successfully.",
				"Topic added", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}

}

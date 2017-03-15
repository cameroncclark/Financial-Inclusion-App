package Controller;

import javax.swing.JOptionPane;

import Model.Model;
import View.AddContentPane;
import View.ContentPanel;
import View.EditContentPane;
import View.MainContainer;
import View.QuizPane;

public class RemoveQuizQuestionCtrl {
	public RemoveQuizQuestionCtrl(Model model, MainContainer view) {
		ContentPanel panel = (ContentPanel) view.getActivePanel();
		if (panel.isAddPaneActive()) {
			AddContentPane activePanel = (AddContentPane) panel.getActivePanel();
			QuizPane quizPanel = (QuizPane) activePanel.getActivePanel();
			if (quizPanel.getSelectedQuestion() == null) {
				JOptionPane.showMessageDialog(null, "You must select a question to delete.", "Please select question",
						JOptionPane.ERROR_MESSAGE);
			} else {
				int sure = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this question?",
						"Are You Sure?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (sure == JOptionPane.YES_OPTION) {
					model.removeQuizQuestion(quizPanel.getSelectedQuestion());
				}
			}
		} else {
			EditContentPane activePanel = (EditContentPane) panel.getActivePanel();
			QuizPane quizPanel = (QuizPane) activePanel.getActivePanel();
			if (quizPanel.getSelectedQuestion() == null) {
				JOptionPane.showMessageDialog(null, "You must select a question to delete.", "Please select question",
						JOptionPane.ERROR_MESSAGE);
			} else {
				int sure = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this question?",
						"Are You Sure?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (sure == JOptionPane.YES_OPTION) {
					model.removeQuizQuestion(quizPanel.getSelectedQuestion());
				}
			}
		}
	}
}

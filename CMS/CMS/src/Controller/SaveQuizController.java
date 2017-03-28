package Controller;

import javax.swing.JOptionPane;

import Model.Model;
import View.AddContentPane;
import View.ContentPanel;
import View.EditContentPane;
import View.MainContainer;
import View.QuizPane;

public class SaveQuizController {
	public SaveQuizController(Model model, MainContainer view) {
		ContentPanel panel = (ContentPanel) view.getActivePanel();
		if (panel.isAddPaneActive()) {
			AddContentPane activePanel = (AddContentPane) panel.getActivePanel();
			QuizPane quizPanel = (QuizPane) activePanel.getActivePanel();
			if (quizPanel.getQuizTitle().replaceAll("\\s+", "").equals("")) {
				JOptionPane.showMessageDialog(null, "Question title cannot be empty.", "Blank field",
						JOptionPane.ERROR_MESSAGE);
			} else if (!model.isQuizActive()) {
				JOptionPane.showMessageDialog(null, "There must be more than one question in a quiz.", "Blank field",
						JOptionPane.ERROR_MESSAGE);
			} else {
				model.saveQuiz(quizPanel.getQuizTitle());
				JOptionPane.showMessageDialog(null,
						"Quiz '" + quizPanel.getQuizTitle() + "' has been added successfully.", "Quiz added",
						JOptionPane.INFORMATION_MESSAGE);
				quizPanel.dispose();

			}
		} else {
			EditContentPane activePanel = (EditContentPane) panel.getActivePanel();
			QuizPane quizPanel = (QuizPane) activePanel.getActivePanel();
			if (quizPanel.getQuizTitle().replaceAll("\\s+", "").equals("")) {
				JOptionPane.showMessageDialog(null, "Question title cannot be empty.", "Blank field",
						JOptionPane.ERROR_MESSAGE);
			} else if (!model.isQuizActive()) {
				JOptionPane.showMessageDialog(null, "There must be more than one question in a quiz.", "Blank field",
						JOptionPane.ERROR_MESSAGE);
			} else {
				model.saveQuiz(quizPanel.getQuizTitle());
				JOptionPane.showMessageDialog(null,
						"Quiz '" + quizPanel.getQuizTitle() + "' has been added successfully.", "Quiz added",
						JOptionPane.INFORMATION_MESSAGE);
				quizPanel.dispose();
			}
		}
	}

}

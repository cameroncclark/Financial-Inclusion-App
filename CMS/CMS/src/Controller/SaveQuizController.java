package Controller;

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
			model.saveQuiz(quizPanel.getQuizTitle());
			quizPanel.dispose();
		} else {
			EditContentPane activePanel = (EditContentPane) panel.getActivePanel();
			QuizPane quizPanel = (QuizPane) activePanel.getActivePanel();
			model.saveQuiz(quizPanel.getQuizTitle());
			quizPanel.dispose();
		}
	}

}

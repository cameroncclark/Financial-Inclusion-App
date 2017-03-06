package Controller;

import Model.Model;
import View.AddContentPane;
import View.ContentPanel;
import View.MainContainer;
import View.QuizPane;

public class RemoveQuizQuestionCtrl {
public RemoveQuizQuestionCtrl(Model model, MainContainer view) {
	ContentPanel panel = (ContentPanel) view.getActivePanel();
	AddContentPane activePanel = (AddContentPane) panel.getActivePanel();
	QuizPane quizPanel = (QuizPane) activePanel.getActivePanel();
	model.removeQuizQuestion(quizPanel.getSelectedQuestion());
}
}

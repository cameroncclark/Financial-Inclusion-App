package Controller;

import Model.Model;
import View.AddContentPane;
import View.ContentPanel;
import View.MainContainer;
import View.QuizAnswerPane;
import View.QuizPane;

public class AddQuizQuestionCtrl {
	public AddQuizQuestionCtrl(Model model, MainContainer view) {
		ContentPanel panel = (ContentPanel) view.getActivePanel();
		AddContentPane activePanel = (AddContentPane) panel.getActivePanel();
		QuizPane quizPanel = (QuizPane) activePanel.getActivePanel();
		QuizAnswerPane quizAnswerPane = (QuizAnswerPane) quizPanel.getActivePanel();
		model.addQuestion(quizAnswerPane.getQuestionType(), quizAnswerPane.getQuestion(), quizAnswerPane.getAnswers(), quizAnswerPane.getCorrectAnswer(),quizAnswerPane.getReasons());
		quizAnswerPane.dispose();
	}

}

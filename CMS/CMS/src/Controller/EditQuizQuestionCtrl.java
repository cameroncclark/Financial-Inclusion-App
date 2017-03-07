package Controller;

import ContentObjects.QuestionObject;
import Model.Model;
import View.AddContentPane;
import View.ContentPanel;
import View.MainContainer;
import View.QuizAnswerPane;
import View.QuizPane;

public class EditQuizQuestionCtrl {
	public EditQuizQuestionCtrl(Model model, MainContainer view) {
		ContentPanel panel = (ContentPanel) view.getActivePanel();
		AddContentPane activePanel = (AddContentPane) panel.getActivePanel();
		QuizPane quizPanel = (QuizPane) activePanel.getActivePanel();
		QuizAnswerPane quizAnswerPane = (QuizAnswerPane) quizPanel.getActivePanel();
		QuestionObject question = model.getQuestion(quizPanel.getSelectedQuestion());
		quizAnswerPane.setQuestion(question.getQuestionText());
		quizAnswerPane.setQuestionType(question.getQuestionType());
		quizAnswerPane.setAnswersAndReasons(question.getAnswers(), question.getReasons());
		quizAnswerPane.setCorrectAnswer(question.getAnswer());
	}
}
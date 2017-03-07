package Controller;

import javax.swing.JOptionPane;

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
		
		if(question.getQuestionType() != "choosePicture"){
			quizAnswerPane.setQuestion(question.getQuestionText());
			quizAnswerPane.setQuestionType(question.getQuestionType());
			quizAnswerPane.setAnswersAndReasons(question.getAnswers(), question.getReasons());
			quizAnswerPane.setCorrectAnswer(question.getAnswer());
		} else {
			quizAnswerPane.dispose();
			JOptionPane.showMessageDialog(null, "Image Questions Cannot Be Edited!\nPlease Remove Question.","Non-Editable Content",JOptionPane.ERROR_MESSAGE);
		}
	}
}

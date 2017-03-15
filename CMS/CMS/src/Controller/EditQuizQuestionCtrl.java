package Controller;

import javax.swing.JOptionPane;

import ContentObjects.QuestionObject;
import Model.Model;
import View.AddContentPane;
import View.ContentPanel;
import View.EditContentPane;
import View.MainContainer;
import View.QuizAnswerPane;
import View.QuizPane;

public class EditQuizQuestionCtrl {
	public EditQuizQuestionCtrl(Model model, MainContainer view) {
		ContentPanel panel = (ContentPanel) view.getActivePanel();
		if (panel.isAddPaneActive()) {
			AddContentPane activePanel = (AddContentPane) panel.getActivePanel();
			QuizPane quizPanel = (QuizPane) activePanel.getActivePanel();
			QuizAnswerPane quizAnswerPane = (QuizAnswerPane) quizPanel.getActivePanel();

			if (quizPanel.getSelectedQuestion() == null) {
				JOptionPane.showMessageDialog(null, "You must select a question to edit.", "Please select question",
						JOptionPane.ERROR_MESSAGE);
			} else {
				QuestionObject question = model.getQuestion(quizPanel.getSelectedQuestion());

				if (question.getQuestionType() != "choosePicture") {
					quizAnswerPane.setQuestion(question.getQuestionText());
					quizAnswerPane.setQuestionType(question.getQuestionType());
					quizAnswerPane.setAnswersAndReasons(question.getAnswers(), question.getReason());
					quizAnswerPane.setCorrectAnswer(question.getAnswer());
				} else {
					quizAnswerPane.dispose();
					JOptionPane.showMessageDialog(null, "Image Questions Cannot Be Edited!\nPlease Remove Question.",
							"Non-Editable Content", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			EditContentPane activePanel = (EditContentPane) panel.getActivePanel();
			QuizPane quizPanel = (QuizPane) activePanel.getActivePanel();
			QuizAnswerPane quizAnswerPane = (QuizAnswerPane) quizPanel.getActivePanel();
			QuestionObject question = model.getQuestion(quizPanel.getSelectedQuestion());
			if (quizPanel.getSelectedQuestion() == null) {
				JOptionPane.showMessageDialog(null, "You must select a question to edit.", "Please select question",
						JOptionPane.ERROR_MESSAGE);
			} else {
				if (question.getQuestionType() != "choosePicture") {
					quizAnswerPane.setQuestion(question.getQuestionText());
					quizAnswerPane.setQuestionType(question.getQuestionType());
					quizAnswerPane.setAnswersAndReasons(question.getAnswers(), question.getReason());
					quizAnswerPane.setCorrectAnswer(question.getAnswer());
				} else {
					quizAnswerPane.dispose();
					JOptionPane.showMessageDialog(null, "Image Questions Cannot Be Edited!\nPlease Remove Question.",
							"Non-Editable Content", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}

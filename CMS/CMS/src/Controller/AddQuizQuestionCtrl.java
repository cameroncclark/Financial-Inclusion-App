package Controller;

import javax.swing.JOptionPane;

import Model.Model;
import View.AddContentPane;
import View.ContentPanel;
import View.EditContentPane;
import View.MainContainer;
import View.QuizAnswerPane;
import View.QuizPane;

public class AddQuizQuestionCtrl {
	public AddQuizQuestionCtrl(Model model, MainContainer view) {
		ContentPanel panel = (ContentPanel) view.getActivePanel();

		if (panel.isAddPaneActive()) {
			AddContentPane activePanel = (AddContentPane) panel.getActivePanel();
			QuizPane quizPanel = (QuizPane) activePanel.getActivePanel();
			QuizAnswerPane quizAnswerPane = (QuizAnswerPane) quizPanel.getActivePanel();

			if (quizAnswerPane.getEditCheck()) {
				if (quizAnswerPane.getQuestion().replaceAll("\\s+", "").equals("")) {
					JOptionPane.showMessageDialog(null, "Quiz questions cannot be empty.", "Blank field",
							JOptionPane.ERROR_MESSAGE);
				} else if (quizAnswerPane.getReasons() != null && quizAnswerPane.getReasons().size() == 0) {
					JOptionPane.showMessageDialog(null, "You must select a number of questions.",
							"Select number of questions", JOptionPane.ERROR_MESSAGE);
				} else if (quizAnswerPane.anyFieldsBlankOrNotUnique()) {
					JOptionPane.showMessageDialog(null, "All answer/reason fields must be completed and not duplicate!",
							"Blank or duplicate field.", JOptionPane.ERROR_MESSAGE);
				} else {
					model.editQuestion(quizAnswerPane.getOldQuestion(), quizAnswerPane.getQuestionType(),
							quizAnswerPane.getQuestion(), quizAnswerPane.getAnswers(),
							quizAnswerPane.getCorrectAnswer(), quizAnswerPane.getReasons());

					JOptionPane.showMessageDialog(null, "Question has been edited successfully.", "Question edited",
							JOptionPane.INFORMATION_MESSAGE);
					quizAnswerPane.dispose();
				}
			} else {
				if (quizAnswerPane.getQuestion().replaceAll("\\s+", "").equals("")) {
					JOptionPane.showMessageDialog(null, "Quiz questions cannot be empty.", "Blank field",
							JOptionPane.ERROR_MESSAGE);
				} else if (quizAnswerPane.getReasons() != null && quizAnswerPane.getReasons().size() == 0) {
					JOptionPane.showMessageDialog(null, "You must select a number of questions.",
							"Select number of questions", JOptionPane.ERROR_MESSAGE);
				} else if (quizAnswerPane.anyFieldsBlankOrNotUnique()) {
					JOptionPane.showMessageDialog(null, "All answer/reason fields must be completed and not duplicate!",
							"Blank or duplicate field.", JOptionPane.ERROR_MESSAGE);
				} else {
					if (quizAnswerPane.getPictureCheck()) {
						model.addQuestionPicture(quizAnswerPane.getQuestionType(), quizAnswerPane.getQuestion(),
								quizAnswerPane.getPicturePaths(), quizAnswerPane.getCorrectAnswer(),
								quizAnswerPane.getReasons());
					} else {
						model.addQuestion(quizAnswerPane.getQuestionType(), quizAnswerPane.getQuestion(),
								quizAnswerPane.getAnswers(), quizAnswerPane.getCorrectAnswer(),
								quizAnswerPane.getReasons());
					}
					JOptionPane.showMessageDialog(null, "Question has been added successfully.", "Question added",
							JOptionPane.INFORMATION_MESSAGE);
					quizAnswerPane.dispose();
				}
			}

		} else {
			EditContentPane activePanel = (EditContentPane) panel.getActivePanel();
			QuizPane quizPanel = (QuizPane) activePanel.getActivePanel();
			QuizAnswerPane quizAnswerPane = (QuizAnswerPane) quizPanel.getActivePanel();

			if (quizAnswerPane.getEditCheck()) {
				if (quizAnswerPane.getQuestion().replaceAll("\\s+", "").equals("")) {
					JOptionPane.showMessageDialog(null, "Quiz questions cannot be empty.", "Blank field",
							JOptionPane.ERROR_MESSAGE);
				} else if (quizAnswerPane.getReasons() != null && quizAnswerPane.getReasons().size() == 0) {
					JOptionPane.showMessageDialog(null, "You must select a number of questions.",
							"Select number of questions", JOptionPane.ERROR_MESSAGE);
				} else if (quizAnswerPane.anyFieldsBlankOrNotUnique()) {
					JOptionPane.showMessageDialog(null, "All answer/reason fields must be completed and not duplicate!",
							"Blank or duplicate field.", JOptionPane.ERROR_MESSAGE);
				} else {
					model.editQuestion(quizAnswerPane.getOldQuestion(), quizAnswerPane.getQuestionType(),
							quizAnswerPane.getQuestion(), quizAnswerPane.getAnswers(),
							quizAnswerPane.getCorrectAnswer(), quizAnswerPane.getReasons());

					JOptionPane.showMessageDialog(null, "Question has been edited successfully.", "Question edited",
							JOptionPane.INFORMATION_MESSAGE);
					quizAnswerPane.dispose();
				}
			} else {
				if (quizAnswerPane.getQuestion().replaceAll("\\s+", "").equals("")) {
					JOptionPane.showMessageDialog(null, "Quiz questions cannot be empty.", "Blank field",
							JOptionPane.ERROR_MESSAGE);
				} else if (quizAnswerPane.getReasons() != null && quizAnswerPane.getReasons().size() == 0) {
					JOptionPane.showMessageDialog(null, "You must select a number of questions.",
							"Select number of questions", JOptionPane.ERROR_MESSAGE);
				} else if (quizAnswerPane.anyFieldsBlankOrNotUnique()) {
					JOptionPane.showMessageDialog(null, "All answer/reason fields must be completed and not duplicate!",
							"Blank or duplicate field.", JOptionPane.ERROR_MESSAGE);
				} else {
					if (quizAnswerPane.getPictureCheck()) {
						model.addQuestionPicture(quizAnswerPane.getQuestionType(), quizAnswerPane.getQuestion(),
								quizAnswerPane.getPicturePaths(), quizAnswerPane.getCorrectAnswer(),
								quizAnswerPane.getReasons());
					} else {
						model.addQuestion(quizAnswerPane.getQuestionType(), quizAnswerPane.getQuestion(),
								quizAnswerPane.getAnswers(), quizAnswerPane.getCorrectAnswer(),
								quizAnswerPane.getReasons());
					}
					JOptionPane.showMessageDialog(null, "Question has been added successfully.", "Question added",
							JOptionPane.INFORMATION_MESSAGE);
					quizAnswerPane.dispose();
				}
			}

		}

	}

}

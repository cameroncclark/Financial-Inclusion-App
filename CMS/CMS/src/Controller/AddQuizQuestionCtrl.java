package Controller;

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
				model.editQuestion(quizAnswerPane.getOldQuestion(), quizAnswerPane.getQuestionType(),
						quizAnswerPane.getQuestion(), quizAnswerPane.getAnswers(), quizAnswerPane.getCorrectAnswer(),
						quizAnswerPane.getReasons());
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
				quizAnswerPane.dispose();
			}
		} else {
			EditContentPane activePanel = (EditContentPane) panel.getActivePanel();
			QuizPane quizPanel = (QuizPane) activePanel.getActivePanel();
			QuizAnswerPane quizAnswerPane = (QuizAnswerPane) quizPanel.getActivePanel();

			if (quizAnswerPane.getEditCheck()) {
				model.editQuestion(quizAnswerPane.getOldQuestion(), quizAnswerPane.getQuestionType(),
						quizAnswerPane.getQuestion(), quizAnswerPane.getAnswers(), quizAnswerPane.getCorrectAnswer(),
						quizAnswerPane.getReasons());
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
			}
			quizAnswerPane.dispose();
		}
		
	}

}

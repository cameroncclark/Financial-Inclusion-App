package ContentObjects;

import java.util.ArrayList;

public class QuestionObject {
	protected String questionType;
	protected String questionText;
	protected ArrayList<String> answers;
	protected int answer;
	protected ArrayList<String> reason;

	public QuestionObject() {

	}

	public QuestionObject(String questionType, String questionText, ArrayList<String> answers, int answer,
			ArrayList<String> reason) {
		this.questionType = questionType;
		this.questionText = questionText;
		this.answers = answers;
		this.answer = answer;
		this.reason = reason;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questtionText) {
		this.questionText = questtionText;
	}

	public ArrayList<String> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<String> answers) {
		this.answers = answers;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public ArrayList<String> getReason() {
		return reason;
	}

	public void setReason(ArrayList<String> reason) {
		this.reason = reason;
	}

}

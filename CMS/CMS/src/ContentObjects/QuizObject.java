package ContentObjects;

import java.util.ArrayList;

public class QuizObject {
	protected String title;
	protected ArrayList<QuestionObject> questions;
	
	public QuizObject(){
		
	}
	
	public QuizObject(String title, ArrayList<QuestionObject> questions){
		this.title = title;
		this.questions = questions;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ArrayList<QuestionObject> getQuestions() {
		return questions;
	}
	public void setQuestions(ArrayList<QuestionObject> questions) {
		this.questions = questions;
	}

}

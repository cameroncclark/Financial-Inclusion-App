package ContentObjects;

public class Topic {
	protected String title;
	protected Integer reference;
	protected String content;
	protected ContentQuizObject quiz;
	
	public Topic() {
		// TODO Auto-generated constructor stub
	}
	
	public Topic(String title, Integer reference, String content, ContentQuizObject quiz){
		this.title = title;
		this.reference = reference;
		this.content = content;
		this.quiz = quiz;
	}
	

	public ContentQuizObject getQuiz() {
		return quiz;
	}

	public void setQuiz(ContentQuizObject quiz) {
		this.quiz = quiz;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getReference() {
		return reference;
	}

	public void setReference(Integer reference) {
		this.reference = reference;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}

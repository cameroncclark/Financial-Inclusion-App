package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import ContentObjects.Link;
import ContentObjects.Number;
import ContentObjects.QuestionObject;
import ContentObjects.Tip;
import ContentObjects.Topic;

public class Model extends Observable {
	final String _PATH = "../../FinancialInclusionApplication/www/content/";
	CategoriesModel catModel;
	ContentModel contentModel;
	NumbersModel numbersModel;
	LinksModel linksModel;
	TipsModel tipsModel;

	public Model() {
		catModel = new CategoriesModel(this);
		contentModel = new ContentModel(this);
		numbersModel = new NumbersModel(this);
		linksModel = new LinksModel(this);
		tipsModel = new TipsModel(this);

	}

	/**
	 * Selects all categories from the file system
	 * 
	 * @return String of all categories
	 */
	public String[] selectCategories() {
		return catModel.selectCategories();
	}

	/**
	 * Selects all numbers from the file system
	 * @return String of all numbers
	 */
	public String[] selectNumbers() {
		return numbersModel.selectNumbers();
	}

	/**
	 * Selects all links from the file system
	 * @return String of all links
	 */
	public String[] selectLinks() {
		return linksModel.selectLinks();
	}

	/**
	 * Selects all tips from the file system
	 * @return String of all tips
	 */
	public String[] selectTips() {
		return tipsModel.selectTips();
	}

	public Boolean addCategory(String categoryName) {
		return catModel.addCategory(categoryName);
	}

	public Boolean addNumber(String name, String blurb, String number) {
		return numbersModel.addUsefulNumber(name, blurb, number);
	}

	public Boolean addLink(String name, String blurb, String website) {
		return linksModel.addLink(name, blurb, website);
	}

	public Boolean addTip(String header, String tip) {
		return tipsModel.addTip(header, tip);
	}

	public Boolean editCategory(String oldCat, String newCat) {
		return catModel.editCategory(oldCat, newCat);
	}

	public Boolean editNumber(String oldName, String newName, String newBlurb, String newNumber) {
		return numbersModel.editUsefulNumber(oldName, newName, newBlurb, newNumber);
	}

	public Boolean editLink(String oldName, String newName, String newBlurb, String newWebsite) {
		return linksModel.editLink(oldName, newName, newBlurb, newWebsite);
	}

	public Boolean editTip(String oldHeader, String newHeader, String newTip) {
		return tipsModel.editTip(oldHeader, newHeader, newTip);
	}

	public void deleteCategory(String selectedCat) {
		catModel.deleteCategory(selectedCat);
	}

	public void deleteNumber(String name) {
		numbersModel.deleteUsefulNumber(name);
	}

	public void deleteLink(String name) {
		linksModel.deleteLink(name);
	}

	public void deleteTip(String header) {
		tipsModel.deleteTip(header);
	}

	public void setModelObserver(Observer o) {
		this.addObserver(o);
	}

	public Link selectLink(String name) {
		return linksModel.selectLink(name);
	}

	public Tip selectTip(String header) {
		return tipsModel.selectTip(header);
	}

	public Number selectNumber(String name) {
		return numbersModel.selectNumber(name);
	}

	public void addContentFile(String title, Integer reference, String content) {
		contentModel.addNewContentFile(title, reference, content);
	}

	public Integer selectCategoryId(String categoryName) {
		return catModel.selectCategoriesId(categoryName);
	}

	public String selectCategoryName(int id) {
		return catModel.selectCategoriesName(id);
	}

	public void setTopics() {
		contentModel.setTopicsMap();
	}

	public Topic selectTopic(String fileName) {
		return contentModel.selectTopic(fileName);
	}

	public void editTopic(String title, String content, String category) {

		contentModel.editTopic(title, content, selectCategoryId(category));
	}

	public void deleteTopic() {
		contentModel.removeTopic();
	}

	public void initialiseQuiz() {
		contentModel.initialiseQuiz();
	}

	public void saveQuiz(String title) {
		contentModel.saveQuiz(title);
	}

	public void addQuestion(String questionType, String questionText, ArrayList<String> answers, Integer answer,
			ArrayList<String> reasons) {
		contentModel.addQuestion(questionType, questionText, answers, answer, reasons);
	}
	
	public void addQuestionPicture(String questionType, String questionText, ArrayList<Path> answers, Integer answer,
			ArrayList<String> reasons) {
		contentModel.addQuestionPicture(questionType, questionText, answers, answer, reasons);
	}

	/**
	 * This returns the JSON file as a string.
	 * 
	 * @param filePath
	 * @return {@link String}
	 * @throws IOException
	 */
	public String getJSONString(String filePath) throws IOException {
		File file = new File(_PATH + filePath);
		FileInputStream fis = null;

		fis = new FileInputStream(file);

		StringBuilder builder = new StringBuilder();
		int ch;
		while ((ch = fis.read()) != -1) {
			builder.append((char) ch);
		}

		fis.close();

		return builder.toString();
	}

	/**
	 * This method is used in conjunction with the observer pattern to update the view
	 */
	public void changed() {
		setChanged();
		notifyObservers();
	}

	public void clearActiveFile() {
		contentModel.clearActiveFile();

	}

	public String copyImageToContent(String name) {
		return contentModel.saveImageFile(name);
	}

	public String[] getQuestionText() {
		ArrayList<String> temp = contentModel.getQuestions();
		return temp.toArray(new String[temp.size()]);
		
	}
	
	public QuestionObject getQuestion(String questionText){
		return contentModel.getQuestion(questionText);
	}

	public void removeQuizQuestion(String selectedQuestion) {
		contentModel.deleteQuestion(selectedQuestion);
	}
	
	public void editQuestion(String oldQuestion, String questionType, String questionText, ArrayList<String> answers,
			int answer, ArrayList<String> reasons){
		contentModel.editQuestion(oldQuestion, questionType, questionText, answers, answer, reasons);
	}

	public String getQuizTitle() {
		return contentModel.getQuizTitle();
	}

	
	public Boolean isQuizActive(){
		return contentModel.isQuizActive();
	}

	public void closeContentPane() {
		contentModel.closeQuiz();		
	}
	
	public Boolean isFileLoaded(){
		return contentModel.isFileLoaded();
	}
}

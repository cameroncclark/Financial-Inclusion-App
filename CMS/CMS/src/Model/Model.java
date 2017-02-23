package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import ContentObjects.Link;
import ContentObjects.Number;
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

	public String[] selectCategories() {
		return catModel.selectCategories();
	}
	
	public String[] selectNumbers(){
		return numbersModel.selectNumbers();
	}
	
	public String[] selectLinks(){
		return linksModel.selectLinks();
	}
	
	public String[] selectTips(){
		return tipsModel.selectTips();
	}

	public void addCategory(String categoryName) {
		catModel.addCategory(categoryName);
	}
	
	public void addNumber(String name, String blurb, String number){
		numbersModel.addUsefulNumber(name, blurb, number);
	}
	
	public void addLink(String name, String blurb, String website){
		linksModel.addLink(name, blurb, website);
	}
	
	public void addTip(String header, String tip){
		tipsModel.addTip(header, tip);
	}

	public void editCategory(String oldCat, String newCat) {
		catModel.editCategory(oldCat, newCat);
	}
	
	public void editNumber(String oldName, String newName, String newBlurb, String newNumber){
		numbersModel.editUsefulNumber(oldName, newName, newBlurb, newNumber);
	}
	
	public void editLink(String oldName, String newName, String newBlurb, String newWebsite){
		linksModel.editLink(oldName, newName, newBlurb, newWebsite);
	}
	
	public void editTip(String oldHeader, String newHeader, String newTip){
		tipsModel.editTip(oldHeader, newHeader, newTip);
	}

	public void deleteCategory(String selectedCat) {
		catModel.deleteCategory(selectedCat);
	}
	
	public void deleteNumber(String name){
		numbersModel.deleteUsefulNumber(name);
	}
	
	public void deleteLink(String name){
		linksModel.deleteLink(name);
	}
	
	public void deleteTip(String header){
		tipsModel.deleteTip(header);
	}

	public void setModelObserver(Observer o) {
		this.addObserver(o);
	}
	
	public Link selectLink(String name){
		return linksModel.selectLink(name);
	}
	
	public Tip selectTip(String header){
		return tipsModel.selectTip(header);
	}
	
	public Number selectNumber(String name){
		return numbersModel.selectNumber(name);
	}
	
	public void addContentFile(String title, Integer reference, String content){
		contentModel.addNewContentFile(title, reference, content);
	}
	
	public Integer selectCategoryId(String categoryName){
		return catModel.selectCategoriesId(categoryName);
	}
	
	public String selectCategoryName(int id){
		return catModel.selectCategoriesName(id);
	}
	
	public void setTopics(){
		contentModel.setTopicsMap();
	}
	
	public Topic selectTopic(String fileName){
		return contentModel.selectTopic(fileName);
	}
	
	public void editTopic(String title, String content, String category){
		
		contentModel.editTopic(title, content, selectCategoryId(category));
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
	
	public void changed(){
		setChanged();
		notifyObservers();
	}
}

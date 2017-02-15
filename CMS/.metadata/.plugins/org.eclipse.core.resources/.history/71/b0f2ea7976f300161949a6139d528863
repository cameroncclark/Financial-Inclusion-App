package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.plaf.synth.SynthScrollBarUI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ContentObjects.Category;

public class Model extends Observable {
	final String _PATH = "../../FinancialInclusionApplication/www/content/";
	CategoriesModel catModel;
	NumbersModel numbersModel;
	LinksModel linksModel;

	public Model() {
		catModel = new CategoriesModel(this);
		numbersModel = new NumbersModel(this);
		linksModel = new LinksModel(this);
		
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

	public void addCategory(String categoryName) {
		catModel.addCategory(categoryName);
	}
	
	public void addNumber(String name, String blurb, String number){
		numbersModel.addUsefulNumber(name, blurb, number);
	}
	
	public void addLink(String name, String blurb, String website){
		linksModel.addLink(name, blurb, website);
	}

	public void editCategory(String oldCat, String newCat) {
		catModel.editCategory(oldCat, newCat);
	}
	
	public void editNumber(String oldName, String newName, String newBlurb, String newNumber){
		numbersModel.editUsefulNumnber(oldName, newName, newBlurb, newNumber);
	}
	
	public void editLink(String oldName, String newName, String newBlurb, String newWebsite){
		linksModel.editLink(oldName, newName, newBlurb, newWebsite);
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

	public void setModelObserver(Observer o) {
		this.addObserver(o);
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

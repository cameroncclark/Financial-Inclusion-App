package Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;

import ContentObjects.Category;
import ContentObjects.Number;
import ContentObjects.Tip;

public class NumbersModel {
	final String _PATH = "../../FinancialInclusionApplication/www/content/";
	Model model;
	Gson gson;
	ArrayList<Number> numbers;
	
	public NumbersModel(Model model) {
		this.model = model;
		gson = new Gson();
		selectNumbers();
	}
	
	/**
	 * Select all numbers from the JSON files and brings them into application context
	 * 
	 * @return All number strings
	 */
	public String[] selectNumbers() {
		try {
			String fileText = model.getJSONString("usefulNumbers.JSON");

			Number[] numberArray = gson.fromJson(fileText, Number[].class);
			numbers = new ArrayList<Number>(Arrays.asList(numberArray));

			String[] numberNames = new String[numberArray.length];
			int count = 0;
			for (Number number : numberArray) {
				numberNames[count] = number.getName();
				count++;
			}
			return numberNames;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String[] {};
	}
	
	/**
	 * Selects a number object given a name
	 * 
	 * @param name The number name
	 * @return the number identified
	 */
	public Number selectNumber(String name){
		if(!name.equals("")){
			for(Number n: numbers){
				if(n.getName().equals(name)){
					return n;
				}
			}
		}
		return new Number();
	}
	
	/**
	 * Adds a number object to the arraylist
	 * 
	 * @param name the number name
	 * @param blurb the number blurb
	 * @param number The number
	 * 
	 * @return True if successful, false if not
	 */
	public Boolean addUsefulNumber(String name, String blurb, String number){
		if(!duplicateName("",name)){
			numbers.add(new Number(name,blurb,number));
			rewriteNumbersFile();
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Modifies a number
	 * 
	 * @param oldName The old number name
	 * @param newName The new number name
	 * @param newBlurb The new blurb
	 * @param newNumber The new number
	 * @return True if successful
	 */
	public Boolean editUsefulNumber(String oldName, String newName, String newBlurb, String newNumber){
		if(!duplicateName(oldName,newName)){
			for(Number n: numbers){
				if(n.getName().equals(oldName)){
					n.setName(newName);
					n.setBlurb(newBlurb);
					n.setNumber(newNumber);
					break;
				}
			}
			rewriteNumbersFile();
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Delete a useful number given the number name
	 * 
	 * @param name
	 */
	public void deleteUsefulNumber(String name){
		Number toBeRemoved = new Number();
		for(Number n: numbers){
			if(n.getName().equals(name)){
				toBeRemoved = n;
				break;
			}
		}
		numbers.remove(toBeRemoved);
		rewriteNumbersFile();
	}
	
	/**
	 * Method to check to see if a number name already exists
	 * 
	 * @param name
	 * @return true/false depending if the name exists
	 */
	private Boolean duplicateName(String oldName, String name) {
		if (oldName.equals(name)) {
			return false;
		}
		for (Number n : numbers) {
			if (n.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method to rewrite the json files and tell the model to notify observers
	 */
	public void rewriteNumbersFile() {
		try {
			Files.write(Paths.get(_PATH + "usefulNumbers.json"), gson.toJson(numbers).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.changed();
	}
	
	
}

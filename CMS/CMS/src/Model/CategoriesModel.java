package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.google.gson.Gson;

import ContentObjects.Category;

public class CategoriesModel {
	final String _PATH = "../../FinancialInclusionApplication/www/content/";
	Gson gson;
	ArrayList<Category> categories;
	Model model;
	Random random;

	public CategoriesModel(Model model) {
		this.model = model;
		gson = new Gson();
		random = new Random();
		selectCategories();
	}
	
	/**
	 * Selects all category names from the categories file.
	 * 
	 * @return The list of category names.
	 */
	public String[] selectCategories() {
		try {
			String fileText = model.getJSONString("categories.JSON");

			Category[] categoryArray = gson.fromJson(fileText, Category[].class);
			categories = new ArrayList<Category>(Arrays.asList(categoryArray));

			String[] categoryNames = new String[categoryArray.length];
			int count = 0;
			for (Category category : categoryArray) {
				categoryNames[count] = category.getName();
				count++;
			}
			return categoryNames;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String[] {};
	}
	

	/**
	 * Get a category ID based off the category name
	 * 
	 * @param category The category name
	 * @return
	 */
	public Integer selectCategoriesId(String category){
		for(Category c: categories){
			if(c.getName().equals(category)){
				return c.getId();
			}
		}
		return -1;
	}
	
	public String selectCategoriesName(int ID){
		for(Category c: categories){
			if(c.getId() == ID){
				return c.getName();
			}
		}
		return "No category found";
	}

	/**
	 * Will add a category with a unique id, given the name is not a duplicate.
	 * 
	 * @param categoryName The name to be added
	 * @return 
	 */
	public Boolean addCategory(String categoryName) {
		if (!duplicateName(categoryName)) {
			int newCatId = random.nextInt(500) + 1;
			while (getCategoryIDs().contains(newCatId)) {
				newCatId = random.nextInt(500) + 1;
			}
			categories.add(new Category(categoryName, newCatId));
			rewriteCategoriesFile();
			return true;
		}{
			return false;
		}
	}

	/**
	 * Edit's the category text and saves to a file.
	 * 
	 * @param oldCat Old category name
	 * @param newCat New category name
	 * @return 
	 */
	public Boolean editCategory(String oldCat, String newCat) {
		if (!duplicateName(newCat)) {
			for (Category c : categories) {
				if (c.getName().equals(oldCat)) {
					c.setName(newCat);
					break;
				}
			}
			rewriteCategoriesFile();
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Deletes the category from the category file.
	 * 
	 * @param selectedCat The category to be deleted
	 */
	public void deleteCategory(String selectedCat) {
		Category toBeRemoved = new Category();

		for (Category c : categories) {
			if (c.getName().equals(selectedCat)) {
				toBeRemoved = c;
				break;
			}
		}

		categories.remove(toBeRemoved);
		rewriteCategoriesFile();
	}

	/**
	 * Returns all category ids
	 * 
	 * @return the list of category ids
	 */
	public ArrayList<Integer> getCategoryIDs() {
		ArrayList<Integer> categoryIDs = new ArrayList<Integer>();
		for (Category c : categories) {
			categoryIDs.add(c.getId());
		}
		return categoryIDs;
	}
	
	/**
	 * Method to check to see if a category name already exists
	 * 
	 * @param name
	 * @return true/false depending if the name exists
	 */
	private Boolean duplicateName(String name) {
		for (Category c : categories) {
			if (c.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Method to rewrite the json files and tell the model to notify observers
	 */
	public void rewriteCategoriesFile() {
		try {
			Files.write(Paths.get(_PATH + "categories.json"), gson.toJson(categories).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.changed();
	}

}

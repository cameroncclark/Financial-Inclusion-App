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

	// TODO: Change path when packaged
	final String _PATH = "../../FinancialInclusionApplication/www/content/";
	Gson gson;
	ArrayList<Category> categories;
	Random random;

	public Model() {
		gson = new Gson();
		random = new Random();
	}

	public void testModel() {
		System.out.println("I've been pressed");
	}

	public String[] selectCategories() {
		try {
			String fileText = getJSONString("categories.JSON");

			Category[] categoryArray = gson.fromJson(fileText, Category[].class);
			categories = new ArrayList<Category>(Arrays.asList(categoryArray));

			String[] categoryNames = new String[categoryArray.length];
			int count = 0;
			for (Category category : categoryArray) {
				categoryNames[count] = category.getName();
				count++;
			}
			System.out.println(gson.toJson(categories));
			return categoryNames;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String[] {};
	}

	private Boolean duplicateName(String name) {
		for (Category c : categories) {
			if (c.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	// Need check to see if category name is in there
	public void addCategory(String categoryName) {
		System.out.println("Add category called");
		if (!duplicateName(categoryName)) {
			System.out.println("Not duplicate");
			int newCatId = random.nextInt(500) + 1;
			while (getCategoryIDs().contains(newCatId)) {
				newCatId = random.nextInt(500) + 1;
			}
			categories.add(new Category(categoryName, newCatId));
			rewriteCategoriesFile();
		}
		System.out.println("Add categories: " +gson.toJson(categories));
	}

	public void editCategory(String oldCat, String newCat) {
		if (!duplicateName(newCat)) {
			System.out.println("Method called");
			System.out.println(oldCat);
			for (Category c : categories) {
				if (c.getName().equals(oldCat)) {
					c.setName(newCat);
					break;
				}
			}
			rewriteCategoriesFile();
		}
	}

	public void deleteCategory(String selectedCat) {
		System.out.println("Delete category called");
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
	 * This returns the JSON file as a string.
	 * 
	 * @param filePath
	 * @return {@link String}
	 * @throws IOException
	 */
	private String getJSONString(String filePath) throws IOException {
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

	public ArrayList<Integer> getCategoryIDs() {
		ArrayList<Integer> categoryIDs = new ArrayList<Integer>();
		for (Category c : categories) {
			categoryIDs.add(c.getId());
		}
		return categoryIDs;
	}

	public void rewriteCategoriesFile() {
		try {
			Files.write(Paths.get(_PATH + "categories.json"), gson.toJson(categories).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		setChanged();
		notifyObservers();
	}

	public void setModelObserver(Observer o) {
		this.addObserver(o);
	}
}
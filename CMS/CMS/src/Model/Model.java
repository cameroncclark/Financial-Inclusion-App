package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import com.google.gson.Gson;

import ContentObjects.Category;

public class Model extends Observable {
	
	//TODO: Change path when packaged	
	final String _PATH = "../../FinancialInclusionApplication/www/content/";
	Gson gson;
	List<Category> categories;
	
	public Model(){
		gson = new Gson();
	}
	
	public void testModel(){
		System.out.println("I've been pressed");
	}
	
	public String[] selectCategories(){	
		try{
			String fileText = getJSONString("categories.JSON");

			Category[] categoryArray = gson.fromJson(fileText, Category[].class);
			categories = Arrays.asList(categoryArray);
			System.out.println(categories.get(0).getName());
			
			String[] categoryNames = new String[categoryArray.length];
			int count = 0;
			for(Category category: categoryArray){
				categoryNames[count] = category.getName();
				count++;
			}
			return categoryNames;
			
		} catch(Exception e){
			e.printStackTrace();
		}
		return new String[] {};
	}
	
	public void addCategory(){
		categories.add(new Category("Hello",4));
	}
	
	/**
	 * This returns the JSON file as a string.
	 * 
	 * @param filePath
	 * @return {@link String}
	 * @throws IOException
	 */
	private String getJSONString(String filePath) throws IOException{
		File file = new File(_PATH + filePath);
		FileInputStream fis = null;
		
		fis = new FileInputStream(file);
		
		StringBuilder builder = new StringBuilder();
		int ch;
		while((ch = fis.read()) != -1){
		    builder.append((char)ch);
		}
		
		fis.close();
		
		return builder.toString();
	}
}

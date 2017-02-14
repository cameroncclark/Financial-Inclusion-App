package Model;

import java.util.ArrayList;

import com.google.gson.Gson;

import ContentObjects.Tip;

public class TipsModel {
	final String _PATH = "../../FinancialInclusionApplication/www/content/";
	Model model;
	Gson gson;
	ArrayList<Tip> tips;
	
	public TipsModel(Model model) {
		this.model = model;
		selectTips();
	}
	
	private String[] selectTips(){
		return new String[]{};
	}
	
	//Limit tips to 200 characters
	
	public void addTip(String header, String tip){
		
	}
	
	public void editTip(String oldHeader, String newHeader, String newTip){
		
	}
	
	public void deleteTip(String header){
		
	}
}

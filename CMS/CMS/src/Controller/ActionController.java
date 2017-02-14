package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Model;
import View.MainContainer;

public class ActionController implements ActionListener {
	Model model;
	MainContainer view;
	
	
	public ActionController(Model model){
		this.model = model;
	}
	
	public void setMainContainer(MainContainer view){
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "addCategory":
			AddCategoryCtrl addCategoryCtrl = new AddCategoryCtrl(model, view);
			break;
		case "editCategory":
			EditCategoryCtrl editCatCrtl = new EditCategoryCtrl(model, view);
			break;
		case "deleteCategory":
			DeleteCategoryCtrl dltCatCtrl = new DeleteCategoryCtrl(model, view);
			break;
			
		}
		
	}
	
	public String[] initaliseCategoriesTab(){
		String[] categories = model.selectCategories();
		return categories;
	}
	
	

}

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
		case "addNumber":
			AddNumberCtrl addNumberCtrl = new AddNumberCtrl(model, view);
			break;
		case "editNumber":
			EditNumberCtrl editNumberCtrl = new EditNumberCtrl(model, view);
			break;
		case "deleteNumber":
			DeleteNumberCtrl deleteNumberCtrl = new DeleteNumberCtrl(model, view);
			break;
		case "addLink":
			break;
		case "editLink":
			break;
		case "deleteLink":
			break;
		case "addTip":
			break;
		case "editTip":
			break;
		case "deleteTip":
			break;	
		}
		
	}
	
	public String[] initaliseCategoriesTab(){
		String[] categories = model.selectCategories();
		return categories;
	}
	
	

}

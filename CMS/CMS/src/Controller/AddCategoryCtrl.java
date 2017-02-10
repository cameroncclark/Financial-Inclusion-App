package Controller;

import Model.Model;
import View.CategoriesPanel;
import View.MainContainer;

public class AddCategoryCtrl {
	
	public AddCategoryCtrl(Model model, MainContainer view) {
		CategoriesPanel panel = (CategoriesPanel) view.getActivePanel();
		model.addCategory(panel.getAddCategoryText());
		panel.clearAddCategoryText();
		
	}
}

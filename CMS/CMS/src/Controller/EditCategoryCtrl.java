package Controller;

import Model.Model;
import View.CategoriesPanel;
import View.MainContainer;

public class EditCategoryCtrl {
	public EditCategoryCtrl(Model model, MainContainer view) {
		CategoriesPanel panel = (CategoriesPanel) view.getActivePanel();
		model.editCategory(panel.getGetSelectedEditItem(), panel.getEditCategoryText());
	}

}

package Controller;

import Model.Model;
import View.CategoriesPanel;
import View.MainContainer;

public class DeleteCategoryCtrl {
	public DeleteCategoryCtrl(Model model, MainContainer view) {
		CategoriesPanel panel = (CategoriesPanel) view.getActivePanel();
		model.deleteCategory(panel.getGetSelectedDeleteItem());
	}

}

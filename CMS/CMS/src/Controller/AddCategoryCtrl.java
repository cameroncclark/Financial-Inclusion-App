package Controller;

import javax.swing.JOptionPane;

import Model.Model;
import View.CategoriesPanel;
import View.MainContainer;

public class AddCategoryCtrl {

	public AddCategoryCtrl(Model model, MainContainer view) {
		CategoriesPanel panel = (CategoriesPanel) view.getActivePanel();
		String category = panel.getAddCategoryText();
		if (panel.getAddCategoryText().replaceAll("\\s+", "").equals("")) {
			JOptionPane.showMessageDialog(null, "Category cannot be empty.", "Blank field", JOptionPane.ERROR_MESSAGE);
		} else if (panel.getAddCategoryText().length() > 20) {
			JOptionPane.showMessageDialog(null,
					"Category '" + category + "' exceeds the maximum length of 20 characters.", "Category too long",
					JOptionPane.ERROR_MESSAGE);
		} else {
			if (model.addCategory(panel.getAddCategoryText())) {
				JOptionPane.showMessageDialog(null, "Category '" + category + "' has been added successfully.",
						"Category added", JOptionPane.INFORMATION_MESSAGE);
				panel.clearAddCategoryText();
			}else{
				JOptionPane.showMessageDialog(null, "Category '" + category + "' could not be added as it is a duplicate.",
						"Add failed", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}

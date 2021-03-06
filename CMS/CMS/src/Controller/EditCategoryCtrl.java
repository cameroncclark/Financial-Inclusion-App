package Controller;

import javax.swing.JOptionPane;

import Model.Model;
import View.CategoriesPanel;
import View.MainContainer;

public class EditCategoryCtrl {

	public EditCategoryCtrl(Model model, MainContainer view) {
		CategoriesPanel panel = (CategoriesPanel) view.getActivePanel();
		String category = panel.getEditCategoryText();
		String old = panel.getGetSelectedEditItem();
		if (panel.getEditCategoryText().replaceAll("\\s+", "").equals("")) {
			JOptionPane.showMessageDialog(null, "Category cannot be empty.", "Empty field", JOptionPane.ERROR_MESSAGE);
		} else {
			if (panel.getEditCategoryText().length() > 70) {
				JOptionPane.showMessageDialog(null, "Category '"+category+"' exceeds the maximum length of 70 characters.", "Category too long", JOptionPane.ERROR_MESSAGE);
			} else {
				if(model.editCategory(panel.getGetSelectedEditItem(), panel.getEditCategoryText())){
				JOptionPane.showMessageDialog(null, "Category '"+old+"' has been changed to '"+category+"'.", "Category added", JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Category '" + category + "' could not be changed as it is a duplicate name.",
							"Edit failed", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
}

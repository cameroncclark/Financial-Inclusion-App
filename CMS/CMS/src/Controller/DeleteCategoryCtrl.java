package Controller;

import javax.swing.JOptionPane;

import Model.Model;
import View.CategoriesPanel;
import View.MainContainer;

public class DeleteCategoryCtrl {

	public DeleteCategoryCtrl(Model model, MainContainer view) {
		CategoriesPanel panel = (CategoriesPanel) view.getActivePanel();
		String item = panel.getGetSelectedDeleteItem();
		int cancel = JOptionPane
				.showConfirmDialog(
						null,
						"Warning, removing '"
								+ item
								+ "' without removing the topics which link to this catgeory will cause serious issues.\nAre you sure you wish to continue?",
						"Warning!", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE);
		if (cancel == JOptionPane.OK_OPTION) {
			int sure = JOptionPane.showConfirmDialog(null,
					"Are you sure you wish to delete category '" + item + "'?",
					"Are you sure?", JOptionPane.YES_NO_OPTION,
					JOptionPane.INFORMATION_MESSAGE);
			if (sure == JOptionPane.YES_OPTION) {
				model.deleteCategory(panel.getGetSelectedDeleteItem());
				JOptionPane.showMessageDialog(null, "Category '" + item
						+ "' has been successfully deleted.", "Deleted",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

	}
}

package Controller;

import javax.swing.JOptionPane;

import Model.Model;
import View.MainContainer;
import View.NumbersPanel;

public class DeleteNumberCtrl {
	public DeleteNumberCtrl(Model model, MainContainer view) {
		NumbersPanel panel = (NumbersPanel) view.getActivePanel();
		String deleteNumber = panel.getSelectedDelete();
		int sure = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + deleteNumber + "?", "Are You Sure?", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		if (sure == JOptionPane.YES_OPTION) {
			model.deleteNumber(deleteNumber);
			JOptionPane.showMessageDialog(null, "'" + deleteNumber + "' successfully deleted!", "Deleted", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
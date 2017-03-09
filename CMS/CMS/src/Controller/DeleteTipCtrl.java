package Controller;

import javax.swing.JOptionPane;

import Model.Model;
import View.MainContainer;
import View.TipsPanel;

public class DeleteTipCtrl {
	public DeleteTipCtrl(Model model, MainContainer view) {
		TipsPanel panel = (TipsPanel) view.getActivePanel();
		String tip = panel.getSelectedDelete();
		int sure = JOptionPane.showConfirmDialog(null, "Are you sure you wish to deleted the '"+tip+"' tip?", "Are you sure?", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		if (sure == JOptionPane.YES_OPTION) {
			model.deleteTip(panel.getSelectedDelete());
			JOptionPane.showMessageDialog(null, "Tip '"+tip+"' has been successfully deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
		}

	}
}

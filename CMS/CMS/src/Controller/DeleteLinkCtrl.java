package Controller;

import javax.swing.JOptionPane;

import Model.Model;
import View.LinksPanel;
import View.MainContainer;

public class DeleteLinkCtrl {
	public DeleteLinkCtrl(Model model, MainContainer view) {
		LinksPanel panel = (LinksPanel) view.getActivePanel();
		String deleteLink = panel.getSelectedDelete();
		int sure = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + deleteLink + "?", "Are You Sure?", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		if (sure == JOptionPane.YES_OPTION) {
			model.deleteLink(deleteLink);
			JOptionPane.showMessageDialog(null,"'" + deleteLink + "' successfully deleted!", "Deleted", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}

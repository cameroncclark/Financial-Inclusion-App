package Controller;

import Model.Model;
import View.LinksPanel;
import View.MainContainer;

public class DeleteLinkCtrl {
	public DeleteLinkCtrl(Model model, MainContainer view) {
		LinksPanel panel = (LinksPanel) view.getActivePanel();
		model.deleteLink(panel.getSelectedDelete());
	}
}
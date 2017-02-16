package Controller;

import Model.Model;
import View.LinksPanel;
import View.MainContainer;

public class EditLinkCtrl {
	public EditLinkCtrl(Model model, MainContainer view) {
		LinksPanel panel = (LinksPanel) view.getActivePanel();
		model.editLink("New Link", "Changed Link", "Changed blurb", "Changed website");
	}
}

package Controller;

import Model.Model;
import View.LinksPanel;
import View.MainContainer;

public class AddLinkCtrl {
	public AddLinkCtrl(Model model, MainContainer view) {
		LinksPanel panel = (LinksPanel) view.getActivePanel();
		if (!panel.getAddLinkTitle().equals("") || !panel.getAddLinkBlurb().equals("")
				|| panel.getAddLinkWebsite().equals("")) {
			model.addLink(panel.getAddLinkTitle(), panel.getAddLinkBlurb(), panel.getAddLinkWebsite());
			panel.setAddLinkTitle("");
			panel.setAddLinkBlurb("");
			panel.setAddLinkWebsite("");
		}
	}
}

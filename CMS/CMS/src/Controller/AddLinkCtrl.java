package Controller;

import Model.Model;
import View.LinksPanel;
import View.MainContainer;

public class AddLinkCtrl {
	public AddLinkCtrl(Model model, MainContainer view) {
	LinksPanel panel = (LinksPanel) view.getActivePanel();
	model.addLink("New Link", "blurb", "website");
	}
}

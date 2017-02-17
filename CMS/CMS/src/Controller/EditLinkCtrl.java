package Controller;

import Model.Model;
import View.LinksPanel;
import View.MainContainer;

public class EditLinkCtrl {
	public EditLinkCtrl(Model model, MainContainer view) {
		LinksPanel panel = (LinksPanel) view.getActivePanel();
		System.out.println("I've been called");
		System.out.println(panel.getEditLinkBlurb());
		model.editLink(panel.getSelectedEdit(), panel.getEditLinkTitle(), panel.getEditLinkBlurb(),
				panel.getEditLinkWebsite());
	}
}

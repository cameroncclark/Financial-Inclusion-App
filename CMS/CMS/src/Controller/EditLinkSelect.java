package Controller;

import Model.Model;
import View.LinksPanel;
import View.MainContainer;

public class EditLinkSelect {
	public EditLinkSelect(Model model, MainContainer view) {
		LinksPanel panel = (LinksPanel) view.getActivePanel();
		
		panel.setEditLinkTitle(model.selectLink(panel.getSelectedEdit()).getName());
		panel.setEditLinkBlurb(model.selectLink(panel.getSelectedEdit()).getBlurb());
		panel.setEditLinkWebsite(model.selectLink(panel.getSelectedEdit()).getWebsite());
	}
}

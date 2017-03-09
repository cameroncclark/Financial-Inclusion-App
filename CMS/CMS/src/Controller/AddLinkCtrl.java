package Controller;

import javax.swing.JOptionPane;

import Model.Model;
import View.LinksPanel;
import View.MainContainer;

public class AddLinkCtrl {
	public AddLinkCtrl(Model model, MainContainer view) {
		LinksPanel panel = (LinksPanel) view.getActivePanel();
		String title = panel.getAddLinkTitle();
		if (panel.getAddLinkTitle().replaceAll("\\s+", "").equals("") || panel.getAddLinkBlurb().replaceAll("\\s+", "").equals("")
				|| panel.getAddLinkWebsite().replaceAll("\\s+", "").equals("")) {
			JOptionPane.showMessageDialog(null, "Neither Title, Blurb or Website can be empty!",
					"Blank Fields", JOptionPane.ERROR_MESSAGE);
		} else {
			
			if(panel.getAddLinkTitle().length() < 70){
				if(model.addLink(panel.getAddLinkTitle(), panel.getAddLinkBlurb(), panel.getAddLinkWebsite())){
				panel.setAddLinkTitle("");
				panel.setAddLinkBlurb("");
				panel.setAddLinkWebsite("");
				JOptionPane.showMessageDialog(null, "'" + title + " successfully created!",
						"Success", JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Link '" + title + "' could not be added as it is a duplicate.",
							"Add failed", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Link '" + title + "' is too long - Max 70 Characters.",
						"Too Long", JOptionPane.ERROR_MESSAGE);
			}	
		}
	}
}

package Controller;

import javax.swing.JOptionPane;

import Model.Model;
import View.LinksPanel;
import View.MainContainer;

public class EditLinkCtrl {
	public EditLinkCtrl(Model model, MainContainer view) {
		LinksPanel panel = (LinksPanel) view.getActivePanel();
		String title = panel.getEditLinkTitle();
		if(panel.getEditLinkTitle().replaceAll("\\s+", "").equals("") || panel.getEditLinkBlurb().replaceAll("\\s+", "").equals("") || panel.getEditLinkWebsite().replaceAll("\\s+", "").equals("")){
			JOptionPane.showMessageDialog(null, "Neither Title, Blurb or Website can be empty!",
					"Blank Fields", JOptionPane.ERROR_MESSAGE);
		} else {
			
			if(panel.getEditLinkTitle().length() < 70){
				if(model.editLink(panel.getSelectedEdit(), panel.getEditLinkTitle(), panel.getEditLinkBlurb(),
						panel.getEditLinkWebsite())){
				JOptionPane.showMessageDialog(null,"'" + title + " successfully updated!",
						"Success", JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Link '" + title + "' could not be changed as it is a duplicate name.",
							"Edit failed", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Link '" + title + "' is too long - Max 70 Characters.",
						"Too Long", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}

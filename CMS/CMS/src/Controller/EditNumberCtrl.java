package Controller;

import javax.swing.JOptionPane;

import Model.Model;
import View.MainContainer;
import View.NumbersPanel;

public class EditNumberCtrl {
	public EditNumberCtrl(Model model, MainContainer view) {
		NumbersPanel panel = (NumbersPanel) view.getActivePanel();
		
		if(panel.getEditTitle().replaceAll("\\s+", "").equals("") || panel.getEditBlurb().replaceAll("\\s+", "").equals("") || panel.getEditNumber().replaceAll("\\s+", "").equals("")){
			JOptionPane.showMessageDialog(null, "Neither Title, Blurb or Number can be empty!",
					"Blank Fields", JOptionPane.ERROR_MESSAGE);
		} else {
			String title = panel.getEditTitle();
			if(panel.getEditTitle().length() < 70){
				if(model.editNumber(panel.getSelectedEdit(), panel.getEditTitle(), panel.getEditBlurb(), panel.getEditNumber())){
				JOptionPane.showMessageDialog(null,"'" + title + "' successfully updated!",
						"Success", JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Number '" + title + "' could not be changed as it is a duplicate name.",
							"Edit failed", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Number '" + title + "' is too long - Max 70 Characters.",
						"Too Long", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
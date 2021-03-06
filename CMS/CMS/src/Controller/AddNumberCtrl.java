package Controller;

import javax.swing.JOptionPane;

import Model.Model;
import View.MainContainer;
import View.NumbersPanel;

public class AddNumberCtrl {
	public AddNumberCtrl(Model model, MainContainer view) {
		NumbersPanel panel = (NumbersPanel) view.getActivePanel();
		String title = panel.getAddTitle();
		if(panel.getAddTitle().replaceAll("\\s+", "").equals("") || panel.getAddBlurb().replaceAll("\\s+", "").equals("") || panel.getAddNumber().replaceAll("\\s+", "").equals("")){
			JOptionPane.showMessageDialog(null, "Neither Title, Blurb or Number can be empty!",
					"Blank Fields", JOptionPane.ERROR_MESSAGE);
		} else {
			if(panel.getAddTitle().length() < 70){
				if(model.addNumber(panel.getAddTitle(), panel.getAddBlurb(), panel.getAddNumber())){
				panel.setAddTitle("");
				panel.setAddBlurb("");
				panel.setAddNumber("");
				JOptionPane.showMessageDialog(null, "'" + title + "' successfully created!",
						"Success", JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Number '" + title + "' could not be added as it is a duplicate.",
							"Add failed", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Number '" + title + "' is too long - Max 70 Characters.",
						"Too Long", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}

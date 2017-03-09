package Controller;

import javax.swing.JOptionPane;

import Model.Model;
import View.MainContainer;
import View.TipsPanel;

public class AddTipCtrl {
	public AddTipCtrl(Model model, MainContainer view) {
		TipsPanel panel = (TipsPanel) view.getActivePanel();
		String header = panel.getAddTipHeader();
		String tip = panel.getAddTipHeader();
		if (panel.getAddTipHeader().replaceAll("\\s+", "").equals("")
				|| panel.getAddTipTip().replaceAll("\\s+", "").equals("")) {
			JOptionPane.showMessageDialog(null,
					"Neither the tip header or the tip can be empty.",
					"Blank fields", JOptionPane.ERROR_MESSAGE);
		} else if (panel.getAddTipHeader().length() > 20) {
			JOptionPane
					.showMessageDialog(
							null,
							"Tip header '"
									+ header
									+ "' is too long, it exeeds maximum length of 20 characters.",
							"Exceeds max length", JOptionPane.ERROR_MESSAGE);
		} else if (panel.getAddTipTip().length() > 200) {
			JOptionPane
					.showMessageDialog(
							null,
							"Tip is too long, it exeeds maximum length of 200 characters.",
							"Exceeds max length", JOptionPane.ERROR_MESSAGE);
		} else {
			if(model.addTip(panel.getAddTipHeader(), panel.getAddTipTip())){
			JOptionPane.showMessageDialog(null, "Tip '" + tip
					+ "' successfully added.", "Tip added",
					JOptionPane.INFORMATION_MESSAGE);
			panel.setAddTipHeader("");
			panel.setAddTipTip("");
			}else{
				JOptionPane.showMessageDialog(null, "Tip '" + header + "' could not be added as it is a duplicate.",
						"Add failed", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}

package Controller;

import javax.swing.JOptionPane;

import Model.Model;
import View.MainContainer;
import View.TipsPanel;

public class EditTipCtrl {

	public EditTipCtrl(Model model, MainContainer view) {
		TipsPanel panel = (TipsPanel) view.getActivePanel();
		String header = panel.getEditTipHeader();
		if (panel.getEditTipHeader().replaceAll("\\s+", "").equals("")
				|| panel.getEditTipTip().replaceAll("\\s+", "").equals("")) {
			JOptionPane.showMessageDialog(null,
					"Neither the tip header or the tip can be empty.",
					"Blank fields", JOptionPane.ERROR_MESSAGE);
		} else if (panel.getEditTipHeader().length() > 70) {
			JOptionPane
					.showMessageDialog(
							null,
							"Tip header '"
									+ header
									+ "' is too long, it exeeds maximum length of 70 characters.",
							"Exceeds max length", JOptionPane.ERROR_MESSAGE);
		} else if (panel.getEditTipTip().length() > 200) {
			JOptionPane
					.showMessageDialog(
							null,
							"Tip is too long, it exeeds maximum length of 200 characters.",
							"Exceeds max length", JOptionPane.ERROR_MESSAGE);
		} else {
			if(model.editTip(panel.getSelectedEdit(), panel.getEditTipHeader(),
					panel.getEditTipTip())){
			JOptionPane.showMessageDialog(null, "Tip '" + header
					+ "' successfully edited.", "Tip edited",
					JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null, "Number '" + header + "' could not be changed as it is a duplicate name.",
						"Edit failed", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}

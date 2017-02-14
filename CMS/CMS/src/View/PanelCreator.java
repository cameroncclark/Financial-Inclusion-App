package View;

import java.awt.BorderLayout;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.ActionController;
import Model.Model;

public class PanelCreator {
	String[] categories;
	Panel catPanel;
	Panel numbersPanel;

	public JComponent makeTextPanel(String text) {
		JPanel panel = new JPanel();
		panel.setVisible(true);
		panel.setLayout(new BorderLayout());
		JLabel label = new JLabel(text);
		panel.add(label);

		return panel;
	}

	public JComponent makeCategoriesPanel(Model model, ActionController actionListener) {
		catPanel = new CategoriesPanel(actionListener);
		model.setModelObserver((Observer) catPanel);
		return catPanel.getPanel();
	}

	public JComponent makeNumbersPanel(Model model, ActionController actionListener) {
		numbersPanel = new CategoriesPanel(actionListener);
		model.setModelObserver((Observer) numbersPanel);
		return numbersPanel.getPanel();
	}

	public Panel getActivePanel(int ID) {
		switch (ID) {
		case 0:
			return catPanel;
		case 1:
			return null;
		case 2:
			return null;
		case 3:
			return numbersPanel;
		default:
			return null;
		}

	}

}

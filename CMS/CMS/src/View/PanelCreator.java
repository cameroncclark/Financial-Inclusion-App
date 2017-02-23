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
	Panel contentPanel;
	Panel numbersPanel;
	Panel linksPanel;
	Panel tipsPanel;

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
	
	public JComponent makeContentPanel(Model model, ActionController actionListener) {
		contentPanel = new ContentPanel(actionListener);
		model.setModelObserver((Observer) contentPanel);
		return contentPanel.getPanel();
	}

	public JComponent makeNumbersPanel(Model model, ActionController actionListener) {
		numbersPanel = new NumbersPanel(actionListener);
		model.setModelObserver((Observer) numbersPanel);
		return numbersPanel.getPanel();
	}
	
	public JComponent makeLinksPanel(Model model, ActionController actionListener){
		linksPanel = new LinksPanel(actionListener);
		model.setModelObserver((Observer) linksPanel);
		return linksPanel.getPanel();
	}
	
	public JComponent makeTipsPanel(Model model, ActionController actionListener){
		tipsPanel = new TipsPanel(actionListener);
		model.setModelObserver((Observer) tipsPanel);
		return tipsPanel.getPanel();
	}

	public Panel getActivePanel(int ID) {
		switch (ID) {
		case 0:
			return catPanel;
		case 1:
			return contentPanel;
		case 2:
			return tipsPanel;
		case 3:
			return numbersPanel;
		default:
			return linksPanel;
		}

	}

}

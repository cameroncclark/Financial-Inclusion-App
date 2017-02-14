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
	
	public JComponent makeTextPanel(String text){
		JPanel panel = new JPanel();
		panel.setVisible(true);
		panel.setLayout(new BorderLayout());
		JLabel label = new JLabel(text);
		panel.add(label);
	
		return panel;
	}
	
	public JComponent makeCategoriesPanel(Model model,ActionController actionListener){
		catPanel = new CategoriesPanel(actionListener);
		model.setModelObserver((Observer) catPanel);
		return catPanel.getPanel();
	}
	
	public Panel getActivePanel(int ID){
		return catPanel;
	}

}

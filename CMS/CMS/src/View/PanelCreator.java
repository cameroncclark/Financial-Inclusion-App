package View;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.ActionController;

public class PanelCreator {
	String[] categories;
	
	public JComponent makeTextPanel(String text){
		JPanel panel = new JPanel();
		panel.setVisible(true);
		panel.setLayout(new BorderLayout());
		JLabel label = new JLabel(text);
		panel.add(label);
	
		return panel;
	}
	
	public JComponent makeCategoriesPanel(ActionController actionListener){
		categories = actionListener.initaliseCategoriesTab();
		
		JPanel panel = new JPanel();
		panel.setVisible(true);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Enter Category Name: ");
		label.setBounds(2,2,200,20);
		
		JTextField categoryName = new JTextField();
		categoryName.setBounds(200, 2, 200, 20);
		
		JButton testButton = new JButton("This is a test");
		testButton.setBounds(500, 2, 100, 100);
		testButton.setActionCommand("test");
		testButton.addActionListener(actionListener);
		
		JComboBox list = new JComboBox(categories);
		list.setSelectedIndex(0);
		list.addActionListener(actionListener);
		list.setBounds(2, 100, 500, 100);
		
		panel.add(label);
		panel.add(categoryName);
		panel.add(testButton);
		panel.add(list);
		
		return panel;
	}

}

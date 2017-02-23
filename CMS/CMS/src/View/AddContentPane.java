package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Controller.ActionController;

public class AddContentPane extends JDialog {
	protected ActionController actionListener;
	protected JTextField topicTitleText;
	protected JComboBox categoriesDropdown;
	protected String[] categories;

	
	
	public AddContentPane(ActionController actionListener) {
		this.actionListener = actionListener;
		categories = actionListener.initaliseCategoriesTab();
		setLayout(null);
		setSize(600, 600);
		createComponents();
		setVisible(true);
	}
	
	private void createComponents(){
		JLabel topicTitle = new JLabel("Topic Title:");
		topicTitle.setBounds(2,2,200,20);
		add(topicTitle);
		
		topicTitleText = new JTextField();
		topicTitleText.setBounds(90, 2, 200, 20);
		add(topicTitleText);
		
		categoriesDropdown = new JComboBox(categories);
		categoriesDropdown.setBounds(400, 2, 200, 25);
		add(categoriesDropdown);
		
		JButton saveContent = new JButton("Save");
		saveContent.addActionListener(actionListener);
		saveContent.setActionCommand("addContentPage");
		saveContent.setBounds(450, 525, 125,20);
		add(saveContent);
	}
	
	public String getTopicTitle(){
		return topicTitleText.getText();
	}
	
	public void setTopicTitle(String text){
		topicTitleText.setText(text);
	}
	
	public String getSelectedCategory(){
		return categoriesDropdown.getSelectedItem().toString();
	}
}

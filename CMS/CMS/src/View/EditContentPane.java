package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controller.ActionController;

public class EditContentPane extends JDialog {
	protected ActionController actionListener;
	protected JTextField topicTitleText;
	protected JComboBox categoriesDropdown;
	protected String[] categories;
	protected JTextArea contentArea;
	
	public EditContentPane(ActionController actionListener) {
		this.actionListener = actionListener;
		categories = actionListener.initaliseCategoriesTab();
		actionListener.setTopicsMap();
		setLayout(null);
		setSize(600, 600);
		createComponents();
		setVisible(true);
	}
	
	private void createComponents(){
		JLabel topicTitle = new JLabel("Topic Title:");
		topicTitle.setBounds(2,42,200,20);
		add(topicTitle);
		
		topicTitleText = new JTextField();
		topicTitleText.setBounds(90, 42, 200, 20);
		add(topicTitleText);
		
		JButton selectTopic = new JButton("Load Topic");
		selectTopic.setBounds(450, 10, 125, 20);
		selectTopic.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser topicChooser = new JFileChooser("../../FinancialInclusionApplication/www/content/topics/");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Topic files", "json");
				topicChooser.setFileFilter(filter);
				int returnVal = topicChooser.showOpenDialog(EditContentPane.this);
				if(returnVal == topicChooser.APPROVE_OPTION){
					actionListener.loadSelectedFile(topicChooser.getSelectedFile().getName());
				}
				
			}
		});
		add(selectTopic);
		
		categoriesDropdown = new JComboBox(categories);
		categoriesDropdown.setBounds(400, 42, 200, 25);
		add(categoriesDropdown);
		
		contentArea = new JTextArea();
		contentArea.setLineWrap(true);
		contentArea.setWrapStyleWord(true);
		contentArea.setBounds(10, 60, 500, 300);
		JScrollPane scrollTextArea = new JScrollPane(contentArea);
		scrollTextArea.setBounds(10, 100, 550, 350);
		add(scrollTextArea);
		
		JButton saveContent = new JButton("Save");
		saveContent.addActionListener(actionListener);
		saveContent.setActionCommand("editContentPage");
		saveContent.setBounds(450, 525, 125,20);
		add(saveContent);
	}
	
	public String getTopicTitle(){
		return topicTitleText.getText();
	}
	
	public String getContentText(){
		return contentArea.getText();
	}
	public String getSelectedCategory(){
		return categoriesDropdown.getSelectedItem().toString();
	}
	
	public void setTopic(String topicTitle, String category, String content){
		topicTitleText.setText(topicTitle);
		contentArea.setText("");
		contentArea.append(content);
		categoriesDropdown.setSelectedItem(category);
	}

}

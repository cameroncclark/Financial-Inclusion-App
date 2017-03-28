package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controller.ActionController;

public class DeleteContentPane extends JDialog {
	protected ActionController actionListener;
	protected JTextArea contentArea;
	protected JLabel categoryText;
	protected JLabel topicTitleText;

	public DeleteContentPane(ActionController actionListener) {
		this.actionListener = actionListener;
		setLayout(null);
		setSize(1010, 610);
		createComponents();
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				actionListener.clearActiveFile();
				actionListener.closeContentPane();
			}
		});
	}

	private void createComponents() {

		JButton selectTopic = new JButton("Load Topic");
		selectTopic.setBounds(870, 10, 125, 20);
		selectTopic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser topicChooser = new JFileChooser("../../FinancialInclusionApplication/www/content/topics/");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Topic files", "json");
				topicChooser.setFileFilter(filter);
				int returnVal = topicChooser.showOpenDialog(DeleteContentPane.this);
				if (returnVal == topicChooser.APPROVE_OPTION) {
					actionListener.loadSelectedFile(topicChooser.getSelectedFile().getName(),false);
				}

			}
		});
		add(selectTopic);

		JLabel topicTitle = new JLabel("Topic title:");
		topicTitle.setBounds(5, 40, 100, 20);
		add(topicTitle);

		topicTitleText = new JLabel("");
		topicTitleText.setBounds(100, 40, 200, 20);
		add(topicTitleText);

		JLabel categoryTitle = new JLabel("Category title:");
		categoryTitle.setBounds(5, 70, 100, 20);
		add(categoryTitle);
		
		categoryText = new JLabel("");
		categoryText.setBounds(100, 70, 200, 20);
		add(categoryText);

		contentArea = new JTextArea();
		contentArea.setLineWrap(true);
		contentArea.setWrapStyleWord(true);
		contentArea.setEditable(false);
		contentArea.setBounds(5, 100, 990, 440);
		JScrollPane scrollTextArea = new JScrollPane(contentArea);
		scrollTextArea.setBounds(5, 100, 990, 440);
		add(scrollTextArea);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				actionListener.clearActiveFile();
				actionListener.closeContentPane();
				dispose();
				
			}
		});
		
		cancelButton.setBounds(770, 550, 100,20);
		add(cancelButton);

		JButton deleteContent = new JButton("Delete");
		deleteContent.addActionListener(actionListener);
		deleteContent.setActionCommand("deleteContentPage");
		deleteContent.setBounds(890, 550, 100,20);
		add(deleteContent);
	}
	
	public void setTopic(String topicName, String categoryName, String content){
		topicTitleText.setText(topicName);
		categoryText.setText(categoryName);
		contentArea.setText("");
		contentArea.append(content);
	}

}

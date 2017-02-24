package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controller.ActionController;

public class DeleteContentPane extends JDialog {
	protected ActionController actionListener;
	
	
	public DeleteContentPane(ActionController actionListener) {
		this.actionListener = actionListener;
		setLayout(null);
		setSize(600, 600);
		createComponents();
		setVisible(true);
	}
	
	private void createComponents(){
		
		JButton selectTopic = new JButton("Load Topic");
		selectTopic.setBounds(450, 10, 125, 20);
		selectTopic.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser topicChooser = new JFileChooser("../../FinancialInclusionApplication/www/content/topics/");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Topic files", "json");
				topicChooser.setFileFilter(filter);
				int returnVal = topicChooser.showOpenDialog(DeleteContentPane.this);
				if(returnVal == topicChooser.APPROVE_OPTION){
					actionListener.loadSelectedFile(topicChooser.getSelectedFile().getName());
				}
				
			}
		});
		add(selectTopic);
		
		JLabel topicTitle = new JLabel("Topic title:");
		topicTitle.setBounds(2,40,100,20);
		add(topicTitle);
		
		JLabel topicTitleText = new JLabel("This is the topic title");
		topicTitleText.setBounds(80,40,200,20);
		add(topicTitleText);
		
		JLabel categoryText = new JLabel("Category 12323223343343");
		categoryText.setBounds(400,40,200,20);
		add(categoryText);
		
		JLabel contentText = new JLabel("<html>jcjkdkjcdkjhcdkjhcdjhkdkjhcjkhcdhjkdchjkdssjdhkkjhdsjhkdsjhkdjhkcdkjcdhjkcdcdhjkcshkjcdkhjc</html>");
		contentText.setBounds(30,70,400,400);
		add(contentText);
		
		JButton deleteContent = new JButton("Delete");
		deleteContent.addActionListener(actionListener);
		deleteContent.setActionCommand("deleteContentPage");
		deleteContent.setBounds(450, 525, 125,20);
		add(deleteContent);
	}

}

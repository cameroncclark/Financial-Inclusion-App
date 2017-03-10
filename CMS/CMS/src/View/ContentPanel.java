package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;

import Controller.ActionController;
import Model.Model;

public class ContentPanel implements Panel, Observer{
	JPanel panel;
	ActionController actionListener;
	JDialog activePanel;
	Boolean addActive;
	Model model;
	
	public JDialog getActivePanel() {
		return activePanel;
	}

	public ContentPanel(ActionController actionListener, Model model) {
		this.model = model;
		this.actionListener = actionListener;
		addActive = false;
		createPanel();
	}
	
	@Override
	public void createPanel() {
		panel = new JPanel();
		panel.setVisible(true);
		panel.setLayout(null);
		addContentButton();
	}
	
	private void addContentButton(){
		JButton addTopicButton = new JButton("Add Content");
		addTopicButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addActive = true;
				activePanel = new AddContentPane(actionListener,model);
			}
		});
		addTopicButton.setBounds(2,2,200,20);
		panel.add(addTopicButton);
		
		JButton editTopicButton = new JButton("Edit Content");
		editTopicButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addActive = false;
				activePanel = new EditContentPane(actionListener,model);
			}
		});
		editTopicButton.setBounds(2,100,200,20);
		panel.add(editTopicButton);
		
		JButton deleteTopicButton = new JButton("Delete Content");
		deleteTopicButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				activePanel = new DeleteContentPane(actionListener);
			}
		});
		deleteTopicButton.setBounds(2,200,200,20);
		panel.add(deleteTopicButton);
		
		
	}
	
	public Boolean isAddPaneActive(){
		return addActive;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JComponent getPanel() {
		// TODO Auto-generated method stub
		return panel;
	}

}

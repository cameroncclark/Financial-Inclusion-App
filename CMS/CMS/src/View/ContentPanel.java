package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;

import Controller.ActionController;

public class ContentPanel implements Panel, Observer{
	JPanel panel;
	ActionController actionListener;
	JDialog activePanel;
	
	public JDialog getActivePanel() {
		return activePanel;
	}

	public ContentPanel(ActionController actionListener) {
		this.actionListener = actionListener;
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
				activePanel = new AddContentPane(actionListener);
				activePanel.addWindowListener(new WindowAdapter() {
				    @Override
				    public void windowClosed(WindowEvent e) {
				        
				    }
				});
				
			}
		});
		addTopicButton.setBounds(2,2,200,20);
		panel.add(addTopicButton);
		
		JButton editTopicButton = new JButton("Edit Content");
		editTopicButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				activePanel = new EditContentPane(actionListener);
				activePanel.addWindowListener(new WindowAdapter() {
				    @Override
				    public void windowClosed(WindowEvent e) {
				        
				    }
				});
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

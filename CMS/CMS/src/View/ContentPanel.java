package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
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
		JButton button = new JButton("Add Content");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				activePanel = new AddContentPane(actionListener);
				
			}
		});
		button.setActionCommand("addContent");
		
		button.setBounds(2,2,200,20);
		panel.add(button);
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

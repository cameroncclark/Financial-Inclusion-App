package View;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import Controller.ActionController;

public class LinksPanel implements Panel, Observer {
	JPanel panel;
	ActionController actionListener;
	
	public LinksPanel(ActionController actionListener) {
		this.actionListener = actionListener;
		createPanel();
	}
	
	@Override
	public void createPanel() {
		panel = new JPanel();
		panel.setVisible(true);
		panel.setLayout(null);
		addSection();
	}
	
	public void addSection(){
		JButton addLinkBtn = new JButton("Test add");
		addLinkBtn.setBounds(2, 2, 125, 20);
		addLinkBtn.setActionCommand("addLink");
		addLinkBtn.addActionListener(actionListener);
		
		JButton editLinkBtn = new JButton("Test edit");
		editLinkBtn.setBounds(200, 2, 125, 20);
		editLinkBtn.setActionCommand("editLink");
		editLinkBtn.addActionListener(actionListener);
		
		JButton deleteLinkBtn = new JButton("Test delete");
		deleteLinkBtn.setBounds(400, 2, 125, 20);
		deleteLinkBtn.setActionCommand("deleteLink");
		deleteLinkBtn.addActionListener(actionListener);
		
		panel.add(addLinkBtn);
		panel.add(editLinkBtn);
		panel.add(deleteLinkBtn);
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}

	@Override
	public JComponent getPanel() {
		return panel;
	}

}

package View;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import Controller.ActionController;

public class NumbersPanel implements Panel, Observer {
	JPanel panel;
	ActionController actionListener;
	
	public NumbersPanel(ActionController actionListener) {
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
		JButton addNumberBtn = new JButton("Test add");
		addNumberBtn.setBounds(2, 2, 125, 20);
		addNumberBtn.setActionCommand("addNumber");
		addNumberBtn.addActionListener(actionListener);
		
		JButton editNumberBtn = new JButton("Test edit");
		editNumberBtn.setBounds(200, 2, 125, 20);
		editNumberBtn.setActionCommand("editNumber");
		editNumberBtn.addActionListener(actionListener);
		
		JButton deleteNumberBtn = new JButton("Test delete");
		deleteNumberBtn.setBounds(400, 2, 125, 20);
		deleteNumberBtn.setActionCommand("deleteNumber");
		deleteNumberBtn.addActionListener(actionListener);
		
		panel.add(addNumberBtn);
		panel.add(editNumberBtn);
		panel.add(deleteNumberBtn);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JComponent getPanel() {
		return panel;
	}

	

}

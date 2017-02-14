package View;

import java.util.Observable;
import java.util.Observer;

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

package View;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JPanel;

import Controller.ActionController;

public class TipsPanel implements Panel,Observer{
	ActionController actionListener;
	JPanel panel;
	
	public TipsPanel(ActionController actionListener) {
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
		
	}

	@Override
	public JComponent getPanel() {
		return panel;
	}

	

}

package View;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
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
		addSection();
	}
	
	public void addSection(){
		JButton addTipBtn = new JButton("Test add");
		addTipBtn.setBounds(2, 2, 125, 20);
		addTipBtn.setActionCommand("addTip");
		addTipBtn.addActionListener(actionListener);
		
		JButton editTipBtn = new JButton("Test edit");
		editTipBtn.setBounds(200, 2, 125, 20);
		editTipBtn.setActionCommand("editTip");
		editTipBtn.addActionListener(actionListener);
		
		JButton deleteTipBtn = new JButton("Test delete");
		deleteTipBtn.setBounds(400, 2, 125, 20);
		deleteTipBtn.setActionCommand("deleteTip");
		deleteTipBtn.addActionListener(actionListener);
		
		panel.add(addTipBtn);
		panel.add(editTipBtn);
		panel.add(deleteTipBtn);
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}

	@Override
	public JComponent getPanel() {
		return panel;
	}

	

}

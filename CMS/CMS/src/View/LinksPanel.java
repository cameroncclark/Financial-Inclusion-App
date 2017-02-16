package View;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controller.ActionController;

public class LinksPanel implements Panel, Observer {
	JPanel panel;
	ActionController actionListener;
	JTextField addLinkTitle;
	JTextArea addLinkBlurb;
	JTextField addLinkWebsite;
	
	public LinksPanel(ActionController actionListener) {
		this.actionListener = actionListener;
		createPanel();
	}
	
	@Override
	public void createPanel() {
		panel = new JPanel();
		panel.setVisible(true);
		panel.setLayout(null);
		createAddLinksComponents();
//		creatEditTipsComponents();
//		creatDeleteTipsComponents();
//		addSection();
	}
	
	public void createAddLinksComponents(){
		JLabel addLinkLbl = new JLabel("Add Link:");
		addLinkLbl.setBounds(10, 10, 200, 20);
		
		JLabel addLinkTitleTF = new JLabel("Link Title:");
		addLinkTitleTF.setBounds(30, 50, 200, 20);
		
		addLinkTitle = new JTextField();
		addLinkTitle.setBounds(130, 50, 200, 20);
		
		JLabel addLinkBlurbTF = new JLabel("Link Blurb:");
		addLinkBlurbTF.setBounds(30, 70, 200, 20);
		
		addLinkBlurb = new JTextArea();
		addLinkBlurb.setBounds(134, 72, 396, 96);
		
		JLabel addLinkWebsiteTF = new JLabel("Link Website:");
		addLinkWebsiteTF.setBounds(30, 170, 200, 20);
		
		addLinkWebsite = new JTextField();
		addLinkWebsite.setBounds(130, 170, 200, 20);
		
		JButton addLinkBtn = new JButton("Add Website");
		addLinkBtn.setBounds(525, 180, 125, 20);
		addLinkBtn.setActionCommand("addLink");
		addLinkBtn.addActionListener(actionListener);
		
		JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
		separator.setBounds(2, 205, 675, 10);
		
		panel.add(addLinkLbl);
		panel.add(addLinkTitleTF);
		panel.add(addLinkTitle);
		panel.add(addLinkBlurbTF);
		panel.add(addLinkBlurb);
		panel.add(addLinkWebsiteTF);
		panel.add(addLinkWebsite);
		panel.add(addLinkBtn);
		panel.add(separator);
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
	
	public String getAddLinkTitle(){
		return addLinkTitle.getText().toString();
	}
	
	public void setAddLinkTitle(String text){
		addLinkTitle.setText(text);
	}
	
	public String getAddLinkBlurb(){
		return addLinkBlurb.getText().toString();
	}
	
	public void setAddLinkBlurb(String text){
		addLinkBlurb.setText(text);
	}
	
	public String getAddLinkWebsite(){
		return addLinkWebsite.getText().toString();
	}
	
	public void setAddLinkWebsite(String text){
		addLinkWebsite.setText(text);
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}

	@Override
	public JComponent getPanel() {
		return panel;
	}

}

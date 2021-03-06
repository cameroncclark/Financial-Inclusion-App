package View;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controller.ActionController;

public class LinksPanel implements Panel, Observer {
	JPanel panel;
	ActionController actionListener;
	String[] links;
	JTextField addLinkTitle;
	JTextArea addLinkBlurb;
	JTextField addLinkWebsite;
	JComboBox editLinkList;
	JComboBox deleteLinkList;
	JTextField editLinkTitle;
	JTextArea editLinkBlurb;
	JTextField editLinkWebsite;

	public LinksPanel(ActionController actionListener) {
		this.actionListener = actionListener;
		createPanel();
	}

	@Override
	public void createPanel() {
		links = actionListener.initaliseLinksTab();
		panel = new JPanel();
		panel.setVisible(true);
		panel.setLayout(null);
		createAddLinksComponents();
		creatEditLinksComponents();
		creatDeleteLinksComponents();
	}

	public void createAddLinksComponents() {
		JLabel addLinkLbl = new JLabel("Add Link:");
		addLinkLbl.setBounds(10, 10, 200, 20);

		JLabel addLinkTitleTF = new JLabel("Link Title:");
		addLinkTitleTF.setBounds(30, 50, 200, 20);

		addLinkTitle = new JTextField();
		addLinkTitle.setBounds(130, 50, 200, 20);

		JLabel addLinkBlurbTF = new JLabel("Link Blurb:");
		addLinkBlurbTF.setBounds(34, 70, 200, 20);

		addLinkBlurb = new JTextArea();
		addLinkBlurb.setLineWrap(true);
		addLinkBlurb.setWrapStyleWord(true);
		addLinkBlurb.setBounds(134, 72, 396, 96);
		JScrollPane scrollTextArea = new JScrollPane(addLinkBlurb);
		scrollTextArea.setBounds(130, 72, 396, 96);

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
		panel.add(scrollTextArea);
		panel.add(addLinkWebsiteTF);
		panel.add(addLinkWebsite);
		panel.add(addLinkBtn);
		panel.add(separator);
	}

	public void creatEditLinksComponents() {
		JLabel headerLbl = new JLabel("Edit Link:");
		headerLbl.setBounds(10, 220, 200, 20);

		editLinkList = new JComboBox(links);
		editLinkList.addActionListener(actionListener);
		editLinkList.setActionCommand("editLinkSelect");
		editLinkList.setBounds(460, 220, 200, 25);

		JLabel editLinkTitleTF = new JLabel("Link Title:");
		editLinkTitleTF.setBounds(30, 260, 200, 20);

		editLinkTitle = new JTextField();
		editLinkTitle.setBounds(130, 260, 200, 20);

		JLabel editLinkBlurbTF = new JLabel("Link Blurb:");
		editLinkBlurbTF.setBounds(34, 280, 200, 20);

		editLinkBlurb = new JTextArea();
		editLinkBlurb.setLineWrap(true);
		editLinkBlurb.setWrapStyleWord(true);
		editLinkBlurb.setBounds(134, 282, 396, 96);
		JScrollPane scrollTextArea = new JScrollPane(editLinkBlurb);
		scrollTextArea.setBounds(130, 282, 396, 96);

		JLabel editLinkWebsiteTF = new JLabel("Link Website:");
		editLinkWebsiteTF.setBounds(30, 380, 200, 20);

		editLinkWebsite = new JTextField();
		editLinkWebsite.setBounds(130, 380, 200, 20);

		JButton editLinkBtn = new JButton("Edit Website");
		editLinkBtn.setBounds(525, 390, 125, 20);
		editLinkBtn.setActionCommand("editLink");
		editLinkBtn.addActionListener(actionListener);

		JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
		separator.setBounds(2, 415, 675, 10);

		panel.add(headerLbl);
		panel.add(editLinkList);
		panel.add(editLinkTitleTF);
		panel.add(editLinkTitle);
		panel.add(editLinkBlurbTF);
		panel.add(scrollTextArea);
		panel.add(editLinkWebsiteTF);
		panel.add(editLinkWebsite);
		panel.add(editLinkBtn);
		panel.add(separator);
	}

	public void creatDeleteLinksComponents() {
		JLabel headerLbl = new JLabel("Delete Link:");
		headerLbl.setBounds(10, 430, 200, 20);
		
		deleteLinkList = new JComboBox(links);
		deleteLinkList.addActionListener(actionListener);
		deleteLinkList.setBounds(460, 430, 200, 25);
		
		JButton deleteLinkBtn = new JButton("Delete Link");
		deleteLinkBtn.setBounds(525, 480, 125, 20);
		deleteLinkBtn.setActionCommand("deleteLink");
		deleteLinkBtn.addActionListener(actionListener);
		
		JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
		separator.setBounds(2, 505, 675, 10);
		
		panel.add(headerLbl);
		panel.add(deleteLinkList);
		panel.add(deleteLinkBtn);
		panel.add(separator);
	}

	public void addSection() {
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

	public String getAddLinkTitle() {
		return addLinkTitle.getText().toString();
	}

	public void setAddLinkTitle(String text) {
		addLinkTitle.setText(text);
	}

	public String getAddLinkBlurb() {
		return addLinkBlurb.getText().toString();
	}

	public void setAddLinkBlurb(String text) {
		addLinkBlurb.setText(text);
	}

	public String getAddLinkWebsite() {
		return addLinkWebsite.getText().toString();
	}

	public void setAddLinkWebsite(String text) {
		addLinkWebsite.setText(text);
	}

	public String getEditLinkTitle() {
		return editLinkTitle.getText().toString();
	}

	public void setEditLinkTitle(String text) {
		editLinkTitle.setText(text);
	}

	public String getEditLinkBlurb() {
		return editLinkBlurb.getText().toString();
	}

	public void setEditLinkBlurb(String text) {
		editLinkBlurb.setText("");
		editLinkBlurb.append(text);
	}

	public String getEditLinkWebsite() {
		return editLinkWebsite.getText().toString();
	}

	public void setEditLinkWebsite(String text) {
		editLinkWebsite.setText(text);
	}

	public String getSelectedEdit() {
		return editLinkList.getSelectedItem().toString();
	}
	
	public String getSelectedDelete() {
		return deleteLinkList.getSelectedItem().toString();
	}

	@Override
	public void update(Observable o, Object arg) {
		links = actionListener.initaliseLinksTab();
		refreshComboBoxes();
		panel.repaint();
		panel.revalidate();
	}

	public void refreshComboBoxes() {
		panel.remove(editLinkList);
		panel.remove(deleteLinkList);

		editLinkList = new JComboBox(links);
		editLinkList.addActionListener(actionListener);
		editLinkList.setActionCommand("editLinkSelect");
		editLinkList.setBounds(460, 220, 200, 25);

		deleteLinkList = new JComboBox(links);
		deleteLinkList.addActionListener(actionListener);
		deleteLinkList.setBounds(460, 430, 200, 25);

		panel.add(editLinkList);
		panel.add(deleteLinkList);
	}

	@Override
	public JComponent getPanel() {
		return panel;
	}

}

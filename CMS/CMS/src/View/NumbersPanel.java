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

public class NumbersPanel implements Panel, Observer {
	JPanel panel;
	ActionController actionListener;
	String[] links;
	JTextField addNumberTitle;
	JTextArea addNumberBlurb;
	JTextField addNumber;
	JComboBox editNumbersList;
	JComboBox deleteNumberList;
	JTextField editNumberTitle;
	JTextArea editNumberBlurb;
	JTextField editNumber;
	
	public NumbersPanel(ActionController actionListener) {
		this.actionListener = actionListener;
		createPanel();
	}
	
	@Override
	public void createPanel() {
		links = actionListener.initaliseNumbersTab();
		panel = new JPanel();
		panel.setVisible(true);
		panel.setLayout(null);
		createAddNumbersComponents();
		creatEditNumbersComponents();
		creatDeleteNumbersComponents();
	}
	
	public void createAddNumbersComponents() {
		JLabel headerLbl = new JLabel("Add Number:");
		headerLbl.setBounds(10, 10, 200, 20);

		JLabel addNumberTitleTF = new JLabel("Number Title:");
		addNumberTitleTF.setBounds(30, 50, 200, 20);

		addNumberTitle = new JTextField();
		addNumberTitle.setBounds(130, 50, 200, 20);

		JLabel addLinkBlurbTF = new JLabel("Number Blurb:");
		addLinkBlurbTF.setBounds(34, 70, 200, 20);

		addNumberBlurb = new JTextArea();
		addNumberBlurb.setLineWrap(true);
		addNumberBlurb.setWrapStyleWord(true);
		addNumberBlurb.setBounds(134, 72, 396, 96);
		JScrollPane scrollTextArea = new JScrollPane(addNumberBlurb);
		scrollTextArea.setBounds(130, 72, 396, 96);

		JLabel addNumberTF = new JLabel("Phone Number:");
		addNumberTF.setBounds(30, 170, 200, 20);

		addNumber = new JTextField();
		addNumber.setBounds(130, 170, 200, 20);

		JButton addNumberBtn = new JButton("Add Number");
		addNumberBtn.setBounds(525, 180, 125, 20);
		addNumberBtn.setActionCommand("addNumber");
		addNumberBtn.addActionListener(actionListener);

		JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
		separator.setBounds(2, 205, 675, 10);

		panel.add(headerLbl);
		panel.add(addNumberTitleTF);
		panel.add(addNumberTitle);
		panel.add(addLinkBlurbTF);
		panel.add(scrollTextArea);
		panel.add(addNumberTF);
		panel.add(addNumber);
		panel.add(addNumberBtn);
		panel.add(separator);
	}
	
	public void creatEditNumbersComponents() {
		JLabel headerLbl = new JLabel("Edit Number:");
		headerLbl.setBounds(10, 220, 200, 20);

		editNumbersList = new JComboBox(links);
		editNumbersList.addActionListener(actionListener);
		editNumbersList.setActionCommand("editNumberSelect");
		editNumbersList.setBounds(460, 220, 200, 25);

		JLabel editLinkTitleTF = new JLabel("Number Title:");
		editLinkTitleTF.setBounds(30, 260, 200, 20);

		editNumberTitle = new JTextField();
		editNumberTitle.setBounds(130, 260, 200, 20);

		JLabel editLinkBlurbTF = new JLabel("Number Blurb:");
		editLinkBlurbTF.setBounds(34, 280, 200, 20);

		editNumberBlurb = new JTextArea();
		editNumberBlurb.setLineWrap(true);
		editNumberBlurb.setWrapStyleWord(true);
		editNumberBlurb.setBounds(134, 282, 396, 96);
		JScrollPane scrollTextArea = new JScrollPane(editNumberBlurb);
		scrollTextArea.setBounds(130, 282, 396, 96);

		JLabel editLinkWebsiteTF = new JLabel("Phone Number:");
		editLinkWebsiteTF.setBounds(30, 380, 200, 20);

		editNumber = new JTextField();
		editNumber.setBounds(130, 380, 200, 20);

		JButton editLinkBtn = new JButton("Edit Number");
		editLinkBtn.setBounds(525, 390, 125, 20);
		editLinkBtn.setActionCommand("editNumber");
		editLinkBtn.addActionListener(actionListener);

		JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
		separator.setBounds(2, 415, 675, 10);

		panel.add(headerLbl);
		panel.add(editNumbersList);
		panel.add(editLinkTitleTF);
		panel.add(editNumberTitle);
		panel.add(editLinkBlurbTF);
		panel.add(scrollTextArea);
		panel.add(editLinkWebsiteTF);
		panel.add(editNumber);
		panel.add(editLinkBtn);
		panel.add(separator);
	}
	
	public void creatDeleteNumbersComponents() {
		JLabel headerLbl = new JLabel("Delete Number:");
		headerLbl.setBounds(10, 430, 200, 20);
		
		deleteNumberList = new JComboBox(links);
		deleteNumberList.addActionListener(actionListener);
		deleteNumberList.setBounds(460, 430, 200, 25);
		
		JButton deleteNumberBtn = new JButton("Delete Number");
		deleteNumberBtn.setBounds(525, 480, 125, 20);
		deleteNumberBtn.setActionCommand("deleteNumber");
		deleteNumberBtn.addActionListener(actionListener);
		
		JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
		separator.setBounds(2, 505, 675, 10);
		
		panel.add(headerLbl);
		panel.add(deleteNumberList);
		panel.add(deleteNumberBtn);
		panel.add(separator);
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
	
	public String getAddTitle() {
		return addNumberTitle.getText().toString();
	}

	public void setAddTitle(String text) {
		addNumberTitle.setText(text);
	}

	public String getAddBlurb() {
		return addNumberBlurb.getText().toString();
	}

	public void setAddBlurb(String text) {
		addNumberBlurb.setText(text);
	}

	public String getAddNumber() {
		return addNumber.getText().toString();
	}

	public void setAddNumber(String text) {
		addNumber.setText(text);
	}
	
	public String getEditTitle() {
		return editNumberTitle.getText().toString();
	}

	public void setEditTitle(String text) {
		editNumberTitle.setText(text);
	}

	public String getEditBlurb() {
		return editNumberBlurb.getText().toString();
	}

	public void setEditBlurb(String text) {
		editNumberBlurb.setText(text);
	}

	public String getEditNumber() {
		return editNumber.getText().toString();
	}

	public void setEditNumber(String text) {
		editNumber.setText(text);
	}
	
	public String getSelectedEdit() {
		return editNumbersList.getSelectedItem().toString();
	}
	
	public String getSelectedDelete() {
		return deleteNumberList.getSelectedItem().toString();
	}

	@Override
	public void update(Observable o, Object arg) {
		links = actionListener.initaliseNumbersTab();
		refreshComboBoxes();
		panel.repaint();
		panel.revalidate();
	}

	public void refreshComboBoxes() {
		panel.remove(editNumbersList);
		panel.remove(deleteNumberList);

		editNumbersList = new JComboBox(links);
		editNumbersList.addActionListener(actionListener);
		editNumbersList.setActionCommand("editNumberSelect");
		editNumbersList.setBounds(460, 220, 200, 25);

		deleteNumberList = new JComboBox(links);
		deleteNumberList.addActionListener(actionListener);
		deleteNumberList.setBounds(460, 430, 200, 25);

		panel.add(editNumbersList);
		panel.add(deleteNumberList);
	}
	

	@Override
	public JComponent getPanel() {
		return panel;
	}

	

}

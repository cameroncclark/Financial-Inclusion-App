package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class TipsPanel implements Panel,Observer{
	ActionController actionListener;
	JPanel panel;
	JTextField addTipHeader;
	JTextArea addTipTip;
	JComboBox editTipList;
	JComboBox deleteTipList;
	JTextField editTipHeader;
	JTextArea editTipTip;
	String[] tips;
	
	public TipsPanel(ActionController actionListener) {
		this.actionListener = actionListener;
		createPanel();
	}
	
	@Override
	public void createPanel() {
		tips = actionListener.initaliseTipsTab();
		panel = new JPanel();
		panel.setVisible(true);
		panel.setLayout(null);
		createAddTipsComponents();
		creatEditTipsComponents();
		creatDeleteTipsComponents();
	}
	
	private void createAddTipsComponents(){
		JLabel addTipLbl = new JLabel("Add Tip:");
		addTipLbl.setBounds(10, 10, 200, 20);
		
		JLabel addTipHeaderTF = new JLabel("Tip Header:");
		addTipHeaderTF.setBounds(30, 50, 200, 20);
		
		addTipHeader = new JTextField();
		addTipHeader.setBounds(200, 50, 150, 20);
		
		JLabel addTipTipTF = new JLabel("Tip Text (MAX 200 Chars):");
		addTipTipTF.setBounds(34, 70, 200, 20);
		
		addTipTip = new JTextArea();
		addTipTip.setLineWrap(true);
		addTipTip.setWrapStyleWord(true);
		addTipTip.setBounds(200, 72, 250, 20);
		JScrollPane scrollTextArea = new JScrollPane(addTipTip);
		scrollTextArea.setBounds(200, 72, 396, 96);
		
		JButton addTipBtn = new JButton("Add Tip");
		addTipBtn.setBounds(525, 180, 125, 20);
		addTipBtn.setActionCommand("addTip");
		addTipBtn.addActionListener(actionListener);
		
		JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
		separator.setBounds(2, 205, 675, 10);
		
		panel.add(addTipLbl);
		panel.add(addTipHeaderTF);
		panel.add(addTipHeader);
		panel.add(addTipTipTF);
		panel.add(scrollTextArea);
		panel.add(addTipBtn);
		panel.add(separator);
	}
	
	private void creatEditTipsComponents(){
		JLabel editTipLbl = new JLabel("Edit Tip:");
		editTipLbl.setBounds(10, 220, 200, 20);
		
		editTipList = new JComboBox(tips);
		editTipList.addActionListener(actionListener);
		editTipList.setActionCommand("editTipSelect");
		editTipList.setBounds(460, 220, 200, 25);
		
		JLabel editTipHeaderTF = new JLabel("Tip Header:");
		editTipHeaderTF.setBounds(30, 260, 200, 20);
		
		editTipHeader = new JTextField();
		editTipHeader.setBounds(200, 260, 150, 20);
		
		JLabel editTipTipTF = new JLabel("Tip Text (MAX 200 Chars):");
		editTipTipTF.setBounds(34, 280, 200, 20);
		
		editTipTip = new JTextArea();
		editTipTip.setLineWrap(true);
		editTipTip.setWrapStyleWord(true);
		editTipTip.setBounds(200, 282, 250, 20);
		JScrollPane scrollTextArea = new JScrollPane(editTipTip);
		scrollTextArea.setBounds(200, 282, 396, 96);
		
		
		JButton editTipBtn = new JButton("Edit Tip");
		editTipBtn.setBounds(525, 390, 125, 20);
		editTipBtn.setActionCommand("editTip");
		editTipBtn.addActionListener(actionListener);
		
		JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
		separator.setBounds(2, 415, 675, 10);
		
		panel.add(editTipLbl);
		panel.add(editTipList);
		panel.add(editTipHeaderTF);
		panel.add(editTipHeader);
		panel.add(editTipTipTF);
		panel.add(scrollTextArea);
		panel.add(editTipBtn);
		panel.add(separator);
	}
	
	private void creatDeleteTipsComponents(){
		JLabel deleteTipLbl = new JLabel("Delete Tip:");
		deleteTipLbl.setBounds(10, 430, 200, 20);
		
		deleteTipList = new JComboBox(tips);
		deleteTipList.addActionListener(actionListener);
		deleteTipList.setBounds(460, 430, 200, 25);
		
		JButton deleteTipBtn = new JButton("Delete tip");
		deleteTipBtn.setBounds(525, 480, 125, 20);
		deleteTipBtn.setActionCommand("deleteTip");
		deleteTipBtn.addActionListener(actionListener);
		
		JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
		separator.setBounds(2, 505, 675, 10);
		
		panel.add(deleteTipLbl);
		panel.add(deleteTipList);
		panel.add(deleteTipBtn);
		panel.add(separator);
	}
	
	public String getAddTipHeader(){
		return addTipHeader.getText().toString();
	}
	
	public void setAddTipHeader(String text){
		addTipHeader.setText(text);
	}
	
	public String getAddTipTip(){
		return addTipTip.getText().toString();
	}
	
	public void setAddTipTip(String text){
		addTipTip.setText(text);
	}
	
	public String getEditTipHeader(){
		return editTipHeader.getText().toString();
	}
	
	public void setEditTipHeader(String text){
		editTipHeader.setText(text);
	}
	
	public String getEditTipTip(){
		return editTipTip.getText().toString();
	}
	
	public void setEditTipTip(String text){
		editTipTip.setText(text);
	}
	
	public String getSelectedEdit(){
		return editTipList.getSelectedItem().toString();
	}
	
	public String getSelectedDelete(){
		return deleteTipList.getSelectedItem().toString();
	}

	@Override
	public void update(Observable o, Object arg) {
		tips = actionListener.initaliseTipsTab();
		refreshComboBoxes();
		panel.repaint();
		panel.revalidate();
	}
	
	public void refreshComboBoxes() {
		panel.remove(editTipList);
		panel.remove(deleteTipList);
		
		editTipList = new JComboBox(tips);
		editTipList.setSelectedIndex(0);
		editTipList.addActionListener(actionListener);
		editTipList.setActionCommand("editTipSelect");
		editTipList.setBounds(460, 220, 200, 25);
		editTipList.setVisible(true);
		
		deleteTipList = new JComboBox(tips);
		deleteTipList.addActionListener(actionListener);
		deleteTipList.setBounds(460, 430, 200, 25);
		
		panel.add(editTipList);
		panel.add(deleteTipList);
	}

	@Override
	public JComponent getPanel() {
		return panel;
	}

	

}

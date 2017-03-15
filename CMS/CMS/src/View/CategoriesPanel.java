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
import javax.swing.JSeparator;
import javax.swing.JTextField;

import Controller.ActionController;

public class CategoriesPanel implements Panel, Observer {
	JPanel panel;
	ActionController actionListener;
	String[] categories;
	JTextField addCTF;
	JTextField editCTF;
	JComboBox editCatList;
	JComboBox deleteCatList;

	public CategoriesPanel(ActionController actionListener) {
		this.actionListener = actionListener;
		createPanel();
	}

	public void createPanel() {
		categories = actionListener.initaliseCategoriesTab();
		panel = new JPanel();
		panel.setVisible(true);
		panel.setLayout(null);
		createAddCategoriesComponents();
		createEditCategoriesComponents();
		createDeleteCategoriesComponents();
	}
	
	private void createAddCategoriesComponents(){
		JLabel addCategoryLbl = new JLabel("Add Category:");
		addCategoryLbl.setBounds(10, 10, 200, 20);

		JLabel addCategoryTF = new JLabel("New Category:");
		addCategoryTF.setBounds(130, 100, 200, 20);
		
		addCTF = new JTextField();
		addCTF.setBounds(230, 100, 200, 20);

		JButton addCategoryBtn = new JButton("Add Category");
		addCategoryBtn.setBounds(525, 180, 125, 20);
		addCategoryBtn.setActionCommand("addCategory");
		addCategoryBtn.addActionListener(actionListener);
		
		JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
		separator.setBounds(2, 205, 675, 10);

		panel.add(addCategoryLbl);
		panel.add(addCategoryTF);
		panel.add(addCTF);
		panel.add(addCategoryBtn);
		panel.add(separator);
	}
	
	private void createEditCategoriesComponents(){
		JLabel editCategoryLbl = new JLabel("Edit Category:");
		editCategoryLbl.setBounds(10, 220, 200, 20);

		editCatList = new JComboBox(categories);
		editCatList.addActionListener(actionListener);
		editCatList.setActionCommand("editCategorySelect");
		editCatList.setBounds(460, 220, 200, 25);
		editCatList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				editCTF.setText(editCatList.getSelectedItem().toString());
			}
		});

		JLabel editCategoryTF = new JLabel("Edit Category:");
		editCategoryTF.setBounds(130, 310, 200, 20);
		
		editCTF = new JTextField();
		editCTF.setBounds(230, 310, 200, 20);
		editCTF.setText(editCatList.getSelectedItem().toString());

		JButton editCategoryBtn = new JButton("Edit Category");
		editCategoryBtn.setBounds(525, 390, 125, 20);
		editCategoryBtn.setActionCommand("editCategory");
		editCategoryBtn.addActionListener(actionListener);
		
		JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
		separator.setBounds(2, 415, 675, 10);
		
		panel.add(editCategoryLbl);
		panel.add(editCatList);
		panel.add(editCTF);
		panel.add(editCategoryTF);
		panel.add(editCategoryBtn);
		panel.add(separator);
		
	}
	
	private void createDeleteCategoriesComponents(){
		JLabel deleteCategoryLbl = new JLabel("Delete Category:");
		deleteCategoryLbl.setBounds(10, 430, 200, 20);
		
		deleteCatList = new JComboBox(categories);
		deleteCatList.setSelectedIndex(0);
		deleteCatList.addActionListener(actionListener);
		deleteCatList.setActionCommand("deleteCategorySelect");
		deleteCatList.setBounds(460, 430, 200, 25);

		JButton deleteCategoryBtn = new JButton("Delete Category");
		deleteCategoryBtn.setBounds(525, 480, 125, 20);
		deleteCategoryBtn.setActionCommand("deleteCategory");
		deleteCategoryBtn.addActionListener(actionListener);
		
		JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
		separator.setBounds(2, 505, 675, 10);
		
		panel.add(deleteCategoryLbl);
		panel.add(deleteCatList);
		panel.add(deleteCategoryBtn);
		panel.add(separator);
	}

	public String getAddCategoryText() {
		return addCTF.getText();
	}

	public void clearAddCategoryText() {
		addCTF.setText("");
	}
	
	public String getEditCategoryText() {
		return editCTF.getText();
	}

	public JComponent getPanel() {
		return panel;
	}

	public String getGetSelectedEditItem() {
		return editCatList.getSelectedItem().toString();
	}
	
	public String getGetSelectedDeleteItem() {
		return deleteCatList.getSelectedItem().toString();
	}

	@Override
	public void update(Observable o, Object arg) {
		categories = actionListener.initaliseCategoriesTab();
		refreshComboBoxes();
		panel.repaint();
		panel.revalidate();
	}

	public void refreshComboBoxes() {
		panel.remove(editCatList);
		panel.remove(deleteCatList);
		
		editCatList = new JComboBox(categories);
		editCatList.setSelectedIndex(0);
		editCatList.addActionListener(actionListener);
		editCatList.setActionCommand("editCategorySelect");
		editCatList.setBounds(460, 160, 200, 25);
		editCatList.setVisible(true);
		editCatList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				editCTF.setText(editCatList.getSelectedItem().toString());
			}
		});
		editCTF.setText(editCatList.getSelectedItem().toString());
		
		deleteCatList = new JComboBox(categories);
		deleteCatList.setSelectedIndex(0);
		deleteCatList.addActionListener(actionListener);
		deleteCatList.setActionCommand("deleteCategorySelect");
		deleteCatList.setBounds(460, 320, 200, 25);
		editCatList.setVisible(true);
		
		panel.add(editCatList);
		panel.add(deleteCatList);
	}

}

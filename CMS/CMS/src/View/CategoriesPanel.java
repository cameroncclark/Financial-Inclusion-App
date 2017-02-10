package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
		addCategoryLbl.setBounds(2, 2, 200, 20);

		addCTF = new JTextField();
		addCTF.setBounds(2, 50, 150, 20);

		JButton addCategoryBtn = new JButton("Add Category");
		addCategoryBtn.setBounds(500, 70, 125, 20);
		addCategoryBtn.setActionCommand("addCategory");
		addCategoryBtn.addActionListener(actionListener);

		panel.add(addCategoryLbl);
		panel.add(addCTF);
		panel.add(addCategoryBtn);
	}
	
	private void createEditCategoriesComponents(){
		JLabel editCategoryLbl = new JLabel("Edit Category:");
		editCategoryLbl.setBounds(2, 102, 200, 20);

		editCatList = new JComboBox(categories);
		editCatList.addActionListener(actionListener);
		editCatList.setActionCommand("editCategorySelect");
		editCatList.setBounds(2, 120, 250, 25);
		editCatList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				editCTF.setText(editCatList.getSelectedItem().toString());
			}
		});

		editCTF = new JTextField();
		editCTF.setBounds(2, 150, 150, 20);
		editCTF.setText(editCatList.getSelectedItem().toString());

		JButton editCategoryBtn = new JButton("Edit Category");
		editCategoryBtn.setBounds(500, 120, 125, 20);
		editCategoryBtn.setActionCommand("editCategory");
		editCategoryBtn.addActionListener(actionListener);

		panel.add(editCategoryLbl);
		panel.add(editCTF);
		panel.add(editCategoryBtn);
		panel.add(editCatList);
	}
	
	private void createDeleteCategoriesComponents(){
		JLabel deleteCategoryLbl = new JLabel("Delete Category:");
		deleteCategoryLbl.setBounds(2, 202, 200, 20);
		
		deleteCatList = new JComboBox(categories);
		deleteCatList.setSelectedIndex(0);
		deleteCatList.addActionListener(actionListener);
		deleteCatList.setActionCommand("deleteCategorySelect");
		deleteCatList.setBounds(2, 320, 250, 25);

		JButton deleteCategoryBtn = new JButton("Delete Category");
		deleteCategoryBtn.setBounds(500, 320, 125, 20);
		deleteCategoryBtn.setActionCommand("deleteCategory");
		deleteCategoryBtn.addActionListener(actionListener);
		
		panel.add(deleteCategoryLbl);
		panel.add(deleteCatList);
		panel.add(deleteCategoryBtn);
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
		for(String cat:categories){
			System.out.println(cat);
		}
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
		editCatList.setBounds(2, 120, 250, 25);
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
		deleteCatList.setBounds(2, 320, 250, 25);
		
		panel.add(editCatList);
		panel.add(deleteCatList);
	}

}
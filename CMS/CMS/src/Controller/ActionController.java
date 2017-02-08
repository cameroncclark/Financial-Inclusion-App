package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Model;

public class ActionController implements ActionListener {
	Model model;
	
	
	public ActionController(Model model){
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "test":
			model.addCategory();;
		}
		
	}
	
	public String[] initaliseCategoriesTab(){
		String[] categories = model.selectCategories();
		return categories;
	}
	
	

}

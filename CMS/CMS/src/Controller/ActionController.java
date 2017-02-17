package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Model;
import View.MainContainer;

public class ActionController implements ActionListener {
	Model model;
	MainContainer view;

	public ActionController(Model model) {
		this.model = model;
	}

	public void setMainContainer(MainContainer view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "addCategory":
			AddCategoryCtrl addCategoryCtrl = new AddCategoryCtrl(model, view);
			break;
		case "editCategory":
			EditCategoryCtrl editCatCrtl = new EditCategoryCtrl(model, view);
			break;
		case "deleteCategory":
			DeleteCategoryCtrl dltCatCtrl = new DeleteCategoryCtrl(model, view);
			break;
		case "addNumber":
			AddNumberCtrl addNumberCtrl = new AddNumberCtrl(model, view);
			break;
		case "editNumber":
			EditNumberCtrl editNumberCtrl = new EditNumberCtrl(model, view);
			break;
		case "deleteNumber":
			DeleteNumberCtrl deleteNumberCtrl = new DeleteNumberCtrl(model, view);
			break;
		case "addLink":
			AddLinkCtrl addLinkCtrl = new AddLinkCtrl(model, view);
			break;
		case "editLink":
			EditLinkCtrl editLinkCtrl = new EditLinkCtrl(model, view);
			break;
		case "deleteLink":
			DeleteLinkCtrl deleteLinkCtrl = new DeleteLinkCtrl(model, view);
			break;
		case "addTip":
			AddTipCtrl addTipCtrl = new AddTipCtrl(model, view);
			break;
		case "editTip":
			EditTipCtrl editTipCtrl = new EditTipCtrl(model, view);
			break;
		case "deleteTip":
			DeleteTipCtrl deleteTipCtrl = new DeleteTipCtrl(model, view);
			break;
		case "editTipSelect":
			EditTipSelect editTipSelect = new EditTipSelect(model, view);
			break;
		case "editLinkSelect":
			EditLinkSelect editLinkSelect = new EditLinkSelect(model, view);
			break;
		case "editNumberSelect":
			EditNumberSelect editNumberSelect = new EditNumberSelect(model, view);
			break;	

		}

	}

	public String[] initaliseCategoriesTab() {
		return model.selectCategories();
	}

	public String[] initaliseTipsTab() {
		return model.selectTips();
	}

	public String[] initaliseLinksTab() {
		return model.selectLinks();
	}
	
	public String[] initaliseNumbersTab() {
		return model.selectNumbers();
	}

}

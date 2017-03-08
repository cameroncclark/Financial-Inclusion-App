package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ContentObjects.Topic;
import Model.Model;
import View.ContentPanel;
import View.DeleteContentPane;
import View.EditContentPane;
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
		case "addContentPage":
			AddContentCtrl addContentCtrl = new AddContentCtrl(model, view);
			break;
		case "editContentPage":
			EditContentCtrl editContentCtrl = new EditContentCtrl(model, view);
			break;
		case "deleteContentPage":
			DeleteContentCtrl deleteContentCtrl = new DeleteContentCtrl(model,view);
			break;
		case "addQuestion":
			AddQuestionCtrl addQuestionCtrl = new AddQuestionCtrl(model,view);
			break;
		case "editQuestion":
			EditQuestionCtrl editQuestionCtrl = new EditQuestionCtrl(model,view);
			break;
		case "deleteQuestion":
			DeleteQuestionCtrl deleteQuestionCtrl = new DeleteQuestionCtrl(model,view);
			break;
		case "createQuiz":
			CreateQuizController createQuizController = new CreateQuizController(model,view);
			break;
		case "saveQuiz":
			SaveQuizController saveQuizController = new SaveQuizController(model,view);
			break;
		case "saveQuizQuestion":
			AddQuizQuestionCtrl addQuizQuestionCtrl = new AddQuizQuestionCtrl(model, view);
			break;
		case "removeQuizQuestion":
			RemoveQuizQuestionCtrl removeQuizQuestionCtrl = new RemoveQuizQuestionCtrl(model, view);
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

	public void setTopicsMap() {
		model.setTopics();
	}
	
	public void clearActiveFile(){
		model.clearActiveFile();
	}
	
	public void intialiseQuiz(){
		model.initialiseQuiz();
	}

	public void loadSelectedFile(String fileName, boolean editPane ) {
		ContentPanel panel = (ContentPanel) view.getActivePanel();
		if(editPane){
			EditContentPane activePanel = (EditContentPane) panel.getActivePanel();
			Topic selectedTopic = model.selectTopic(fileName);
			activePanel.setTopic(selectedTopic.getTitle(), model.selectCategoryName(selectedTopic.getReference()),
					selectedTopic.getContent());
		}else{
			DeleteContentPane activePanel = (DeleteContentPane) panel.getActivePanel();
			Topic selectedTopic = model.selectTopic(fileName);
			activePanel.setTopic(selectedTopic.getTitle(), model.selectCategoryName(selectedTopic.getReference()),
					selectedTopic.getContent());
		}
		
	}

	public void copyImageFile(String name) {
		model.copyImageToContent(name);
	}
	
	public String[] getQuestions(){
		return model.getQuestionText();
	}

}

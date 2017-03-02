package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import Controller.ActionController;

public class QuizPane extends JDialog {
	protected ActionController actionListener;
	protected JTextField quizTitleText;
	protected JList<String> quizList;
	protected JButton removeQuizQuestion;
	protected JButton addQuizQuestion;
	protected JButton saveQuiz;
	
	JDialog activePanel;
	
	public QuizPane(ActionController actionListener) {
		this.actionListener = actionListener;
		setLayout(null);
		setSize(500, 500);
		createLayout();
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e)
		    {
//				System.out.println("I Get Here!");
		    }
		});
	}
	
	private void createLayout(){
		JLabel topicTitle = new JLabel("Quiz Title:");
		topicTitle.setBounds(2,10,200,20);
		add(topicTitle);
		
		quizTitleText = new JTextField();
		quizTitleText.setBounds(90, 10, 200, 20);
		add(quizTitleText);
	
		String[] listData = {"test1","test2"};
		quizList = new JList<>(listData);
		quizList.setBounds(50, 40, 350, 350);
		add(quizList);
		
		removeQuizQuestion = new JButton("Remove");
		removeQuizQuestion.setBounds(150, 400, 100, 20);
		add(removeQuizQuestion);
		
		addQuizQuestion = new JButton("Add");
		addQuizQuestion.setBounds(250, 400, 100, 20);
		addQuizQuestion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				activePanel = new QuizAnswerPane(actionListener);
				
			}
		});
		add(addQuizQuestion);
		
		saveQuiz = new JButton("Save");
		saveQuiz.setBounds(200, 420, 100, 20);
		add(saveQuiz);
	}

}

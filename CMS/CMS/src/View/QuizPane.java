package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import Controller.ActionController;
import Model.Model;

public class QuizPane extends JDialog implements Observer {
	protected ActionController actionListener;
	protected JTextField quizTitleText;
	protected JList<String> quizList;
	protected JButton removeQuizQuestion;
	protected JButton addQuizQuestion;
	protected JButton editQuizQuestion;
	protected JButton saveQuiz;
	protected String[] questionText;
	protected JDialog activePanel;

	public QuizPane(ActionController actionListener) {
		this.actionListener = actionListener;
		actionListener.intialiseQuiz();
		setLayout(null);
		setSize(500, 460);
		questionText = actionListener.getQuestions();
		createLayout();
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

			}
		});
	}

	private void createLayout() {
		JLabel topicTitle = new JLabel("Quiz Title:");
		topicTitle.setBounds(5, 10, 200, 20);
		add(topicTitle);

		quizTitleText = new JTextField();
		quizTitleText.setText(actionListener.getQuizTitle());
		quizTitleText.setBounds(5, 30, 475, 20);
		add(quizTitleText);

		quizList = new JList<>(questionText);
		quizList.setBounds(10, 60, 350, 350);
		add(quizList);

		addQuizQuestion = new JButton("Add Question");
		addQuizQuestion.setBounds(365, 60, 115, 20);
		addQuizQuestion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				activePanel = new QuizAnswerPane(actionListener);

			}
		});
		add(addQuizQuestion);

		editQuizQuestion = new JButton("Edit Question");
		editQuizQuestion.setBounds(365, 90, 115, 20);
		editQuizQuestion.setActionCommand("editQuizQuestion");
		editQuizQuestion.addActionListener(actionListener);
		editQuizQuestion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (getSelectedQuestion() != null) {
					activePanel = new QuizAnswerPane(actionListener);
				}

			}
		});
		add(editQuizQuestion);

		removeQuizQuestion = new JButton("Delete Question");
		removeQuizQuestion.setBounds(365, 120, 115, 20);
		removeQuizQuestion.addActionListener(actionListener);
		removeQuizQuestion.setActionCommand("removeQuizQuestion");
		add(removeQuizQuestion);

		saveQuiz = new JButton("Save");
		saveQuiz.setBounds(365, 390, 115, 20);
		saveQuiz.addActionListener(actionListener);
		saveQuiz.setActionCommand("saveQuiz");
		add(saveQuiz);
		
		JButton cancel = new JButton("Cancel");
		cancel.setBounds(365, 360, 115, 20);
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();				
			}
		});
		add(cancel);
	}

	public String getSelectedQuestion() {
		return quizList.getSelectedValue();
	}

	public String getQuizTitle() {
		return quizTitleText.getText();
	}

	public JDialog getActivePanel() {
		return activePanel;
	}

	@Override
	public void update(Observable o, Object arg) {
		questionText = actionListener.getQuestions();
		this.remove(quizList);
		quizList = new JList<>(questionText);
		quizList.setBounds(10, 60, 350, 350);
		add(quizList);
		repaint();
		revalidate();
	}

}

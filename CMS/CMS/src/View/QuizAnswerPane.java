package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controller.ActionController;

public class QuizAnswerPane extends JDialog {
	protected ActionController actionListener;
	protected JTextField quizAnswerTitleText;
	protected JComboBox<String> questionType;
	protected JComboBox<String> numQuestionAnswers;
	protected JComboBox<String> correctAnswers;
	protected JPanel answersPanel;
	protected JButton save;
	protected ArrayList<JTextArea> answers = new ArrayList<>();
	protected ArrayList<JTextArea> reasons = new ArrayList<>();
	protected ArrayList<JButton> pictureLoaders = new ArrayList<>();
	protected ArrayList<JLabel> selectedFile = new ArrayList<>();
	protected ArrayList<Path> picturePath = new ArrayList<>();
	protected boolean edit;
	protected String oldQuestion;
	protected boolean imageInUse;

	public QuizAnswerPane(ActionController actionListener) {
		this.actionListener = actionListener;
		setLayout(null);
		setSize(600, 650);
		createLayout();
		setVisible(true);
		edit = false;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
			}
		});
	}

	private void createLayout() {
		JLabel topicTitle = new JLabel("Quiz Question:");
		topicTitle.setBounds(5, 10, 200, 20);
		add(topicTitle);

		quizAnswerTitleText = new JTextField();
		quizAnswerTitleText.setBounds(5, 30, 580, 20);
		add(quizAnswerTitleText);

		JLabel questionTypeText = new JLabel("Question type:");
		questionTypeText.setBounds(5, 60, 200, 20);
		add(questionTypeText);
		
		String[] listData = { "Please Select", "True/False", "Multiple Choice", "Image Multiple Choice" };
		questionType = new JComboBox<>(listData);
		questionType.setBounds(5, 80, 200, 20);
		questionType.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setupNumQuestionDropdown(questionType.getSelectedIndex());
			}
		});
		add(questionType);

		JLabel numAnswers = new JLabel("Num Answers:");
		numAnswers.setBounds(205, 60, 200, 20);
		add(numAnswers);

		JLabel correct = new JLabel("Correct Answer:");
		correct.setBounds(405, 60, 200, 20);
		add(correct);

		numQuestionAnswers = new JComboBox<>();
		numQuestionAnswers.setBounds(205, 80, 170, 20);
		numQuestionAnswers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setupCorrectDropdown(numQuestionAnswers.getSelectedIndex());
				setupAnswers(numQuestionAnswers.getSelectedIndex());
			}
		});
		add(numQuestionAnswers);

		correctAnswers = new JComboBox<>();
		correctAnswers.setBounds(405, 80, 170, 20);
		add(correctAnswers);

		
		answersPanel = new JPanel();
		answersPanel.setLayout(null);
		answersPanel.setBounds(10, 110, 580, 450);
		add(answersPanel);

		save = new JButton("Save");
		save.setBounds(530, 600, 50, 20);
		save.setActionCommand("saveQuizQuestion");
		save.addActionListener(actionListener);
		add(save);
	}

	private void setupNumQuestionDropdown(int index) {
		if (index == 3) {
			answers = new ArrayList<JTextArea>();
			reasons = new ArrayList<JTextArea>();
			imageInUse = true;
			numQuestionAnswers.setEnabled(true);
			numQuestionAnswers.removeAllItems();
			numQuestionAnswers.addItem("Please Select");
			numQuestionAnswers.addItem("2");
			numQuestionAnswers.addItem("3");
			numQuestionAnswers.addItem("4");
			numQuestionAnswers.addItem("5");
		} else if (index == 2) {
			answers = new ArrayList<JTextArea>();
			reasons = new ArrayList<JTextArea>();
			imageInUse = false;
			numQuestionAnswers.setEnabled(true);
			numQuestionAnswers.removeAllItems();
			numQuestionAnswers.addItem("Please Select");
			numQuestionAnswers.addItem("2");
			numQuestionAnswers.addItem("3");
			numQuestionAnswers.addItem("4");
			numQuestionAnswers.addItem("5");
		} else if (index == 1) {
			answers = new ArrayList<JTextArea>();
			reasons = new ArrayList<JTextArea>();
			imageInUse = false;
			numQuestionAnswers.removeAllItems();
			numQuestionAnswers.addItem("0");
			numQuestionAnswers.addItem("2");
			numQuestionAnswers.setSelectedIndex(1);
			numQuestionAnswers.setEnabled(false);
			correctAnswers.removeAllItems();
			correctAnswers.addItem("1");
			correctAnswers.addItem("2");
		} else {
			// If they pressed --Please Select--
			answers = new ArrayList<JTextArea>();
			reasons = new ArrayList<JTextArea>();
			imageInUse = false;
			numQuestionAnswers.removeAllItems();
			correctAnswers.removeAllItems();
		}
	}

	private void setupCorrectDropdown(int index) {
		if (index == 1) {
			correctAnswers.removeAllItems();
			correctAnswers.addItem("1");
			correctAnswers.addItem("2");
		} else if (index == 2) {
			correctAnswers.removeAllItems();
			correctAnswers.addItem("1");
			correctAnswers.addItem("2");
			correctAnswers.addItem("3");
		} else if (index == 3) {
			correctAnswers.removeAllItems();
			correctAnswers.addItem("1");
			correctAnswers.addItem("2");
			correctAnswers.addItem("3");
			correctAnswers.addItem("4");
		} else if (index == 4) {
			correctAnswers.removeAllItems();
			correctAnswers.addItem("1");
			correctAnswers.addItem("2");
			correctAnswers.addItem("3");
			correctAnswers.addItem("4");
			correctAnswers.addItem("5");
		} else {
			// If they pressed --Please Select--
			correctAnswers.removeAllItems();
		}
	}

	private void imageAns(Integer numQuestions) {
		if (numQuestions + 1 < pictureLoaders.size()) {
			// Remove the difference
			int numberOfAnswers = pictureLoaders.size();
			for (int i = numberOfAnswers; i > numQuestions + 1; i--) {
				picturePath.remove(i - 1);
				selectedFile.remove(i - 1);
				pictureLoaders.remove(i - 1);
				reasons.remove(i - 1);
			}
		} else if (numQuestions + 1 > pictureLoaders.size()) {
			// Add the difference
			for (int i = pictureLoaders.size(); i < numQuestions + 1; i++) {
				picturePath.add(Paths.get(""));
				selectedFile.add(new JLabel("No File Selected"));
				pictureLoaders.add(getJButton(i));
				reasons.add(getJTextArea());
			}
		}

		int ypos = 2;
		for (int i = 0; i < pictureLoaders.size(); i++) {
			JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
			separator.setBounds(2, ypos-2, 550, 10);
			answersPanel.add(separator);
			
			JLabel ans = new JLabel("Answer #" + (i + 1) + ":");
			ans.setBounds(2, ypos, 150, 20);
			answersPanel.add(ans);
			ypos += 20;

			selectedFile.get(i).setBounds(2, ypos, 150, 20);
			answersPanel.add(selectedFile.get(i));
			pictureLoaders.get(i).setBounds(400, ypos, 150, 20);
			answersPanel.add(pictureLoaders.get(i));
			ypos += 25;

			JLabel reason = new JLabel("Reason #" + (i + 1) + ":");
			reason.setBounds(2, ypos, 150, 20);
			answersPanel.add(reason);
			ypos += 20;

			reasons.get(i).setBounds(2, ypos, 550, 20);
			answersPanel.add(reasons.get(i));
			ypos += 25;
		}
	}

	private void textAns(Integer numQuestions) {
		if (numQuestions + 1 < answers.size()) {
			// Remove the difference
			int numberOfAnswers = answers.size();
			for (int i = numberOfAnswers; i > numQuestions + 1; i--) {
				answers.remove(i - 1);
				reasons.remove(i - 1);
			}
		} else if (numQuestions + 1 > answers.size()) {
			// Add the difference
			for (int i = answers.size(); i < numQuestions + 1; i++) {
				answers.add(getJTextArea());
				reasons.add(getJTextArea());
			}
		}

		int ypos = 2;
		for (int i = 0; i < answers.size(); i++) {
			JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
			separator.setBounds(2, ypos-2, 550, 10);
			answersPanel.add(separator);
			
			JLabel ans = new JLabel("Answer #" + (i + 1) + ":");
			ans.setBounds(2, ypos, 150, 20);
			answersPanel.add(ans);
			ypos += 20;

			answers.get(i).setBounds(2, ypos, 550, 20);
			answersPanel.add(answers.get(i));
			ypos += 25;

			JLabel reason = new JLabel("Reason #" + (i + 1) + ":");
			reason.setBounds(2, ypos, 150, 20);
			answersPanel.add(reason);
			ypos += 20;

			reasons.get(i).setBounds(2, ypos, 550, 20);
			answersPanel.add(reasons.get(i));
			ypos += 25;
		}
	}

	private void setupAnswers(int numQuestions) {
		if (numQuestions != 0) {
			answersPanel.removeAll();

			if (imageInUse) {
				imageAns(numQuestions);
			} else {
				textAns(numQuestions);
			}
			answersPanel.revalidate();
			answersPanel.validate();
			answersPanel.repaint();
		}
	}

	private JTextArea getJTextArea() {
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		return textArea;
	}

	private JButton getJButton(Integer index) {
		JButton jButton = new JButton("Load Image");
		jButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser imageChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpeg", "jpg", "png", "gif");
				imageChooser.setFileFilter(filter);
				int returnVal = imageChooser.showOpenDialog(QuizAnswerPane.this);
				if (returnVal == imageChooser.APPROVE_OPTION) {
					picturePath.set(index, Paths.get(imageChooser.getSelectedFile().getPath()));
					selectedFile.get(index).setText(imageChooser.getSelectedFile().getName());
				}
			}
		});
		return jButton;
	}

	public ArrayList<String> getAnswers() {
		ArrayList<String> answersStrings = new ArrayList<String>();
		for (JTextArea answerArea : answers) {
			answersStrings.add(answerArea.getText());
		}
		return answersStrings;
	}

	public ArrayList<Path> getPicturePaths() {
		return picturePath;
	}

	public ArrayList<String> getReasons() {
		ArrayList<String> reasonsStrings = new ArrayList<String>();
		for (JTextArea reasonArea : reasons) {
			reasonsStrings.add(reasonArea.getText());
		}
		return reasonsStrings;
	}

	public String getQuestion() {
		return quizAnswerTitleText.getText();
	}

	public Integer getCorrectAnswer() {
		return Integer.parseInt((String) correctAnswers.getSelectedItem()) - 1;
	}

	public String getQuestionType() {
		if (questionType.getSelectedItem().toString().equals("True/False")
				|| questionType.getSelectedItem().toString().equals("Multiple Choice")) {
			return "multipleChoice";
		} else {
			return "choosePicture";
		}
	}

	public void setQuestion(String question) {
		edit = true;
		oldQuestion = question;
		quizAnswerTitleText.setText(question);
	}

	public void setAnswersAndReasons(ArrayList<String> ans, ArrayList<String> res) {
		numQuestionAnswers.setSelectedItem(Integer.toString(ans.size()));

		for (int i = 0; i < ans.size(); i++) {
			answers.get(i).setText(ans.get(i));
		}

		for (int i = 0; i < res.size(); i++) {
			reasons.get(i).setText(res.get(i));
		}
	}

	public void setCorrectAnswer(Integer correct) {
		int item = correct + 1;
		correctAnswers.setSelectedItem(Integer.toString(item));
	}

	public void setQuestionType(String type) {
		if (type.equals("multipleChoice")) {
			questionType.setSelectedItem("Multiple Choice");
		} else {
			questionType.setSelectedItem("Image Multiple Choice");
		}
	}

	public boolean getEditCheck() {
		return edit;
	}

	public boolean anyFieldsBlankOrNotUnique() {
		ArrayList<String> duplicates = new ArrayList<String>();
		for (String reason : getReasons()) {
			if (reason.replaceAll("\\s+", "").equals("") || duplicates.contains(reason)) {
				return true;
			}
			duplicates.add(reason);
		}
		if (getPictureCheck()) {
			duplicates = new ArrayList<String>();
			for (Path picture : picturePath) {
				if (picture.toString().replaceAll("\\s+", "").equals("")|| duplicates.contains(picture.toString())) {
					return true;
				}
				duplicates.add(picture.toString());
			}
		} else {
			duplicates = new ArrayList<String>();
			for (String answer : getAnswers()) {
				if (answer.toString().replaceAll("\\s+", "").equals("")|| duplicates.contains(answer)) {
					return true;
				}
				duplicates.add(answer);
			}
		}

		return false;
	}

	public String getOldQuestion() {
		return oldQuestion;
	}

	public boolean getPictureCheck() {
		return imageInUse;
	}
}

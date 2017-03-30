package Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ContentObjects.ContentQuizObject;
import ContentObjects.QuestionObject;
import ContentObjects.QuizObject;
import ContentObjects.Topic;

/**
 * Need to put catch in here for activeFile != "".
 *
 */
public class ContentModel {
	Model model;
	Gson gson;
	final String _PATH = "../../FinancialInclusionApplication/www/content/";
	HashMap<String, Topic> topicsMap;
	String activeFile = "";
	QuizObject quiz = null;
	ArrayList<QuestionObject> questions;
	ArrayList<String> quizImages = new ArrayList<>();;
	ArrayList<Path> quizImagePaths = new ArrayList<>();;

	public ContentModel(Model model) {
		this.model = model;
		gson = new Gson();

	}

	/**
	 * Don't think this method ever gets called, maps all files to the Topic
	 * object though.
	 */
	public void setTopicsMap() {
		topicsMap = new HashMap<String, Topic>();
		for (String fileName : getAllFileNames("topics/")) {
			String fileText;
			try {
				fileText = model.getJSONString("topics/" + fileName);
				topicsMap.put(fileName, gson.fromJson(fileText, Topic.class));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * Loads a Topic into memory given the file name
	 * 
	 * @param fileName File to be loaded
	 * @return Topic loaded
	 */
	public Topic selectTopic(String fileName) {
		String fileText;
		try {
			fileText = model.getJSONString("topics/" + fileName);
			activeFile = fileName;
			loadQuizFile(fileName);
			return gson.fromJson(fileText, Topic.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Topic();
	}

	/**
	 * Loads a quiz into memory given a filename
	 * 
	 * @param filename Quiz to be loaded
	 */
	private void loadQuizFile(String filename) {
		String fileText;
		try {
			fileText = model.getJSONString("quizzes/" + filename);
			quiz = gson.fromJson(fileText, QuizObject.class);
			questions = quiz.getQuestions();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Add a new topic file into the JSON
	 * 
	 * @param title Topic title
	 * @param reference The category id the topic links to
	 * @param content The content within the topic
	 */
	public void addNewContentFile(String title, Integer reference, String content) {
		if (!quiz.equals(null)) {
			String fileName = getFileName(title, "topics/", ".json");
			Topic newTopic = new Topic(title, reference, content, getContentQuizObject(fileName, quiz.getTitle()));
			createFile(fileName, newTopic);
		}
	}

	/**
	 * Get a content quiz object
	 * 
	 * @param filename The quiz file name
	 * @param title The quiz title
	 * @return
	 */
	private ContentQuizObject getContentQuizObject(String filename, String title) {
		return new ContentQuizObject(filename, title);
	}

	/**
	 * Write out topic and quiz files into JSON
	 * 
	 * @param filename The file name to write out as
	 * @param topic The topic to write
	 */
	private void createFile(String filename, Topic topic) {
		writeTopic(filename, topic);
		writeQuiz(filename, quiz);
		for (int i = 0; i < quizImagePaths.size(); i++) {
			saveImageFileQuiz(quizImagePaths.get(i).toString(), quizImages.get(i));
		}
		quizImagePaths = new ArrayList<>();
		quizImages = new ArrayList<>();
		questions = new ArrayList<>();
		quiz = new QuizObject();
		rewriteTopicsFile();
	}

	/**
	 * Write out the editted topic and quiz to JSON
	 *  
	 * @param title The topic title
	 * @param content the content
	 * @param category the category ID
	 */
	public void editTopic(String title, String content, Integer category) {
		if (!title.equals("") && !content.equals("")) {
			writeTopic(activeFile,
					new Topic(title, category, content, new ContentQuizObject(activeFile, quiz.getTitle())));
			writeQuiz(activeFile, quiz);
			for (int i = 0; i < quizImagePaths.size(); i++) {
				saveImageFileQuiz(quizImagePaths.get(i).toString(), quizImages.get(i));
			}
			quizImagePaths = new ArrayList<>();
			quizImages = new ArrayList<>();
			questions = new ArrayList<>();
			quiz = new QuizObject();
		}
	}

	/**
	 * This removes the topic file from the JSON file structure
	 * 
	 */
	public void removeTopic() {
		if (activeFile != "") {
			deleteFile(activeFile);
			rewriteTopicsFile();
		}
	}

	/**
	 * Returns a unique file name given the original name, the folder and the file type
	 * 
	 * @param title The file title
	 * @param folder The folder to check
	 * @param fileType The file extension
	 * @return The unique file name
	 */
	private String getFileName(String title, String folder, String fileType) {
		String fileName = title.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
		ArrayList<String> allFileNames = getAllFileNames(folder);
		if (!allFileNames.contains(fileName + fileType)) {
			return fileName + fileType;
		} else {
			int countAppender = 1;
			while (allFileNames.contains(fileName + "-" + countAppender + fileType)) {
				countAppender++;
			}
			return fileName + "-" + countAppender + fileType;
		}
	}

	/**
	 * Returns a list of all file names in a given folder
	 * 
	 * @param folder The folder of the FIS to check
	 * @return all file names
	 */
	private ArrayList<String> getAllFileNames(String folder) {
		ArrayList<String> fileNames = new ArrayList<String>();
		File tempFile = new File(_PATH + folder);
		File[] listOfFiles = tempFile.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			fileNames.add(listOfFiles[i].getName().toLowerCase());
		}

		return fileNames;
	}

	/**
	 * QUIZ STUFF
	 */
	public void initialiseQuiz() {
		// If the quiz isn't null
		if (quiz == null) {
			quiz = new QuizObject();
			questions = new ArrayList<QuestionObject>();
			quizImagePaths = new ArrayList<>();
			quizImages = new ArrayList<>();
		}
	}

	/**
	 * Sets all quiz objects back to null
	 */
	public void closeQuiz() {
		questions = new ArrayList<QuestionObject>();
		quiz = null;
		quizImagePaths = new ArrayList<>();
		quizImages = new ArrayList<>();
	}

	/**
	 * Gets all questions in a quiz
	 * @return The list of all questions in string form
	 */
	public ArrayList<String> getQuestions() {
		ArrayList<String> questionText = new ArrayList<String>();
		for (QuestionObject question : questions) {
			questionText.add(question.getQuestionText());
		}
		return questionText;
	}

	/**
	 * Returns a Question object given the question
	 * 
	 * @param questionText The question
	 * @return The question object
	 */
	public QuestionObject getQuestion(String questionText) {
		for (QuestionObject question : questions) {
			if (question.getQuestionText().equals(questionText)) {
				return question;
			}
		}
		return new QuestionObject();
	}

	/**
	 * Add a question to the list of all questions
	 * 
	 * @param questionType The type of question
	 * @param questionText The question text
	 * @param answers The list of answers
	 * @param answer The correct answer
	 * @param reasons The list of reasosn
	 */
	public void addQuestion(String questionType, String questionText, ArrayList<String> answers, int answer,
			ArrayList<String> reasons) {
		questions.add(new QuestionObject(questionType, questionText, answers, answer, reasons));
		model.changed();
	}

	/**
	 * Adds a question picture to the list of all questions
	 * 
	 * @param questionType The type of questions
	 * @param questionText The question itself
	 * @param answers The list of all image paths
	 * @param answer The answer index
	 * @param reasons The list of string reasons
	 */
	public void addQuestionPicture(String questionType, String questionText, ArrayList<Path> answers, Integer answer,
			ArrayList<String> reasons) {
		ArrayList<String> imgPath = new ArrayList<>();
		for (Path p : answers) {
			quizImagePaths.add(p);
			File file = new File(p.toString());
			String extension = "";
			String oldFileName = "";

			int i = file.getName().lastIndexOf('.');
			if (i > 0) {
				oldFileName = file.getName().substring(0, i);
				extension = file.getName().substring(i);
			}
			String temp = getFileName(oldFileName, "quizImages/", extension);
			int count = 1;
			while (quizImages.contains(temp)) {
				File nF = new File(temp);
				int j = nF.getName().lastIndexOf('.');
				if (j > 0) {
					oldFileName = nF.getName().substring(0, j) + "-" + count;
					extension = nF.getName().substring(j);
				}
				temp = getFileName(oldFileName, "quizImages/", extension);
				count++;
			}
			quizImages.add(temp);
			imgPath.add(temp);
		}
		questions.add(new QuestionObject(questionType, questionText, imgPath, answer, reasons));
		model.changed();
	}

	/**
	 * Deletes the question from the quiz
	 * 
	 * @param questionText The question to delete
	 */
	public void deleteQuestion(String questionText) {
		QuestionObject toBeRemoved = new QuestionObject();
		for (QuestionObject question : questions) {
			if (question.getQuestionText().equals(questionText)) {
				toBeRemoved = question;
			}
		}
		questions.remove(toBeRemoved);
		model.changed();
	}

	/**
	 * Edit a question text
	 * 
	 * @param oldQuestion The old question text
	 * @param questionType The new question type
	 * @param questionText The new text
	 * @param answers The new answers
	 * @param answer The new answer index
	 * @param reasons The new reasons
	 */
	public void editQuestion(String oldQuestion, String questionType, String questionText, ArrayList<String> answers,
			int answer, ArrayList<String> reasons) {
		if (!duplicateQuestion(oldQuestion, questionText)) {
			for (QuestionObject q : questions) {
				if (q.getQuestionText().equals(oldQuestion)) {
					q.setAnswer(answer);
					q.setAnswers(answers);
					q.setQuestionType(questionType);
					q.setQuestionText(questionText);
					q.setReason(reasons);
				}
			}
		}
		model.changed();
	}

	/**
	 * Set the quiz questions and quiz title
	 * 
	 * @param quizTitle
	 */
	public void saveQuiz(String quizTitle) {
		quiz.setTitle(quizTitle);
		quiz.setQuestions(questions);
	}

	/**
	 * Checks to see if a quiz question is a duplicate
	 * 
	 * @param oldQuestion The old question
	 * @param questionText The new question text
	 * @return
	 */
	private Boolean duplicateQuestion(String oldQuestion, String questionText) {
		if (oldQuestion.equals(questionText)) {
			return false;
		}
		for (QuestionObject q : questions) {
			if (q.getQuestionText().equals(questionText)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * END OF QUIZZ STUFF
	 */

	/**
	 * Write the topic to a file given a specific file name
	 * 
	 * @param fileName The file name to write to
	 * @param topic The topic to write out
	 */
	private void writeTopic(String fileName, Topic topic) {
		try (Writer writer = new FileWriter(_PATH + "topics/" + fileName)) {
			Gson gson = new GsonBuilder().create();
			gson.toJson(topic, writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Write out a quiz to a file path
	 * 
	 * @param fileName The file name to create
	 * @param quiz The quiz to write
	 */
	private void writeQuiz(String fileName, QuizObject quiz) {
		try (Writer writer = new FileWriter(_PATH + "quizzes/" + fileName)) {
			Gson gson = new GsonBuilder().create();
			gson.toJson(quiz, writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete a file given a specific path
	 * 
	 * @param file The path of the file
	 */
	public void deleteFile(String file) {
		Path path = Paths.get(_PATH + "topics/" + file);
		try {
			Files.delete(path);
		} catch (NoSuchFileException x) {
			System.err.format("%s: no such" + " file or directory%n", path);
		} catch (DirectoryNotEmptyException x) {
			System.err.format("%s not empty%n", path);
		} catch (IOException x) {
			System.err.println(x);
		}

		path = Paths.get(_PATH + "quizzes/" + file);
		try {
			Files.delete(path);
		} catch (NoSuchFileException x) {
			System.err.format("%s: no such" + " file or directory%n", path);
		} catch (DirectoryNotEmptyException x) {
			System.err.format("%s not empty%n", path);
		} catch (IOException x) {
			System.err.println(x);
		}
	}

	/**
	 * Rewrites the topic file with all the URL's of topics
	 */
	public void rewriteTopicsFile() {
		try {
			ArrayList<String> jsonFiles = getAllFileNames("topics/");
			Files.write(Paths.get(_PATH + "topics.json"), gson.toJson(jsonFiles).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
/**
 * Clears the active file path
 */
	public void clearActiveFile() {
		activeFile = "";
	}

	/**
	 * Saves an image to the FIS directory given the file path
	 * 
	 * @param path The path to take the image from
	 * @return The file name created
	 */
	public String saveImageFile(String path) {
		File file = new File(path);
		String extension = "";
		String oldFileName = "";

		int i = file.getName().lastIndexOf('.');
		if (i > 0) {
			oldFileName = file.getName().substring(0, i);
			extension = file.getName().substring(i);
		}

		String filename = getFileName(oldFileName, "images/", extension);

		try {
			Files.write(Paths.get(_PATH + "images/" + filename), Files.readAllBytes(Paths.get(path)));
			return filename;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "CouldntWriteFile";
	}

	/**
	 * Save all quiz images to the FIS path
	 * 
	 * @param path The path to save the file 
	 * @param filename The file name to save the quiz as
	 */
	public void saveImageFileQuiz(String path, String filename) {
		try {
			Files.write(Paths.get(_PATH + "quizImages/" + filename), Files.readAllBytes(Paths.get(path)));
			// to add compression:
			// https://www.tutorialspoint.com/java_dip/image_compression_technique.htm
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the title of the quiz
	 * 
	 * @return The quiz title
	 */
	public String getQuizTitle() {
		if (quiz == null) {
			return "";
		} else {
			return quiz.getTitle();
		}
	}

	/**
	 * Returns true if a quiz has been created
	 * 
	 * @return True is quiz has been created
	 */
	public Boolean isQuizActive() {
		if (quiz != null && questions.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * Returns true if active file is loaded
	 * 
	 * @return True if file is loaded
	 */
	public Boolean isFileLoaded() {
		if (!activeFile.equals("")) {
			return true;
		}
		return false;
	}
}

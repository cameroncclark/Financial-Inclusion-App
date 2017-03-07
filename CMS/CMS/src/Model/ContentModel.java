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
import ContentObjects.Link;
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
	ArrayList<String> quizImages;
	ArrayList<Path> quizImagePaths;

	public ContentModel(Model model) {
		this.model = model;
		gson = new Gson();
		
	}

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

	public Topic selectTopic(String fileName) {
		String fileText;
		try {
			fileText = model.getJSONString("topics/" + fileName);
			activeFile = fileName;
			return gson.fromJson(fileText, Topic.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Topic();
	}

	public void addNewContentFile(String title, Integer reference, String content) {
		if (!quiz.equals(null)) {
			String fileName = getFileName(title, "topics/", ".json");
			Topic newTopic = new Topic(title, reference, content, getContentQuizObject(fileName, quiz.getTitle()));
			createFile(fileName, newTopic);
		}
	}

	private ContentQuizObject getContentQuizObject(String filename, String title) {
		return new ContentQuizObject(filename, title);
	}

	private void createFile(String filename, Topic topic) {
		writeTopic(filename, topic);
		writeQuiz(filename, quiz);
		for(int i = 0; i < quizImagePaths.size(); i++){
			saveImageFileQuiz(quizImagePaths.get(i).toString(), quizImages.get(i));
		}
		quizImagePaths = new ArrayList<>();
		quizImages = new ArrayList<>();
		questions = new ArrayList<>();
		quiz = new QuizObject();
		rewriteTopicsFile();
	}

	public void editTopic(String title, String content, Integer category) {
		if (!title.equals("") && !content.equals("")) {
			writeTopic(activeFile, new Topic(title, category, content, new ContentQuizObject("url.json", "title")));
		}
	}

	public void removeTopic() {
		if (activeFile != "") {
			deleteFile(activeFile);
			rewriteTopicsFile();
		}
	}

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
		//If the quiz isn't null
		quiz = new QuizObject();
		questions = new ArrayList<QuestionObject>();
		quizImagePaths = new ArrayList<>();
		quizImages = new ArrayList<>();
	}

	public void closeWithoutSaving() {
		questions = new ArrayList<QuestionObject>();
		quiz = null;
	}

	public ArrayList<String> getQuestions() {
		ArrayList<String> questionText = new ArrayList<String>();
		for (QuestionObject question : questions) {
			questionText.add(question.getQuestionText());
		}
		return questionText;
	}

	public QuestionObject getQuestion(String questionText) {
		for (QuestionObject question : questions) {
			if (question.getQuestionText().equals(questionText)) {
				return question;
			}
		}
		return new QuestionObject();
	}

	public void addQuestion(String questionType, String questionText, ArrayList<String> answers, int answer,
			ArrayList<String> reasons) {
		questions.add(new QuestionObject(questionType, questionText, answers, answer, reasons));
		model.changed();
	}
	
	public void addQuestionPicture(String questionType, String questionText,
			ArrayList<Path> answers, Integer answer, ArrayList<String> reasons) {
		ArrayList<String> imgPath = new ArrayList<>();
		for(Path p : answers){
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
			System.out.println("Count = " + count + " - Name = " + temp + " - Old = " + oldFileName + " - Ext = " + extension);
//			while(quizImages.contains(temp)){
//				File nF = new File(temp);
//				int j = nF.getName().lastIndexOf('.');
//				if (j > 0) {
//					oldFileName = nF.getName().substring(0, j) + "-" + count;
//					extension = nF.getName().substring(j);
//				}
//				temp = getFileName(oldFileName, "quizImages/", extension);
//				count++;
//				System.out.println("Count = " + count + " - Name = " + temp + " - Old = " + oldFileName + " - Ext = " + extension);
//			}
			quizImages.add(temp);
			imgPath.add(temp);
		}
		questions.add(new QuestionObject(questionType, questionText, imgPath, answer, reasons));
		model.changed();
	}

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

	public void editQuestion(String oldQuestion, String questionType, String questionText, ArrayList<String> answers,
			int answer, ArrayList<String> reasons) {
		if (!duplicateQuestion(oldQuestion, questionText)) {
			for (QuestionObject q : questions) {
				if (q.getQuestionText().equals(oldQuestion)) {
					q.setAnswer(answer);
					q.setAnswers(answers);
					q.setQuestionType(questionType);
					q.setQuestionText(questionText);
					q.setReasons(reasons);
				}
			}
		}
		model.changed();
	}

	public void saveQuiz(String quizTitle) {
		quiz.setTitle(quizTitle);
		quiz.setQuestions(questions);
	}

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

	private void writeTopic(String fileName, Topic topic) {
		try (Writer writer = new FileWriter(_PATH + "topics/" + fileName)) {
			Gson gson = new GsonBuilder().create();
			gson.toJson(topic, writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void writeQuiz(String fileName, QuizObject quiz) {
		try (Writer writer = new FileWriter(_PATH + "quizzes/" + fileName)) {
			Gson gson = new GsonBuilder().create();
			gson.toJson(quiz, writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteFile(String file) {
		Path path = Paths.get(_PATH + "topics/" + file);
		System.out.println(path.toString());
		try {
			Files.delete(path);
		} catch (NoSuchFileException x) {
			System.err.format("%s: no such" + " file or directory%n", path);
		} catch (DirectoryNotEmptyException x) {
			System.err.format("%s not empty%n", path);
		} catch (IOException x) {
			// File permission problems are caught here.
			System.err.println(x);
		}
	}

	public void rewriteTopicsFile() {
		try {
			ArrayList<String> jsonFiles = getAllFileNames("topics/");
			Files.write(Paths.get(_PATH + "topics.json"), gson.toJson(jsonFiles).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void clearActiveFile() {
		activeFile = "";

	}

	public void saveImageFile(String path) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveImageFileQuiz(String path, String filename) {
		try {
			Files.write(Paths.get(_PATH + "quizImages/" + filename), Files.readAllBytes(Paths.get(path)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

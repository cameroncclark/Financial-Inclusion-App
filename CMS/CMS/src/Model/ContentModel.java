package Model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
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
		String fileName = getFileName(title, "topics/", ".json");
		Topic newTopic = new Topic(title, reference, content, new ContentQuizObject(fileName, "title"));
		createFile(fileName, newTopic);
	}

	private void createFile(String filename, Topic topic) {
		writeTopic(filename, topic);
		rewriteTopicsFile(filename);
	}

	public void editTopic(String title, String content, Integer category) {
		if (!title.equals("") && !content.equals("")) {
			writeTopic(activeFile, new Topic(title, category, content, new ContentQuizObject("url.json", "title")));
		}
	}

	public void removeTopic() {
		if (activeFile != "") {

			deleteFile(activeFile);
		}
	}

	private String getFileName(String title, String folder, String fileType) {
		String fileName = title.replaceAll("[^a-zA-Z]", "").toLowerCase();
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

	public void writeTopic(String fileName, Topic topic) {
		try (Writer writer = new FileWriter(_PATH + "topics/" + fileName)) {
			Gson gson = new GsonBuilder().create();
			gson.toJson(topic, writer);
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

	public void rewriteTopicsFile(String newFile) {
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
}

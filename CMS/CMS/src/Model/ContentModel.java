package Model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;

import ContentObjects.ContentQuizObject;
import ContentObjects.Tip;
import ContentObjects.Topic;

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
		for (String fileName : getAllFileNames()) {
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
		String fileName = getFileName(title);
		Topic newTopic = new Topic(title, reference, content, new ContentQuizObject(fileName, "title"));
		createFile(fileName, newTopic);
	}

	private void createFile(String filename, Topic topic) {
		writeTopic(filename, topic);
		rewriteTopicsFile(filename);
	}
	
	public void editTopic(String title, String content, Integer category){
		if(!title.equals("") && !content.equals("")){
			writeTopic(activeFile, new Topic(title,category,content, new ContentQuizObject("url.json", "title")));
		}
	}
	
	

	private String getFileName(String title) {
		String fileName = title.replaceAll("[^a-zA-Z]", "").toLowerCase();
		ArrayList<String> allFileNames = getAllFileNames();
		if (!allFileNames.contains(fileName + ".json")) {
			return fileName + ".json";
		} else {
			int countAppender = 1;
			while (allFileNames.contains(fileName + "-" + countAppender + ".json")) {
				countAppender++;
			}
			return fileName + "-" + countAppender + ".json";
		}
	}

	private ArrayList<String> getAllFileNames() {
		ArrayList<String> fileNames = new ArrayList<String>();
		File tempFile = new File(_PATH + "topics/");
		File[] listOfFiles = tempFile.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			fileNames.add(listOfFiles[i].getName().toLowerCase());
		}

		return fileNames;
	}

	public void writeTopic(String fileName, Topic topic) {
		try {
			Files.write(Paths.get(_PATH + "topics/" + fileName), gson.toJson(topic).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void rewriteTopicsFile(String newFile) {
		try {
			ArrayList<String> jsonFiles = getAllFileNames();
			Files.write(Paths.get(_PATH + "topics.json"), gson.toJson(jsonFiles).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

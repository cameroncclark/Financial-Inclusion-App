package Model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;

import ContentObjects.Topic;

public class ContentModel {
	Model model;
	Gson gson;
	final String _PATH = "../../FinancialInclusionApplication/www/content/";

	public ContentModel(Model model) {
		this.model = model;
		gson = new Gson();
	}

	public void addNewContentFile(String title, Integer reference, String content) {
		Topic newTopic = new Topic(title, reference, content);
		String fileName = getFileName(title);
		createFile(fileName,newTopic);
	}
	
	private void createFile(String filename, Topic topic){
		writeTopic(filename,topic);
		rewriteTopicsFile(filename);
	}

	private String getFileName(String title) {
		String fileName = title.replaceAll("[^a-zA-Z]", "").toLowerCase();
		ArrayList<String> allFileNames = getAllFileNames();
		if(!allFileNames.contains(fileName+ ".json")){
			return fileName+ ".json";
		}else{
			int countAppender = 1;
			while(allFileNames.contains(fileName +"-"+countAppender+ ".json")){
				countAppender++;
			}
			return fileName +"-"+countAppender+ ".json";
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
	
	public void writeTopic(String fileName, Topic topic){
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

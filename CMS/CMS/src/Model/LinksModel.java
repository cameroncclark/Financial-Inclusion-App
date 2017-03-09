package Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;

import ContentObjects.Link;
import ContentObjects.Number;
import ContentObjects.Tip;

public class LinksModel {
	final String _PATH = "../../FinancialInclusionApplication/www/content/";
	Model model;
	Gson gson;
	ArrayList<Link> links;

	public LinksModel(Model model) {
		this.model = model;
		gson = new Gson();
		selectLinks();
	}

	public String[] selectLinks() {
		try {
			String fileText = model.getJSONString("externalLinks.JSON");

			Link[] linkArray = gson.fromJson(fileText, Link[].class);
			links = new ArrayList<Link>(Arrays.asList(linkArray));

			String[] linkNames = new String[linkArray.length];
			int count = 0;
			for (Link link : linkArray) {
				linkNames[count] = link.getName();
				count++;
			}
			return linkNames;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String[] {};
	}

	public Link selectLink(String name) {
		if (!name.equals("")) {
			for (Link l : links) {
				if (l.getName().equals(name)) {
					return l;
				}
			}
		}
		return new Link();
	}

	public Boolean addLink(String name, String blurb, String website) {
		if (!name.equals("")) {
			if (!duplicateLink("", name)) {
				links.add(new Link(name, blurb, website));
				rewriteLinksFile();
				return true;
			}
			return false;
		}else{
			return false;
		}
	}

	public Boolean editLink(String oldName, String newName, String newBlurb, String newWebsite) {
		if (!duplicateLink(oldName, newName)) {
			for (Link l : links) {
				if (l.getName().equals(oldName)) {
					l.setName(newName);
					l.setBlurb(newBlurb);
					l.setWebsite(newWebsite);
					break;
				}
			}
			rewriteLinksFile();
			return true;
		}else{
			return false;
		}
	}

	public void deleteLink(String name) {
		Link toBeRemoved = new Link();
		for (Link l : links) {
			if (l.getName().equals(name)) {
				toBeRemoved = l;
				break;
			}
		}
		links.remove(toBeRemoved);
		rewriteLinksFile();
	}

	/**
	 * Method to check to see if a link name already exists
	 * 
	 * @param name
	 * @return true/false depending if the name exists
	 */
	private Boolean duplicateLink(String oldName, String name) {
		if (oldName.equals(name)) {
			return false;
		}
		for (Link l : links) {
			if (l.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Method to rewrite the json files and tell the model to notify observers
	 */
	public void rewriteLinksFile() {
		try {
			Files.write(Paths.get(_PATH + "externalLinks.json"), gson.toJson(links).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.changed();
	}
}

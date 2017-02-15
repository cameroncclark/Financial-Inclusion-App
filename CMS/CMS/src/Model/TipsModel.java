package Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;

import ContentObjects.Number;
import ContentObjects.Tip;

public class TipsModel {
	final String _PATH = "../../FinancialInclusionApplication/www/content/";
	Model model;
	Gson gson;
	ArrayList<Tip> tips;

	public TipsModel(Model model) {
		this.model = model;
		selectTips();
	}

	public String[] selectTips() {
		return new String[] {};
	}

	

	public void addTip(String header, String tip) {
		if (!duplicateName(header)) {
			if (tip.length() < 199) {
				tips.add(new Tip(header, tip));
				rewriteTipsFile();
			}
		}
	}

	public void editTip(String oldHeader, String newHeader, String newTip) {
		if (!duplicateName(newHeader)) {
			if (newTip.length() < 199) {
				for (Tip t : tips) {
					if (t.getHeader().equals(oldHeader)) {
						t.setHeader(newHeader);
						t.setTip(newTip);
						break;
					}
				}
			}
			rewriteTipsFile();
		}
	}

	public void deleteTip(String header) {
		Tip toBeRemoved = new Tip();
		for (Tip t : tips) {
			if (t.getHeader().equals(header)) {
				toBeRemoved = t;
				break;
			}
		}
		tips.remove(toBeRemoved);
		rewriteTipsFile();

	}

	/**
	 * Method to check to see if a number name already exists
	 * 
	 * @param name
	 * @return true/false depending if the name exists
	 */
	private Boolean duplicateName(String header) {
		for (Tip t : tips) {
			if (t.getHeader().equals(header)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Method to rewrite the json files and tell the model to notify observers
	 */
	public void rewriteTipsFile() {
		try {
			Files.write(Paths.get(_PATH + "tips.json"), gson.toJson(tips).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.changed();
	}
}

package Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;

import ContentObjects.Tip;

public class TipsModel {
	final String _PATH = "../../FinancialInclusionApplication/www/content/";
	Model model;
	Gson gson;
	ArrayList<Tip> tips;

	public TipsModel(Model model) {
		this.model = model;
		gson = new Gson();
		selectTips();
	}

	public String[] selectTips() {
		try {
			String fileText = model.getJSONString("tips.JSON");

			Tip[] tipsArray = gson.fromJson(fileText, Tip[].class);
			tips = new ArrayList<Tip>(Arrays.asList(tipsArray));

			String[] tipNames = new String[tipsArray.length];
			int count = 0;
			for (Tip tip : tipsArray) {
				tipNames[count] = tip.getHeader();
				count++;
			}
			return tipNames;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String[] {};
	}

	public Tip selectTip(String header) {
		if (!header.equals("")) {
			for (Tip t : tips) {
				if (t.getHeader().equals(header)) {
					return t;
				}
			}
		}
		return new Tip();
	}

	public Boolean addTip(String header, String tip) {
		if (!header.equals("") && !tip.equals("")) {
			if (!duplicateName("", header)) {
				if (tip.length() < 199) {
					tips.add(new Tip(header, tip));
					rewriteTipsFile();
					return true;
				}
				return false;
			}
			return false;
		} else {
			return false;
		}
	}

	public Boolean editTip(String oldHeader, String newHeader, String newTip) {
		if (!newHeader.equals("") && !newTip.equals("")) {
			if (!duplicateName(oldHeader, newHeader)) {
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
				return true;
			} else {
				return false;
			}
		} else {
			return false;
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
	private Boolean duplicateName(String oldHeader, String header) {
		if (oldHeader.equals(header)) {
			return false;
		}
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
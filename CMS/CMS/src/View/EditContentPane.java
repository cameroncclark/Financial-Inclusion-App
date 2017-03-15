package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controller.ActionController;
import Model.Model;

public class EditContentPane extends JDialog {
	protected ActionController actionListener;
	protected JTextField topicTitleText;
	protected JComboBox categoriesDropdown;
	protected String[] categories;
	protected JTextArea contentArea;
	protected Model model;
	protected JDialog activePanel;

	public EditContentPane(ActionController actionListener, Model model) {
		this.model = model;
		this.actionListener = actionListener;
		categories = actionListener.initaliseCategoriesTab();
		setLayout(null);
		setSize(1000, 600);
		createComponents();
		createButtons();
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				actionListener.clearActiveFile();
				actionListener.closeContentPane();
			}
		});
	}

	private void createComponents() {
		JLabel topicTitle = new JLabel("Topic Title:");
		topicTitle.setBounds(5,10,200,20);
		add(topicTitle);

		topicTitleText = new JTextField();
		topicTitleText.setBounds(5, 30, 300, 20);
		add(topicTitleText);

		JButton selectTopic = new JButton("Load Topic");
		selectTopic.setBounds(890, 10, 100,20);
		selectTopic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser topicChooser = new JFileChooser("../../FinancialInclusionApplication/www/content/topics/");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Topic files", "json");
				topicChooser.setFileFilter(filter);
				int returnVal = topicChooser.showOpenDialog(EditContentPane.this);
				if (returnVal == topicChooser.APPROVE_OPTION) {
					actionListener.loadSelectedFile(topicChooser.getSelectedFile().getName(),true);
				}

			}
		});
		add(selectTopic);

		categoriesDropdown = new JComboBox(categories);
		categoriesDropdown.setBounds(800, 40, 200, 20);
		add(categoriesDropdown);

		contentArea = new JTextArea();
		contentArea.setLineWrap(true);
		contentArea.setWrapStyleWord(true);
		contentArea.setBounds(5, 100, 990, 440);
		JScrollPane scrollTextArea = new JScrollPane(contentArea);
		scrollTextArea.setBounds(5, 100, 990, 440);
		add(scrollTextArea);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				actionListener.clearActiveFile();
				actionListener.closeContentPane();
				dispose();
				
			}
		});
		
		cancelButton.setBounds(770, 550, 100,20);
		add(cancelButton);

		JButton saveContent = new JButton("Save");
		saveContent.addActionListener(actionListener);
		saveContent.setActionCommand("editContentPage");
		saveContent.setBounds(890, 550, 100,20);
		add(saveContent);
	}
	
	private void createButtons(){
		JButton sectionButton = new JButton("Section");
		sectionButton.setBounds(5,70,99,20);
		sectionButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentArea.insert("<section><section-title>SectionTitle</section-title>ContentHere</section>", contentArea.getText().length());
			}
		});
		add(sectionButton);
		
		JButton quizButton = new JButton("Quiz");
		quizButton.setBounds(320,30,100,20);
		quizButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				activePanel = new QuizPane(actionListener);
				model.setModelObserver((QuizPane) activePanel);
			}
		});
		add(quizButton);
		
		JButton titleButton = new JButton("Title");
		titleButton.setBounds(103,70,100,20);
		titleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentArea.insert("<title>TitleText</title>", contentArea.getCaretPosition());
			}
		});
		add(titleButton);
		
		JButton subtitleButton = new JButton("Sub title");
		subtitleButton.setBounds(203,70,100,20);
		subtitleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentArea.insert("<subtitle>SubtitleText</subtitle>", contentArea.getCaretPosition());
			}
		});
		add(subtitleButton);
		
		JButton linkButton = new JButton("Link");
		linkButton.setBounds(303,70,100,20);
		linkButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentArea.insert("<link>URL,ContentText</link>", contentArea.getCaretPosition());
			}
		});
		add(linkButton);
		
		JButton videoButton = new JButton("Video");
		videoButton.setBounds(403,70,100,20);
		videoButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentArea.insert("<video>YoutubeURL</video>", contentArea.getCaretPosition());
			}
		});
		add(videoButton);
		
		JButton imageButton = new JButton("Image");
		imageButton.setBounds(503,70,100,20);
		imageButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser imageChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpeg","jpg","png","gif");
				imageChooser.setFileFilter(filter);
				int returnVal = imageChooser.showOpenDialog(EditContentPane.this);
				if (returnVal == imageChooser.APPROVE_OPTION) {
					contentArea.insert("<image>" + imageChooser.getSelectedFile().getName() + "</image>", contentArea.getCaretPosition());
					actionListener.copyImageFile(imageChooser.getSelectedFile().getPath());
				}
			}
		});
		add(imageButton);
		
		JButton boldButton = new JButton("Bold");
		boldButton.setBounds(603,70,100,20);
		boldButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentArea.replaceSelection("<b>" + contentArea.getSelectedText() + "</b>");
			}
		});
		add(boldButton);
		
		JButton underlineButton = new JButton("Underline");
		underlineButton.setBounds(703,70,100,20);
		underlineButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentArea.replaceSelection("<u>" + contentArea.getSelectedText() + "</u>");
			}
		});
		add(underlineButton);
		
		JButton italicButton = new JButton("Italic");
		italicButton.setBounds(803,70,100,20);
		italicButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentArea.replaceSelection("<i>" + contentArea.getSelectedText() + "</i>");
			}
		});
		add(italicButton);
	}
	
	public JDialog getActivePanel(){
		return activePanel;
	}

	public String getTopicTitle() {
		return topicTitleText.getText();
	}

	public String getContentText() {
		return contentArea.getText();
	}

	public String getSelectedCategory() {
		return categoriesDropdown.getSelectedItem().toString();
	}

	public void setTopic(String topicTitle, String category, String content) {
		topicTitleText.setText(topicTitle);
		contentArea.setText("");
		contentArea.append(content);
		categoriesDropdown.setSelectedItem(category);
	}

}

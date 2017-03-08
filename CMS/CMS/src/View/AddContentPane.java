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

public class AddContentPane extends JDialog {
	protected ActionController actionListener;
	protected JTextField topicTitleText;
	protected JComboBox categoriesDropdown;
	protected String[] categories;
	protected JTextArea contentArea;
	protected Model model;
	protected JDialog activePanel;
	
	public AddContentPane(ActionController actionListener, Model model) {
		this.model = model;
		this.actionListener = actionListener;
		categories = actionListener.initaliseCategoriesTab();
		setLayout(null);
		setSize(600, 600);
		createComponents();
		createButtons();
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e)
		    {
				actionListener.clearActiveFile();
		    }
		});
	}
	
	private void createComponents(){
		JLabel topicTitle = new JLabel("Topic Title:");
		topicTitle.setBounds(2,30,200,20);
		add(topicTitle);
		
		topicTitleText = new JTextField();
		topicTitleText.setBounds(90, 30, 200, 20);
		add(topicTitleText);
		
		categoriesDropdown = new JComboBox(categories);
		categoriesDropdown.setBounds(400, 30, 200, 25);
		add(categoriesDropdown);
		
		contentArea = new JTextArea();
		contentArea.setLineWrap(true);
		contentArea.setWrapStyleWord(true);
		contentArea.setBounds(30, 100, 500, 400);
		JScrollPane scrollTextArea = new JScrollPane(contentArea);
		scrollTextArea.setBounds(30, 100, 550, 400);
		add(scrollTextArea);
		
		JButton saveContent = new JButton("Save");
		saveContent.addActionListener(actionListener);
		saveContent.setActionCommand("addContentPage");
		saveContent.setBounds(450, 525, 125,20);
		add(saveContent);
	}
	
	private void createButtons(){
		JButton sectionButton = new JButton("Section");
		sectionButton.setBounds(2,50,99,20);
		sectionButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentArea.insert("<section><section-title>SectionTitle</section-title>ContentHere</section>", contentArea.getText().length());
			}
		});
		add(sectionButton);
		
		JButton quizButton = new JButton("Quiz");
		quizButton.setBounds(2,70,99,20);
		quizButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				activePanel = new QuizPane(actionListener);
				model.setModelObserver((QuizPane) activePanel);
			}
		});
		add(quizButton);
		
		JButton titleButton = new JButton("Title");
		titleButton.setBounds(100,50,100,20);
		titleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentArea.insert("<title>TitleText</title>", contentArea.getCaretPosition());
			}
		});
		add(titleButton);
		
		JButton subtitleButton = new JButton("Sub title");
		subtitleButton.setBounds(200,50,100,20);
		subtitleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentArea.insert("<subtitle>SubtitleText</subtitle>", contentArea.getCaretPosition());
			}
		});
		add(subtitleButton);
		
		JButton linkButton = new JButton("Link");
		linkButton.setBounds(300,50,100,20);
		linkButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentArea.insert("<link>URL,ContentText</link>", contentArea.getCaretPosition());
			}
		});
		add(linkButton);
		
		JButton videoButton = new JButton("Video");
		videoButton.setBounds(400,50,100,20);
		videoButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentArea.insert("<video>YoutubeURL</video>", contentArea.getCaretPosition());
			}
		});
		add(videoButton);
		
		JButton imageButton = new JButton("Image");
		imageButton.setBounds(500,50,100,20);
		imageButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser imageChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpeg","jpg","png","gif");
				imageChooser.setFileFilter(filter);
				int returnVal = imageChooser.showOpenDialog(AddContentPane.this);
				if (returnVal == imageChooser.APPROVE_OPTION) {
					contentArea.insert("<image>" + imageChooser.getSelectedFile().getName() + "</image>", contentArea.getCaretPosition());
					actionListener.copyImageFile(imageChooser.getSelectedFile().getPath());
				}
			}
		});
		add(imageButton);
		
		JButton boldButton = new JButton("Bold");
		boldButton.setBounds(100,70,100,20);
		boldButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentArea.replaceSelection("<b>" + contentArea.getSelectedText() + "</b>");
			}
		});
		add(boldButton);
		
		JButton underlineButton = new JButton("Underline");
		underlineButton.setBounds(200,70,100,20);
		underlineButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentArea.replaceSelection("<u>" + contentArea.getSelectedText() + "</u>");
			}
		});
		add(underlineButton);
		
		JButton italicButton = new JButton("Italic");
		italicButton.setBounds(300,70,100,20);
		italicButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				contentArea.replaceSelection("<i>" + contentArea.getSelectedText() + "</i>");
			}
		});
		add(italicButton);
	}
	
	public String getTopicTitle(){
		return topicTitleText.getText();
	}
	
	public void setTopicTitle(String text){
		topicTitleText.setText(text);
	}
	
	public String getSelectedCategory(){
		return categoriesDropdown.getSelectedItem().toString();
	}
	
	public String getContent(){
		return contentArea.getText();
	}
	
	public JDialog getActivePanel(){
		return activePanel;
	}
}

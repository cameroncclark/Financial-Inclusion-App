package View;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;

import Controller.ActionController;
import Model.Model;

public class MainContainer {
	private Model model;
	private ActionListener actionListener;
	PanelCreator panelCreator;

	private JFrame jFrame;

	private JMenuBar jMenuBar;
	private JMenu fileMenu, settingsMenu;

	private JTabbedPane tabbedPane;

	public MainContainer(Model model, ActionListener actionListener) {
		this.model = model;
		this.actionListener = actionListener;

		panelCreator = new PanelCreator();
		createJFrame();
		createMenus();
		createTabbedPane();

		jFrame.setVisible(true);
	}

	private void createJFrame() {
		jFrame = new JFrame();
		jFrame.setFocusable(true);
		jFrame.requestFocusInWindow();
		jFrame.getContentPane().setLayout(new BorderLayout());
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setSize(700, 600);
	}

	// http://docs.oracle.com/javase/tutorial/uiswing/components/menu.html
	private void createMenus() {
		// jMenuBar = new JMenuBar();
		//
		// fileMenu = new JMenu("File");
		// settingsMenu = new JMenu("Build");
		//
		// fileMenu.add(new JMenuItem("Test"));
		// fileMenu.addSeparator();
		// fileMenu.add(new JMenuItem("Another"));
		//
		// jMenuBar.add(fileMenu);
		// jMenuBar.add(settingsMenu);
		//
		// jFrame.setJMenuBar(jMenuBar);
	}

	/**
	 * Initialises all panes in the system and adds them to a tabbed pane
	 */
	private void createTabbedPane() {
		tabbedPane = new JTabbedPane();

		JComponent categoriesPanel = panelCreator.makeCategoriesPanel(model, (ActionController) actionListener);
		tabbedPane.addTab("Categories", categoriesPanel);

		JComponent contentPanel = panelCreator.makeContentPanel(model, (ActionController) actionListener);
		tabbedPane.addTab("Content", contentPanel);

		JComponent tipsPanel = panelCreator.makeTipsPanel(model, (ActionController) actionListener);
		tabbedPane.addTab("Tips", tipsPanel);

		JComponent numbersPanel = panelCreator.makeNumbersPanel(model, (ActionController) actionListener);
		tabbedPane.addTab("External numbers", numbersPanel);

		JComponent externalLinksPanel = panelCreator.makeLinksPanel(model, (ActionController) actionListener);
		tabbedPane.addTab("External links", externalLinksPanel);

		jFrame.add(tabbedPane);
	}

	/**
	 * Gets the currently active panel
	 * 
	 * @return the active Panel
	 */
	public Panel getActivePanel() {
		return panelCreator.getActivePanel(tabbedPane.getSelectedIndex());
	}
}

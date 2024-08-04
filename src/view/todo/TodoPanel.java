package view.todo;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class TodoPanel extends JPanel {
	private ArrayList<String> listNames;
	private GridLayout buttonLayout;
	private JScrollPane scroller;
	private JPanel buttonPanel;

	public TodoPanel() {
		super();

		// UI Components
		this.listNames = new ArrayList<String>();
		listNames.add("FUCK");
		this.buttonPanel = new JPanel();
		this.buttonLayout = new GridLayout(listNames.size(), 1);
		this.scroller = new JScrollPane(buttonPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.setLayout(new GridLayout(1, 1));
		this.add(scroller);

		setupPanel();
		setupLayout();
	}

	private void setupPanel() {
		this.buttonPanel.setLayout(buttonLayout);
		this.buttonPanel.add(new JButton(listNames.get(0)));
	}

	private void setupListeners() {
		

	}

	private void setupLayout() {
		// Add action listener to your button here
	}

	private void changeList() {
		listNames.add("name3");
		this.buttonPanel.add(new JButton(listNames.get(1)));
		this.buttonLayout = new GridLayout(listNames.size(), 1);
		this.buttonPanel.setLayout(buttonLayout);
		System.out.println(listNames.size());
		this.validate();
	}
}

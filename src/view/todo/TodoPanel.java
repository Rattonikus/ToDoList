package view.todo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import controller.todo.TodoController;


public class TodoPanel extends JPanel 
{ 

	private TodoController app;

	//Top Panel
	private JPanel topPanel;
	private JPanel actionsPanel; 
	private JButton addItemButton;
	private JButton editItemButton;
	private JButton backButton; 

	private ArrayList<String> listNames; 
	private JPanel buttonPanel;
	private BoxLayout buttonLayout;
	private JScrollPane scroller; 
	private JButton list; 
	
	public TodoPanel(TodoController appController)
	{
		super();

		this.app = appController;

		//Top Panel
		this.topPanel = new JPanel();
		this.actionsPanel = new JPanel();
		this.addItemButton = new JButton("Add Item");
		this.editItemButton = new JButton("Edit Item");
		this.backButton = new JButton("Back");
		
		//UI Components
		this.buttonPanel = new JPanel();
		this.listNames = new ArrayList<String>();
		this.scroller = new JScrollPane(buttonPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		listNames.add("Task1");
		this.scroller.setPreferredSize(new Dimension(100, 800));
		this.list = new JButton(listNames.get(0));

		this.buttonLayout = new BoxLayout(buttonPanel, BoxLayout.Y_AXIS);
		
		this.setLayout(new BorderLayout());
		this.buttonPanel.setLayout(buttonLayout);

		this.paintBorder(getGraphics());
		
		
		setupPanel();
		setupLayout();
	}
	
	private void setupPanel()
	{
		//Top Panel
		this.add(topPanel, BorderLayout.NORTH);	
		this.topPanel.setLayout(new BorderLayout());
		this.topPanel.add(actionsPanel, BorderLayout.EAST);
		this.actionsPanel.add(addItemButton);
		this.actionsPanel.add(editItemButton);
		this.actionsPanel.setBackground(Color.GREEN);
		this.topPanel.add(backButton, BorderLayout.WEST);
		this.topPanel.setBackground(Color.GREEN);


		// Dimension maxSize = new Dimension(300, 20);
		// this.list.setMaximumSize(maxSize);
		// this.buttonPanel.add(list);
		// this.list.setAlignmentX(CENTER_ALIGNMENT);
		// this.buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // 10px vertical space

		// this.buttonPanel.setBackground(Color.CYAN);
		// //this.add(scroller, BorderLayout.NORTH);
		// this.validate();

	}
	
	private void setupListeners()
	{
		
	}
	
	private void setupLayout()
	{
		list.addActionListener(click -> changeList());
		

	}
	
	private void changeList()
	{
		Dimension maxSize = new Dimension(300, 20);
		listNames.add("name3");
		JButton newButton = new JButton(listNames.get(listNames.size() - 1));
		newButton.setMaximumSize(maxSize);
		this.buttonPanel.add(newButton);
		newButton.setAlignmentX(CENTER_ALIGNMENT);
		this.buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		System.out.println(listNames.size());
		this.validate();
	}


}
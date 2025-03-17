package view.todo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.time.LocalDateTime;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
	private JLabel time;

	//List Panel
	private JPanel listPanel;
	
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
		this.time = new JLabel(LocalDateTime.now().toString());

		//List Panel
		this.listPanel = new JPanel();

		
		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupPanel()
	{
		//Top Panel
		this.topPanel.setLayout(new BorderLayout());
		this.topPanel.add(actionsPanel, BorderLayout.EAST);
		this.actionsPanel.add(addItemButton);
		this.actionsPanel.add(editItemButton);
		this.actionsPanel.add(time);
		this.actionsPanel.setBackground(Color.GREEN);
		this.topPanel.add(backButton, BorderLayout.WEST);
		this.topPanel.setBackground(Color.GREEN);

		//List Panel
		this.listPanel.setBackground(Color.RED);
		this.listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

		this.setLayout(new BorderLayout());
		this.add(listPanel, BorderLayout.CENTER);
		this.add(topPanel, BorderLayout.NORTH);	

	}
	
	private void setupListeners()
	{
		this.backButton.addActionListener(click -> app.switchPanel("main"));
		this.addItemButton.addActionListener(click -> addItem());
	}
	
	private void setupLayout()
	{

	}
	
	private void addItem()
	{
		String itemName = JOptionPane.showInputDialog(null, "Enter the name of the item");
		JCheckBox newItem = new JCheckBox(itemName);
		listPanel.add(newItem);
		newItem.setAlignmentX(this.listPanel.CENTER_ALIGNMENT);
		this.revalidate();
		this.repaint();
	}


}
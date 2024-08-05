package view.todo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class TodoPanel extends JPanel 
{
	private ArrayList<String> listNames; 
	private JPanel fuckingPanel;
	private BoxLayout buttontrayofcook;
	private JScrollPane upanddown; 
	private JButton list; 
	private JButton button1; 

	

	
	public TodoPanel()
	{
		super();
		
		//UI Components
		this.button1 = new JButton();
		
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
		Dimension maxSize = new Dimension(300, 20);
		this.list.setMaximumSize(maxSize);
		this.buttonPanel.add(list);
		this.list.setAlignmentX(CENTER_ALIGNMENT);
		this.buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // 10px vertical space

		this.buttonPanel.setBackground(Color.CYAN);
		this.add(scroller, BorderLayout.NORTH);
		this.validate();

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

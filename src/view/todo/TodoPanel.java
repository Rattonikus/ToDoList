package view.todo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TodoPanel extends JPanel 
{
	private ArrayList<String> listNames; 
	private JPanel buttonPanel;
	private BoxLayout buttonLayout;
	private JScrollPane scroller; 
	private JButton list; 
	
	

	
	public TodoPanel()
	{
		super();
		
		//UI Components
		this.buttonPanel = new JPanel();
		this.listNames = new ArrayList<String>();
		this.scroller = new JScrollPane(buttonPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		listNames.add("Task1");
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
		
		this.buttonPanel.add(list);
		this.buttonPanel.setBackground(Color.CYAN);
		this.add(scroller);
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
		listNames.add("name3");
		this.buttonPanel.add(new JButton(listNames.get(listNames.size() - 1)));
		System.out.println(listNames.size());
		this.validate();
	}

}

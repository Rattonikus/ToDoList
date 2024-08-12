package view.todo;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import controller.todo.TodoController;


public class TodoFrame extends JFrame
{
	private TodoController app; 
	private TodoPanel panel; 
	private MainPanel mainPanel;

	public TodoFrame(TodoController appController)
	{
		super();

		this.app = appController;

		this.panel = new TodoPanel(app);
		this.mainPanel = new MainPanel(app);
		setupFrame();
	}
	
	private void setupFrame()
	{
		this.setContentPane(mainPanel);
		 this.setSize(500, 900);
		 this.setMinimumSize(new Dimension(300, 500));
		 this.setTitle("Java Todo");
		 this.setResizable(true);
		 this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		 this.setVisible(true);
	}

	public void switchPanel(String panelString)
	{
		if(panelString.equals("main"))
		{
			this.setContentPane(mainPanel);
		}
		else if(panelString.equals("todo"))
		{
			this.setContentPane(panel);
		}
		validate();
	}

	
	
	
}

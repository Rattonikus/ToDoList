package view.todo;

import java.awt.BorderLayout;

import javax.swing.JFrame;


public class TodoFrame extends JFrame
{
	private TodoPanel panel; 
	public TodoFrame()
	{
		super();
		this.panel = new TodoPanel();
		setupFrame();
	}
	
	private void setupFrame()
	{
		this.setContentPane(panel);
		 this.setSize(1200, 900);
		 this.setTitle("Java Todo");
		 this.setResizable(true);
		 
		 this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		 this.setVisible(true);
	}
	
	
}

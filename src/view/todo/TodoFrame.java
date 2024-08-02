package view.todo;

import javax.swing.JFrame;


public class TodoFrame extends JFrame
{
	
	public TodoFrame()
	{
		super();
		
		setupFrame();
	}
	
	private void setupFrame()
	{
		 this.setSize(1200, 900);
		 this.setTitle("Java Artwork");
		 this.setResizable(false);
		 this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		 this.setVisible(true);
	}
	
	
}

package controller.todo;

import javax.swing.JOptionPane;

import view.todo.TodoFrame;

public class TodoController 
{
	private TodoFrame frame; 
	
	public TodoController()
	{

	}
	
	public void start()
	{
		this.frame = new TodoFrame(this); //Initialize the frame	
	}

	public void switchPanel(String panelString)
	{
		frame.switchPanel(panelString);
	} 

	public static void errorHandler(Exception e)
	{
		JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	}

}

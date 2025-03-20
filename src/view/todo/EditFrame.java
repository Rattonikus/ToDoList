package view.todo;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class EditFrame extends JFrame {

    private JPanel editPanel;

    public EditFrame()
    {
        super();
		this.editPanel = new JPanel();
		setupPanel();
		setupFrame();
    }

	private void setupPanel()
	{
		this.setBackground(Color.blue);
	}

    private void setupFrame()
	{
		 this.setContentPane(editPanel);
		 this.setSize(500, 300);
		 this.setMinimumSize(new Dimension(300, 500));
		 this.setMaximumSize(new Dimension(500, 900));
		 this.setTitle("Java Todo");
		 this.setResizable(false);
		 this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		 this.setVisible(true);
	}
}

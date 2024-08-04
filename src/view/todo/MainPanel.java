package view.todo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MainPanel extends JPanel
{

    private JPanel topPanel;
    private JButton addList;
    private JButton editList;


    public MainPanel()
    {
        super();
        
        //Top Panel
        this.topPanel = new JPanel();
        this.topPanel.setPreferredSize(new Dimension(100, 100));


        this.addList = new JButton("Add List");
        this.editList = new JButton("Edit List");


        this.setLayout(new BorderLayout());
        this.topPanel.setLayout(null);

        setupPanel();

    }

    private void setupPanel()
    {
        //Top panel
        this.topPanel.add(addList);
        this.topPanel.add(editList);
        this.addList.setBounds(10, 10, 100, 80);
        this.editList.setBounds(120, 10, 100, 80);

        this.topPanel.setBackground(Color.gray);

        //Main Panel
        this.add(topPanel, BorderLayout.NORTH);
    
    }
    
}

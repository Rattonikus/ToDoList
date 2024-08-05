package view.todo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class MainPanel extends JPanel
{

    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel leftSpacer; 
    private JPanel rightSpacer;

    private JButton addList;
    private JButton editList;


    public MainPanel()
    {
        super();
        
        //Top Panel
        this.topPanel = new JPanel();
        this.topPanel.setPreferredSize(new Dimension(100, 100));

        //Top Panel Buttons
        this.addList = new JButton("Add List");
        this.editList = new JButton("Edit List");

        //Spacer Panels
        this.bottomPanel = new JPanel();
        this.leftSpacer = new JPanel();
        this.rightSpacer = new JPanel();
        
        this.bottomPanel.setPreferredSize(new Dimension(100, 100));
        this.leftSpacer.setPreferredSize(new Dimension(100, 100));
        this.rightSpacer.setPreferredSize(new Dimension(100, 100));
        
        //Main Panel
        this.setLayout(new BorderLayout());
        this.topPanel.setLayout(new BorderLayout());
        
        //Function Calls
        setupPanel();

    }

    private void setupPanel()
    {
        //Top panel buttons 
        this.topPanel.add(addList, BorderLayout.WEST);
        this.topPanel.add(editList, BorderLayout.EAST);
        this.addList.setPreferredSize(new Dimension(100,100));
        this.editList.setPreferredSize(new Dimension(100,100));
    
        this.topPanel.setBackground(Color.gray);

        //Border between buttons and edge of topPanel
        this.topPanel.setBorder(new EmptyBorder(10,10,10,10));

        //Spacer Panels
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(leftSpacer, BorderLayout.WEST);
        this.add(rightSpacer, BorderLayout.EAST);

        this.bottomPanel.setBackground(Color.green);
        this.leftSpacer.setBackground(Color.red);
        this.rightSpacer.setBackground(Color.blue);

        //Main Panel
        this.add(topPanel, BorderLayout.NORTH);
    
    }
    
}

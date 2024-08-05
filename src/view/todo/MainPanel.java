package view.todo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class MainPanel extends JPanel
{
    //Top panel data members
    private JPanel topPanel;

    private JButton addList;
    private JButton editList;

    //Spacer Panels
    private JPanel bottomPanel;
    private JPanel leftSpacer; 
    private JPanel rightSpacer;

    //List View Panel data members
    private JScrollPane listScroller;
    private JPanel listPanel;
    private JButton listButton;


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
        
        this.bottomPanel.setPreferredSize(new Dimension(100, 50));
        this.leftSpacer.setPreferredSize(new Dimension(50, 100));
        this.rightSpacer.setPreferredSize(new Dimension(50, 100));

        //List View Panel
        this.listPanel = new JPanel();
        this.listScroller = new JScrollPane(listPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.listButton = new JButton("List 1");
        
        //Main Panel
        this.setLayout(new BorderLayout());
        this.topPanel.setLayout(new BorderLayout());
        
        //Function Calls
        setupPanel();
        setupListeners();

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

        //List View Panel


        this.listPanel.setBackground(Color.MAGENTA);
        this.listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        this.listPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.listScroller.setPreferredSize(new Dimension(100, 800));
        
        //Main Panel
        this.add(listScroller, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);
    
    }

    private void setupListeners()
    {
        this.addList.addActionListener(click -> addButton());
    }

    private void addButton()
    {
        String newName = JOptionPane.showInputDialog("Enter List Name");
        JButton newList = new JButton(newName);
        newList.setPreferredSize(new Dimension(10, 50)); 
        newList.setMaximumSize(new Dimension(1200, 50)); 
        newList.setMinimumSize(new Dimension(10, 50)); 
        newList.setAlignmentX(this.listPanel.CENTER_ALIGNMENT);

        this.listPanel.add(newList);
        this.revalidate();
        this.repaint();

        System.out.println("Button Added");
    }
    
}

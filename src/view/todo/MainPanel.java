package view.todo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import controller.todo.IOController;
import controller.todo.TodoController;

public class MainPanel extends JPanel
{

    private TodoController app; 

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
    private JPanel editListPanel;
    private JPanel listContainer; 

    private SpringLayout layout;

    public MainPanel(TodoController appController)
    {
        super();
        
        this.app = appController;

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
        this.editListPanel = new JPanel();
        this.listContainer = new JPanel(); 

        this.listScroller = new JScrollPane(listPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.listContainer.add(listScroller);
        this.listContainer.add(editListPanel);
        
        //Main Panel
        this.setLayout(new BorderLayout());
        this.topPanel.setLayout(new BorderLayout());

        this.layout = new SpringLayout(); 
        
        //Function Calls
        setupPanel();
        setupListeners();
        setupConstraints();

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


        this.listContainer.setLayout(layout);

        //List View Panel


        //For the list Panel 
        this.listPanel.setBackground(Color.MAGENTA);
        this.listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        this.listPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        for (int i = 0; i<IOController.loadFromFileAsArray("FILE TWO").size(); i++)
        {
            JButton newList = new JButton(IOController.loadFromFileAsArray("FILE TWO").get(i));
            newList.setPreferredSize(new Dimension(10, 50)); 
            newList.setMaximumSize(new Dimension(1200, 50)); 
            newList.setMinimumSize(new Dimension(10, 50)); 
            newList.setAlignmentX(this.listPanel.CENTER_ALIGNMENT);
            newList.addActionListener(click -> listClicked("todo", "list", newList));

        this.listPanel.add(newList);
        }

        //For the edit buttons

        this.editListPanel.setBackground(Color.green);
        this.editListPanel.setLayout(new BoxLayout(editListPanel, BoxLayout.Y_AXIS));
        this.editListPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        for (int i = 0; i<IOController.loadFromFileAsArray("FILE TWO").size(); i++)
        {
            JButton newList = new JButton(IOController.loadFromFileAsArray("FILE TWO").get(i));
            newList.setPreferredSize(new Dimension(10, 50)); 
            newList.setMaximumSize(new Dimension(50, 50)); 
            newList.setMinimumSize(new Dimension(10, 50)); 
            newList.setAlignmentX(this.editListPanel.CENTER_ALIGNMENT);
            newList.addActionListener(click -> listClicked("todo", "list", newList));

        this.editListPanel.add(newList);
        }

        this.listScroller.setPreferredSize(new Dimension(100, 800));
        
        //Main Panel
        this.add(listContainer, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);
    
    }

    private void setupConstraints()
    {
        layout.putConstraint(SpringLayout.NORTH, listScroller, 50, SpringLayout.NORTH, listContainer);
        layout.putConstraint(SpringLayout.EAST, listScroller, -50, SpringLayout.EAST, listContainer);
        layout.putConstraint(SpringLayout.WEST, listScroller, 50, SpringLayout.WEST, listContainer);
        layout.putConstraint(SpringLayout.SOUTH, listScroller, -50, SpringLayout.SOUTH, listContainer);



    }

    private void setupListeners()
    {
        this.addList.addActionListener(click -> addButton());
    }

    private void addButton()
    {
        String newName = JOptionPane.showInputDialog("Enter List Name");
        IOController.saveToFile("FILE TWO", newName, true);
        JButton newList = new JButton(newName);
        newList.setPreferredSize(new Dimension(10, 50)); 
        newList.setMaximumSize(new Dimension(1200, 50)); 
        newList.setMinimumSize(new Dimension(10, 50)); 
        newList.setAlignmentX(this.listPanel.CENTER_ALIGNMENT);
        newList.addActionListener(click -> listClicked("todo", "list", newList));

        this.listPanel.add(newList);
        this.revalidate();
        this.repaint();

        System.out.println("Button Added");
    }

    private void listClicked(String panel, String list, JButton button)
    {
        System.out.println("List \"" + button.getText() + "\" Clicked");
        this.app.switchPanel(panel);
    }
    
}

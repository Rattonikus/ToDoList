package view.todo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

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

    private ArrayList<JButton> buttonList; 

    private SpringLayout layout;

    private int maxButton; 

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

        //this.listContainer.add(listPanel);
        //this.listContainer.add(editListPanel);
        this.listScroller = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        
        //Main Panel
        this.setLayout(new BorderLayout());
        this.topPanel.setLayout(new BorderLayout());

        this.layout = new SpringLayout(); 

        this.maxButton = 50; 

        this.buttonList = new ArrayList<JButton>();
        
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
            newList.setAlignmentX(this.listContainer.CENTER_ALIGNMENT);
            newList.addActionListener(click -> listClicked("todo", "list", newList));
            buttonList.add(newList);
            this.listContainer.add(buttonList.get(i));
            setupConstraints(newList, i + 1);
            System.out.println("button added");
        }


        //For the edit buttons

        this.editListPanel.setBackground(Color.green);
        this.editListPanel.setLayout(new BoxLayout(editListPanel, BoxLayout.Y_AXIS));
        this.editListPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        for (int i = 0; i<IOController.loadFromFileAsArray("FILE TWO").size(); i++)
        {
            String listName = (IOController.loadFromFileAsArray("FILE TWO").get(i) + "-" + i); 
            this.maxButton = this.maxButton += 50; 
            JButton newList = new JButton(listName);
            newList.setPreferredSize(new Dimension(10, 50)); 
            newList.setMaximumSize(new Dimension(50, 50)); 
            newList.setMinimumSize(new Dimension(10, 10)); 
            newList.setAlignmentX(this.editListPanel.CENTER_ALIGNMENT);
            newList.addActionListener(click -> listClicked("todo", "list", newList));
            this.editListPanel.add(newList);
        }

        this.listScroller.setPreferredSize(new Dimension(800, 800));
        this.listScroller.setMinimumSize(new Dimension(500, 500));

        //this.listContainer.setPreferredSize(new Dimension(800, 600));
        //Update the second value on this to the size of all the buttons in the constraint
        this.listContainer.setPreferredSize(new Dimension(800, maxButton));


        //this.listContainer.setMinimumSize(new Dimension(500, 500));

        this.listScroller.setViewportView(listContainer);

        
        //Main Panel
        this.add(listScroller, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);
    
    }

    private void setupConstraints(JButton someButton, int index)
    {
        
        layout.putConstraint(SpringLayout.NORTH, someButton, index * 50, SpringLayout.NORTH, listContainer);
        layout.putConstraint(SpringLayout.EAST, someButton, -50, SpringLayout.EAST, listContainer);
        layout.putConstraint(SpringLayout.WEST, someButton, 50, SpringLayout.WEST, listContainer);
    }

    private void setupListeners()
    {
        this.addList.addActionListener(click -> addButton());
        this.editList.addActionListener(click -> changeButtonName());
    }

    private void changeButtonName()
    {
        System.out.println(buttonList.get(1).getText());

        buttonList.get(1).setText("new name hahaha");
        System.out.println(buttonList.get(1).getText());
    }

    private void createEdit(JButton listItem)
    {
        
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
        setupConstraints(newList, IOController.loadFromFileAsArray("FILE TWO").size());

        this.listContainer.add(newList);
        this.maxButton = this.maxButton += 50; 
        this.listContainer.setPreferredSize(new Dimension(800, maxButton));
        

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

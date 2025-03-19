package view.todo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
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
    private JPanel listContainer; 
    private JScrollPane listScroller;
    private SpringLayout layout;
    private ArrayList<JButton> buttonList; 
    private int maxButton; 

    public MainPanel(TodoController appController)
    {
        super();
        
        this.app = appController;

        //Top Panel
        this.topPanel = new JPanel();
        this.topPanel.setPreferredSize(new Dimension(100, 100));
        this.topPanel.setLayout(new BorderLayout());

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
        this.listContainer = new JPanel(); 
        this.listScroller = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.layout = new SpringLayout(); 
        this.buttonList = new ArrayList<JButton>();
        this.maxButton = 50; 

        //Main Panel
        this.setLayout(new BorderLayout());

        //Setup functions
        setupPanel();
        setupListeners();
    }

    private void setupPanel()
    {
        //Top panel buttons 
        this.topPanel.add(addList, BorderLayout.WEST);
        this.topPanel.add(editList, BorderLayout.EAST);
        this.topPanel.setBackground(Color.gray);
        this.addList.setPreferredSize(new Dimension(100,100));
        this.editList.setPreferredSize(new Dimension(100,100));
        
        //Border between buttons and edge of topPanel
        this.topPanel.setBorder(new EmptyBorder(10,10,10,10));

        //Spacer Panels
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(leftSpacer, BorderLayout.WEST);
        this.add(rightSpacer, BorderLayout.EAST);

        this.bottomPanel.setBackground(Color.green);
        this.leftSpacer.setBackground(Color.red);
        this.rightSpacer.setBackground(Color.blue);

        
        
        //List container panel
        this.listContainer.setLayout(layout); 
        //create list buttons
        for (int i = 0; i<IOController.loadFromFileAsArray("FILE TWO").size(); i++)
        {
            JButton newList = new JButton(IOController.loadFromFileAsArray("FILE TWO").get(i));
            newList.setPreferredSize(new Dimension(20, 50)); 
            newList.setAlignmentX(this.listContainer.CENTER_ALIGNMENT);
            newList.addActionListener(click -> listClicked("todo", "list", newList));
            buttonList.add(newList);
            this.listContainer.add(buttonList.get(i));
            setupConstraints(buttonList.get(i), i + 1);
            System.out.println("button added");
        }
        //Update the second value on this to the size of all the buttons in the constraint
        this.listContainer.setPreferredSize(new Dimension(200, maxButton));


        //For the edit buttons
        for (int i = 0; i<IOController.loadFromFileAsArray("FILE TWO").size(); i++)
        {
            String listName = (IOController.loadFromFileAsArray("FILE TWO").get(i) + "-" + i); 
            this.maxButton = this.maxButton += 50; 
            JButton newList = new JButton(listName);
            newList.setPreferredSize(new Dimension(10, 50));
            newList.addActionListener(click -> listClicked("todo", "list", newList));
        }

        //For scrollbar
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
        this.editList.addActionListener(click -> editClicked());
    }

    private void editClicked()
    {
        System.out.println("edit clicked");
        for (int i = 0; i<IOController.loadFromFileAsArray("FILE TWO").size(); i++)
        {
            layout.putConstraint(SpringLayout.EAST, buttonList.get(i), -200, SpringLayout.EAST, listContainer);
            this.revalidate();
            this.repaint();
        }

        this.revalidate();
        this.repaint();
    }

    private void addButton()
    {
        String newName = JOptionPane.showInputDialog("Enter List Name");
        IOController.saveToFile("FILE TWO", newName, true);
        JButton newList = new JButton(newName);
        newList.setPreferredSize(new Dimension(10, 50)); 
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

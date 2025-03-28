package view.todo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

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
    private JPanel listContainer; 
    private JScrollPane listScroller;
    private SpringLayout layout;
    private ArrayList<JButton> listButtons;
    private int maxButton; 
    private boolean editMode; 
    
    private ArrayList<JButton> editButtons; 

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

        //Buttons
        this.listButtons = new ArrayList<JButton>();
        for (int i = 0; i<IOController.loadFromFileAsArray("FILE TWO").size(); i++)
        {
            JButton newList = new JButton(IOController.loadFromFileAsArray("FILE TWO").get(i));
            newList.setPreferredSize(new Dimension(20, 50)); 
            newList.setAlignmentX(this.listContainer.CENTER_ALIGNMENT);
            newList.addActionListener(click -> listClicked("todo", "list", newList));
            listButtons.add(newList);
        }

        this.maxButton = 50; 
        this.editMode = false;

        //Main Panel
        this.setLayout(new BorderLayout());

        //Setup functions
        setupPanel();
        setupListeners();
    }

    private void setupPanel()
    {
        //Top panel buttons 
        this.topPanel.add(this.addList, BorderLayout.WEST);
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

        //ListContainer
        this.listContainer.setLayout(layout); 

        setupButtons(listButtons, "list");

        //Update the second value on this to the size of all the buttons in the constraint
        this.listContainer.setPreferredSize(new Dimension(200, listButtons.size() * 50));

        //For scrollbar
        this.listScroller.setViewportView(listContainer);

        //Main Panel
        this.add(listScroller, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);
    }

    private void setupButtons(ArrayList<JButton> buttonList, String type)
    {
        for (int i = 0; i < buttonList.size(); i ++)
        {
            this.listContainer.add(buttonList.get(i));
            setupConstraints(buttonList.get(i), i, type);
        }
    }

    private void setupConstraints(JButton someButton, int index, String type)
    {
        layout.putConstraint(SpringLayout.NORTH, someButton, index * 50, SpringLayout.NORTH, listContainer);
        layout.putConstraint(SpringLayout.EAST, someButton, -50, SpringLayout.EAST, listContainer);
        if (type == "edit")
        {
            layout.putConstraint(SpringLayout.EAST, listButtons.get(index), -200, SpringLayout.EAST, listContainer);
            layout.putConstraint(SpringLayout.WEST, someButton, 50, SpringLayout.EAST, listButtons.get(index));
        }
        else if (type == "list")
        {
            layout.putConstraint(SpringLayout.WEST, someButton, 50, SpringLayout.WEST, listContainer);
        }
    }

    private void setupListeners()
    {
        this.addList.addActionListener(click -> addButton());
        this.editList.addActionListener(click -> editClicked());
    }

    private void editClicked()
    {
        if (!editMode)
        {
            this.editButtons = new ArrayList<JButton>();
            for (int i = 0; i<this.listButtons.size(); i++)
              {
                     int index = i; 
                     JButton listToDelete = this.listButtons.get(index);
                   System.out.println(i);
                  JButton newList = new JButton("Delete " + i);
                  newList.setPreferredSize(new Dimension(10, 50));
                  newList.addActionListener(click -> deleteItem(listToDelete, newList));
                  editButtons.add(newList);
              }
            setupButtons(editButtons, "edit");
            this.topPanel.repaint();
            this.editMode = true;
            this.editList.setText("Cancel");
            this.revalidate();
        }
        else 
        {
            this.editMode = false;
            this.editList.setText("Edit list");
            for (int i = 0; i < this.listButtons.size(); i++)
            {
                // layout.putConstraint(SpringLayout.NORTH, listButtons.get(i), i * 50, SpringLayout.NORTH, listContainer);
                // layout.putConstraint(SpringLayout.EAST, listButtons.get(i), -50, SpringLayout.EAST, listContainer);
                // layout.putConstraint(SpringLayout.WEST, listButtons.get(i), 50, SpringLayout.WEST, listContainer);

                setupConstraints(listButtons.get(i), i, "list");
                this.listContainer.remove(editButtons.get(i));
                this.revalidate();
            }

            this.topPanel.add(this.addList, BorderLayout.WEST);
            
            this.topPanel.repaint();

            this.revalidate();
        }
    }

    private void renameItem(int index)
    {
        String newName = JOptionPane.showInputDialog("Rename " + listButtons.get(index).getText() + "?");
        System.out.println(newName);
    }

    private void addButton()
    {
        String newName = JOptionPane.showInputDialog("Enter List Name");
        IOController.saveToFile("FILE TWO", newName, true);
        JButton newList = new JButton(newName);
        newList.setPreferredSize(new Dimension(10, 50)); 
        newList.addActionListener(click -> listClicked("todo", "list", newList));
        setupConstraints(newList, IOController.loadFromFileAsArray("FILE TWO").size() - 1, "list");
        listButtons.add(newList);
        
        
        this.listContainer.add(listButtons.get(listButtons.size() - 1));
        this.maxButton = this.maxButton += 50;         
        this.listContainer.setPreferredSize(new Dimension(200, maxButton));
        this.revalidate();

        System.out.println("Button Added");
    }

    private void listClicked(String panel, String list, JButton button)
    {
        if(editMode)
        {
            String newName = JOptionPane.showInputDialog("Rename " + button.getText() + "?");
            System.out.println(newName);
        }
        else
        {
            System.out.println("List \"" + button.getText() + "\" Clicked");
            this.app.switchPanel(panel);

        }
    }

    private void deleteItem(JButton itemToDelete, JButton deleteButton)
    {
        System.out.println(itemToDelete.getText() + " " + deleteButton.getText());
        this.listContainer.remove(itemToDelete);
        this.listContainer.remove(deleteButton);
        this.editButtons.remove(deleteButton);
        this.listButtons.remove(itemToDelete);
        // {
            //     //remove from list, regenerate file from list, remove all items and add them back. 
            //     this.revalidate();
            //     this.listContainer.remove(index);
            //     this.listButtons.remove(index);
            //     this.listContainer.remove(index);
            //     this.editButtons.remove(index);
            //     // IOController.saveToFile("FILE TWO", "", false);
            
            //     // System.out.println("Yes" + index);
            for (int i = 0; i < listButtons.size(); i ++)
            {
                // IOController.saveToFile("FILE TWO", listButtons.get(i).getText(), true);
                setupConstraints(listButtons.get(i), i, "list");
                setupConstraints(editButtons.get(i), i, "edit");
                this.revalidate();
            }
            
            //    // this.listButtons.remove(index);
            
            
            this.listContainer.repaint();
            this.revalidate();
            // if (JOptionPane.showConfirmDialog(this, "Delete " + listButtons.get(index).getText() + "?") == 0)
            

        // }
        // else
        // {
        //     System.out.println("no");
        // }
        // this.revalidate();

    }

    
}

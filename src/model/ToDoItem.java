package model;

import java.time.*;
import controller.todo.IOController;

public class ToDoItem {

    String[] listItems; 

    public ToDoItem()
    {
        this.listItems = new String[0];
    }

    public void saveTest()
    { 
        IOController.saveToFile("file", "Im text!!", false);
    }
    

    
}

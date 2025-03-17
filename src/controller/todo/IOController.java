package controller.todo;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class IOController 
{
        
        public static void saveToFile(String fileName, String text, boolean append)
        {

            File newFile = new File(fileName);

            try (FileOutputStream fileOutput = new FileOutputStream(newFile, append); Scanner textScanner = new Scanner(text))
            {
                while(textScanner.hasNext())
                {
                    String currentLine = textScanner.nextLine();
                    byte[] bytes = currentLine.getBytes();
                    if (append)
                    {
                        fileOutput.write('\n');
                        fileOutput.write(bytes);
                    }
                    else
                    {
                        fileOutput.write(bytes);
                    }                    
                }
            }

            catch (Exception e)
            {
                TodoController.errorHandler(e);
            }
            
            
        }
        
        public static String loadFromFile(String fileName)
        {
            String fileContents = "";
            
            File newFile = new File(fileName);
            
            try (Scanner fileScanner = new Scanner(newFile))
            {
                while(fileScanner.hasNext())
                {
                    fileContents += fileScanner.nextLine() + "\n";
                }
            }
            
            catch (Exception e)
            {
                TodoController.errorHandler(e);
            }
            
            return fileContents;
        }

        public static ArrayList<String> loadFromFileAsArray(String fileName)
        {
            ArrayList<String> fileContents = new ArrayList<String>(); 
            
            File newFile = new File(fileName);
            
            try (Scanner fileScanner = new Scanner(newFile))
            {
                while(fileScanner.hasNext())
                {
                    fileContents.add(fileScanner.nextLine());
                }
            }
            
            catch (Exception e)
            {
                TodoController.errorHandler(e);
            }
            
            return fileContents;
        }
        
        public void appendToFile(String fileName, String text)
        {
            
        }
        
        public void clearFile(String fileName)
        {
            
        }
        
        public void createFile(String fileName)
        {
            
        }
        
        public void deleteFile(String fileName)
        {
            
        }
}

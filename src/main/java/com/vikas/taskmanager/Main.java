/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vikas.taskmanager;

import java.util.List;
import java.util.Scanner;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.completer.StringsCompleter;


/**
 *
 * @author vikas
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int choice = 0;
        DBManager database = new DBManager();
        database.CREATETABLE("test");
        List<String> columnValues = database.fetchTask();
        // Create JLine's LineReader with a custom completer
        StringsCompleter completer = new StringsCompleter(columnValues); // Adding values for completion
        LineReader reader = LineReaderBuilder.builder().completer(completer).build();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        while(choice != 9) {
            choice = Integer.valueOf(scan.nextLine());
            switch(choice) {
//              What if user wants to enter only sleective fields????
//              Create user data file or class to store id count.
                case 1:
                    System.out.println("Enter Task name:");
                    String Task = scan.nextLine();
                    System. out.println("Enter Description:");
                    String Description = scan.nextLine();
                    System.out.println("Enter Status:");
                    String Status = scan.nextLine();
                    System.out.println("Enter due date & time (YYYY-MM-DD HH:MM:SS)");
                    String Due = scan.nextLine();
                    database.INSERTTASK(Task,Description,Status,Due);
                    columnValues = database.fetchTask();
                break; 
//              what if user has only one project? Why'd he need to tell the project name?
//              what if user wants priority from all projects/
//              What if user wants only specific task
                case 2:
                    database.DISPLAY();
                break;
//                  Add autocomplete feature
                case 3:
                    String MAD = reader.readLine("Enter task to mark as done (TAB to autocomplete): ");
                    String edited = MAD.trim();
                    database.MarkasDone(edited);
                break;
                case 4:
                    // CREATE DELETE TASK FUNCTION;
                    System.out.println("Enter the task Name/Id you want to delete");
                    String delkey = scan.nextLine();
                    if(isInteger(delkey)) {
                        database.DELETETask(Integer.parseInt(delkey));
                    } else {
                        database.DELETETask(delkey);
                    }
                break;
                case 5:
                    System.out.println("Enter Task Id to update");
                    int Id = Integer.valueOf(scan.nextLine());
                    System.out.println("Enter updated Task(Enter to skip):");
                    String updatedTask = scan.nextLine(); 
                    System.out.println("Enter updated Description(Enter to skip:)");
                    String updatedDescription = scan.nextLine();
                    System.out.println("Enter updated Status(Enter to skip:)");
                    String updatedStatus = scan.nextLine();
                    System.out.println("Enter updated Due Date(Enter to skip:)");
                    String updatedDuedate = scan.nextLine();
                break;
                case 6:
                    System.out.println("What task (Name/Id) do you want to search for");
                    String task = scan.nextLine();
                    if(isInteger(task)) {
                        database.SEARCH(Integer.parseInt(task));
                    } else {
                        database.SEARCH(task);
                    }
                break;
                case 7:
                    System.out.println("What tags do you want to search for?");
                    String tag = scan.nextLine();
                    database.SearchTag(tag);
                break;
                case 8:
                    database.SORTDue();
                break;
                case 9:
                    database.disconnect();
                break;    
                default:
                    System.out.println("Enter valid Input...");
                break;
            }
        }
    }
    public static boolean isInteger(String arg) {
        try {
            Integer.parseInt(arg);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

// to add autocomplete feature...


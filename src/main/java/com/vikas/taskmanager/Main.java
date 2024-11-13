/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vikas.taskmanager;

import java.time.Year;
import java.util.List;
import java.util.Scanner;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.completer.StringsCompleter;
import java.time.Year;


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
//              What if user wants to enter only sleective fields???? (Done)
//              Create user data file or class to store id count.(Done)
                case 1:
                    System.out.println("Enter Task name:");
                    String Task = scan.nextLine();
                    System. out.println("Enter Description:");
                    String Description = scan.nextLine();
                    System.out.println("Enter Status:");
                    String Status = scan.nextLine();
                    System.out.println("Enter due date & time (YYYY-MM-DD HH:MM:SS)");
                    String Due = scan.nextLine();
                    /* String[] dateSet = Due.split("-");
                   while (Integer.valueOf(dateSet[1]) >12 || Integer.valueOf(dateSet[1]) < 1 || Integer.valueOf(dateSet[2]) > 31 || Integer.valueOf(dateSet[2]) < 1 || Integer.valueOf(dateSet[0]) < Year.now().getValue()) {
                        System.out.println("Invalid Date...\nEnter Again");
                        Due = scan.nextLine();
                        dateSet = Due.split("-");
                    }*/
                    if(!Task.isEmpty() && !Description.isEmpty() && !Status.isEmpty() && !Due.isEmpty()) {
                        database.INSERTTASK(Task,Description,Status,Due);
                    System.out.println("Successfully saved the Task");
                    } else if(!Task.isEmpty() && !Status.isEmpty() && !Due.isEmpty() && Description.isEmpty()) {
                    database.INSERTTASK(Task,Status,Due);
                    System.out.println("Successfully saved the Task");
                    } else if(!Task.isEmpty() && !Due.isEmpty() && Description.isEmpty() && Status.isEmpty()) {
                    database.INSERTTASK(Task,Due);
                    System.out.println("Successfully saved the Task");
                    } else if(!Task.isEmpty() && Due.isEmpty() && Status.isEmpty() && Description.isEmpty()) {
                    database.INSERTTASK(Task);
                    System.out.println("Successfully saved the Task");
                    }
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
                    System.out.println("Task Status changed to Done");
                break;
                case 4:
                    // CREATE DELETE TASK FUNCTION;
                    System.out.println("Enter the task Name/Id you want to delete");
                    String delkey = scan.nextLine();
                    if(isInteger(delkey)) {
                        database.DELETETask(Integer.parseInt(delkey));
                        System.out.println("Task deleted Successfully");
                    } else {
                        database.DELETETask(delkey);
                        System.out.println("Task deleted successfully");
                    }
                break;
                case 5:
                    System.out.println("Enter Task Id to update");
                    int Id = Integer.valueOf(scan.nextLine());
                    System.out.println("Enter updated Description(Enter to skip:)");
                    String updatedDescription = scan.nextLine();
                    System.out.println("Enter updated Status(Enter to skip:)");
                    String updatedStatus = scan.nextLine();
                    System.out.println("Enter updated Due Date(Enter to skip:)");
                    String updatedDuedate = scan.nextLine();
                    database.EDIT(Id,updatedDescription,updatedStatus,updatedDuedate);
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
                case 10:
                    System.out.println("Due tasks you wanna search for");
                    System.out.println("1 For today\n2 For Tommorrow\n3 For past tasks\n4 For tasks this week\n5 For tasks this month");
                    int input = Integer.valueOf(scan.nextLine());
                    if(input ==1) {
                        database.searchInToday();    
                    } else if(input ==2) {
                        database.searchInTomm();
                } else if(input == 3) {
                    System.out.println("From: ");
                    String from = scan.nextLine();
                    System.out.println("To: ");
                    String to = scan.nextLine();
                    database.searchInDates(from,to);
                } else if(input == 4) {
                    database.searchInWeek();
                } else if(input == 5) {
                    database.searchInMonth();
                }
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


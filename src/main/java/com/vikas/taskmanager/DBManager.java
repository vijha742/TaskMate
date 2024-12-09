/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vikas.taskmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.Period;
import java.time.Duration;
import java.sql.*;
/**
 *
 * @author vikas
 */
public class DBManager {
    private Connection conn;
    public DBManager() {
        // entering a db that doen't existy will crteate that db
//      give user way to create new db without altering code...
        String url = "jdbc:sqlite:VikasJha.db";
        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                var meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
            }
            System.out.println("Connection to SQLite has been established.");
//            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        
    }
//  Id {PPTTT}
    public void CREATETABLE(String table_name) {
        var sql = "CREATE TABLE IF NOT EXISTS " + table_name + " ("
                + "	Id INTEGER PRIMARY KEY,"
                + "	Task TEXT NOT NULL,"
                + "	Description TEXT,"
                + "	Status TEXT,"
                + "     CreatedAt INTEGER,"
                + "     DueDate TEXT"
                + ");";

        try {
            var stmt = conn.createStatement();
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error in creating table");
        }
    }

//    INSERT INTO " + "test" + " (Id,Task,Description,Status,Created,Due) VALUES(1,?,?,?,CURRENT_TIMESTAMP,?);
//  what if some field doesn't exist?
    //  can be implemented when the databse structure is changed to have the first table name of all databses

    public void EDIT(int Id, String Description, String Status, String Duedate) {
        if(!Description.isEmpty() && !Status.isEmpty() && !Duedate.isEmpty()) {
        String update = "UPDATE test SET Description = ?, Status = ?, Duedate = ? WHERE Id = ?;";
        try (var pstmt = conn.prepareStatement(update)) {
            pstmt.setString(1, Description);
            pstmt.setString(2, Status);
            pstmt.setString(3, Duedate);
            pstmt.setInt(4, Id); 
            pstmt.executeUpdate();
            System.out.println("Task Modified Successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        } else if(!Description.isEmpty() && !Status.isEmpty() && Duedate.isEmpty()) {

        String update = "UPDATE test SET Description = ?, Status = ? WHERE Id = ?;";
        try (var pstmt = conn.prepareStatement(update)) {
            pstmt.setString(1, Description);
            pstmt.setString(2, Status);
            pstmt.setInt(3, Id); 
            pstmt.executeUpdate();
            System.out.println("Task Modified Successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        } else if(!Description.isEmpty() && Status.isEmpty() && !Duedate.isEmpty()) {

        String update = "UPDATE test SET Description = ?, Duedate = ? WHERE Id = ?;";
        try (var pstmt = conn.prepareStatement(update)) {
            pstmt.setString(1, Description);
            pstmt.setString(2, Duedate);
            pstmt.setInt(3, Id); 
            pstmt.executeUpdate();
            System.out.println("Task Modified Successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        } else if(Description.isEmpty() && !Status.isEmpty() && !Duedate.isEmpty()) {

        String update = "UPDATE test SET Status = ?, Duedate = ? WHERE Id = ?;";
        try (var pstmt = conn.prepareStatement(update)) {
            pstmt.setString(1, Status);
            pstmt.setString(2, Duedate);
            pstmt.setInt(3, Id); 
            pstmt.executeUpdate();
            System.out.println("Task Modified Successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        } else if(Description.isEmpty() && Status.isEmpty() && !Duedate.isEmpty()) {

        String update = "UPDATE test Duedate = ? WHERE Id = ?;";
        try (var pstmt = conn.prepareStatement(update)) {
            pstmt.setString(1, Duedate);
            pstmt.setInt(2, Id); 
            pstmt.executeUpdate();
            System.out.println("Task Modified Successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        }else if(Description.isEmpty() && !Status.isEmpty() && Duedate.isEmpty() ) {
        String update = "UPDATE test SET Status = ? WHERE Id = ?;";
        try (var pstmt = conn.prepareStatement(update)) {
            pstmt.setString(1, Status);
            pstmt.setInt(2, Id); 
            pstmt.executeUpdate();
            System.out.println("Task Modified Successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        } else if(!Description.isEmpty() && Status.isEmpty() && Duedate.isEmpty()) {
        String update = "UPDATE test SET Description = ? WHERE Id = ?;";
        try (var pstmt = conn.prepareStatement(update)) {
            pstmt.setString(1, Description);
            pstmt.setInt(2, Id); 
            pstmt.executeUpdate();
            System.out.println("Task Modified Successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        }
    }

    public void RENAMEPROJECT(String old_name, String new_name) {
        var rename = "ALTER TABLE ? RENAME TO ?;";
        try (var pstmt = conn.prepareStatement(rename)) {
            pstmt.setString(1, old_name);
            pstmt.setString(1, new_name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Couldn't rename project");
        }
    }
    
    public void DELETEPROJECT(String name) {
        var delete = "DROP TABLE ?;";
        try (var pstmt = conn.prepareStatement(delete)) {
            pstmt.setString(1,name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Couldn't delete project");
        }
    }
    
    public void Filter(String content) {
        try {
            var query = "SELECT * FROM test WHERE Description LIKE %?%;";
            var pstmt = conn.prepareStatement(query);
            pstmt.setString(1, content);
            var rs = pstmt.executeQuery();
            System.out.printf("%d\t%s\t%s\t%s\t%s\n",rs.getInt("Id"),rs.getString("Task"),rs.getString("Description"),rs.getString("Status"),rs.getInt("CreatedAt"),rs.getString("DueDate"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void INSERTTASK(String task,String description,String status,String due) {
        var sql = "INSERT INTO test" + " (Task,Description,Status,CreatedAt,DueDate) VALUES(?,?,?,CURRENT_TIMESTAMP,?);";
        try (var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,task);
            pstmt.setString(2,'"'+description+'"');
            pstmt.setString(3,status);
            pstmt.setString(4,due);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void INSERTTASK(String task,String status,String due) {
        var sql = "INSERT INTO test" + " (Task,Status,CreatedAt,DueDate) VALUES(?,?,CURRENT_TIMESTAMP,?);";
        try (var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,task);
            pstmt.setString(2,status);
            pstmt.setString(3,due);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void INSERTTASK(String task,String due) {
        var sql = "INSERT INTO test" + " (Task,CreatedAt,DueDate) VALUES(?,CURRENT_TIMESTAMP,?);";
        try (var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,task);
            pstmt.setString(2,due);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void INSERTTASK(String task) {
        var sql = "INSERT INTO test" + " (Task,CreatedAt) VALUES(?,CURRENT_TIMESTAMP);";
        try (var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,task);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void DISPLAY() {
        var sql = "SELECT * FROM test;"; // to fetch all the tsak in the selected table
        try ( var pstmt = conn.prepareStatement(sql)) {
        var res = pstmt.executeQuery();
        while(res.next()){
            System.out.printf("%d\t%s\t%s\t%s\t%s\t%s\n",res.getInt("Id"),res.getString("Task"),res.getString("Description"),res.getString("Status"),res.getString("CreatedAt"),res.getString("DueDate"));
        }
        } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        }
    }   

    public void MarkasDone(String task) {
        var update = "UPDATE test SET Status = 'Done' WHERE Task = ?;";
        try(var pstmt = conn.prepareStatement(update)) {
            pstmt.setString(1,task);
            System.out.println("Execution successful or not: " + pstmt.executeUpdate());
        } catch (SQLException e) {
            System.out.println("Couldn't mark task as done");
            System.out.println(e.getMessage());
        }
    }
    
    public void DELETETask(int Id) {
    var del = "DELETE FROM test WHERE Id = ?;";
        try {
            var stmt = conn.prepareStatement(del);
            stmt.setInt(1,Id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void DELETETask(String task) {
        var del = "DELETE FROM test WHERE Task = ?;";
        try {
            var stmt = conn.prepareStatement(del);
            stmt.setString(1,task);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void SEARCH(int Id) {
        var search = "SELECT * FROM test WHERE Id = ?;";
        try(var pstmt = conn.prepareStatement(search)) {
            pstmt.setInt(1,Id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.printf("%d\t%s\t%s\t%s\t%s\n",rs.getInt("Id"),rs.getString("Task"),rs.getString("Description"),rs.getString("Status"),rs.getInt("CreatedAt"),rs.getString("DueDate")); // Fetch values from the column
            }
        } catch (SQLException e) {
            System.out.println("Couldn't complete search");
        }
    }
    
    public void SEARCH(String task) {
        var search = "SELECT * FROM test WHERE Task LIKE ?;";
        String userInput = "%" + task + "%";
        try(var pstmt = conn.prepareStatement(search)) {
            pstmt.setString(1,userInput);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.printf("%d\t%s\t%s\t%s\t%s\n",rs.getInt("Id"),rs.getString("Task"),rs.getString("Description"),rs.getString("Status"),rs.getInt("CreatedAt"),rs.getString("DueDate")); // Fetch values from the column
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void SearchTag(String tag) {
        try {
            var query = "SELECT * FROM test WHERE Description LIKE ?;";
            var pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "%#"+tag+"%");
            var rs = pstmt.executeQuery();
            System.out.printf("%d\t%s\t%s\t%s\t%s\n",rs.getInt("Id"),rs.getString("Task"),rs.getString("Description"),rs.getString("Status"),rs.getInt("CreatedAt"),rs.getString("DueDate"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchInDates(String start, String end) {
        try {
            var query = "SELECT * FROM test WHERE DueDate > ? and DueDate < ?;";
            var pstmt = conn.prepareStatement(query);
            pstmt.setString(1, start);
            pstmt.setString(2, end);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.printf("%d\t%s\t%s\t%s\t%d\t%s\n",
                    rs.getInt("Id"),
                    rs.getString("Task"),
                    rs.getString("Description"),
                    rs.getString("Status"),
                    rs.getInt("CreatedAt"),
                    rs.getString("DueDate"));
            } 
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }

    public void searchInToday() {
        try {
            String query = "SELECT * FROM test WHERE DueDate = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, LocalDate.now().toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.printf("%d\t%s\t%s\t%s\t%d\t%s\n",
                    rs.getInt("Id"),
                    rs.getString("Task"),
                    rs.getString("Description"),
                    rs.getString("Status"),
                    rs.getInt("CreatedAt"),
                    rs.getString("DueDate"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchInTomm() {
    try {
        String query = "SELECT * FROM test WHERE DueDate = ?;";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, LocalDate.now().plusDays(1).toString()); // Set date to tomorrow
        ResultSet rs = pstmt.executeQuery();
        
        while (rs.next()) {
            System.out.printf("%d\t%s\t%s\t%s\t%d\t%s\n",
                rs.getInt("Id"),
                rs.getString("Task"),
                rs.getString("Description"),
                rs.getString("Status"),
                rs.getInt("CreatedAt"),
                rs.getString("DueDate"));
        }
    } catch (SQLException e) {
        System.out.println("Error retrieving tasks due tomorrow: " + e.getMessage());
    }
}

    public void searchInWeek() {
        try {
            var query = "SELECT * FROM test WHERE DueDate > ? AND DueDate < ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,LocalDate.now().toString());
            pstmt.setString(2,LocalDate.now().plusDays(7).toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.printf("%d\t%s\t%s\t%s\t%d\t%s\n",
                    rs.getInt("Id"),
                    rs.getString("Task"),
                    rs.getString("Description"),
                    rs.getString("Status"),
                    rs.getInt("CreatedAt"),
                    rs.getString("DueDate"));
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchInMonth() {
        try {
            var query = "SELECT * FROM test WHERE strftime('%Y', DueDate) = ? AND strftime('%m', DueDate) = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,String.valueOf(LocalDate.now().getYear()));
            pstmt.setString(2,String.format("%02d",LocalDate.now().getMonthValue()));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.printf("%d\t%s\t%s\t%s\t%d\t%s\n",
                    rs.getInt("Id"),
                    rs.getString("Task"),
                    rs.getString("Description"),
                    rs.getString("Status"),
                    rs.getInt("CreatedAt"),
                    rs.getString("DueDate"));
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void SORTDue() {
    String sql = "SELECT * FROM test WHERE Status <> 'Done' ORDER BY DueDate DESC;";

    try (var pstmt = conn.prepareStatement(sql)) {
        var res = pstmt.executeQuery();
        while (res.next()) {
            System.out.printf(
                "%d\t%s\t%s\t%s\t%d\t%s\n", 
                res.getInt("Id"),
                res.getString("Task"),
                res.getString("Description"),
                res.getString("Status"),
                res.getInt("CreatedAt"),
                res.getString("DueDate")
            );
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

    public void disconnect() {
        try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
        }
    }
    
    public List<String> fetchTask() {
        List<String> values = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            String query = "SELECT DISTINCT(Task) FROM test;";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                values.add(rs.getString(1)); // Fetch values from the column
            }

        } catch (Exception e) {
        }
        return values;
    }
}

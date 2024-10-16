
# TaskMate

### A Comprehensive Task Management System

TaskMate is a powerful and user-friendly task management system built with Java. It helps users manage their tasks efficiently, with features like task creation, editing, filtering, and completion. The application stores tasks in an SQLite database and supports both command-line and TUI-based interaction, making it flexible for different use cases.

---

## Features

- **Task Management**: Create, edit, delete, and mark tasks as complete.
- **Task Filtering**: Filter tasks based on status, tags, or due date.
- **Tag-Based Search**: Search for tasks using specific tags in their descriptions.
- **Autocomplete**: Speed up task creation and editing using JLine Reader's autocomplete functionality.
- **TUI (Text User Interface)**: Integrated TUI using TMUX and Bash to create a streamlined workflow.
- **Persistent Storage**: All tasks are stored in an SQLite database for persistence.

---

## Installation Instructions

### Prerequisites
- Java 11 or above.
- SQLite 3.
- TMUX (for TUI support).
- Bash (for TUI startup script).

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/taskmate.git
```

### 2. Set Up the Database
Ensure you have SQLite installed and set up the initial database schema by running the SQL file provided in `/db`.

```bash
sqlite3 taskmate.db < db/schema.sql
```

### 3. Configure the Project
- Open the project in your preferred IDE.
- Ensure the JDBC driver for SQLite is configured correctly in the project classpath.

### 4. Build and Run the Application
- Use your IDE to build the project.
- Run the `Main.java` class to start the application.

```bash
cd src
javac com/taskmate/Main.java
java com.taskmate.Main
```

### 5. Optional: Start the TUI
To use the TUI, run the Bash startup script:
```bash
./start_taskmate_tui.sh
```

---

## Usage

### 1. Add a Task
- Use the `add` command to create a new task. You can specify the task description, due date, and status.
- Example:
```bash
add "Complete Java project" due:2024-10-20 status:pending
```

### 2. Edit a Task
- Use the `edit` command to modify an existing task. Specify the task ID and the new details.
- Example:
```bash
edit 5 "Update JavaDocs for project" due:2024-10-25
```

### 3. Mark a Task as Done
- Use the `done` command to mark a task as complete.
- Example:
```bash
done 3
```

### 4. Filter and Search Tasks
- Use the `filter` command to view tasks based on status, due date, or tags.
- Example:
```bash
filter status:pending due:<2024-10-20
```

- Use the `search` command to find tasks with specific keywords or tags.
- Example:
```bash
search #development
```

---

## Future Enhancements
- **User Authentication**: Add multi-user support with login functionality.
- **Task Categorization**: Allow users to categorize tasks into projects or labels.
- **File Handling**: Add functionality for attaching files or documents to tasks.
- **Notifications**: Implement reminder notifications for upcoming or overdue tasks.

---

## Contributing

We welcome contributions to improve the TaskMate project! Feel free to fork the repository and submit a pull request. Please ensure that your code follows our guidelines and is properly documented.

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## Contact

For any questions or feedback, feel free to reach out to the project maintainer:

- **Email**: yourname@example.com
- **GitHub**: [yourusername](https://github.com/yourusername)

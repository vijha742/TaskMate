
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
- Maven
- TMUX (for TUI support).
- Bash (for TUI startup script).

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/taskmate.git
```

### 2. Set Up the Database
- Ensure you have SQLite installed.
Check by running:

```bash
sqlite3
```
- Ensure maven is installed.
Check by running:

```bash
mvn --version
```
- For TUI to work, you need to install tmux
To install tmux, run:

```bash
sudo apt install tmux
```
To check tmux version use:

```bash
tmux --version
```

### 3. Configure the Project
- Go to the TaskMate directory
```bash
cd path/to/directory/Taskmate/
```
- Run the following command:
```bash
mvn compile
chmod +x TaskMate.sh
```
It'd take around 30 seconds to configure for the fist time and now you're all set to keep track of your tasks using TaskMate.

### 5. Start the TUI
To use the TUI, run the Bash startup script:
```bash
./TaskMate.sh
```

---

## Usage

### 1. Add a Task
### 2. Edit a Task
### 3. Mark a Task as Done
### 4. Filter and Search Tasks
### 5. Task Categorization
---

## Future Enhancements
- **User Authentication**: Add multi-user support with login functionality.
- **Recurring Tasks**: Allow users to add recurring tasks.
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

- **Email**: jhavikas2004@google.com
- **GitHub**: [Vijha742](https://github.com/vijha742)

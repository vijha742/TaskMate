#!/bin/bash
gnome-terminal --full-screen -- /bin/bash -c "tmux new-session -d -s taskManager;
tmux split-window -v;
tmux split-window -h;
tmux send-keys \"cd Downloads/ && ./sqlite3 /home/vikas/sqlite3/VikasJha.db\" Enter;
sleep 1
tmux send-keys \".mode column\" C-m;
tmux send-keys \".headers on\" C-m;
tmux send-keys \".width auto auto 25 auto auto auto\" C-m;
tmux send-keys \"SELECT * FROM test;\";
tmux select-pane -t 0;
tmux send-keys \"figlet -t -c -f big ...Welcome to Task Manager...\" Enter;
tmux select-pane -t 1;
tmux split-window -v;
tmux select-pane -t 1;
tmux split-window -h;
tmux send-keys \"cat Documents/Vikas/Task\ Manager/User_status.txt\" Enter;
tmux select-pane -t 3;
tmux send-keys \"cd ~/Documents/taskManager/\" Enter;
tmux send-keys \"mvn -q compile\" Enter;
tmux send-keys \"mvn -q exec:java\" Enter;
tmux select-pane -t 1;
tmux send-keys \"cat Documents/taskManager/Commands.md\" Enter;
# Attach to tmux first, and then resize after everything has initialized
tmux attach -t taskManager"

# Now resize after attaching
sleep 2  # Give time for tmux to start fully
tmux resize-pane -t 0 -y 9;
tmux resize-pane -t 2 -x 80;
tmux resize-pane -t 3 -y 25;
tmux resize-pane -t 4 -x 82;
tmux resize-pane -t 1 -x 30;

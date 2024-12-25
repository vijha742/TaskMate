#!/bin/bash
gnome-terminal --full-screen -- /bin/bash -c "tmux new-session -d -s TaskMate;
tmux split-window -v;
tmux split-window -h;
sleep 0.1;
tmux send-keys \"sqlite3 VikasJha.db\" Enter;
tmux send-keys \".mode box\" C-m;
tmux send-keys \".headers on\" C-m;
tmux send-keys \".width auto auto\" C-m;
tmux send-keys \"SELECT Id,Task FROM test\;\" Enter;
tmux select-pane -t 1;
tmux split-window -v;
tmux select-pane -t 1;
tmux split-window -h;
tmux select-pane -t 3;
tmux send-keys \"cd ~/TaskMate/\" Enter;
tmux send-keys \"mvn -q exec:java\" Enter;
tmux select-pane -t 0;
tmux send-keys \"export PS1=''\" Enter;
tmux send-keys \"echo -e '\e[1;36m'; figlet -t -c -f standard ...Welcome to Task Mate...; echo -e '\e[0m'\" Enter;
tmux send-keys -t1 \"export PS1=''\" Enter;
tmux send-keys -t1 \"clear\" Enter;
tmux send-keys -t1 \"cat ~/TaskMate/Commands.md\" Enter;
tmux send-keys -t2 \"export PS1=''\" Enter;
tmux send-keys -t2 \"clear\" Enter;
tmux send-keys -t2 \"~/TaskMate/./Userdata\" Enter;
tmux attach -t TaskMate"

# Now resize after attaching
sleep 1  # Give time for tmux to start fully
tmux resize-pane -t 0 -y 9;
tmux resize-pane -t 2 -x 80;
tmux resize-pane -t 3 -y 25;
tmux resize-pane -t 4 -x 82;
tmux resize-pane -t 1 -x 30;

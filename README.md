# Cube-Solver
A Rubik's Cube solver

This is a heuristic (not-optimal, but still functional) Rubik's Cube solver that is built in Java and in Racket. The reason it does not 
produce an optimal solution is because it is extremly difficult to find an optimal solution without brute force. This is because 
non-brute-force solutions involve filtering complicated cycles, mirror solutions, and more. It is possible, and certainly has been done, 
but it is very difficult and is a better fit for a team of several people rather than a single person. So then the next question, why not 
brute force it? Well the most amount of moves it takes to solve a Rubik's Cube is 20 (assuming half turn metric), and there are 
19 half turn metric possible moves including a do nothing move. To brute force an optimal solution, the computer would have to run through
20^19 states and check every one and then compare it to others to see which is the shortest possible solution. Unless super computers
and/or CPU clusters have become readily available, I do not have enough computation power for that.

Enjoy solving! 
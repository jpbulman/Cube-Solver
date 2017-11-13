# Cube-Solver
A Rubik's Cube solver

This is a heuristic (not-optimal, but still functional) Rubik's Cube solver that is built in Java. The reason it does not 
produce an optimal solution is because it is extremly difficult to find one without brute force. This is because non-brute-force solutions 
involve filtering complicated cycles, mirror solutions, and more. It is possible, and certainly has been done, 
but it is very difficult and is a better fit for a team of several people rather than a single person. So then the next question, why not 
brute force it? Well the most amount of moves it takes to solve a Rubik's Cube is 20 (assuming half turn metric), and there are 
19 half turn metric possible moves including a do nothing move. To brute force an optimal solution, the computer would have to run through
20^19 states, check every one, and then compare each to others to see which has the shortest possible solution. Unless super computers
and/or CPU clusters have become readily available, I do not have enough computation power for that.

This solver uses standard half-turn metric notation for solving; if you want to learn more about this, here is a helpful link:
https://ruwix.com/the-rubiks-cube/notation/

I also made a flowchart for this program, it can be viewed and/or downloaded here:
https://drive.google.com/file/d/1V4ERkIpcY0_ZWzC1y0A-drk8lpb8yKcy/view?usp=sharing
This algorithm is able to take in any scrambled cube and produce a solved one through step by step processing.

Enjoy solving! 
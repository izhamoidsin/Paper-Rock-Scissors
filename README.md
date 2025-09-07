# Paper-Rock-Scissors
Simple player versus environment Paper-Rock-Scissors game implemented as a java console application
 
# General requirements and instructions
- Make sure we can compile and run your code

The purpose of this test is evaluate your technical skills, design skills and ability to interpret
requirements. At IMC we value simple, readable and maintainable code. Use of third party libraries is
acceptable, but ensure they add sufficient value. 

They are open problems and there is no right or wrong answer.

## Problem
Paper-Rock-Scissors is a game for two players. Each player simultaneously opens his/her hand to display
a symbol:
- Fist equals rock
- Open hand equals paper
- Showing the index and middle finger equals scissors.

The winner is determined by the following schema:
- Paper beats (wraps) rock
- Rock beats (blunts) scissors
- Scissors beats (cuts) paper.

Write a program that plays Paper-Rock-Scissors between the computer and a real player. You should be
able to play the game n times before the program exits.

When starting the assignment, please take the following into account:
- We would like you to provide a solution as console application and as if it were for a production system. Please keep it simple (e.g., refrain from http-server based solutions and / or docker components).
- The solution needs to meet the requirements with simple, clean, understandable, and extensible code.
- Testing is important to us, so provide good test coverage.
- We like candidates to demonstrate the power of Java, (e.g., apply design patterns and use object-oriented modeling where appropriate).
- If do you decide to use frameworks or libraries, please ensure they add sufficient value and explain why you chose to use a particular framework or library in a README file.


# Design considerations:
1. Game could be extended in the future: 
   1. Player vs computer -> player vs mob of computers.
   2. Paper-rock-scissors -> paper-rock-scissors-lizard-spock. 
2. Computer should be able to play differently:
   1. Make random moves.
   2. Make the same move all the time.
   3. Learn from previous (all/n) rounds and chose the best next move.
3. Game logic is computer/human agnostic
4. HumanPlayer and Game are console agnostic
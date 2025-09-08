# Paper-Rock-Scissors
Simple player versus environment Paper-Rock-Scissors game implemented as a java console application

# Instructions to run:
Standard maven lifecycle
- Test: ```mvn test```
- Run (via exec plugin): ```mvn compile exec::java```

Or build and run the class directly
```
mvn package
java -cp target/paper-rock-scissors-1.0-SNAPSHOT.jar com/izham/prs/Main
```

> The app does not take any command line parameters and does not have a configuration file. It reads all it needs from the console after launch. You'll be prompted to specify number of rounds and a gesture/move for each round.

Sample of the game output
```aiignore
Enter number of rounds you want to play: 2
Please show your hand F - FIST \ H - OPEN_HAND \ V - INDEX_AND_MIDDLE_FINGERS: f
Round 1
Player played ROCK
Computer played ROCK
It's a draw!


Please show your hand F - FIST \ H - OPEN_HAND \ V - INDEX_AND_MIDDLE_FINGERS: v
Round 2
Player played SCISSORS
Computer played PAPER
The winner is Player!


==================================
GAME OVER
The score board:
Player: 1
Computer: 0
```

# Implementation notes
## Design considerations
1. Anticipated future changes to the game requirements: 
   1. Player vs computer -> player vs mob of computers.
      - Then another subclass of `Game` should be created, e.g. `GameOfMany`. Knowing there are only two players, keeps `GameOfTwo` easier to limit premature generalization.
      - `Player`, `Strategy`, `RoundHistory` and `UI` were easy to make future-proof, so I went for it. They are not limited to two players only. 
   2. Paper-rock-scissors -> paper-rock-scissors-lizard-spock.
      - `Move` enum should be updated. Compiler and unit tests will provide safety net for the updated game logic.
2. Computer should be able to play differently (via _strategy pattern_):
   1. Make random moves.
      - Built-in `Random` is used for a sequence generation.
      - The uniform distribution of moves is tested in `RandomStrategyTest`.
      - There's internally available constructor with `seed` parameter to instrument the Random in tests.
   2. Make the same move all the time.
      - see the `BruteStrategy`.
   3. Learn from previous (all/last n) rounds and chose the best next move.
      - I implemented only naive tracking of all previous moves of the learning computer opponent.
      - There could be one more strategy pattern application to `ComputerPlayer` memory. 
3. Game logic is computer/human agnostic (thanks to the `Player` abstraction).
4. HumanPlayer is console agnostic (due to _DI_ and `Supplier<>` abstraction)
   1. The input can be stubbed and replaced by any iterator, file, etc.
5. Game is console agnostic (via _observer pattern_)
   1. Console could be easily replaced with a logger.
   

## Technology and library choice
- **Build**: Apache Maven (quickstart template). It’s a simple, widely used build tool for Java apps.
- **Run**: exec-maven-plugin added for convenience.
- **Tests**: JUnit 5, including parameterized tests. Standard solution for unit tests. Unless anything specific/sophisticated is needed, it is my default choice. 
- **Language features**: modern Java constructs such as **Record** classes, **Stream API**, and **pattern-matching for switch**.
  - **Runtime requirement**: Java 21.
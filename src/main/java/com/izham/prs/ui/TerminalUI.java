package com.izham.prs.ui;

import com.izham.prs.game.Move;
import com.izham.prs.game.RoundHistory;
import com.izham.prs.player.Player;

import java.util.*;
import java.util.stream.Collectors;

public class TerminalUI implements UI {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public int askRounds() {
        while (true) {
            System.out.print("Enter number of rounds you want to play: ");
            var input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                // ignore the typo, just ask one more time
            }
        }
    }

    @Override
    public Move askNextMove() {
        Optional<Symbol> symbol = Optional.empty();
        while (symbol.isEmpty()) {
            var hint = Arrays.stream(Symbol.values()).map(Symbol::name).collect(Collectors.joining("\\"));
            System.out.printf("Please show your hand %s: ", hint);
            var input = scanner.nextLine().trim();
            symbol = Symbol.fromToken(input);
        }

        return symbol.get().getMove();
    }

    @Override
    public void showRoundResult(RoundHistory roundHistory) {
        System.out.printf("Round %d\n", roundHistory.round()); // todo maybe redundant
        roundHistory.moves().forEach((player, move) -> {
            System.out.printf("%s shows %s\n", player.getName(), move); // todo emoji?
        });
        if (roundHistory.winners().isEmpty()) {
            System.out.print("It's a draw!\n");
        } else if (roundHistory.winners().size() == 1) {
            System.out.printf("The winner is %s!\n", roundHistory.winners().iterator().next().getName());
        } else {
            var winners = roundHistory.winners().stream().map(Player::getName).collect(Collectors.joining(","));
            System.out.printf("Winner are %s!\n", winners);
        }

        System.out.println("\n");
    }

    @Override
    public void showScoreboard(Map<Player, Integer> scoreboard) {
        System.out.println("The score board:");
        scoreboard.forEach((key, value) -> System.out.println(key.getName() + ": " + value));

        // todo find an print a winner or sort the scoreboard by the final score
    }
}

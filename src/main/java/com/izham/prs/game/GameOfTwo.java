package com.izham.prs.game;

import com.izham.prs.player.Player;

import java.util.*;
import java.util.stream.Collectors;

public class GameOfTwo implements Game {
    private final int numberOfRounds;

    private final Player playerA;
    private final Player playerB;

    private final Map<Player, Integer> scoreboard;

    private final Set<RoundHistoryObserver> observers;

    public GameOfTwo(int numberOfRounds, Player playerA, Player playerB) {
        this.numberOfRounds = numberOfRounds;
        this.playerA = playerA;
        this.playerB = playerB;
        this.scoreboard = new HashMap<>() {{
            put(playerA, 0);
            put(playerB, 0);
        }};
        this.observers = new HashSet<>() {{
           add(playerA);
           add(playerB);
        }};
    }

    private void playRound(int round) {
        var moveA = playerA.nextMove();
        var moveB = playerB.nextMove();

        Optional<Player> winner = switch (moveA) {
            case Move m when m.beats(moveB) -> Optional.of(playerA);
            case Move m when moveB.beats(m) -> Optional.of(playerB);
            default -> Optional.empty();
        };

        winner.ifPresent(player ->
                scoreboard.put(player, scoreboard.getOrDefault(player, 0) + 1)
        );

        var roundResult = new RoundHistory(
                round,
                Map.of(playerA, moveA, playerB, moveB),
                winner.stream().collect(Collectors.toSet())
        );

        notifyObservers(roundResult);
    }

    @Override
    public void play() {
        for (int round = 1; round <= numberOfRounds; round++) {
            playRound(round);
        }
    }

    @Override
    public Map<Player, Integer> getScoreboard() {
        // readonly copy to be safe that it is not modified by the code outside of this class
        return Map.copyOf(scoreboard);
    }

    public void addObserver(RoundHistoryObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(RoundHistoryObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(RoundHistory roundHistory) {
        observers.forEach(observer -> observer.onRoundComplete(roundHistory));
    }
}

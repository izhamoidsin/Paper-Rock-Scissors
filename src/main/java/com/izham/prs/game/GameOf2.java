package com.izham.prs.game;

import com.izham.prs.player.Player;
import com.izham.prs.ui.UI;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GameOf2 implements Game {
    private final int numberOfRounds;
    private final UI ui;

    private final Player playerA;
    private final Player playerB;

    private final Map<Player, Integer> scoreboard;

    public GameOf2(int numberOfRounds, UI ui, Player playerA, Player playerB) {
        this.numberOfRounds = numberOfRounds;
        this.ui = ui;
        this.playerA = playerA;
        this.playerB = playerB;
        this.scoreboard = new HashMap<>() {{
            put(playerA, 0);
            put(playerB, 0);
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

        playerA.observeRound(roundResult);
        playerB.observeRound(roundResult);

        // todo try using observer
        ui.showRoundResult(roundResult);
    }

    @Override
    public void play() {
        for (int round = 0; round < numberOfRounds; round++) {
            playRound(round);
        }
    }

    @Override
    public Map<Player, Integer> getScoreboard() {
        return Map.copyOf(scoreboard);
    }
}

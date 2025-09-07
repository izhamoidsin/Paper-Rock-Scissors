package com.izham.prs.player;

import com.izham.prs.game.Move;
import com.izham.prs.game.RoundHistory;
import com.izham.prs.player.strategy.Strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ComputerPlayer extends Player {
    private final Strategy strategy;
    private final List<Move> opponentMovesMemory;

    public ComputerPlayer(String name, Strategy strategy) {
        super(name);
        this.strategy = strategy;
        this.opponentMovesMemory = new ArrayList<>();
    }

    @Override
    public Move nextMove() {
        return strategy.decideMove(opponentMovesMemory);
    }

    @Override
    public void observeRound(RoundHistory roundHistory) {
        var movesToRemember = roundHistory
                .moves()
                .entrySet()
                .stream()
                .filter(playersMove -> !playersMove.getKey().getId().equals(getId()))
                .map(Map.Entry::getValue)
                .toList();

        opponentMovesMemory.addAll(movesToRemember);
    }
}


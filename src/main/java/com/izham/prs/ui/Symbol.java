package com.izham.prs.ui;

import com.izham.prs.game.Move;

import java.util.Optional;
import java.util.Set;

public enum Symbol {
    FIST(Set.of("f", "fist"), Move.ROCK),
    OPEN_HAND(Set.of("h", "hand", "open hand"), Move.PAPER),
    INDEX_AND_MIDDLE_FINGERS(Set.of("v", "fingers", "index and middle finger"), Move.SCISSORS);

    private final Set<String> tokens;
    private final Move move;

    Symbol(Set<String> tokens, Move move) {
        this.tokens = tokens;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    public static Optional<Symbol> fromToken(String token) {
        token = token.toLowerCase();
        for (Symbol symbol : Symbol.values()) {
            if (symbol.tokens.contains(token)) return Optional.of(symbol);
        }
        return Optional.empty();
    }
}

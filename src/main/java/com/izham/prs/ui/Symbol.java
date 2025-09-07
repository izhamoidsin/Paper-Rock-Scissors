package com.izham.prs.ui;

import com.izham.prs.game.Move;

import java.util.Optional;

public enum Symbol {
    FIST("F", Move.ROCK),
    OPEN_HAND("H", Move.PAPER),
    INDEX_AND_MIDDLE_FINGERS("V", Move.SCISSORS);

    private final String token;
    private final Move move;

    Symbol(String token, Move move) {
        this.token = token;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    public String getUIHint() {
        return String.format("%s - %s", token, name());
    }

    public static Optional<Symbol> fromToken(String token) {
        token = token.toUpperCase();
        for (Symbol symbol : Symbol.values()) {
            if (symbol.token.equals(token)) return Optional.of(symbol);
        }
        return Optional.empty();
    }
}

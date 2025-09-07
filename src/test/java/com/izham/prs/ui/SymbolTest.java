package com.izham.prs.ui;

import com.izham.prs.game.Move;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SymbolTest {
    @ParameterizedTest
    @CsvSource({
            "h,PAPER",
            "H,PAPER",
            "f,ROCK",
            "F,ROCK",
            "V,SCISSORS",
            "v,SCISSORS",
    })
    public void inputDecoding(String input, Move expectedMove) {
        var symbol = Symbol.fromToken(input);
        assertTrue(symbol.isPresent());
        assertEquals(expectedMove, symbol.get().getMove());
    }
}

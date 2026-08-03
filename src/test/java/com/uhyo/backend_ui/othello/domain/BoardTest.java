package com.uhyo.backend_ui.othello.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardTest {

    @Test
    void initialBoardShouldExposeOnlyLegalBlackMovesForTheStandardOpening() {
        Board board = new Board();

        List<Move> moves = board.getValidMoves(1);

        assertEquals(4, moves.size());
        assertTrue(moves.stream().anyMatch(m -> m.getX() == 2 && m.getY() == 3));
        assertTrue(moves.stream().anyMatch(m -> m.getX() == 3 && m.getY() == 2));
        assertTrue(moves.stream().anyMatch(m -> m.getX() == 4 && m.getY() == 5));
        assertTrue(moves.stream().anyMatch(m -> m.getX() == 5 && m.getY() == 4));
    }

    @Test
    void placeStoneShouldFlipOpponentStonesInDirectionOfSandwich() {
        Board board = new Board();

        board.placeStone(2, 3, 1);

        assertEquals(1, board.getCells()[3][2]);
        assertEquals(1, board.getCells()[3][3]);
    }
}

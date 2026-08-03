package com.uhyo.backend_ui.othello.service;

import com.uhyo.backend_ui.othello.domain.Board;
import com.uhyo.backend_ui.othello.domain.GameState;
import com.uhyo.backend_ui.othello.domain.Move;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CpuScoringTest {

    @Test
    void normalCpuShouldPreferMoveThatFlipsMostOpponentStones() {
        GameState state = new GameState("test");
        state.setCpuColor(1);
        state.setPlayerColor(2);

        Board board = state.getBoard();
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                board.getCells()[y][x] = 0;
            }
        }

        board.getCells()[1][2] = 2;
        board.getCells()[1][3] = 2;
        board.getCells()[1][4] = 1;
        board.getCells()[2][2] = 1;
        board.getCells()[2][3] = 1;
        board.getCells()[2][4] = 1;

        List<Move> validMoves = board.getValidMoves(1);
        Move chosen = new NormalCpu().chooseMove(state, validMoves);

        assertEquals(2, chosen.getX());
        assertEquals(0, chosen.getY());
    }

    @Test
    void hardCpuShouldPreferCornerBeforeOtherMoves() {
        GameState state = new GameState("test");
        state.setCpuColor(1);
        state.setPlayerColor(2);

        Board board = state.getBoard();
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                board.getCells()[y][x] = 0;
            }
        }

        board.getCells()[0][1] = 2;
        board.getCells()[0][2] = 1;
        board.getCells()[1][0] = 2;
        board.getCells()[2][0] = 1;
        board.getCells()[3][0] = 1;
        board.getCells()[6][6] = 2;
        board.getCells()[5][6] = 1;

        List<Move> validMoves = board.getValidMoves(1);
        Move chosen = new HardCpu().chooseMove(state, validMoves);

        assertEquals(0, chosen.getX());
        assertEquals(0, chosen.getY());
    }

    @Test
    void hardCpuShouldAvoidDangerousNearCornerMoveWhenSafeEdgeMoveExists() {
        GameState state = new GameState("test-danger");
        state.setCpuColor(1);
        state.setPlayerColor(2);

        Board board = state.getBoard();
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                board.getCells()[y][x] = 0;
            }
        }

        board.getCells()[0][0] = 2;
        board.getCells()[2][0] = 2;
        board.getCells()[3][0] = 1;
        board.getCells()[4][0] = 2;
        board.getCells()[5][0] = 1;

        List<Move> validMoves = board.getValidMoves(1);
        Move chosen = new HardCpu().chooseMove(state, validMoves);

        assertEquals(0, chosen.getX());
        assertEquals(1, chosen.getY());
    }

    @Test
    void hardCpuShouldPreferEdgeMoveOverInteriorMoveWhenFlipCountIsEqual() {
        GameState state = new GameState("test-edge");
        state.setCpuColor(1);
        state.setPlayerColor(2);

        Board board = state.getBoard();
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                board.getCells()[y][x] = 0;
            }
        }

        board.getCells()[2][0] = 2;
        board.getCells()[3][0] = 1;
        board.getCells()[5][0] = 1;
        board.getCells()[2][2] = 2;
        board.getCells()[2][3] = 2;
        board.getCells()[2][4] = 1;

        List<Move> validMoves = board.getValidMoves(1);
        Move chosen = new HardCpu().chooseMove(state, validMoves);

        assertEquals(1, chosen.getX());
        assertEquals(2, chosen.getY());
    }
}


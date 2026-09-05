package com.uhyo.backend_ui.othello.service;

import com.uhyo.backend_ui.othello.domain.Move;
import com.uhyo.backend_ui.othello.domain.GameState;
import com.uhyo.backend_ui.othello.domain.Board;

import java.util.List;

public class NormalCpu implements CpuStrategy {

    @Override
    public Move chooseMove(GameState state, List<Move> validMoves) {

        Board board = state.getBoard();
        int cpuColor = state.getCpuColor();

        Move bestMove = validMoves.get(0);
        int bestScore = -1;

        for (Move m : validMoves) {
            int flipped = board.getFlippedStones(m.getX(), m.getY(), cpuColor).size();
            if (flipped > bestScore) {
                bestScore = flipped;
                bestMove = m;
            }
        }

        return bestMove;
    }
}

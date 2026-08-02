package com.uhyo.backend_ui.othello.cpu;

import com.uhyo.backend_ui.othello.domain.Board;
import com.uhyo.backend_ui.othello.domain.GameState;
import com.uhyo.backend_ui.othello.domain.Move;
import com.uhyo.backend_ui.othello.service.CpuStrategy;

import java.util.List;

public class CpuStrategyUltra implements CpuStrategy {

    @Override
    public Move chooseMove(GameState state, List<Move> validMoves) {

        Board board = state.getBoard();
        int cpuColor = state.getCpuColor();
        if (validMoves.isEmpty()) return null;

        int opponent = (cpuColor == 1 ? 2 : 1);

        Move bestMove = null;
        int bestScore = Integer.MIN_VALUE;

        for (Move move : validMoves) {

            Board temp = board.copy();
            temp.placeStone(move.getX(), move.getY(), cpuColor);

            int score = evaluate(temp, move, cpuColor, opponent);

            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }

        return bestMove;
    }

    /**
     * ULTRA の評価関数
     */
    private int evaluate(Board temp, Move move, int cpuColor, int opponent) {
        int score = 0;

        int x = move.getX();
        int y = move.getY();

        if ((x == 0 && y == 0) ||
            (x == 7 && y == 0) ||
            (x == 0 && y == 7) ||
            (x == 7 && y == 7)) {
            score += 1000;
        }

        if (x == 0 || x == 7 || y == 0 || y == 7) {
            score += 200;
        }

        int flipped = temp.countStones(cpuColor) - temp.countStones(opponent);
        score += flipped;

        int before = temp.getValidMoves(opponent).size();
        int after = temp.getValidMoves(opponent).size();
        score += (before - after) * 5;

        int myStones = temp.countStones(cpuColor);
        int oppStones = temp.countStones(opponent);
        score += (myStones - oppStones) * 2;

        return score;
    }
}

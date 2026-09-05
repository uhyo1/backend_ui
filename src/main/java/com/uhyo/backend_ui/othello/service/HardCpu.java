package com.uhyo.backend_ui.othello.service;

import com.uhyo.backend_ui.othello.domain.Move;
import com.uhyo.backend_ui.othello.domain.GameState;
import com.uhyo.backend_ui.othello.domain.Board;

import java.util.List;

public class HardCpu implements CpuStrategy {

    @Override
    public Move chooseMove(GameState state, List<Move> validMoves) {

        Board board = state.getBoard();
        int cpuColor = state.getCpuColor();

        Move bestMove = validMoves.get(0);
        int bestScore = Integer.MIN_VALUE;

        for (Move m : validMoves) {

            int score = 0;

            // ① 角は最強
            if (isCorner(m)) score += 100;

            // ② 辺は強い
            if (isEdge(m)) score += 20;

            // ③ 反転数（基本評価）
            int flipped = board.getFlippedStones(m.getX(), m.getY(), cpuColor).size();
            score += flipped;

            // ④ 危険手（相手に角を取られやすい位置）は減点
            if (isDangerous(m)) score -= 30;

            if (score > bestScore) {
                bestScore = score;
                bestMove = m;
            }
        }

        return bestMove;
    }

    private boolean isCorner(Move m) {
        int x = m.getX(), y = m.getY();
        return (x == 0 && y == 0) ||
               (x == 0 && y == 7) ||
               (x == 7 && y == 0) ||
               (x == 7 && y == 7);
    }

    private boolean isEdge(Move m) {
        int x = m.getX(), y = m.getY();
        return (x == 0 || x == 7 || y == 0 || y == 7);
    }

    private boolean isDangerous(Move m) {
        int x = m.getX(), y = m.getY();
        return (x == 1 && y == 0) ||
               (x == 0 && y == 1) ||
               (x == 6 && y == 0) ||
               (x == 7 && y == 1) ||
               (x == 1 && y == 7) ||
               (x == 0 && y == 6) ||
               (x == 6 && y == 7) ||
               (x == 7 && y == 6);
    }
}

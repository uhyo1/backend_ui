package com.uhyo.backend_ui.othello.service;

import com.uhyo.backend_ui.othello.domain.Move;
import com.uhyo.backend_ui.othello.domain.GameState;

import java.util.List;
import java.util.Random;

public class EasyCpu implements CpuStrategy {

    private final Random rand = new Random();

    @Override
    public Move chooseMove(GameState state, List<Move> validMoves) {
        // 完全ランダム
        return validMoves.get(rand.nextInt(validMoves.size()));
    }
}

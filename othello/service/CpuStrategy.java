package com.uhyo.backend_ui.othello.service;

import com.uhyo.backend_ui.othello.domain.Move;
import com.uhyo.backend_ui.othello.domain.GameState;

import java.util.List;

public interface CpuStrategy {
    Move chooseMove(GameState state, List<Move> validMoves);
}

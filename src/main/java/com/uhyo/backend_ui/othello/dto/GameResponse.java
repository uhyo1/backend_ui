package com.uhyo.backend_ui.othello.dto;

import com.uhyo.backend_ui.othello.domain.Move;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GameResponse {

    private String gameId;

    private int[][] board;            // 盤面（8x8）
    private List<Move> validMoves;    // 合法手一覧

    private String currentTurn;       // "PLAYER" / "CPU"

    private int playerColor;          // 1=黒, 2=白
    private int cpuColor;             // 1=黒, 2=白
    private String cpuLevel;          // EASY / NORMAL / HARD

    private int blackCount;
    private int whiteCount;

    private boolean isGameOver;

    private String tauntMessage;      // CPU煽りメッセージ
}

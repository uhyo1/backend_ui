package com.uhyo.backend_ui.othello.domain;

import lombok.Data;

import java.util.List;

@Data
public class GameState {

    private String gameId;

    private Board board;

    private List<Move> validMoves;

    private String currentTurn;   // "PLAYER" / "CPU"

    private int playerColor;      // 1=黒, 2=白
    private int cpuColor;         // 1=黒, 2=白
    private String cpuLevel;      // EASY / NORMAL / HARD / ULTRA(予定)

    private int blackCount;
    private int whiteCount;

    private boolean isGameOver;

    private String winner;        // "PLAYER" / "CPU" / "DRAW"

    private String tauntMessage;  // CPU煽りメッセージ

    public GameState(String gameId) {
        this.gameId = gameId;
        this.board = new Board();     // 初期盤面を Board が生成する
        this.isGameOver = false;
        this.winner = null;
        this.tauntMessage = null;
    }
}

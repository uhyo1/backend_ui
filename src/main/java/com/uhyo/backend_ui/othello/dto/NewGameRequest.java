package com.uhyo.backend_ui.othello.dto;

import lombok.Data;

@Data
public class NewGameRequest {
    private String playerColor;   // "BLACK" or "WHITE"
    private String cpuLevel;      // "EASY" / "NORMAL" / "HARD"
}

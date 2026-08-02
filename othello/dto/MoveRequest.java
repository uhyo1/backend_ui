package com.uhyo.backend_ui.othello.dto;

import lombok.Data;

@Data
public class MoveRequest {
    private String gameId;
    private int x;
    private int y;
}

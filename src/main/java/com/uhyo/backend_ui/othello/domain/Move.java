package com.uhyo.backend_ui.othello.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Move {
    private int x;
    private int y;
}

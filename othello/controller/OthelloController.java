package com.uhyo.backend_ui.othello.controller;

import com.uhyo.backend_ui.othello.dto.NewGameRequest;
import com.uhyo.backend_ui.othello.dto.MoveRequest;
import com.uhyo.backend_ui.othello.dto.GameResponse;
import com.uhyo.backend_ui.othello.service.OthelloService;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/othello")
@RequiredArgsConstructor
public class OthelloController {

    private final OthelloService othelloService;

    /**
     * 新規ゲーム開始
     * POST /api/othello/new
     */
    @PostMapping("/new")
    public GameResponse newGame(@RequestBody NewGameRequest request) {
        return othelloService.startNewGame(request);
    }

    /**
     * プレイヤーの手
     * POST /api/othello/move
     */
    @PostMapping("/move")
    public GameResponse playerMove(@RequestBody MoveRequest request) {
        return othelloService.playerMove(request);
    }

    /**
     * CPUの手
     * POST /api/othello/cpu-move
     */
    @PostMapping("/cpu-move")
    public GameResponse cpuMove(@RequestBody MoveRequest request) {
        return othelloService.cpuMove(request.getGameId());
    }

    /**
     * ゲーム状態取得（任意）
     * GET /api/othello/state/{gameId}
     */
    @GetMapping("/state/{gameId}")
    public GameResponse getState(@PathVariable String gameId) {
        return othelloService.getGameState(gameId);
    }
}

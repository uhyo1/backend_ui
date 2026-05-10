package com.uhyo.backend_ui.controller;


import com.uhyo.backend_ui.game.BlackjackGame;
import com.uhyo.backend_ui.game.Card;
import org.springframework.web.bind.annotation.*;
import java.util.*;

// ブラックジャック API を提供するコントローラ
@RestController
@RequestMapping("/blackjack")
public class BlackjackController {

    // 現在のゲーム状態（1ゲーム分）
    private BlackjackGame game;

    // -----------------------------
    // ゲーム開始
    // -----------------------------
    @GetMapping("/start")
    public Map<String, Object> start() {
        game = new BlackjackGame();

        Map<String, Object> result = new HashMap<>();
        result.put("player", game.getPlayerHand());
        result.put("dealer", Arrays.asList(game.getDealerHand().get(0), "HIDDEN"));
        result.put("message", "ゲーム開始！ Hit か Stand を選んでください。");

        return result;
    }

    // -----------------------------
    // プレイヤーがカードを引く
    // -----------------------------
    @GetMapping("/hit")
    public Map<String, Object> hit() {
        game.playerHit();

        Map<String, Object> result = new HashMap<>();
        result.put("player", game.getPlayerHand());
        result.put("dealer", Arrays.asList(game.getDealerHand().get(0), "HIDDEN"));

        int score = game.calculateScore(game.getPlayerHand());
        if (score > 21) {
            result.put("message", "バースト！あなたの負け");
        } else {
            result.put("message", "Hit しました。続けますか？");
        }

        return result;
    }

    // -----------------------------
    // Stand → ディーラーのターン
    // -----------------------------
    @GetMapping("/stand")
    public Map<String, Object> stand() {
        game.dealerTurn();

        Map<String, Object> result = new HashMap<>();
        result.put("player", game.getPlayerHand());
        result.put("dealer", game.getDealerHand());
        result.put("result", game.judge());

        return result;
    }
}

/*
  File: TetrisController.java
  Role: テトリスの開始・終了・ハイスコア取得 API を提供するコントローラ（backend_ui）
  Created: 2026-07-12
  Author: Seiji
*/

package com.uhyo.backend_ui.controller;

import com.uhyo.common.tetris.GameStartDto;
import com.uhyo.common.tetris.ScoreDto;
import com.uhyo.common.tetris.HighScoreDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/tetris")
public class TetrisController {

    private static final String DATA_URL = "http://localhost:9001/tetris";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * ゲーム開始ログ送信
     */
    @PostMapping("/start")
    public String startGame() {
        GameStartDto dto = new GameStartDto("seiji", java.time.LocalDateTime.now().toString());
        return restTemplate.postForObject(DATA_URL + "/start", dto, String.class);
    }

    /**
     * ゲーム終了時のスコア送信
     */
    @PostMapping("/finish")
    public String finishGame(@RequestBody ScoreDto scoreDto) {
        return restTemplate.postForObject(DATA_URL + "/finish", scoreDto, String.class);
    }

    /**
     * ハイスコア一覧取得
     */
    @GetMapping("/highscores")
    public List<HighScoreDto> getHighScores() {
        HighScoreDto[] result =
                restTemplate.getForObject(DATA_URL + "/highscores", HighScoreDto[].class);
        return List.of(result);
    }
}

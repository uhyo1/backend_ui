package com.uhyo.backend_ui.othello.service;

import com.uhyo.backend_ui.othello.domain.GameState;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class TauntService {

    private static final Random rand = new Random();

    public String generate(GameState state) {
        String cpuLevel = state.getCpuLevel();

        switch (cpuLevel) {
            case "EASY":
                return pick(List.of(
                    "あれ？そこ置いちゃう？",
                    "まだまだこれからですよ〜",
                    "優しくいきますね…？"
                ));

            case "NORMAL":
                return pick(List.of(
                    "ふむ、悪くない一手ですね。",
                    "おっと、そこは読んでましたよ。",
                    "いい勝負になってきましたね。"
                ));

            case "HARD":
                return pick(List.of(
                    "その一手、甘いですね。",
                    "読めてますよ、その手筋。",
                    "そろそろ本気出しますか。"
                ));

            case "ULTRA":
                return pick(List.of(
                    "角は渡しませんよ？当然でしょう。",
                    "その一手、完全に想定内です。",
                    "勝てると思ってました？ふふ。",
                    "読みが浅いですね…もっと頑張って。",
                    "その手、私にはノーダメージです。",
                    "終盤は私の領域ですよ？",
                    "あら、そこ置くんですね…かわいい選択。",
                    "勝負はもう決まってますよ。",
                    "その一手で流れが変わるとでも？",
                    "私に勝つにはあと 30 手は読みが必要ですね。"
                ));

            default:
                return "";
        }
    }

    private String pick(List<String> list) {
        return list.get(rand.nextInt(list.size()));
    }
}

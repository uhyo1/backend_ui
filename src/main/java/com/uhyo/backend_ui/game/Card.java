package com.uhyo.backend_ui.game;

// 1枚のカードを表すクラス
public class Card {
    private String suit; // C, D, H, S, JOKER
    private String rank; // A, 2〜10, J, Q, K, JOKER

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() { return suit; }
    public String getRank() { return rank; }

    // Vue 側で画像ファイル名に使う
    public String getFileName() {
        return suit + "_" + rank + ".png";
    }
}

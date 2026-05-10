package com.uhyo.backend_ui.game;

import java.util.*;

// ブラックジャック1ゲーム分の状態を管理するクラス
public class BlackjackGame {

    private List<Card> deck = new ArrayList<>();
    private List<Card> playerHand = new ArrayList<>();
    private List<Card> dealerHand = new ArrayList<>();

    public BlackjackGame() {
        initDeck();
        dealInitialCards();
    }

    // -----------------------------
    // デッキ初期化（52枚＋ジョーカー1枚）
    // -----------------------------
    private void initDeck() {
        deck.clear();

        String[] suits = {"C", "D", "H", "S"};
        String[] ranks = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};

        // 52枚
        for (String s : suits) {
            for (String r : ranks) {
                deck.add(new Card(s, r));
            }
        }

        // ジョーカー1枚
        deck.add(new Card("JOKER", "JOKER"));

        Collections.shuffle(deck);
    }

    // -----------------------------
    // 初期カードを配る
    // -----------------------------
    private void dealInitialCards() {
        playerHand.clear();
        dealerHand.clear();

        playerHand.add(drawCard());
        playerHand.add(drawCard());
        dealerHand.add(drawCard());
        dealerHand.add(drawCard());
    }

    // -----------------------------
    // デッキから1枚引く
    // -----------------------------
    public Card drawCard() {
        return deck.remove(0);
    }

    // -----------------------------
    // プレイヤーがカードを引く
    // -----------------------------
    public void playerHit() {
        playerHand.add(drawCard());
    }

    // -----------------------------
    // ディーラーが17以上になるまで引く
    // -----------------------------
    public void dealerTurn() {
        while (calculateScore(dealerHand) < 17) {
            dealerHand.add(drawCard());
        }
    }

    // -----------------------------
    // 手札の合計点を計算（A=1 or 11）
    // -----------------------------
    public int calculateScore(List<Card> hand) {
        int sum = 0;
        int aceCount = 0;

        for (Card c : hand) {
            String r = c.getRank();

            if (r.equals("JOKER")) continue; // ジョーカーは0点扱い

            int value;
            switch (r) {
                case "A": value = 1; aceCount++; break;
                case "J":
                case "Q":
                case "K": value = 10; break;
                default: value = Integer.parseInt(r);
            }

            sum += value;
        }

        // A を 11 として扱えるなら 10 足す
        while (aceCount > 0 && sum + 10 <= 21) {
            sum += 10;
            aceCount--;
        }

        return sum;
    }

    // -----------------------------
    // 勝敗判定
    // -----------------------------
    public String judge() {
        int player = calculateScore(playerHand);
        int dealer = calculateScore(dealerHand);

        if (player > 21) return "プレイヤーの負け（バースト）";
        if (dealer > 21) return "プレイヤーの勝ち（ディーラーがバースト）";

        if (player > dealer) return "プレイヤーの勝ち";
        if (player < dealer) return "ディーラーの勝ち";
        return "引き分け";
    }

    public List<Card> getPlayerHand() { return playerHand; }
    public List<Card> getDealerHand() { return dealerHand; }
}

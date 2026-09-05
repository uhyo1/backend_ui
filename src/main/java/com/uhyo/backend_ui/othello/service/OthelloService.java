package com.uhyo.backend_ui.othello.service;

import com.uhyo.backend_ui.othello.cpu.CpuStrategyUltra;
import com.uhyo.backend_ui.othello.domain.Board;
import com.uhyo.backend_ui.othello.domain.GameState;
import com.uhyo.backend_ui.othello.domain.Move;
import com.uhyo.backend_ui.othello.dto.NewGameRequest;
import com.uhyo.backend_ui.othello.dto.MoveRequest;
import com.uhyo.backend_ui.othello.dto.GameResponse;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OthelloService {

    private final TauntService tauntService;

    // ゲーム状態を保持する（簡易インメモリ）
    private final Map<String, GameState> gameStore = new ConcurrentHashMap<>();

    /**
     * 新規ゲーム開始
     */
    public GameResponse startNewGame(NewGameRequest req) {

        String gameId = UUID.randomUUID().toString();
        GameState state = new GameState(gameId);

        // プレイヤー色設定
        if ("BLACK".equals(req.getPlayerColor())) {
            state.setPlayerColor(1);
            state.setCpuColor(2);
            state.setCurrentTurn("PLAYER");
        } else {
            state.setPlayerColor(2);
            state.setCpuColor(1);
            state.setCurrentTurn("CPU");
        }

        // CPU強さ設定
        state.setCpuLevel(req.getCpuLevel());

        Board board = state.getBoard();

        // 合法手計算（プレイヤー側）
        state.setValidMoves(board.getValidMoves(state.getPlayerColor()));

        // 石数更新
        state.setBlackCount(board.countStones(1));
        state.setWhiteCount(board.countStones(2));

        gameStore.put(gameId, state);

        return toResponse(state);
    }

    /**
     * プレイヤーの手
     */
    public GameResponse playerMove(MoveRequest req) {

        GameState state = gameStore.get(req.getGameId());
        if (state == null) {
            throw new RuntimeException("Game not found");
        }

        Board board = state.getBoard();

        // 合法手チェック
        if (!board.canPlace(req.getX(), req.getY(), state.getPlayerColor())) {
            throw new RuntimeException("Invalid move");
        }

        // 石を置く
        board.placeStone(req.getX(), req.getY(), state.getPlayerColor());

        // 石数更新
        state.setBlackCount(board.countStones(1));
        state.setWhiteCount(board.countStones(2));

        // ターン切り替え
        state.setCurrentTurn("CPU");

        // 合法手更新（CPU側）
        state.setValidMoves(board.getValidMoves(state.getCpuColor()));

        // ゲーム終了判定
        checkGameOver(state);

        return toResponse(state);
    }

    /**
     * CPUの手
     */
    public GameResponse cpuMove(String gameId) {

        GameState state = gameStore.get(gameId);
        if (state == null) {
            throw new RuntimeException("Game not found");
        }

        Board board = state.getBoard();

        // 合法手取得
        List<Move> moves = board.getValidMoves(state.getCpuColor());

        // CPUが打てない場合 → プレイヤーへ
        if (moves.isEmpty()) {
            state.setCurrentTurn("PLAYER");
            state.setValidMoves(board.getValidMoves(state.getPlayerColor()));
            checkGameOver(state);
            return toResponse(state);
        }

        // CPUロジック呼び出し（Strategy）
        Move cpuMove = chooseCpuMove(state, moves);

        // 石を置く
        board.placeStone(cpuMove.getX(), cpuMove.getY(), state.getCpuColor());

        // 石数更新
        state.setBlackCount(board.countStones(1));
        state.setWhiteCount(board.countStones(2));

        // ターン切り替え
        state.setCurrentTurn("PLAYER");

        // 合法手更新（プレイヤー側）
        state.setValidMoves(board.getValidMoves(state.getPlayerColor()));

        // ★★★ CPU煽りメッセージ生成 ★★★
        state.setTauntMessage(tauntService.generate(state));

        // ゲーム終了判定
        checkGameOver(state);

        return toResponse(state);
    }

    /**
     * ゲーム状態取得
     */
    public GameResponse getGameState(String gameId) {
        GameState state = gameStore.get(gameId);
        if (state == null) {
            throw new RuntimeException("Game not found");
        }
        return toResponse(state);
    }

    /**
     * CPU Strategy 呼び出し
     */
    private Move chooseCpuMove(GameState state, List<Move> moves) {

        CpuStrategy strategy;

        switch (state.getCpuLevel()) {
            case "EASY":
                strategy = new EasyCpu();
                break;
            case "NORMAL":
                strategy = new NormalCpu();
                break;
            case "HARD":
                strategy = new HardCpu();
                break;
            case "ULTRA":
                strategy = new CpuStrategyUltra();
                break;

            default:
                strategy = new NormalCpu();
        }

        return strategy.chooseMove(state, moves);
    }

    /**
     * ゲーム終了判定
     */
    private void checkGameOver(GameState state) {

        Board board = state.getBoard();

        boolean playerHasMoves = !board.getValidMoves(state.getPlayerColor()).isEmpty();
        boolean cpuHasMoves = !board.getValidMoves(state.getCpuColor()).isEmpty();

        if (!playerHasMoves && !cpuHasMoves) {
            state.setGameOver(true);
        }
    }

    /**
     * GameState → GameResponse 変換
     */
    private GameResponse toResponse(GameState state) {
        return new GameResponse(
            state.getGameId(),
            state.getBoard().getCells(),
            state.getValidMoves(),
            state.getCurrentTurn(),
            state.getPlayerColor(),
            state.getCpuColor(),
            state.getCpuLevel(),
            state.getBlackCount(),
            state.getWhiteCount(),
            state.isGameOver(),
            state.getTauntMessage()
        );
    }
}

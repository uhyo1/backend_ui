package com.uhyo.backend_ui.othello.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Board {

    private int[][] cells = new int[8][8];  // 0=空, 1=黒, 2=白

    // 8方向
    private static final int[][] DIRS = {
        {-1,-1}, {-1,0}, {-1,1},
        {0,-1},          {0,1},
        {1,-1},  {1,0},  {1,1}
    };

    public Board() {
        // 初期配置
        cells[3][3] = 2;
        cells[3][4] = 1;
        cells[4][3] = 1;
        cells[4][4] = 2;
    }

    /**
     * 合法手一覧
     */
    public List<Move> getValidMoves(int color) {
        List<Move> moves = new ArrayList<>();

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (canPlace(x, y, color)) {
                    moves.add(new Move(x, y));
                }
            }
        }

        return moves;
    }

    /**
     * 石を置けるか判定
     */
    public boolean canPlace(int x, int y, int color) {
        if (cells[y][x] != 0) return false;

        int opponent = (color == 1 ? 2 : 1);

        for (int[] d : DIRS) {
            int nx = x + d[0];
            int ny = y + d[1];

            boolean hasOpponent = false;

            while (nx >= 0 && nx < 8 && ny >= 0 && ny < 8) {
                if (cells[ny][nx] == opponent) {
                    hasOpponent = true;
                } else if (cells[ny][nx] == color) {
                    if (hasOpponent) return true;
                    break;
                } else {
                    break;
                }
                nx += d[0];
                ny += d[1];
            }
        }

        return false;
    }

    /**
     * 指定座標に置いたときにひっくり返る石一覧を返す
     */
    public List<int[]> getFlippedStones(int x, int y, int color) {
        if (cells[y][x] != 0) {
            return List.of();
        }

        int opponent = (color == 1 ? 2 : 1);
        List<int[]> flips = new ArrayList<>();

        for (int[] d : DIRS) {
            int nx = x + d[0];
            int ny = y + d[1];
            List<int[]> lineFlips = new ArrayList<>();

            while (nx >= 0 && nx < 8 && ny >= 0 && ny < 8) {
                if (cells[ny][nx] == opponent) {
                    lineFlips.add(new int[]{nx, ny});
                } else if (cells[ny][nx] == color) {
                    if (!lineFlips.isEmpty()) {
                        flips.addAll(lineFlips);
                    }
                    break;
                } else {
                    break;
                }
                nx += d[0];
                ny += d[1];
            }
        }

        return flips;
    }

    /**
     * 石を置く（ひっくり返し含む）
     */
    public void placeStone(int x, int y, int color) {
        cells[y][x] = color;
        List<int[]> flips = getFlippedStones(x, y, color);
        for (int[] f : flips) {
            cells[f[1]][f[0]] = color;
        }
    }

    /**
     * 石数カウント
     */
    public int countStones(int color) {
        int count = 0;
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (cells[y][x] == color) count++;
            }
        }
        return count;
    }
    public Board copy() {
    Board b = new Board();
    for (int y = 0; y < 8; y++) {
        for (int x = 0; x < 8; x++) {
            b.getCells()[y][x] = this.cells[y][x];
        }
    }
    return b;
}
}

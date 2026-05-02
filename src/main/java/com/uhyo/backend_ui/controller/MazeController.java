package com.uhyo.backend_ui.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class MazeController {

    private final Random rand = new Random();

    @GetMapping("/maze/generate")
    public char[][] generateMaze() {
        int h = 33; // 奇数にする
        int w = 33; // 奇数にする
        char[][] maze = new char[h][w];

        // 1. 全部壁で埋める
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                maze[y][x] = '#';
            }
        }

        // 2. 掘り始める（1,1 から）
        dig(maze, 1, 1);

        // 3. スタートとゴールを設定
        maze[1][1] = 'S';
        maze[h - 2][w - 2] = 'G';

        return maze;
    }

    // 深さ優先探索で掘る
    private void dig(char[][] maze, int y, int x) {
        maze[y][x] = '.';

        int[][] dirs = {
                {0, 2}, {0, -2}, {2, 0}, {-2, 0}
        };

        // ランダムに方向をシャッフル
        for (int i = 0; i < dirs.length; i++) {
            int r = rand.nextInt(dirs.length);
            int[] tmp = dirs[i];
            dirs[i] = dirs[r];
            dirs[r] = tmp;
        }

        for (int[] d : dirs) {
            int ny = y + d[0];
            int nx = x + d[1];

            if (ny <= 0 || ny >= maze.length - 1 || nx <= 0 || nx >= maze[0].length - 1)
                continue;

            if (maze[ny][nx] == '#') {
                // 間の壁を壊す
                maze[y + d[0] / 2][x + d[1] / 2] = '.';
                dig(maze, ny, nx);
            }
        }
    }
}

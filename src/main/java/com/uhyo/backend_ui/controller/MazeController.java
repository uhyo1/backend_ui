package com.uhyo.backend_ui.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class MazeController {

    private final Random rand = new Random();

    @GetMapping("/maze/generate")
    public char[][] generateMaze() {
        // 迷路の高さと幅を奇数にして、壁と通路の生成を安定させます。
        int h = 33;
        int w = 33;
        char[][] maze = new char[h][w];

        // 1. 迷路全体を壁で埋めて、後から通路を掘っていく土台を作ります。
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                maze[y][x] = '#';
            }
        }

        // 2. 迷路の中心から深さ優先探索で通路を掘り進めます。
        dig(maze, 1, 1);

        // 3. 生成された通路の中から、スタートとゴールをランダムに選びます。
        List<int[]> openCells = new ArrayList<>();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                // 通路として掘られたセルだけを候補に入れます。
                if (maze[y][x] == '.') {
                    openCells.add(new int[]{y, x});
                }
            }
        }

        // 通路が十分にある場合だけ、2つの異なる位置を選びます。
        if (openCells.size() >= 2) {
            // スタート位置としてランダムに 1 つ選びます。
            int startIndex = rand.nextInt(openCells.size());

            // ゴール位置はスタートとは別の位置をランダムに選びます。
            int goalIndex = rand.nextInt(openCells.size() - 1);
            if (goalIndex >= startIndex) {
                goalIndex++;
            }

            int[] start = openCells.get(startIndex);
            int[] goal = openCells.get(goalIndex);
            maze[start[0]][start[1]] = 'S';
            maze[goal[0]][goal[1]] = 'G';
        } else {
            // 例外的に通路が少ない場合は、固定の位置に置いておきます。
            maze[1][1] = 'S';
            maze[h - 2][w - 2] = 'G';
        }

        return maze;
    }

    // 深さ優先探索で迷路を掘る処理です。再帰的に通路を広げていきます。
    private void dig(char[][] maze, int y, int x) {
        // 現在のセルを通路として開けます。
        maze[y][x] = '.';

        // 4方向に進む候補を用意し、毎回ランダムな順番に試します。
        int[][] dirs = {
                {0, 2}, {0, -2}, {2, 0}, {-2, 0}
        };

        // 方向の順番をランダムに並び替えて、迷路の見た目を変えます。
        for (int i = 0; i < dirs.length; i++) {
            int r = rand.nextInt(dirs.length);
            int[] tmp = dirs[i];
            dirs[i] = dirs[r];
            dirs[r] = tmp;
        }

        // 並び替えた方向を順番に確認し、進める場所があれば掘っていきます。
        for (int[] d : dirs) {
            int ny = y + d[0];
            int nx = x + d[1];

            // 迷路の外枠に出る場合はスキップします。
            if (ny <= 0 || ny >= maze.length - 1 || nx <= 0 || nx >= maze[0].length - 1)
                continue;

            // 未開拓の壁にたどり着いた場合、間の壁を壊して通路をつなげます。
            if (maze[ny][nx] == '#') {
                maze[y + d[0] / 2][x + d[1] / 2] = '.';
                dig(maze, ny, nx);
            }
        }
    }
}

package com.uhyo.backend_ui.controller;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MazeControllerTest {

    @Test
    void generateMazeShouldRandomizeStartAndGoalPositionsAcrossCalls() {
        // MazeController のインスタンスを作成して、迷路生成処理をテスト対象にします。
        MazeController controller = new MazeController();

        // 生成されたスタート位置・ゴール位置の組み合わせを重複なしで記録します。
        Set<String> positions = new HashSet<>();

        // 20回迷路を生成し、それぞれの S と G の座標を記録します。
        for (int i = 0; i < 20; i++) {
            char[][] maze = controller.generateMaze();
            positions.add(findPosition(maze, 'S') + ":" + findPosition(maze, 'G'));
        }

        // 生成ごとに位置が変わることを確認するため、異なる位置の組み合わせが 4 つ以上あることを期待します。
        assertTrue(positions.size() > 3,
                "Expected multiple random start/goal positions across generations, but got: " + positions);
    }

    // 指定した文字が迷路配列のどこにあるかを座標文字列で返します。
    private String findPosition(char[][] maze, char target) {
        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[y].length; x++) {
                if (maze[y][x] == target) {
                    return y + "," + x;
                }
            }
        }
        throw new IllegalStateException("Target not found: " + target);
    }
}

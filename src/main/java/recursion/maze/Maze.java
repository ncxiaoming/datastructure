package recursion.maze;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author: liming
 * @Date: 2020/2/19 15:26
 * @Description: 迷宫问题
 */

public class Maze {

    private static int max = 8;

    public static void main(String[] args) {
        int[][] maze = formatArray();

        setWay(maze, 1, 1);


    }

    public static int[][] formatArray() {
        int[][] arr = new int[max][max];

        for (int i = 0; i < max; i++) {
            arr[0][i] = 1;
            arr[max - 1][i] = 1;
            arr[i][0] = 1;
            arr[i][max -1] = 1;
        }

        arr[3][1] = 1;
        arr[3][2] = 1;

//        arr[1][3] = 1;
//        arr[2][3] = 1;
        return arr;
    }

    /**
     * 0 表示未走过的路，1 表示墙，2 表示走过的路，3 表示死路
     * 规则 下 -> 右 -> 上 -> 左
     * @param maze 迷宫地图
     * @param x 起始位置 x 坐标
     * @param y 起始位置 y 坐标
     * @return
     */
    public static boolean setWay(int[][] maze, int x, int y) {
        printArray(maze);
        // 终点
        if (maze[max - 2][1] == 2) {
            return true;
        } else {
            if (maze[x][y] == 0) {
                maze[x][y] = 2;
                if (setWay(maze, x + 1, y)) {
                    return true;
                } else if (setWay(maze, x, y + 1)) {
                    return true;
                } else if (setWay(maze, x - 1, y)) {
                    return true;
                } else if (setWay(maze, x, y - 1)) {
                    return true;
                } else {
                    maze[x][y] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    public static void printArray(int[][] maze) {
        File file;
        OutputStream outputStream = null;
        try {
            file = new File("D://maze.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            outputStream = new FileOutputStream(file, true);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[i].length; j++) {
                    sb.append(maze[i][j] + " ");
                }
                sb.append("\r\n");
            }
            sb.append("-------------------------------------------------------------------------");
            sb.append("\r\n");
            sb.append("-------------------------------------------------------------------------");
            sb.append("\r\n");
            outputStream.write(sb.toString().getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

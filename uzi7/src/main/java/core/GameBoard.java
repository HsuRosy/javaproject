package core;

/**
 * @author Hsu琛君珩
 * @ClassName:GameBoard
 * @date 2023-12-14
 * @apiNote
 * @Version: v1.0
 */

/**
 * GameBoard 类表示五子棋的棋盘。
 */
public class GameBoard {
    public static final int ROWS = 15; // 棋盘行数
    public static final int COLS = 15; // 棋盘列数
    private int[][] board; // 棋盘数组，存储每个位置的棋子状态
    private static final int WIN_COUNT = 5; // 获胜所需的连续棋子数

    /**
     * 构造函数初始化棋盘。
     */
    public GameBoard() {
        board = new int[ROWS][COLS]; // 初始化棋盘，所有位置为空
    }

    /**
     * 在棋盘上放置棋子。
     *
     * @param row    落子的行
     * @param col    落子的列
     * @param player 当前玩家
     * @return 如果落子成功返回true，如果位置已被占用返回false
     */
    public boolean placeStone(int row, int col, int player) {
        if (board[row][col] == 0) { // 检查位置是否已被占用
            board[row][col] = player; // 放置棋子
            return true;
        }
        return false; // 位置已被占用，落子失败
    }

    /**
     * 检查是否有玩家获胜。
     *
     * @return 获胜玩家的编号，如果没有玩家获胜则返回0
     */
    public int checkWin() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int player = board[row][col];
                if (player != 0) {
                    if (checkLine(player, row, col)) {
                        return player;
                    }
                }
            }
        }
        return 0; // 没有玩家获胜
    }

    /**
     * 检查从指定位置开始的线条上是否有连续的获胜棋子。
     *
     * @param player 当前检查的玩家
     * @param row    起始行
     * @param col    起始列
     * @return 如果找到获胜的线条，则返回true
     */
    private boolean checkLine(int player, int row, int col) {
        return checkHorizontal(player, row, col) || checkVertical(player, row, col) ||
                checkDiagonalFromTopLeft(player, row, col) || checkDiagonalFromTopRight(player, row, col);
    }

    //检查水平方向上是否有连续的获胜棋子
    private boolean checkHorizontal(int player, int row, int col) {
        int count = 0;
        for (int i = col; i < COLS && board[row][i] == player; i++) {
            count++;
            if (count == WIN_COUNT) return true;
        }
        return false;
    }

    //检查垂直方向上是否有连续的获胜棋子
    private boolean checkVertical(int player, int row, int col) {
        int count = 0;
        for (int i = row; i < ROWS && board[i][col] == player; i++) {
            count++;
            if (count == WIN_COUNT) return true;
        }
        return false;
    }

    //检查从左上到右下的对角线上是否有连续的获胜棋子
    private boolean checkDiagonalFromTopLeft(int player, int row, int col) {
        int count = 0;
        for (int i = 0; i < WIN_COUNT && row + i < ROWS && col + i < COLS; i++) {
            if (board[row + i][col + i] == player) {
                count++;
                if (count == WIN_COUNT) return true;
            }
        }
        return false;
    }

    //检查从右上到左下的对角线上是否有连续的获胜棋子
    private boolean checkDiagonalFromTopRight(int player, int row, int col) {
        int count = 0;
        for (int i = 0; i < WIN_COUNT && row + i < ROWS && col - i >= 0; i++) {
            if (board[row + i][col - i] == player) {
                count++;
                if (count == WIN_COUNT) return true;
            }
        }
        return false;
    }
}

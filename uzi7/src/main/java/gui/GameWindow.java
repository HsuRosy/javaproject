package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Hsu琛君珩
 * @ClassName:GameWindow
 * @date 2023-12-14
 * @apiNote
 * @Version: v1.0
 */

/**
 * GameWindow 类负责创建和管理游戏的主窗口。
 */
public class GameWindow extends JFrame {

    /**
     * 构造函数，用于初始化游戏窗口。
     */
    public GameWindow() {
        setTitle("五子棋游戏 - uzi7");
        setSize(800, 600); // 设置窗口大小
        setLocationRelativeTo(null); // 窗口居中
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // 设置布局管理器

        // 初始化并添加棋盘面板
        BoardPanel boardPanel = new BoardPanel();
        add(boardPanel, BorderLayout.CENTER);

        // 初始化并添加控制面板
        ControlPanel controlPanel = new ControlPanel();
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        // 在事件调度线程中创建和显示游戏窗口
        SwingUtilities.invokeLater(() -> new GameWindow());
        System.out.println();
    }

    /**
     * BoardPanel 类负责绘制棋盘。
     */
    private class BoardPanel extends JPanel {
        // 这里可以添加棋盘的绘制逻辑
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // 绘制棋盘背景和棋子等
        }
    }

    /**
     * ControlPanel 类提供游戏控制按钮。
     */
    private class ControlPanel extends JPanel {
        public ControlPanel() {
            // 添加开始游戏按钮
            JButton startButton = new JButton("开始游戏");
            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 开始游戏的逻辑
                }
            });
            add(startButton);

            // 添加悔棋按钮
            JButton undoButton = new JButton("悔棋");
            undoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 悔棋的逻辑
                }
            });
            add(undoButton);
        }
    }
}

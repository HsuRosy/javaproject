package view;

import connect.MySocket;
import data.GameCenter;
import game.AutoChess;
import game.GameCoupe;
import game.GameOnline;
import game.GameRobot;

import javax.swing.*;
import java.awt.*;

/**
 * 主界面右边的控制按钮面板
 */
public class ControlPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    public static ControlPanel my;
    private JButton btnAgain, btnChessAI, btnCoupe, btnOnline, btnRobot;

    public ControlPanel() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setBackground(new Color(220, 220, 220, 220));
        btnAgain = new JButton("重新游戏");
        btnCoupe = new JButton("双人对战");
        btnRobot = new JButton("人机对战");
        btnOnline = new JButton("联机对战");
        btnChessAI = new JButton("智能AI下棋");

        this.add(btnAgain);
        this.add(btnCoupe);
        this.add(btnRobot);
        this.add(btnOnline);
        this.add(btnChessAI);

        my = this;
        addListener();
    }

    public static void init() {
        my.repaint();
    }

    /**
     * 添加监听事件
     */
    private void addListener() {

        btnAgain.addActionListener(event -> {
            GameCenter.reStart();
            ChessBroad.myBroad.repaint();
            MySocket.close();
            btnCoupe.setEnabled(true);
            btnRobot.setEnabled(true);
            btnOnline.setEnabled(true);
            btnChessAI.setEnabled(true);
            try {
                MySocket.socket.close();
            } catch (Exception e) {
            }
        });

        btnChessAI.addActionListener(event -> {
            if (GameCenter.isEnd()) {
                JOptionPane.showMessageDialog(null, "游戏未开始，无法使用AI下棋", "提示信息", JOptionPane.CANCEL_OPTION);
            } else {
                new AutoChess();
            }
        });
        btnCoupe.addActionListener(event -> {
            GameCoupe.reStart();
            btnCoupe.setEnabled(false);
            btnRobot.setEnabled(false);
            btnOnline.setEnabled(false);
        });

        btnRobot.addActionListener(event -> {
            GameRobot.reStart();
            btnCoupe.setEnabled(false);
            btnRobot.setEnabled(false);
            btnOnline.setEnabled(false);
        });

        btnOnline.addActionListener(event -> {
            // new 一定要在前面，否则数据被重置！
            GameOnline.reStart();
            MyDialog.online();

            btnCoupe.setEnabled(false);
            btnRobot.setEnabled(false);
            btnOnline.setEnabled(false);
            btnChessAI.setEnabled(false);
        });
    }
}

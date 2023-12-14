package com.yyc.gobang.game;

import com.yyc.gobang.connect.MySocket;
import com.yyc.gobang.data.GameCenter;
import com.yyc.gobang.data.Player;
import com.yyc.gobang.view.MyDialog;
import com.yyc.gobang.view.UserPanel;

/**
 * 联机对战类
 *
 */
public class GameOnline {

    private GameOnline() {
    }

    public static void reStart() {
        GameCenter.reStart();
        GameCenter.setMode(GameCenter.MODE_ONLINE);
    }

    /**
     * 启动监控线程(监控对方是否下线)
     */
    public static void monitor() {
        // 用于检测游戏是否结束
        new Thread(() -> {
            while (MySocket.isStart) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
            MyDialog.monitor();
            System.out.println("在线监控线程 已终止！");
        }).start();
    }

    /**
     * 相互发送接收玩家信息
     */
    public static void getOtherAddress() {
        // 用于检测游戏是否结束
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                if (MySocket.isStart) {
                    Player.otherPlayer.setAddress(MySocket.peAddress);
                    UserPanel.setUserInfo(Player.otherPlayer, UserPanel.right);
                }
            } catch (InterruptedException e) {
            }

        }).start();
    }

}

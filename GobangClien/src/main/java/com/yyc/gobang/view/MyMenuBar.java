package com.yyc.gobang.view;

import com.yyc.gobang.connect.MyIPTool;
import com.yyc.gobang.data.Algorithm;
import com.yyc.gobang.game.CountDown;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 界面显示 之 菜单栏
 */
public class MyMenuBar extends JMenuBar {
    private static final long serialVersionUID = 1L;
    JMenuItem checkIP, exit, setTime, setLevel, word;

    public MyMenuBar() {
        UIManager.put("Menu.font", new Font("宋体", Font.BOLD, 22));
        UIManager.put("MenuItem.font", new Font("宋体", Font.BOLD, 22));

        JMenu menu = new JMenu("菜单(F)");
        menu.setMnemonic('f'); // 助记符

        JMenu setting = new JMenu("设置(S)");
        setting.setMnemonic('s'); // 助记符

        JMenu help = new JMenu("帮助(H)");
        help.setMnemonic('h'); // 助记符

        checkIP = new JMenuItem("查看本机IP");
        exit = new JMenuItem("退出");
        setLevel = new JMenuItem("难度设置");
//        setTime = new JMenuItem("倒计时设置");
        word = new JMenuItem("游戏说明");

        menu.add(checkIP);
        menu.add(exit);
        setting.add(setLevel);
//        setting.add(setTime);
        help.add(word);

        this.add(menu);
        this.add(setting);
        this.add(help);
        addListener();
    }

    public void addListener() {
        exit.addActionListener(event -> MainFrame.close());
        checkIP.addActionListener(event -> {
            String localIP = "本机所有IP地址:";
            List<String> res = MyIPTool.getAllLocalHostIP();
            String allIp = res.stream().collect(Collectors.joining("\n"));
            localIP = localIP + "\n" + allIp;

            JOptionPane.showMessageDialog(MainFrame.mainFrame, localIP, "查看本机IP", JOptionPane.INFORMATION_MESSAGE);
        });
//        setTime.addActionListener(event -> {
//            String input = JOptionPane.showInputDialog("请输入超时时间(秒)", "30");
//            try {
//                int time = Integer.valueOf(input);
//                CountDown.startTiming(time);
//            } catch (Exception e) {
//            }
//        });
        setLevel.addActionListener(event -> {
            Object[] options = {"初级", "中级", "高级"};
            int m = JOptionPane
                    .showOptionDialog(MainFrame.mainFrame, "请选择AI智能程度", "请选择",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, options,
                            options[0]);
            if (m == 0) {
                Algorithm.LEVEL = Algorithm.LEVEL_Low;
                return;
            }
            if (m == 1) {
                JOptionPane.showMessageDialog(MainFrame.mainFrame, "算法设计,AI智能：YYC", "重要提示..",
                        JOptionPane.INFORMATION_MESSAGE);
                Algorithm.LEVEL = Algorithm.LEVEL_Middle;
                return;
            }
            if (m == 2) {
                JOptionPane.showMessageDialog(MainFrame.mainFrame, "算法设计,AI智能：YYC", "重要提示..",
                        JOptionPane.INFORMATION_MESSAGE);
                Algorithm.LEVEL = Algorithm.LEVEL_High;
                return;
            }
        });

        word.addActionListener(event -> JOptionPane
                .showMessageDialog(
                        MainFrame.mainFrame,
                        "游戏名称：在线五子棋\n主要功能：双人游戏,人机对战,联机对战",
                        "游戏说明", JOptionPane.INFORMATION_MESSAGE));
    }
}

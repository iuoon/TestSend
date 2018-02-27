package com.iuoon.yumni;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        // 1. 创建一个顶层容器（窗口）
        JFrame jf = new JFrame("测试窗口");          // 创建窗口
        jf.setSize(480, 320);                       // 设置窗口大小
        jf.setLocationRelativeTo(null);             // 把窗口位置设置到屏幕中心
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 当点击窗口的关闭按钮时退出程序（没有这一句，程序不会退出）
        // 2. 创建中间容器（面板容器）
        JPanel panel = new JPanel();                // 创建面板容器，使用默认的布局管理器

        final JTextArea textArea2 = new JTextArea(6, 30);
        // 设置自动换行
        textArea2.setLineWrap(true);

        Client socketClient=new Client(textArea2);
        socketClient.create();

        // 创建一个 6 行 30 列的文本区域
        final JTextArea textArea = new JTextArea(6, 30);
        // 设置自动换行
        textArea.setLineWrap(true);
        panel.add(textArea);
        // 创建一个提交按钮，点击按钮获取输入文本
        JButton btn = new JButton("发送");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String header="YN";
                String msg=textArea.getText();
                int len=msg.length();
                if (len<0){
                    return;
                }
                if (len<10){
                    header+="000"+String.valueOf(len);
                }
                if (len<100){
                    header+="00"+String.valueOf(len);
                }
                if (len<1000){
                    header+="0"+String.valueOf(len);
                }
                if (len<10000){
                    header+=String.valueOf(len);
                }
                socketClient.sendMsg(header+textArea.getText());
            }
        });
        panel.add(btn);



        JLabel label=new JLabel("信息");
        panel.add(label);

        panel.add(textArea2);


        // 4. 把 面板容器 作为窗口的内容面板 设置到 窗口
        jf.setContentPane(panel);

        // 5. 显示窗口，前面创建的信息都在内存中，通过 jf.setVisible(true) 把内存中的窗口显示在屏幕上。
        jf.setVisible(true);
    }
}

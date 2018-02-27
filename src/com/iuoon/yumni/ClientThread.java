package com.iuoon.yumni;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
/**
 *   作用：一直接收服务端转发过来的信息
 * */
public class ClientThread extends Thread {

    private Socket socket;
    private JTextArea textArea;

    public ClientThread(Socket socket,JTextArea textArea) {
        this.socket = socket;
        this.textArea=textArea;
    }

    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(
                    inputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);
            try {
                while (true) {
                    String msg=br.readLine();
                    System.out.println(msg);
                    textArea.append("收到消息\n");
                    textArea.append(msg);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}

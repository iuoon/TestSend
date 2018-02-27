package com.iuoon.yumni;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 *   client线程主要是负责：
 *   1.发送信息
 *   2.一直接收信息，并解析
 * */
public class Client {

    private Socket socketClient;

    private JTextArea textArea;

    public Client(JTextArea textArea){
        this.textArea=textArea;
    }

    public boolean create() {
        try {
            socketClient = new Socket("localhost", 9999);
            //开启一个线程接收信息，并解析
            ClientThread thread=new ClientThread(socketClient,textArea);
            thread.start();
            return true;
        }catch(Exception e){
            System.out.println("服务器异常");
            return false;
        }
    }

    public void sendMsg(String s){
        try {
        PrintWriter out=new PrintWriter(socketClient.getOutputStream());
        out.println(s);
        out.flush();
        }catch(Exception e){
            System.out.println("服务器异常");
        }
    }
}

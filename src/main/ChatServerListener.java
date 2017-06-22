package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 廷江 on 2017/6/21.
 */
public class ChatServerListener extends Thread{
    public static int port = 12345;

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("接收到连接");
                ChatSocket chatSocket = new ChatSocket(socket);
                chatSocket.start();
//                chatSocket.setIndex(ChatManager.getManager().size());
                //添加到管理器
                ChatManager.getManager().addSocket(chatSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

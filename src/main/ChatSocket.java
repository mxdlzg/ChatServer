package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by 廷江 on 2017/6/21.
 */
public class ChatSocket extends Thread {
    private Socket socket;
    private int index;

    public ChatSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * 向客户端发送内容
     * @param content 内容
     */
    public void out(String content){
        try {
            if (socket.isConnected()){
                socket.getOutputStream().write((content+"\n").getBytes(Config.CHAR_SET));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
//        out("连接成功");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),Config.CHAR_SET));
            String line;
            while ((line = reader.readLine()) != null){
                if (line.equals(Config.CLOSE_SOCKET)){
                    ChatManager.getManager().removeSocket(this);
                    socket.close();
                    reader.close();
                    System.out.println("关闭连接");
                    return;
                }
                if (line.split(Config.SEPARATOR)[0].equals(Config.NET_LOGIN)){
                    out("login&_&100&_&10000");
                }
                ChatManager.getManager().publishToAll(this,line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

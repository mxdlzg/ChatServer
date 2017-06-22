package main;

import java.util.Vector;

/**
 * Created by 廷江 on 2017/6/21.
 */
public class ChatManager {
    private Vector<ChatSocket> vector = new Vector<>();
    private ChatManager(){}
    private static final ChatManager manager = new ChatManager();
    public static ChatManager getManager(){
        return manager;
    }

    public void addSocket(ChatSocket chatSocket){
        vector.add(chatSocket);
    }

    public void removeSocket(int index){
        vector.remove(index);
    }

    public void removeSocket(ChatSocket chatSocket){
        vector.remove(chatSocket);
    }

    public int size(){
        return vector.size();
    }

    public void publishToAll(ChatSocket chatSocket,String content){
        for (int i= 0;i<vector.size();i++){
            ChatSocket socket = vector.get(i);
            if (!chatSocket.equals(socket)){
                socket.out("用户数量"+vector.size()+content);
            }
        }
    }
}

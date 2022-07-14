package com.oracle.oBootS20220603.dao.hk;

import com.oracle.oBootS20220603.model.ChatRoom;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ChatRoomRepository {
    private Map<String, ChatRoom> chatRoomMap;
 
    @PostConstruct
    private void init(){
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom(){
        List chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    public ChatRoom findRoomById(String roomId){
        return chatRoomMap.get(roomId);
    }
    
    public ChatRoom createChatRoom(String name){
    	System.out.println("createChatRoom Start...");
        ChatRoom chatRoom = ChatRoom.create(name);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }
    
    public void deleteChatRoom(String roomId) {
    	chatRoomMap.remove(roomId);
    }
    
}

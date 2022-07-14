package com.oracle.oBootS20220603.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.oBootS20220603.dao.hk.ChatRoomRepository;
import com.oracle.oBootS20220603.handler.hk.WebSocketHandler;

import lombok.Getter;
import lombok.Setter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;

@Getter
@Setter
public class ChatRoom {
    private String roomId;
    private String name;
    private int cnt = 0;
    private Set<WebSocketSession> sessions = new HashSet<>();

    public static ChatRoom create(String name){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.name = name;
        System.out.println("roomId->"+chatRoom.roomId);
        System.out.println("roomName->"+chatRoom.name);
        return chatRoom;
    }

    public void handleMessage(WebSocketSession session, ChatMessage chatMessage,
                              ObjectMapper objectMapper) throws IOException {
        if(chatMessage.getType() == MessageType.ENTER){
            sessions.add(session);
            cnt++;
            chatMessage.setMessage(chatMessage.getWriter() + "님이 입장하셨습니다.");
        }
        else if(chatMessage.getType() == MessageType.LEAVE){
            sessions.remove(session);
            cnt--;
            chatMessage.setMessage(chatMessage.getWriter() + "님이 퇴장하셨습니다.");
        }
        else{
            chatMessage.setMessage(chatMessage.getMessage());
        }
        send(chatMessage,objectMapper);
    }

    private void send(ChatMessage chatMessage, ObjectMapper objectMapper) throws IOException {
//    	String str_result = objectMapper.writeValueAsString(chatMessage.getMessage()).substring(1,chatMessage.getMessage().length()+1);
    	
//    	System.out.println("chatMessage->"+str_result);
    	
    	String chatMsg = objectMapper.writeValueAsString(chatMessage);
    	TextMessage textMessage = new TextMessage(chatMsg);
    	
//        TextMessage textMessage = new TextMessage(objectMapper.
//                                    writeValueAsString(chatMessage.getMessage()));
        for(WebSocketSession sess : sessions){
            sess.sendMessage(textMessage);
        }
    }
    
    
}
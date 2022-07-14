package com.oracle.oBootS20220603.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ChatMessage {
    private String chatRoomId;	// mem_num
    private String writer;		// grade
    private String message;		// msg
    private MessageType type;	// 
    private String sessionId;
}

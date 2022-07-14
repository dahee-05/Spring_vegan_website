package com.oracle.oBootS20220603.controller.hk;

import com.oracle.oBootS20220603.model.ChatRoom;
import com.oracle.oBootS20220603.model.ChatRoomForm;
import com.oracle.oBootS20220603.model.Member;
import com.oracle.oBootS20220603.service.hk.HKMemberService;
import com.oracle.oBootS20220603.dao.hk.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {
	
    private final ChatRoomRepository chatRoomRepository;
    
    @Autowired
    private HKMemberService ms;
    
    // 관리자 페이지 리스트 목록
    @GetMapping("/chatrooms")
    public String rooms(Model model){
        model.addAttribute("rooms",chatRoomRepository.findAllRoom());
        model.addAttribute("menu_num", 10);
        return "chatRoomsA";
    }
    
    @GetMapping("/rooms/{roomId}")
    public String room(@PathVariable String roomId, Model model){
        ChatRoom room = chatRoomRepository.findRoomById(roomId);
        model.addAttribute("room",room);
        System.out.println("roomId->"+room.getRoomId());
        System.out.println("roomName->"+room.getName());
        
        return "room";
    }
    
    @GetMapping("/rooms_a/{roomId}")
    public String room_a(@PathVariable String roomId, Model model, HttpServletRequest request){
        ChatRoom room = chatRoomRepository.findRoomById(roomId);
        model.addAttribute("room",room);
        System.out.println("roomId->"+room.getRoomId());
        System.out.println("roomName->"+room.getName());
        String admin_id = (String) request.getSession().getAttribute("admin_id");
        
        model.addAttribute("id", admin_id);
        
        return "room";
    }

    
    // 사용자가 방 생성
    @PostMapping("/room/new")
    public String makeRoom(String id){
    	System.out.println("방 생성 Start...");
        ChatRoom chatroom =	chatRoomRepository.createChatRoom(id);
        return "redirect:/rooms/"+chatroom.getRoomId();
    }
    
    @GetMapping("/chatrooms/room")
    public String room_a(String roomId, Model model) {
    	System.out.println("room_a Start...");
    	
    	model.addAttribute("roomId", roomId);
    	model.addAttribute("menu_num", 10);
    	
    	return "room_a";
    }
    
}

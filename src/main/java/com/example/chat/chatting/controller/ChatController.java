package com.example.chat.chatting.controller;

import com.example.chat.chatting.ChatService;
import com.example.chat.chatting.controller.dto.CreateChatRoomRequestDto;
import com.example.chat.chatting.controller.dto.GetChatRoomResponseDto;
import com.example.chat.chatting.domain.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/chatroom/create")
    public ResponseEntity<ChatRoom> createChatRoom(@RequestBody CreateChatRoomRequestDto request){
        ChatRoom chatRoom = chatService.createChatRoom(UUID.fromString(request.getOriginMemberUUID()), UUID.fromString(request.getDestinationMemberUUID()));
        return ResponseEntity.ok(chatRoom);
    }

    @GetMapping("/chatroom/{chatRoomUUID}")
    public ResponseEntity<GetChatRoomResponseDto> getChatRoomByUUID(@PathVariable("chatRoomUUID") String chatRoomUUID){
        ChatRoom chatRoom = chatService.getChatRoomByUUID(UUID.fromString(chatRoomUUID));
        GetChatRoomResponseDto response = GetChatRoomResponseDto.toDto(chatRoom);
        return ResponseEntity.ok(response);
    }
}

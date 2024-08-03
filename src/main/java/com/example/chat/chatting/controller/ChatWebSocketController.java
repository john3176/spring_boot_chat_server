package com.example.chat.chatting.controller;

import com.example.chat.chatting.ChatService;
import com.example.chat.chatting.controller.dto.ChatDto;
import com.example.chat.chatting.controller.dto.ChatRoomEnterDto;
import com.example.chat.chatting.domain.ChatMessage;
import com.example.chat.chatting.domain.ChatRoom;
import com.example.chat.member.MemberService;
import com.example.chat.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ChatWebSocketController {

    private final ChatService chatService;
    private final SimpMessagingTemplate template;
    private final MemberService memberService;

    @MessageMapping("/enter/{chatRoomUUID}")
    public void enter(@DestinationVariable("chatRoomUUID") String chatRoomUUID, @RequestBody ChatRoomEnterDto dto) {
        ChatRoom chatRoom = chatService.getChatRoomByUUID(UUID.fromString(chatRoomUUID));
        Member member = memberService.getMemberByUUID(dto.getMemberUUID());

        template.convertAndSend("/sub/chat/room/" + chatRoom.getUuid().toString(), member.getUsername() + " connected!");

        System.out.println(member.getUsername() + " connected!");
    }

    @MessageMapping("/send/{chatRoomUUID}")
    private void send(@DestinationVariable("chatRoomUUID") String chatRoomUUID, @RequestBody ChatDto dto) {
        ChatMessage message = chatService.createMessage(dto.getMessage(), UUID.fromString(dto.getSenderUUID()), UUID.fromString(chatRoomUUID));
        template.convertAndSend("/sub/chat/room/" + message.getChatRoom().getUuid().toString(), message.getMessage() + "/" + message.getCreatedAt());

        System.out.println(dto.getMessage());
    }
}

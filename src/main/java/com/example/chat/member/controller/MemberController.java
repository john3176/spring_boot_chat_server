package com.example.chat.member.controller;

import com.example.chat.chatting.ChatService;
import com.example.chat.chatting.controller.dto.GetChatRoomsResponseDto;
import com.example.chat.chatting.domain.ChatRoom;
import com.example.chat.member.MemberService;
import com.example.chat.member.controller.dto.MemberRegisterRequestDto;
import com.example.chat.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final ChatService chatService;

    @PostMapping("/create")
    public ResponseEntity<Member> registerMember(@RequestBody MemberRegisterRequestDto request){
        Member member = memberService.createMember(request.getUsername(), request.getName());
        return ResponseEntity.ok(member);
    }

    @GetMapping("/{memberUUID}")
    public ResponseEntity<Member> getMemberByUUID(@PathVariable("memberUUID") String memberUUID) {
        Member member = memberService.getMemberByUUID(memberUUID);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/{memberUUID}/chatrooms")
    public ResponseEntity<GetChatRoomsResponseDto> getChatRoomsByMemberUUID(@PathVariable("memberUUID") String memberUUID){
        List<ChatRoom> chatRooms = chatService.getChatRoomByMemberUUID(UUID.fromString(memberUUID));
        GetChatRoomsResponseDto response = GetChatRoomsResponseDto.toDto(chatRooms);
        return ResponseEntity.ok(response);
    }

}

package com.example.chat.chatting.controller.dto;

import com.example.chat.chatting.domain.ChatRoom;
import com.example.chat.chatting.domain.MemberChatRoomMapping;
import com.example.chat.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetChatRoomResponseDto {
    private UUID chatRoomUUID;
    private List<ChatRoomMemberDto> members;

    public static GetChatRoomResponseDto toDto(ChatRoom chatRoom){
        List<ChatRoomMemberDto> chatRoomMembers = new ArrayList<ChatRoomMemberDto>();

        for(MemberChatRoomMapping memberChatRoomMapping : chatRoom.getMembers()){
            Member member = memberChatRoomMapping.getMember();

            chatRoomMembers.add(ChatRoomMemberDto.builder()
                    .memberUUID(member.getUuid())
                    .memberUserName(member.getUsername())
                    .memberName(member.getName())
                    .build());
        }

        return GetChatRoomResponseDto.builder()
                .chatRoomUUID(chatRoom.getUuid())
                .members(chatRoomMembers)
                .build();
    }
}

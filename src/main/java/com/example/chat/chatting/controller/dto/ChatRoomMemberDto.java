package com.example.chat.chatting.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomMemberDto {
    private UUID memberUUID;
    private String memberUserName;
    private String memberName;
}

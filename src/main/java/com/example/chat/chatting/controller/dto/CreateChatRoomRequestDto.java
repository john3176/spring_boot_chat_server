package com.example.chat.chatting.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateChatRoomRequestDto {
    private String originMemberUUID;
    private String destinationMemberUUID;
}

package com.example.chat.chatting.controller.dto;

import com.example.chat.chatting.domain.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetChatRoomsResponseDto {

    private List<GetChatRoomResponseDto> chatRooms;

    public static GetChatRoomsResponseDto toDto(List<ChatRoom> chatRooms){
        List<GetChatRoomResponseDto> getChatRoomResponseDtos = new ArrayList<GetChatRoomResponseDto>();
        for(ChatRoom chatRoom : chatRooms){
            GetChatRoomResponseDto getChatRoomresponseDto = GetChatRoomResponseDto.toDto(chatRoom);
            getChatRoomResponseDtos.add(getChatRoomresponseDto);
        }

        return GetChatRoomsResponseDto.builder()
                .chatRooms(getChatRoomResponseDtos)
                .build();
    }
}


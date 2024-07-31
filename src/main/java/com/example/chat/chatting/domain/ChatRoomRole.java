package com.example.chat.chatting.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ChatRoomRole {
    MemberOne("member_one"),
    MemberTwo("member_two");

    private String name;
}

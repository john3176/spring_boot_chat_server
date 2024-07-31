package com.example.chat.chatting.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Builder(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroom_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "chatroom_uuid", nullable = false, unique = true)
    private UUID uuid;

    @OneToMany(mappedBy = "chatRoom")
    private List<MemberChatRoomMapping> members = new ArrayList<MemberChatRoomMapping>();

    public static ChatRoom create() {
        return ChatRoom.builder()
                .uuid(UUID.randomUUID())
                .build();
    }
}

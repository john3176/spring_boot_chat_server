package com.example.chat.chatting.domain;

import com.example.chat.BaseEntity;
import com.example.chat.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_message_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "chat_message_uuid", nullable = false, unique = true)
    private UUID uuid;

    @Column(nullable = false)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sending_member_id")
    private Member sender;

    public static ChatMessage create(String message,ChatRoom chatRoom, Member sender){
        return ChatMessage.builder()
                .message(message)
                .chatRoom(chatRoom)
                .sender(sender)
                .uuid(UUID.randomUUID())
                .build();
    }
}

package com.example.chat.chatting.domain;

import com.example.chat.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Builder(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberChatRoomMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_chatroom_mapping_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "member_chatroom_mapping_uuid", nullable = false, unique = true)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static MemberChatRoomMapping create(Member member, ChatRoom chatRoom){
        return MemberChatRoomMapping.builder()
                .member(member)
                .chatRoom(chatRoom)
                .uuid(UUID.randomUUID())
                .build();
    }

}

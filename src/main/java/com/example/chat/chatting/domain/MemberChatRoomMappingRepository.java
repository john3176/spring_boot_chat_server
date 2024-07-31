package com.example.chat.chatting.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberChatRoomMappingRepository extends JpaRepository<MemberChatRoomMapping, Long> {
    List<MemberChatRoomMapping> findAllByMemberId(Long memberId);
    List<MemberChatRoomMapping> findAllByChatRoomId(Long chatRoomId);

}

package com.example.chat.chatting.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    Optional<ChatMessage> getChatMessageById(Long id);
    Optional<ChatMessage> getChatMessageByUuid(UUID uuid);
    List<ChatMessage> getAllChatMessageByChatRoomId(Long id);


}

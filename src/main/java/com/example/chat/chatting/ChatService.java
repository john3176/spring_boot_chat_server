package com.example.chat.chatting;

import com.example.chat.chatting.domain.*;
import com.example.chat.member.domain.Member;
import com.example.chat.member.domain.MemberRepository;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;
    private final MemberChatRoomMappingRepository memberChatRoomMappingRepository;
    private final MemberRepository memberRepository;
    private final ChatMessageRepository chatMessageRepository;

    @Transactional
    public ChatRoom createChatRoom(UUID originMemberUUID, UUID destMemberUUID) {
        Member originMember = memberRepository.findByUuid(originMemberUUID)
                .orElseThrow(() -> new NoSuchElementException());
        Member destMember = memberRepository.findByUuid(destMemberUUID)
                .orElseThrow(() -> new NoSuchElementException());
        List<MemberChatRoomMapping> memberChatRoomMappings = new ArrayList<MemberChatRoomMapping>();

        List<MemberChatRoomMapping> originMemberChatRooms = memberChatRoomMappingRepository.findAllByMemberId(originMember.getId());

        for(MemberChatRoomMapping memberChatRoomMapping : originMemberChatRooms){
            List<MemberChatRoomMapping> chatRoomMembers = memberChatRoomMapping.getChatRoom().getMembers();
            chatRoomMembers.forEach((chatRoomMember) -> {
                if(chatRoomMember.getMember().getUuid().equals(destMemberUUID)){
                    throw new DuplicateRequestException();
                }
            });
        }

        ChatRoom chatRoom = ChatRoom.create();
        chatRoomRepository.save(chatRoom);

        memberChatRoomMappings.add(MemberChatRoomMapping.create(originMember, chatRoom));
        memberChatRoomMappings.add(MemberChatRoomMapping.create(destMember, chatRoom));
        memberChatRoomMappingRepository.saveAll(memberChatRoomMappings);

        return chatRoomRepository.findById(chatRoom.getId())
                .orElseThrow(() -> new NoSuchElementException());
    }

    @Transactional(readOnly = true)
    public ChatRoom getChatRoomByUUID(UUID chatRoomUUID){
        return chatRoomRepository.findByUuid(chatRoomUUID)
                .orElseThrow(() -> new NoSuchElementException());
    }

    @Transactional(readOnly = true)
    public List<ChatRoom> getChatRoomByMemberUUID(UUID memberUUID){
        Member member = memberRepository.findByUuid(memberUUID)
                .orElseThrow(() -> new NoSuchElementException());

        List<ChatRoom> chatRooms = new ArrayList<ChatRoom>();

        memberChatRoomMappingRepository.findAllByMemberId(member.getId()).forEach((memberChatRoomMapping -> {
            chatRooms.add(memberChatRoomMapping.getChatRoom());
        }));

        return chatRooms;
    }

    @Transactional
    public ChatMessage createMessage(String message, UUID senderUUID, UUID chatRoomUUID){
        Member sender = memberRepository.findByUuid(senderUUID)
                .orElseThrow(() -> new NoSuchElementException());

        ChatRoom chatRoom = chatRoomRepository.findByUuid(chatRoomUUID)
                .orElseThrow(() -> new NoSuchElementException());

        ChatMessage chatMessage = ChatMessage.create(message, chatRoom, sender);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }
}

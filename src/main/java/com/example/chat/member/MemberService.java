package com.example.chat.member;

import com.example.chat.member.domain.Member;
import com.example.chat.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member createMember(String username, String name){
        Member member = Member.create(username, name);
        memberRepository.save(member);
        return member;
    }

    @Transactional(readOnly = true)
    public Member getMemberByUUID(String memberUUID){
        Member member = memberRepository.findByUuid(UUID.fromString(memberUUID))
                .orElseThrow(() -> new NoSuchElementException());
        return member;
    }
}

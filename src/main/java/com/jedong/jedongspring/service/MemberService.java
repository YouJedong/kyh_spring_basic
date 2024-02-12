package com.jedong.jedongspring.service;

import com.jedong.jedongspring.domain.Member;
import com.jedong.jedongspring.repository.MemberRepository;
import com.jedong.jedongspring.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {

        // 이름 중복 체크
        /*
        Optional<Member> result = memoryMemberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */
        /*위의 코드를 이렇게 줄여서 사용함
        memoryMemberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원");
                        });
         */

        // 위의 코드를 메서드화 해서 사용 control + t
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 상세 조회
     * */
    public Optional<Member> findOne(Long id) {
        return memberRepository.findById(id);
    }



    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원");
                });
    }
}

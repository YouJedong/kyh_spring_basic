package com.jedong.jedongspring.service;

import com.jedong.jedongspring.domain.Member;
import com.jedong.jedongspring.repository.MemberRepository;
import com.jedong.jedongspring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.array;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;

    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    void beforeEach() {
        this.memoryMemberRepository = new MemoryMemberRepository();
        this.memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    void afterEach() {
        memoryMemberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("jedong");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    void 중복_회원_예외() {
        // given
        Member member = new Member();
        member.setName("jedong");

        Member member2 = new Member();
        member2.setName("jedong");

        // when
        memberService.join(member);

        /*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");
        }
        */
        // try catch를 쓰지않고 테스트
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");

        // then

    }

    @Test
    void 회원목록조회() {
    }

    @Test
    void 회원상세조회() {
    }
}
package com.jedong.jedongspring.repository;

import com.jedong.jedongspring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {

        Member member = new Member();
        member.setName("jedong");

        repository.save(member);

        Member result = repository.findById(member.getUserId()).get();
        //Assertions.assertEquals(member, null); // org.assertj.core.api.Assertions 사용법
        assertThat(member).isEqualTo(result); // org.assertj.core.api.Assertions 사용법(이걸 많이씀)

    }

    @Test
    public void findByName() {
        Member member = new Member();
        member.setName("jedong1");
        repository.save(member);

        Member member2 = new Member();
        member2.setName("jedong2");
        repository.save(member2);

        Member result = repository.findByName("jedong1").get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findAll() {
        Member member = new Member();
        member.setName("jedong1");
        repository.save(member);

        Member member2 = new Member();
        member2.setName("jedong2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}

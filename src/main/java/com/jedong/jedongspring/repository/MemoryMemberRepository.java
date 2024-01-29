package com.jedong.jedongspring.repository;

import com.jedong.jedongspring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setUserId(++sequence);
        store.put(member.getUserId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long userId) {
        return Optional.ofNullable(store.get(userId));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}

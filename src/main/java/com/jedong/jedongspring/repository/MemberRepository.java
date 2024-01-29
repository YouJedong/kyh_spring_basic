package com.jedong.jedongspring.repository;


import com.jedong.jedongspring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long userId);
    Optional<Member> findByName(String name);
    List<Member> findAll();

}

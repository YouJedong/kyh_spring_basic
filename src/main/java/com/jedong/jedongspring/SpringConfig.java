package com.jedong.jedongspring;

import com.jedong.jedongspring.repository.*;
import com.jedong.jedongspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
////        return  new JdbcTemplateMemberRepository(dataSource);
////        return new JpaMemberRepository(entityManager);
//    }
}

package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberServiceInMemoryTest {

    @Autowired
    MemberRepository memberRepository;

    static MemberService memberService;

    static int mem1ID, mem2ID;

    @BeforeAll
    static void setUp(@Autowired MemberRepository memberRepository) {
        memberRepository.deleteAll();

        memberService = new MemberService(memberRepository);

        mem1ID= memberRepository.save(new Member("m", "m@d.kl"))
    }

    @Test
    void getMemebers() {
    }

    @Test
    void getMember() {
    }

    @Test
    void addMember() {
    }

    @Test
    void editMember() {
    }

    @Test
    void deleteMember() {
    }
}
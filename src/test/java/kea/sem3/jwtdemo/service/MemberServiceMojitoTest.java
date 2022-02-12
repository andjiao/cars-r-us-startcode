package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceMojitoTest {

    @Mock
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @BeforeEach
    void setUp() {
        memberService = new MemberService(memberRepository);
    }

    @Test
    void getMemebers() {
        Mockito.when(memberRepository.findAll()).thenReturn(List.of(
                //new Member((body,"ella@k.dk","test123","alfa","jens","kisser","juletræ",30,true,"øl"))
        ));
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
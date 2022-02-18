package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.MemberResponse;
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
import java.util.Optional;

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
    //Læg mærke til ay her er det i flertal, altså flere memberS
    void getMemebers() {
        Mockito.when(memberRepository.findAll()).thenReturn(List.of(
                new Member("hej","ella@k.dk","test123","alfa","jens","kisser","juletræ",30,true,"øl"),
                new Member("Coolio","mia@k.dk","test123","omega","åse","yver","zebra",70,true,"æble")
        ));
        List<MemberResponse> members = memberService.getAllMembers();
        assertEquals(2,members.size());
    }

    @Test
    //Læg mærke til at her er det i ental, EN member
    void getMember() throws Exception {
        Member member = new Member("radius","rella@k.dk","test123","ralfa","rjens","rkisser","rjuletræ",50,true,"røl");
        member.setUsername("roll");
        Mockito.when(memberRepository.findById("roll")).thenReturn(Optional.of(member));
        MemberResponse memRes = memberService.getMemberByUserName("roll");

        assertEquals("roll", memRes.getUsername());

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
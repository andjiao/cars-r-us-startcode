package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.MemberResponse;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberServiceInMemoryTest {

    @Autowired
    MemberRepository memberRepository;

    static MemberService memberService;

    static String mem1ID, mem2ID;

    @BeforeAll
    static void setUp(@Autowired MemberRepository memberRepository) {
        memberRepository.deleteAll();

        memberService = new MemberService(memberRepository);

        mem1ID= memberRepository.save(new Member("hej","mail@k.dk","test123","kurt","niels","3","3",1,true,"23")).getUsername();
        mem2ID= memberRepository.save(new Member("nisse","ella@k.dk","test123","alfa","jens","kisser","juletræ",30,true,"øl")).getUsername();



    }

    //@Test
    void getMemebers() {
        List<MemberResponse> memberResponses = memberService.getAllMembers();
        /*Der forventes to, da der i metoden setUp er blevet instansieret 2 memberobjekter*/

        assertEquals(2,memberResponses.size());
        assertInstanceOf(MemberResponse.class,memberResponses.get(1));
        assertThat(memberResponses, containsInAnyOrder(hasProperty("mail", is("ella@k.dk")), hasProperty("mail", is("Up"))));
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
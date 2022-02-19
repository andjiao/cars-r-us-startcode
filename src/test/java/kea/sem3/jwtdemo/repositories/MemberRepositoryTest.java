package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Member;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    static String mem1,mem2;

    @Autowired
    MemberRepository memberRepository;
    @BeforeAll
    static void setUp(@Autowired MemberRepository memberRepository) {
        /*deleteAll bruges for at sikre at der ikke er flere instansieret objekter, da der før denne pludslige var 45 objekter,
        selvom vi i denne metode kun instasiere to objekter*/
        memberRepository.deleteAll();

        //Første måde at instansiere et objekt
        Member member1 = memberRepository.save(new Member("niels","janus@dk.dk","test123", "kurt","niels","3","3",1,true,"23"));
        mem1=member1.getUsername();

        //Anden måde at instansiere et objekt
        mem2 = memberRepository.save(new Member("janus","niels@dk.dk","test123", "kurt","niels","3","3",1,true,"23")).getUsername();


    }

    @Test
    public void testCount(){
        assertEquals(2, memberRepository.count());

    }
}
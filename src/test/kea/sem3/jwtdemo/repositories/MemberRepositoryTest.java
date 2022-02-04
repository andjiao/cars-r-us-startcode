package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @BeforeEach
    void setUp() {
        memberRepository.save(new Member("niels","mail@k.dk","test123", "kurt","niels","3","3",1,true,"23"));
    }

    @Test
    public void testCount(){
        assertEquals(2, memberRepository.count());

    }
}
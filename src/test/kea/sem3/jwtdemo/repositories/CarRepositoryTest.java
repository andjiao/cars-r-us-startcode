package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {

    static int carId1,carId2;

        @Autowired
        CarRepository carRepository;

        MemberRepository memberRepository;

        @BeforeEach
        void setUp() {

            carId1= carRepository.save(new Car(1, "hus", "villa", "payday")).getId();
            carId2 =carRepository.save(new Car(2, "n", "jens", "day")).getId();
        }

        @Test
        public void testCount() {

            assertEquals(2, carRepository.count());
        }
    }
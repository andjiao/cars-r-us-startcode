package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Car;
import org.junit.jupiter.api.BeforeAll;
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

       /* @BeforeEach
        void setUp() {

            carId1= carRepository.save(new Car(1, "hus", "villa", "payday")).getId();
            carId2 =carRepository.save(new Car(2, "n", "jens", "day")).getId();
        }*/

    @BeforeAll
    static void setUp(@Autowired CarRepository carRepository){
        carRepository.deleteAll();

        Car c1 = carRepository.save(new Car("anne","æble",90.00,90.00));
        carId1=c1.getId();

        carId2= carRepository.save(new Car("yoghuret","påske",00.00,09.00)).getId();
    }

        @Test
        public void testCount() {

            assertEquals(2, carRepository.count());
        }

        @Test
    public void testAddCar(){
        Car carNew = carRepository.save(new Car("kalle","spiser",90.00,80.00));
        assertEquals(4, carRepository.count());
        assertNotEquals(0,carNew.getId());
        }

       /* @Test
    public void testGetById(){
        Car carFound = carRepository.findById(carId1).orElse(null);
        assertEquals("påske",carFound.getModel());
        }*/


    }
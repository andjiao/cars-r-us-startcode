package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.CarResponse;
import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.repositories.CarRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

/*Den laver kun de ting, der skal til for at kunne teste. Lavede en transtionel test (og kan rulles tilbage).
Det er også denne der kobler til h2-databasen, altså en midlertidig DB. */
@DataJpaTest
class CarServiceInMemoryTest
{

    @Autowired
    CarRepository carRepository;

    CarService carService;

    static int car1Id, car2Id;
/* @BeforeEach: Denne annotation, sørger for at data rolles tilabge*/
   @BeforeAll
    static void setup(@Autowired CarRepository carRepository ){
       /*Hvis ting ikke virker gør sådan: carRepository.deleteAll(); */
        car1Id = carRepository.save(new Car("Volvo", "C40", 560,10)).getId();
        car2Id = carRepository.save(new Car("WW", "Up", 300,10)).getId();
    }

    @BeforeEach
    void setService(){
        carService = new CarService(carRepository);
    }

    @Test
    void getCars() {
       /*Forventer ikke at få en liste af cars, men en liste af carRepsonse*/


        List<CarResponse> carResponses = carService.getCars();
        assertEquals(2,carResponses.size());
        assertInstanceOf(CarResponse.class,carResponses.get(0));

        /*Med database, har man ikke altid kontrol over, hvilken rækkefølge man får data tilbage i, hvilket er derfor
        containInAnyOrder (her siger vi, at vi er ligeglad med returværdiernes rækkefølge),
        for at kunne styre rækkefølgen af returværdierne. Man kunne fx. bruge orderBy() */
        assertThat(carResponses, containsInAnyOrder(hasProperty("model", is("C40")), hasProperty("model", is("Up"))));
    }

    @Test
    void getCar() {
    }

    @Test
    void addCar() {
    }

    @Test
    void deleteCar() {
    }
}


package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.CarRequest;
import kea.sem3.jwtdemo.dto.CarResponse;
import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.repositories.CarRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

/*Den laver kun de ting, der skal til for at kunne teste. Lavede en transtionel test (og kan rulles tilbage),
Det er også denne der kobler til h2-databasen, altså en midlertidig DB. */
@DataJpaTest
class CarServiceInMemoryTest
{

    @Autowired
    CarRepository carRepository;

    static CarService carService;

    static int car1Id, car2Id;
/* @BeforeEach: Denne annotation, sørger for at data rolles tilabge*/
   @BeforeAll
    static void setup(@Autowired CarRepository carRepository ){
       /*Hvis ting ikke virker gør sådan: carRepository.deleteAll(); */
       carRepository.deleteAll();
       carService = new CarService(carRepository);
        car1Id = carRepository.save(new Car("Volvo", "C40", 560,10)).getId();
        car2Id = carRepository.save(new Car("WW", "Up", 300,10)).getId();
    }

   /* @BeforeEach
    void setService(){
        carService = new CarService(carRepository);
    }*/

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


    /*@Test
    public void editCar() throws Exception {
        //New price and discount for the ford
        CarRequest carToEdit = new CarRequest("Ford", "Focus", 500, 20);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/cars/" + carFordId)
                        .contentType("application/json")
                        .accept("application/json")
                        .content(objectMapper.writeValueAsString(carToEdit)))
                .andExpect(status().isOk());
        Car editedCarFromDB = carRepository.findById(carFordId).orElse(null);
        assertEquals(500, editedCarFromDB.getPricePrDay());
        assertEquals(20, editedCarFromDB.getBestDiscount());
    }

    @Test
    void deleteCar() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/cars/" + carFordId))
                .andExpect(status().isOk());
        //Verify that we only have one car in the database
        assertEquals(1, carRepository.count());
    }*/


}


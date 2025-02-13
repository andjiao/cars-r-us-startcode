package kea.sem3.jwtdemo.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.util.TestUtils;
import kea.sem3.jwtdemo.dto.CarRequest;
import kea.sem3.jwtdemo.dto.CarResponse;
import kea.sem3.jwtdemo.entity.BaseUser;
import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.entity.Role;
import kea.sem3.jwtdemo.repositories.CarRepository;
import kea.sem3.jwtdemo.security.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static kea.sem3.jwtdemo.TestUtils.login;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;


/*Denne gør at vi kan få en fuld applications test
Når Lars siger byggeservere, referr han til githubAction
Denne annotation kører alle annotationer*/
@SpringBootTest

@AutoConfigureMockMvc
/*referer til @Profile("!test") (som ligger i MakeTestData), som altså fortæller at vi ønkser at få
data fra denne klass med ober i denne test */
@ActiveProfiles("test")
class CarControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CarRepository carRepository;

    @Autowired
    UserRepository userRepository;

    //Do something here

    @Autowired
    private ObjectMapper objectMapper;

    static int carFordId, carSuzukiId;

    @BeforeEach
    public void setup() throws Exception {
        carRepository.deleteAll();
        carFordId = carRepository.save(new Car("Ford", "Focus", 400, 10)).getId();
        carSuzukiId = carRepository.save(new Car("Suzuki", "Vitara", 500, 14)).getId();

        //Create user(s) needed to login to get a token for protected endpoints
        userRepository.deleteAll();;
        BaseUser admin = new BaseUser("xxx-user", "a@b.dk", "test12");
        admin.addRole(Role.ADMIN);
        userRepository.save(admin);
    }

    @Test
    void getCars() {
    }

    @Test
    public void testCarById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                /*De to linjer, er requestet*/
                        .get("/api/cars/" + carFordId)
                        .accept(MediaType.APPLICATION_JSON))

                /*.andDo(print()): denne metode bruges til at printe ens fejl, goa at bruge til at idenficere fejl,
                men hvsi alt kører er den overløfdig. Den printer returværierne, som er blebvet omformet til json*/
                /*Her sker repsonse*/
                .andExpect(status().isOk())
                /*jsonPath, parser respoonse som json*/
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(carFordId))

                /*Den kigger på det json den fik retur og tjek om der findes "Focus" i json*/
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").value("Focus"));
    }

    @Test
    public void testAllCars() throws Exception {

        String model = "$[?(@.model == '%s')]";
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/cars")
                        .accept(MediaType.APPLICATION_JSON))
                /*andDo(print) printer ens returværdier i json*/
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                //One way of testing this
                .andExpect(MockMvcResultMatchers.jsonPath(model, "Focus").exists())
                .andExpect(MockMvcResultMatchers.jsonPath(model, "Vitara").exists())
                //Another way
                .andExpect(MockMvcResultMatchers.content().string(containsString("Focus")))
                .andExpect(MockMvcResultMatchers.content().string(containsString("Vitara")));


    }

    @Test
        public void testAddCar() throws Exception {
            CarRequest newCar = new CarRequest("WW", "Polo", 200, 10);
            //Login and get the token
            String adminToken = TestUtils.login("xxx-user","test12",mockMvc);

        // System.out.println("XXXXXXX"+objectMapper.writeValueAsString(newCar));
            mockMvc.perform(MockMvcRequestBuilders.post("/api/cars")
                            .contentType("application/json")
                            .accept("application/json")
                            .header("Authorization","Bearer "+adminToken) // //Add token to the request

                            /*Det er denne linje, der laver java-objekter om til json,
                              ved at gøre brug af objectmapper(altså java-objekt) og omforme det til en string,
                              og i parantese tager vi altså java-objektet, newCar, til en String, altså en json*/

                            /*content er bodyen
                            hele princippet i api er at få request og få response*/
                            .content(objectMapper.writeValueAsString(newCar)))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
            //Verify that it actually ended in the database
        /*expected: altså hvor mange biler vi forventer at få retur*/
            assertEquals(3, carRepository.count());

        }

    // @Test
    public void editCar() throws Exception {}

    @Test
    void deleteCar() throws Exception {}
}




package kea.sem3.jwtdemo.configuration;

import kea.sem3.jwtdemo.entity.*;
import kea.sem3.jwtdemo.repositories.CarRepository;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import kea.sem3.jwtdemo.repositories.ReservationReporsitory;
import kea.sem3.jwtdemo.security.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Controller
/*Denne annotation gør, at netop denne test ikke skal køres*/
@Profile("!test")
public class MakeTestData implements ApplicationRunner {

    //Repo
    UserRepository userRepository;
    MemberRepository memberRepository;
    CarRepository carRepository;
    ReservationReporsitory reservationReporsitory;

    public MakeTestData(UserRepository userRepository, MemberRepository memberRepository, CarRepository carRepository, ReservationReporsitory reservationReporsitory) {
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
        this.carRepository = carRepository;
        this.reservationReporsitory= reservationReporsitory;
    }

    public void makePlainUsers(){
        //Baseuser
        BaseUser user = new BaseUser("user", "user@a.dk", "test12");
        user.addRole(Role.USER);
        BaseUser admin = new BaseUser("admin", "admin@a.dk", "test12");
        admin.addRole(Role.ADMIN);

        BaseUser both = new BaseUser("user_admin", "both@a.dk", "test12");
        both.addRole(Role.USER);
        both.addRole(Role.ADMIN);

        userRepository.save(user);
        userRepository.save(admin);
        userRepository.save(both);

        //Member
        Member m1 = new Member("xx","mail@k.dk","test123", "kurt","niels","3","3",1,true,"23");
        Member m2 = new Member("yy","alice@k.dk","test123", "kurt","niels","3","3",1,true,"23");
        m1.addRole(Role.USER);
        memberRepository.save(m1);
        memberRepository.save(m2);

        memberRepository.save(new Member("niels","mia@d.fk","test123","Mia","jeg","3","3",1,true,"23"));


        //Car
        Car carVolvo = new Car("Volvo","niels",90.00, 10.00 );
        carRepository.save(carVolvo);
        carRepository.save(new Car("Volvo", "V70", 500,10));
        carRepository.save(new Car("Volvo", "V49", 400,10));
        carRepository.save(new Car("Suzuki", "Vitara", 500,14));
        carRepository.save(new Car("Suzuki", "Vitara", 500,14));
        carRepository.save(new Car("Suzuki", "S-Cross", 500,14));



        //Create a Reservation
        Reservation res1 = new Reservation(LocalDate.of(2022,03,01),carVolvo,m1);
        reservationReporsitory.save(res1);
        Reservation res = reservationReporsitory.
                findReservationByReservedCar_IdAndRentalDate(carVolvo.getId(),(LocalDate.of(2022,3,1)));
        if(res == null) {
            Reservation res2 = new Reservation(LocalDate.of(2022, 03, 01), carVolvo, m2);
            reservationReporsitory.save(res2);

        } else{
            System.out.println("Car is reserved this day");
        }



        System.out.println("########################################################################################");
        System.out.println("########################################################################################");
        System.out.println("#################################### WARNING ! #########################################");
        System.out.println("## This part breaks a fundamental security rule -> NEVER ship code with default users ##");
        System.out.println("########################################################################################");
        System.out.println("########################  REMOVE BEFORE DEPLOYMENT  ####################################");
        System.out.println("########################################################################################");
        System.out.println("########################################################################################");
        System.out.println("Created TEST Users");

    }

    public void makeCar(){

        Car c1 = new Car("karen","niels",90.00,15.00);
        carRepository.save(c1);

        Car c2 = new Car("kurt","alice",09.00,18.00);
        carRepository.save(c2);

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        userRepository.deleteAll();
        carRepository.deleteAll();

        makePlainUsers();

        makeCar();

    }
}

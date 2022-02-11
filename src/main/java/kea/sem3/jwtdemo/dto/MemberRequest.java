package kea.sem3.jwtdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MemberRequest {
    //fra baseUser
    private String username;
    private String email;
    private String password;
    private boolean enabled;

    //fra memeber
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private int zip;
    private boolean approved;
    private String ranking;
}

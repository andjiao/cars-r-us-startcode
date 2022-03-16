package kea.sem3.jwtdemo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
/*include.non_null betyder at den kun tager værdier med som ikke er nul*/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberResponse {

    String username;
    String firstName;
    String lastName;
    String street;
    String city;
    int zip;
    boolean approved;
    String ranking;
    List<String> roleNames;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime created;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime updated;

    public MemberResponse(Member member, boolean includeAll){
        this.username= member.getUsername();
        this.firstName = member.getFirstName();
        this.lastName = member.getLastName();
        this.street = member.getStreet();
        this.city = member.getCity();
        this.zip = member.getZip();
        this.approved = member.isApproved();
        this.firstName = member.getRanking();

        if(includeAll){
            this.created = member.getCreated();
            this.updated = member.getLastEdited();
        }
    }
    public MemberResponse(String username, LocalDateTime created, List<Role> roleList) {
        this.created= created;
        this.roleNames=roleList.stream().map(role -> role.toString()).collect(Collectors.toList());
        this.username=username;

    }

    public static List<MemberResponse> getMemberEntities (List<Member> members){
        return members.stream().map(member -> new MemberResponse(member,false)).collect(Collectors.toList());
    }


}

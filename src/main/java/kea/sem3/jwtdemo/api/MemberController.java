package kea.sem3.jwtdemo.api;


import kea.sem3.jwtdemo.dto.CarRequest;
import kea.sem3.jwtdemo.dto.CarResponse;
import kea.sem3.jwtdemo.dto.MemberRequest;
import kea.sem3.jwtdemo.dto.MemberResponse;
import kea.sem3.jwtdemo.service.CarService;
import kea.sem3.jwtdemo.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("api/members")
public class MemberController {

    /*Bruger skal have lov til at kunne ændre på sine egne data, med undtagelse af id.
    * Admin skal have lov til at kunne ændre på det hele.
    * Bruger skal ikke have lov til at se informationer på andre end sig selv
    Admin skal gerne kunne se det hele*/

    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers(){
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping("/{username}")
    public MemberResponse getMembersFromUsername(@PathVariable String username){
        return(memberService.getMemberByUserName(username));
    }


    @PostMapping
    public MemberResponse addMember(@RequestBody MemberRequest body){
        return memberService.addMember(body);
    }



    @PutMapping("/{username}")
    public MemberResponse editMember(@RequestBody MemberRequest body, @PathVariable String username){return null;}

    @DeleteMapping("/{username}")
    public void deleteMember(@PathVariable String username){

        memberService.deleteMember(username);
    }

}
